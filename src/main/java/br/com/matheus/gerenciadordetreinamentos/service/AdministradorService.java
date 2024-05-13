package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Administrador;
import br.com.matheus.gerenciadordetreinamentos.dto.AdministradorDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.AdministradorSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.AdministradorUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.custom.AdministradorMapperCustom;
import br.com.matheus.gerenciadordetreinamentos.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    private final String notFoundText = "Administrador not found";

    @Autowired
    private AdministradorRepository repository;

    @Autowired
    private AdministradorMapperCustom mapperCustom;

    public List<AdministradorDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        return listEntity.stream().map(Administrador::buildDTO).toList();
    }

    public AdministradorDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.buildDTO();
    }

    public List<AdministradorDTO> findByNome(String nome) {
        var listEntity = repository.findByNomeContainingAndAtivoTrue(nome);
        return listEntity.stream().map(Administrador::buildDTO).toList();
    }

    public List<AdministradorDTO> findByUsuario(String usuario) {
        var listEntity = repository.findByUsuarioContainingAndAtivoTrue(usuario);
        return listEntity.stream().map(Administrador::buildDTO).toList();
    }

    public AdministradorDTO save(AdministradorSaveDTO data) {
        var entity = repository.save(mapperCustom.saveToEntity(data));
        return entity.buildDTO();
    }

    public AdministradorDTO update(AdministradorUpdateDTO data) {
        var entity = mapperCustom.updateToEntity(data);
        entity = repository.save(entity);
        return entity.buildDTO();
    }

    public void delete(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        entity.setAtivo(false);
        repository.save(entity);
    }
}