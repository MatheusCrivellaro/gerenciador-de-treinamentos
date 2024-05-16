package br.com.matheus.gerenciadordetreinamentos.mapeador.custom;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Grupo;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Treinamento;
import br.com.matheus.gerenciadordetreinamentos.dto.save.TreinamentoSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.TreinamentoUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.repository.GrupoRepository;
import br.com.matheus.gerenciadordetreinamentos.repository.ProfessorRepository;
import br.com.matheus.gerenciadordetreinamentos.service.CodigoTreinamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TreinamentoMapperCustom {

    @Autowired
    private CodigoTreinamentoService codigoTreinamentoService;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    public Treinamento saveToEntity(TreinamentoSaveDTO saveDTO) {
        var entity = new Treinamento();
        entity.setNome(saveDTO.nome());
        entity.setDescricao(saveDTO.descricao());
        entity.setData(saveDTO.data());
        entity.setAbertura(saveDTO.abertura());
        entity.setEncerramento(saveDTO.encerramento());
        entity.setCodigo(codigoTreinamentoService.buildCodigo());
        entity.setAtivo(true);
        entity.setDataCadastro(LocalDateTime.now());

        entity.setProfessor(professorRepository.findByIdAndAtivoTrue(saveDTO.professor()).orElseThrow(() -> new DataNotFoundException("Professor not found")));
        entity.setGrupos(idsForGrupos(saveDTO.grupos()));

        return entity;
    }

    public Treinamento updateToEntity(TreinamentoUpdateDTO updateDTO) {
        var entity = new Treinamento();
        entity.setId(updateDTO.id());
        entity.setNome(updateDTO.nome());
        entity.setDescricao(updateDTO.descricao());
        entity.setData(updateDTO.data());
        entity.setAbertura(updateDTO.abertura());
        entity.setEncerramento(updateDTO.encerramento());

        entity.setGrupos(idsForGrupos(updateDTO.grupos()));
        entity.setProfessor(professorRepository.findByIdAndAtivoTrue(updateDTO.professor()).orElseThrow(() -> new DataNotFoundException("Professor not found")));

        return entity;
    }

    private List<Grupo> idsForGrupos(List<Long> grupos) {
        return grupos.stream().map(
                id -> grupoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Funcionario not found"))
        ).toList();
    }

}
