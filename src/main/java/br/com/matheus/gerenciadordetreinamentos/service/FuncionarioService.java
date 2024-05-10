package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Funcionario;
import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.FuncionarioMapper;
import br.com.matheus.gerenciadordetreinamentos.repository.FuncionarioRepository;
import br.com.matheus.gerenciadordetreinamentos.repository.PresencaRepository;
import br.com.matheus.gerenciadordetreinamentos.repository.TreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private GrupoService grupoService;

    @Autowired
    private TreinamentoRepository trinamentoRepository;

    @Autowired
    private PresencaRepository presencaRepository;

    public List<FuncionarioDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        return buildDTO(listEntity);
    }

    public FuncionarioDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Funcionario not found"));
        return buildDTO(entity);
    }

    public List<FuncionarioDTO> findByNome(String nome) {
        var listEntity = repository.findByNomeContainingAndAtivoTrue(nome);
        return buildDTO(listEntity);
    }

    public List<FuncionarioDTO> findByUsuario(String usuario) {
        var listEntity = repository.findByUsuarioContainingAndAtivoTrue(usuario);
        return buildDTO(listEntity);
    }

    public FuncionarioDTO findByCpf(String cpf) {
        var entity = repository.findByCpfAndAtivoTrue(cpf).orElseThrow(() -> new DataNotFoundException("Funcionario not found"));
        return buildDTO(entity);
    }

    public List<FuncionarioDTO> findByEmail(String email) {
        var listEntity = repository.findByEmailContainingAndAtivoTrue(email);
        return buildDTO(listEntity);
    }

    public FuncionarioDTO findByTelefone(String telefone) {
        var entity = repository.findByTelefoneAndAtivoTrue(telefone).orElseThrow(() -> new DataNotFoundException("Funcionario not found"));
        return buildDTO(entity);
    }

    public List<FuncionarioDTO> findByGrupo(Long id) {
        return buildDTO(grupoService.findById(id).getFuncionarios());
    }

    public List<FuncionarioDTO> findByTreinamento(Long id) {
        List<Funcionario> list = new ArrayList<>();
        trinamentoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Treinamento not found")).getGrupos().forEach(
                grupo -> list.addAll(grupo.getFuncionarios())
        );
        return buildDTO(list);
    }

    public FuncionarioDTO findByPresenca(Long id) {
        return buildDTO(presencaRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Presença not found")).getFuncionario());
    }

    public FuncionarioDTO save(FuncionarioDTO data) {
        var entity = FuncionarioMapper.INSTANCE.toEntity(data);
        return buildDTO(repository.save(entity));
    }

    public FuncionarioDTO update(FuncionarioDTO data) {
        if (repository.findByIdAndAtivoTrue(data.getKey()).isEmpty())
            throw new DataNotFoundException("Funcionario not found");
        var entity = FuncionarioMapper.INSTANCE.toEntity(data);
        return buildDTO(repository.save(entity));
    }

    public void delete(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Funcionario not found"));
        entity.setAtivo(false);
        repository.save(entity);
    }

    public List<FuncionarioDTO> buildDTO(List<Funcionario> listEntity) {
        var listDto = FuncionarioMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(FuncionarioDTO::addWithSelfRel);
        return listDto;
    }

    public FuncionarioDTO buildDTO(Funcionario entity) {
        var dto = FuncionarioMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }

}
