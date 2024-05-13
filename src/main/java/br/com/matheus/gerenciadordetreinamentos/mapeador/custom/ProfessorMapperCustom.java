package br.com.matheus.gerenciadordetreinamentos.mapeador.custom;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Professor;
import br.com.matheus.gerenciadordetreinamentos.dto.save.ProfessorSaveDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ProfessorMapperCustom {

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
}
