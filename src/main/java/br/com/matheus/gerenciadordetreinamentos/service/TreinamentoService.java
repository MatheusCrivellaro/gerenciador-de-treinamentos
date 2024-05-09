package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Professor;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Treinamento;
import br.com.matheus.gerenciadordetreinamentos.dto.ProfessorDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.ProfessorMapper;
import br.com.matheus.gerenciadordetreinamentos.mapeador.TreinamentoMapper;
import br.com.matheus.gerenciadordetreinamentos.repository.TreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TreinamentoService {

    @Autowired
    private TreinamentoRepository repository;

    public List<TreinamentoDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        var listDto = TreinamentoMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(TreinamentoDTO::addWithSelfRel);
        return listDto;
    }

    public TreinamentoDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Grupo not found"));
        var dto = TreinamentoMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }

    private List<TreinamentoDTO> buildListDTO(List<Treinamento> listEntity) {
        var listDto = TreinamentoMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(TreinamentoDTO::addWithSelfRel);
        return listDto;
    }

    private TreinamentoDTO buildDTO(Treinamento entity) {
        var dto = TreinamentoMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }

}
