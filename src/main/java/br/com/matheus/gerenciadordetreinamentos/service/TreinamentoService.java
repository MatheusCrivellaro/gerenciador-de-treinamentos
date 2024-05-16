package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Grupo;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Presenca;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Treinamento;
import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.GrupoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.TreinamentoSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.TreinamentoUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.custom.TreinamentoMapperCustom;
import br.com.matheus.gerenciadordetreinamentos.repository.TreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreinamentoService {

    private final String notFoundText = "Treinamento not found";

    @Autowired
    private TreinamentoRepository repository;

    @Autowired
    private TreinamentoMapperCustom mapperCustom;

    public List<TreinamentoDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        return listEntity.stream().map(Treinamento::buildDTO).toList();
    }

    public TreinamentoDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.buildDTO();
    }

    public List<TreinamentoDTO> findByNome(String nome) {
        var listEntity = repository.findByNomeContainingAndAtivoTrue(nome);
        return listEntity.stream().map(Treinamento::buildDTO).toList();
    }

    public TreinamentoDTO findByCodigo(String codigo) {
        var entity = repository.findByCodigoAndAtivoTrue(codigo).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.buildDTO();
    }

    public List<GrupoDTO> gruposBy(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.getGrupos().stream().map(Grupo::buildDTO).toList();
    }

    public List<PresencaDTO> presencasBy(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.getPresencas().stream().map(Presenca::buildDTO).toList();
    }

    public List<FuncionarioDTO> funcionariosBy(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        var list = new ArrayList<FuncionarioDTO>();
        entity.getGrupos().forEach(grupo -> grupo.getFuncionarios().forEach(funcionario -> list.add(funcionario.buildDTO())));
        return list;
    }

    public TreinamentoDTO save(TreinamentoSaveDTO data) {
        return repository.save(mapperCustom.saveToEntity(data)).buildDTO();
    }

    public TreinamentoDTO update(TreinamentoUpdateDTO data) {
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
