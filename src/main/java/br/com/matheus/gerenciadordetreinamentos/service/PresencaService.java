package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Grupo;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Presenca;
import br.com.matheus.gerenciadordetreinamentos.dto.GrupoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.GrupoMapper;
import br.com.matheus.gerenciadordetreinamentos.mapeador.PresencaMapper;
import br.com.matheus.gerenciadordetreinamentos.repository.PresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresencaService {

    @Autowired
    private PresencaRepository repository;

    public List<PresencaDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        var listDto = PresencaMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(PresencaDTO::addWithSelfRel);
        return listDto;
    }

    public PresencaDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Grupo not found"));
        var dto = PresencaMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }

    private List<PresencaDTO> buildListDTO(List<Presenca> listEntity) {
        var listDto = PresencaMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(PresencaDTO::addWithSelfRel);
        return listDto;
    }

    private PresencaDTO buildDTO(Presenca entity) {
        var dto = PresencaMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }

}
