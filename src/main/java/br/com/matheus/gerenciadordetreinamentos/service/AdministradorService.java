package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Administrador;
import br.com.matheus.gerenciadordetreinamentos.dto.AdministradorDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.AdministradorSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.AdministradorUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.AdministradorMapper;
import br.com.matheus.gerenciadordetreinamentos.repository.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository repository;

    public List<AdministradorDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        return buildDTO(listEntity);
    }

    public AdministradorDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Administrador not found"));
        return buildDTO(entity);
    }

    public List<AdministradorDTO> findByNome(String nome) {
        var listEntity = repository.findByNomeContainingAndAtivoTrue(nome);
        return buildDTO(listEntity);
    }

    public List<AdministradorDTO> findByUsuario(String usuario) {
        var listEntity = repository.findByUsuarioContainingAndAtivoTrue(usuario);
        return buildDTO(listEntity);
    }

    public AdministradorDTO save(AdministradorSaveDTO data) {
        var entity = new Administrador(data.nome(), data.usuario(), data.senha(), true);
        return buildDTO(repository.save(entity));
    }

    public AdministradorDTO update(AdministradorUpdateDTO data) {
        if (repository.findById(data.id()).isEmpty())
            throw new DataNotFoundException("Administrador not found");
        var entity = new Administrador(data.id(), data.nome(), data.usuario(), data.senha(), true);;
        return buildDTO(repository.save(entity));
    }

    public void delete(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Administrador not found"));
        entity.setAtivo(false);
        repository.save(entity);
    }

    public List<AdministradorDTO> buildDTO(List<Administrador> listEntity) {
        var listDto = AdministradorMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(AdministradorDTO::addWithSelfRel);
        return listDto;
    }

    public AdministradorDTO buildDTO(Administrador entity) {
        var dto = AdministradorMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }
}