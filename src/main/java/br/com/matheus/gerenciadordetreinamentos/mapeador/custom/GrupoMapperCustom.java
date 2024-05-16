package br.com.matheus.gerenciadordetreinamentos.mapeador.custom;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Funcionario;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Grupo;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Treinamento;
import br.com.matheus.gerenciadordetreinamentos.dto.save.GrupoSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.GrupoUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.repository.FuncionarioRepository;
import br.com.matheus.gerenciadordetreinamentos.repository.TreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoMapperCustom {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private TreinamentoRepository treinamentoRepository;

    public Grupo saveToEntity(GrupoSaveDTO saveDTO) {
        var entity = new Grupo();
        entity.setNome(saveDTO.nome());
        entity.setDescricao(saveDTO.descricao());
        entity.setAtivo(true);

        entity.setFuncionarios(idsToFuncionarios(saveDTO.funcionarios()));
        return entity;
    }

    public Grupo updateToEntity(GrupoUpdateDTO updateDTO) {
        var entity = new Grupo();
        entity.setId(updateDTO.id());
        entity.setNome(updateDTO.nome());
        entity.setDescricao(updateDTO.descricao());
        entity.setAtivo(true);

        entity.setFuncionarios(idsToFuncionarios(updateDTO.funcionarios()));
        entity.setTreinamentos(idsToTreinamentos(updateDTO.treinamentos()));

        return entity;
    }

    private List<Funcionario> idsToFuncionarios(List<Long> funcionarios) {
        return funcionarios.stream().map(
                id -> funcionarioRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Funcionario not found"))
        ).toList();
    }

    private List<Treinamento> idsToTreinamentos(List<Long> treinamentos) {
        return treinamentos.stream().map(
                id -> treinamentoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Treinamentos not found"))
        ).toList();
    }
}
