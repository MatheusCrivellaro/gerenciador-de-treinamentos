package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Funcionario;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Grupo;
import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.GrupoDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.FuncionarioMapper;
import br.com.matheus.gerenciadordetreinamentos.mapeador.GrupoMapper;
import br.com.matheus.gerenciadordetreinamentos.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository repository;

    public List<GrupoDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        var listDto = GrupoMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(GrupoDTO::addWithSelfRel);
        return listDto;
    }

    public GrupoDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Grupo not found"));
        var dto = GrupoMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }

    private List<GrupoDTO> buildListDTO(List<Grupo> listEntity) {
        var listDto = GrupoMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(GrupoDTO::addWithSelfRel);
        return listDto;
    }

    private GrupoDTO buildDTO(Grupo entity) {
        var dto = GrupoMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }

}
