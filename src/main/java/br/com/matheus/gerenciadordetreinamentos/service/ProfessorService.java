package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Presenca;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Professor;
import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.ProfessorDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.FuncionarioMapper;
import br.com.matheus.gerenciadordetreinamentos.mapeador.PresencaMapper;
import br.com.matheus.gerenciadordetreinamentos.mapeador.ProfessorMapper;
import br.com.matheus.gerenciadordetreinamentos.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    public List<ProfessorDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        var listDto = ProfessorMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(ProfessorDTO::addWithSelfRel);
        return listDto;
    }

    public ProfessorDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Grupo not found"));
        var dto = ProfessorMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }

    public List<ProfessorDTO> findByNome(String nome) {
        var listEntity = repository.findByNomeContainingAndAtivoTrue(nome);
        var listDto = ProfessorMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(ProfessorDTO::addWithSelfRel);
        return listDto;
    }

    public List<ProfessorDTO> findByUsuario(String usuario) {
        var listEntity = repository.findByUsuarioContainingAndAtivoTrue(usuario);
        var listDto = ProfessorMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(ProfessorDTO::addWithSelfRel);
        return listDto;
    }

    public List<ProfessorDTO> findByEmail(String email) {
        var listEntity = repository.findByEmailContainingAndAtivoTrue(email);
        var listDto = ProfessorMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(ProfessorDTO::addWithSelfRel);
        return listDto;
    }

    private List<ProfessorDTO> buildListDTO(List<Professor> listEntity) {
        var listDto = ProfessorMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(ProfessorDTO::addWithSelfRel);
        return listDto;
    }

    private ProfessorDTO buildDTO(Professor entity) {
        var dto = ProfessorMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }
}
