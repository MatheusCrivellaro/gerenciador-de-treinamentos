package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Funcionario;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Grupo;
import br.com.matheus.gerenciadordetreinamentos.domain.model.Presenca;
import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.GrupoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.FuncionarioSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.FuncionarioUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.custom.FuncionarioMapperCustom;
import br.com.matheus.gerenciadordetreinamentos.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService {

    private final String notFoundText = "Funcionario not found";

    @Autowired
    private FuncionarioRepository repository;
    @Autowired
    private FuncionarioMapperCustom mapperCustom;

    public List<FuncionarioDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        return listEntity.stream().map(Funcionario::buildDTO).toList();
    }

    public FuncionarioDTO findById(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.buildDTO();
    }

    public List<FuncionarioDTO> findByNome(String nome) {
        var listEntity = repository.findByNomeContainingAndAtivoTrue(nome);
        return listEntity.stream().map(Funcionario::buildDTO).toList();
    }

    public List<FuncionarioDTO> findByUsuario(String usuario) {
        var listEntity = repository.findByUsuarioContainingAndAtivoTrue(usuario);
        return listEntity.stream().map(Funcionario::buildDTO).toList();
    }

    public FuncionarioDTO findByCpf(String cpf) {
        var entity = repository.findByCpfAndAtivoTrue(cpf).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.buildDTO();
    }

    public List<FuncionarioDTO> findByEmail(String email) {
        var listEntity = repository.findByEmailContainingAndAtivoTrue(email);
        return listEntity.stream().map(Funcionario::buildDTO).toList();
    }

    public FuncionarioDTO findByTelefone(String telefone) {
        var entity = repository.findByTelefoneAndAtivoTrue(telefone).orElseThrow(() -> new DataNotFoundException(notFoundText));
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
    public List<TreinamentoDTO> treinamentosBy(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        var list = new ArrayList<TreinamentoDTO>();
        entity.getGrupos().forEach(grupo -> grupo.getTreinamentos().forEach(treinamento -> list.add(treinamento.buildDTO())));
        return list;
    }

    public FuncionarioDTO save(FuncionarioSaveDTO data) {
        return repository.save(mapperCustom.saveToEntity(data)).buildDTO();
    }

    public FuncionarioDTO update(FuncionarioUpdateDTO data) {
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
