package br.com.matheus.gerenciadordetreinamentos.mapeador.custom;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Funcionario;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Grupo;
import br.com.matheus.gerenciadordetreinamentos.dto.save.GrupoSaveDTO;
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

    public Grupo saveToEntity(GrupoSaveDTO saveDTO) {
        var entity = new Grupo();
        entity.setNome(saveDTO.nome());
        entity.setDescricao(saveDTO.descricao());
        entity.setAtivo(true);

        entity.setFuncionarios(idsToFuncionarios(saveDTO.funcionarios()));
        return entity;
    }

    private List<Funcionario> idsToFuncionarios(List<Long> funcionarios) {
        return funcionarios.stream().map(
                id -> funcionarioRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Funcionario not found"))
        ).toList();
    }
}
