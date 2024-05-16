package br.com.matheus.gerenciadordetreinamentos.mapeador.custom;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Professor;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Treinamento;
import br.com.matheus.gerenciadordetreinamentos.dto.save.ProfessorSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.ProfessorUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.repository.TreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfessorMapperCustom {

    @Autowired
    private TreinamentoRepository treinamentoRepository;

    public Professor saveToEntity(ProfessorSaveDTO saveDTO) {
        var entity = new Professor();
        entity.setNome(saveDTO.nome());
        entity.setUsuario(saveDTO.usuario());
        entity.setSenha(saveDTO.senha());
        entity.setEmail(saveDTO.email());
        entity.setTelefone(saveDTO.telefone());
        entity.setDataNascimento(saveDTO.dataNascimento());
        entity.setDataRegistro(LocalDateTime.now());
        entity.setAtivo(true);
        return entity;
    }

    public Professor updateToEntity(ProfessorUpdateDTO updateDTO) {
        var entity = new Professor();
        entity.setId(updateDTO.id());
        entity.setNome(updateDTO.nome());
        entity.setUsuario(updateDTO.usuario());
        entity.setSenha(updateDTO.senha());
        entity.setEmail(updateDTO.email());
        entity.setTelefone(updateDTO.telefone());
        entity.setAtivo(true);
        entity.setTreinamentos(idsToTreinamentos(updateDTO.treinamentos()));
        return entity;
    }

    private List<Treinamento> idsToTreinamentos(List<Long> treinamentos) {
        return treinamentos.stream().map(
                id -> treinamentoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Treinamentos not found"))
        ).toList();
    }
}
