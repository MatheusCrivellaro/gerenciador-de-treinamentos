package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Presenca;
import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.FuncionarioNaoAutorizado;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.TreinamentoEncerradoException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.FuncionarioMapper;
import br.com.matheus.gerenciadordetreinamentos.mapeador.PresencaMapper;
import br.com.matheus.gerenciadordetreinamentos.mapeador.TreinamentoMapper;
import br.com.matheus.gerenciadordetreinamentos.repository.PresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PresencaService {

    @Autowired
    private PresencaRepository repository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private TreinamentoService treinamentoService;

    public List<PresencaDTO> findAll() {
        var listEntity = repository.findAll();
        return buildDTO(listEntity);
    }

    public PresencaDTO findById(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException("Grupo not found"));
        return buildDTO(entity);
    }

    public List<PresencaDTO> findByFuncionario(Long id) {
        return buildDTO(funcionarioService.findById(id).getPresencas());
    }

    public List<PresencaDTO> findByTreinamento(Long id) {
        return buildDTO(treinamentoService.findById(id).getPresencas());
    }

    public PresencaDTO confirmPresenca(String code, Long id) {
        var treinamento = treinamentoService.findByCodigo(code);
        var funcionario = funcionarioService.findById(id);
        if (!treinamento.getAtivo())
            throw new TreinamentoEncerradoException("O Treinamento já foi encerrado");
        if (treinamentoService.findByFuncionario(id).stream().noneMatch(t -> t.getKey().equals(treinamento.getKey())))
            throw new FuncionarioNaoAutorizado("O funcionario não faz parte do treinamento");

        var presenca = new Presenca(LocalDateTime.now(), true, FuncionarioMapper.INSTANCE.toEntity(funcionario), TreinamentoMapper.INSTANCE.toEntity(treinamento));
        return buildDTO(repository.save(presenca));
    }

    public void endTreinamento(Long id) {
        buildListaPresenca(id);
        var entity = TreinamentoMapper.INSTANCE.toEntity(treinamentoService.findById(id));
        entity.setAtivo(false);
        treinamentoService.save(TreinamentoMapper.INSTANCE.toDTO(entity));
    }

    public void buildListaPresenca(Long id) {
        var listFuncionariosTotais = funcionarioService.findByTreinamento(id);
        var listPresencas = findByTreinamento(id);
        var listaFuncionariosAusentes = listFuncionariosTotais.stream().filter(
                ft -> listPresencas.stream().noneMatch(
                        p -> ft.getKey().equals(p.getFuncionario().getId()))
        ).toList();
        listaFuncionariosAusentes.forEach(
                f -> {
                    var presencaEntity = new Presenca(LocalDateTime.now(), false, FuncionarioMapper.INSTANCE.toEntity(f), TreinamentoMapper.INSTANCE.toEntity(treinamentoService.findById(id)));
                    repository.save(presencaEntity);
                }
        );
    }

    public PresencaDTO save(PresencaDTO data) {
        var entity = PresencaMapper.INSTANCE.toEntity(data);
        return buildDTO(repository.save(entity));
    }

    public List<PresencaDTO> buildDTO(List<Presenca> listEntity) {
        var listDto = PresencaMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(PresencaDTO::addWithSelfRel);
        return listDto;
    }

    public PresencaDTO buildDTO(Presenca entity) {
        var dto = PresencaMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }

}
