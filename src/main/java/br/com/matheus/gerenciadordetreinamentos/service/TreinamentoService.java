package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Treinamento;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.TreinamentoMapper;
import br.com.matheus.gerenciadordetreinamentos.repository.PresencaRepository;
import br.com.matheus.gerenciadordetreinamentos.repository.TreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreinamentoService {

    @Autowired
    private TreinamentoRepository repository;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private PresencaRepository presencaRepository;

    public List<TreinamentoDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        return buildDTO(listEntity);
    }

    public TreinamentoDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Treinamento not found"));
        return buildDTO(entity);
    }

    public List<TreinamentoDTO> findByNome(String nome) {
        var listEntity = repository.findByNomeContainingAndAtivoTrue(nome);
        return buildDTO(listEntity);
    }

    public TreinamentoDTO findByCodigo(String codigo) {
        var entity = repository.findByCodigoAndAtivoTrue(codigo).orElseThrow(() -> new DataNotFoundException("Treinamento not found"));
        return buildDTO(entity);
    }

    public List<TreinamentoDTO> findByFuncionario(Long id) {
        List<Treinamento> list = new ArrayList<>();
        funcionarioService.findById(id)
                .getGrupos().forEach(
                    grupo -> list.addAll(grupo.getTreinamentos())
                );
        return buildDTO(list);
    }

    public TreinamentoDTO findByPresenca(Long id) {
        return buildDTO(presencaRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Presença not found")).getTreinamento());
    }

    public List<TreinamentoDTO> findByGrupo(Long id) {
        return buildDTO(grupoService.findById(id).getTreinamentos());
    }

    public TreinamentoDTO save(TreinamentoDTO data) {
        var entity = TreinamentoMapper.INSTANCE.toEntity(data);
        return buildDTO(repository.save(entity));
    }

    public TreinamentoDTO update(TreinamentoDTO data) {
        if (repository.findByIdAndAtivoTrue(data.getKey()).isEmpty())
            throw new DataNotFoundException("Treinamento not found");
        var entity = TreinamentoMapper.INSTANCE.toEntity(data);
        return buildDTO(repository.save(entity));
    }

    public void delete(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Treinamento not found"));
        entity.setAtivo(false);
        repository.save(entity);
    }

    public List<TreinamentoDTO> buildDTO(List<Treinamento> listEntity) {
        var listDto = TreinamentoMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(TreinamentoDTO::addWithSelfRel);
        return listDto;
    }

    public TreinamentoDTO buildDTO(Treinamento entity) {
        var dto = TreinamentoMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }

}
