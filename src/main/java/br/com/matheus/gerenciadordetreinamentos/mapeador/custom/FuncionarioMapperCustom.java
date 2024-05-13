package br.com.matheus.gerenciadordetreinamentos.mapeador.custom;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Funcionario;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Grupo;
import br.com.matheus.gerenciadordetreinamentos.dto.save.FuncionarioSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.FuncionarioUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.repository.FuncionarioRepository;
import br.com.matheus.gerenciadordetreinamentos.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FuncionarioMapperCustom {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private FuncionarioRepository repository;

    public Funcionario saveToEntity(FuncionarioSaveDTO saveDTO) {
        var entity = new Funcionario();
        entity.setNome(saveDTO.nome());
        entity.setEmail(saveDTO.email());
        entity.setUsuario(saveDTO.usuario());
        entity.setSenha(saveDTO.senha());
        entity.setTelefone(saveDTO.telefone());
        entity.setCpf(saveDTO.cpf());
        entity.setGenero(saveDTO.genero());
        entity.setDataNascimento(saveDTO.dataNascimento());
        entity.setDataRegistro(LocalDateTime.now());
        entity.setAtivo(true);

        entity.setGrupos(idsForGrupos(saveDTO.grupos()));
        return entity;
    }

    public Funcionario updateToEntity(FuncionarioUpdateDTO updateDTO) {
        var entity = repository.findByIdAndAtivoTrue(updateDTO.id()).orElseThrow(() -> new DataNotFoundException("Funcionario not found"));
        var gruposUpdate = idsForGrupos(updateDTO.grupos());
        if(!updateDTO.nome().isBlank())
            entity.setNome(updateDTO.nome());
        if(!updateDTO.email().isBlank())
            entity.setEmail(updateDTO.email());
        if(!updateDTO.usuario().isBlank())
            entity.setUsuario(updateDTO.usuario());
        if(!updateDTO.senha().isBlank())
            entity.setSenha(updateDTO.senha());
        if(!updateDTO.telefone().isBlank())
            entity.setTelefone(updateDTO.telefone());
        if(!equalsGruposList(entity.getGrupos(), gruposUpdate))
            entity.setGrupos(gruposUpdate);
        return entity;
    }

    private List<Grupo> idsForGrupos(List<Long> grupos) {
        return grupos.stream().map(
                id -> grupoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Funcionario not found"))
        ).toList();
    }

    private Boolean equalsGruposList(List<Grupo> grupos1, List<Grupo> grupos2) {
        return grupos1.stream().allMatch(
                grupo1 -> grupos2.stream().anyMatch(
                        grupo2 -> grupo2.getId().equals(grupo1.getId())
                )
        );
    }
}
