package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Professor;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Treinamento;
import br.com.matheus.gerenciadordetreinamentos.dto.ProfessorDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.ProfessorSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.ProfessorUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.custom.ProfessorMapperCustom;
import br.com.matheus.gerenciadordetreinamentos.repository.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final String notFoundText = "Professor not found";

    @Autowired
    private ProfessorRepository repository;

    @Autowired
    private TreinamentoService treinamentoService;

    @Autowired
    private ProfessorMapperCustom mapperCustom;

    public List<ProfessorDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        return listEntity.stream().map(Professor::buildDTO).toList();
    }

    public ProfessorDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.buildDTO();
    }

    public List<ProfessorDTO> findByNome(String nome) {
        var listEntity = repository.findByNomeContainingAndAtivoTrue(nome);
        return listEntity.stream().map(Professor::buildDTO).toList();
    }

    public List<ProfessorDTO> findByUsuario(String usuario) {
        var listEntity = repository.findByUsuarioContainingAndAtivoTrue(usuario);
        return listEntity.stream().map(Professor::buildDTO).toList();
    }

    public List<ProfessorDTO> findByEmail(String email) {
        var listEntity = repository.findByEmailContainingAndAtivoTrue(email);
        return listEntity.stream().map(Professor::buildDTO).toList();
    }

    public ProfessorDTO findByTelefone(String telefone) {
        var entity = repository.findByTelefoneAndAtivoTrue(telefone).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.buildDTO();
    }

    public List<TreinamentoDTO> treinamentosBy(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.getTreinamentos().stream().map(Treinamento::buildDTO).toList();
    }

    public ProfessorDTO save(ProfessorSaveDTO data) {
        return repository.save(mapperCustom.saveToEntity(data)).buildDTO();
    }

    public ProfessorDTO update(ProfessorUpdateDTO data) {
        if (repository.findByIdAndAtivoTrue(data.id()).isEmpty())
            throw new DataNotFoundException("Professor not found");
        return repository.save(mapperCustom.updateToEntity(data)).buildDTO();
    }

    public void delete(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        entity.getTreinamentos().forEach(
                treinamento -> treinamentoService.delete(treinamento.getId())
        );
        entity.setAtivo(false);
        repository.save(entity);
    }
}
