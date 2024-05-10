package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Professor;
import br.com.matheus.gerenciadordetreinamentos.dto.ProfessorDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.ProfessorSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.ProfessorMapper;
import br.com.matheus.gerenciadordetreinamentos.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    @Autowired
    private TreinamentoService treinamentoService;

    public List<ProfessorDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        return buildDTO(listEntity);
    }

    public ProfessorDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Grupo not found"));
        return buildDTO(entity);
    }

    public List<ProfessorDTO> findByNome(String nome) {
        var listEntity = repository.findByNomeContainingAndAtivoTrue(nome);
        return buildDTO(listEntity);
    }

    public List<ProfessorDTO> findByUsuario(String usuario) {
        var listEntity = repository.findByUsuarioContainingAndAtivoTrue(usuario);
        return buildDTO(listEntity);
    }

    public List<ProfessorDTO> findByEmail(String email) {
        var listEntity = repository.findByEmailContainingAndAtivoTrue(email);
        return buildDTO(listEntity);
    }

    public ProfessorDTO findByTelefone(String telefone) {
        var entity = repository.findByTelefoneAndAtivoTrue(telefone).orElseThrow(() -> new DataNotFoundException("Telefone not found"));
        return buildDTO(entity);
    }

    public ProfessorDTO findByTreinamento(Long id) {
        return buildDTO(treinamentoService.findById(id).getProfessor());
    }

    public ProfessorDTO save(ProfessorSaveDTO data) {
        var entity = new Professor(data.nome(), data.usuario(), data.senha(), data.email(), data.telefone(), data.dataNascimento(), LocalDateTime.now(), true);
        return buildDTO(repository.save(entity));
    }

    public ProfessorDTO update(ProfessorDTO data) {
        if (repository.findByIdAndAtivoTrue(data.getKey()).isEmpty())
            throw new DataNotFoundException("Professor not found");
        var entity = ProfessorMapper.INSTANCE.toEntity(data);
        return buildDTO(repository.save(entity));
    }

    public void delete(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Grupo not found"));
        entity.setAtivo(false);
        repository.save(entity);
    }

    public List<ProfessorDTO> buildDTO(List<Professor> listEntity) {
        var listDto = ProfessorMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(ProfessorDTO::addWithSelfRel);
        return listDto;
    }

    public ProfessorDTO buildDTO(Professor entity) {
        var dto = ProfessorMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }
}
