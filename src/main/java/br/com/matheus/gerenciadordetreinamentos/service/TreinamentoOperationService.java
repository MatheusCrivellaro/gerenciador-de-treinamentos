package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Presenca;
import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.FuncionarioNaoAutorizado;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.TreinamentoEncerradoException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.mapStruct.FuncionarioMapper;
import br.com.matheus.gerenciadordetreinamentos.mapeador.mapStruct.TreinamentoMapper;
import br.com.matheus.gerenciadordetreinamentos.repository.PresencaRepository;
import br.com.matheus.gerenciadordetreinamentos.repository.TreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TreinamentoOperationService {

    @Autowired
    private TreinamentoService treinamentoService;

    @Autowired
    private FuncionarioService funcionarioService;

    @Autowired
    private PresencaRepository presencaRepository;

    @Autowired
    private TreinamentoRepository treinamentoRepository;

    public PresencaDTO confirmPresenca(String code, Long id) {
        var treinamento = treinamentoService.findByCodigo(code);
        var funcionario = funcionarioService.findById(id);
        if (!treinamento.getAtivo())
            throw new TreinamentoEncerradoException("O Treinamento já foi encerrado");
        if (funcionarioService.treinamentosBy(id).stream().noneMatch(t -> treinamento.getKey().equals(t.getKey())))
            throw new FuncionarioNaoAutorizado("O funcionario não faz parte do treinamento");
        var presenca = new Presenca(LocalDateTime.now(), true, FuncionarioMapper.INSTANCE.toEntity(funcionario), TreinamentoMapper.INSTANCE.toEntity(treinamento));
        return presencaRepository.save(presenca).buildDTO();
    }

    public void endTreinamento(Long id) {
        buildListaPresenca(id);
        var entity = TreinamentoMapper.INSTANCE.toEntity(treinamentoService.findById(id));
        entity.setAtivo(false);
        treinamentoRepository.save(entity);
    }

    public void buildListaPresenca(Long id) {
        var listFuncionariosTotais = treinamentoService.funcionariosBy(id);
        var listPresencas = treinamentoService.presencasBy(id);
        var listaFuncionariosAusentes = listFuncionariosTotais.stream().filter(
                ft -> listPresencas.stream().noneMatch(
                        p -> ft.getKey().equals(p.getFuncionario().getId()))
        ).toList();
        listaFuncionariosAusentes.forEach(
                f -> {
                    var presencaEntity = new Presenca(LocalDateTime.now(), false, FuncionarioMapper.INSTANCE.toEntity(f), TreinamentoMapper.INSTANCE.toEntity(treinamentoService.findById(id)));
                    presencaRepository.save(presencaEntity);
                }
        );
    }

}
