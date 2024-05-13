package br.com.matheus.gerenciadordetreinamentos.mapeador.custom;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Administrador;
import br.com.matheus.gerenciadordetreinamentos.dto.save.AdministradorSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.AdministradorUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdministradorMapperCustom {

    @Autowired
    private AdministradorRepository repository;

    public Administrador saveToEntity(AdministradorSaveDTO saveDTO) {
        var entity = new Administrador();
        entity.setNome(saveDTO.nome());
        entity.setUsuario(saveDTO.usuario());
        entity.setSenha(saveDTO.senha());
        entity.setAtivo(true);
        return entity;
    }

    public Administrador updateToEntity(AdministradorUpdateDTO updateDTO) {
        var entity = repository.findByIdAndAtivoTrue(updateDTO.id()).orElseThrow(() -> new DataNotFoundException("Administrador not found"));
        if(!updateDTO.nome().isBlank())
            entity.setNome(updateDTO.nome());
        if(!updateDTO.usuario().isBlank())
            entity.setUsuario(updateDTO.nome());
        if(!updateDTO.senha().isBlank())
            entity.setSenha(updateDTO.senha());
        return entity;
    }
}
