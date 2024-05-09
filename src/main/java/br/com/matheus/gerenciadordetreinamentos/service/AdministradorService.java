package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Administrador;
import br.com.matheus.gerenciadordetreinamentos.dto.AdministradorDTO;
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
        return buildListDTO(listEntity);
    }

    public AdministradorDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Administrador not found"));
        var dto = AdministradorMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }

    public List<AdministradorDTO> findByNome(String nome) {
        var listEntity = repository.findByNomeContainingAndAtivoTrue(nome);
        var listDto = AdministradorMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(AdministradorDTO::addWithSelfRel);
        return listDto;
    }

    public List<AdministradorDTO> findByUsuario(String usuario) {
        var listEntity = repository.findByUsuarioContainingAndAtivoTrue(usuario);
        var listDto = AdministradorMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(AdministradorDTO::addWithSelfRel);
        return listDto;
    }

    public AdministradorDTO save(AdministradorDTO data) {
        var entity = AdministradorMapper.INSTANCE.toEntity(data);
        var dto = AdministradorMapper.INSTANCE.toDTO(repository.save(entity));
        dto.addWithSelfRel();
        return dto;
    }

    public AdministradorDTO update(AdministradorDTO data) {
        if (repository.findById(data.getKey()).isEmpty())
            throw new DataNotFoundException("Administrador not found");
        var entity = AdministradorMapper.INSTANCE.toEntity(data);
        var dto = AdministradorMapper.INSTANCE.toDTO(repository.save(entity));
        dto.addWithSelfRel();
        return dto;
    }

    private List<AdministradorDTO> buildListDTO(List<Administrador> listEntity) {
        var listDto = AdministradorMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(AdministradorDTO::addWithSelfRel);
        return listDto;
    }

    private AdministradorDTO buildDTO(Administrador entity) {
        var dto = AdministradorMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }
}