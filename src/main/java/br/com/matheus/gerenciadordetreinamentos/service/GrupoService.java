package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Funcionario;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Grupo;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Treinamento;
import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.GrupoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.GrupoSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.GrupoUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.custom.GrupoMapperCustom;
import br.com.matheus.gerenciadordetreinamentos.repository.GrupoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    private final String notFoundText = "Grupo not found";

    @Autowired
    private GrupoRepository repository;

    @Autowired
    private GrupoMapperCustom mapperCustom;

    public List<GrupoDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        return listEntity.stream().map(Grupo::buildDTO).toList();
    }

    public GrupoDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.buildDTO();
    }

    public List<GrupoDTO> findByNome(String nome) {
        var listEntity = repository.findByNomeContainingAndAtivoTrue(nome);
        return listEntity.stream().map(Grupo::buildDTO).toList();
    }

    public List<FuncionarioDTO> funcionariosBy(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.getFuncionarios().stream().map(Funcionario::buildDTO).toList();
    }

    public List<TreinamentoDTO> treinamentosBy(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.getTreinamentos().stream().map(Treinamento::buildDTO).toList();
    }

    public GrupoDTO save(GrupoSaveDTO data) {
        return repository.save(mapperCustom.saveToEntity(data)).buildDTO();
    }

    public GrupoDTO update(GrupoUpdateDTO data) {
        if (repository.findByIdAndAtivoTrue(data.id()).isEmpty())
            throw new DataNotFoundException(notFoundText);
        return repository.save(mapperCustom.updateToEntity(data)).buildDTO();
    }

    public void delete(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        entity.setAtivo(false);
        repository.save(entity);
    }
}
