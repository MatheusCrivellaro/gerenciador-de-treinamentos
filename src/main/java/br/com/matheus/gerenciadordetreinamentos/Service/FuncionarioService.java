package br.com.matheus.gerenciadordetreinamentos.Service;

import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.FuncionarioMapper;
import br.com.matheus.gerenciadordetreinamentos.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public List<FuncionarioDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        var listDto = FuncionarioMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(FuncionarioDTO::addWithSelfRel);
        return listDto;
    }

    public FuncionarioDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Funcionario not found"));
        var dto = FuncionarioMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }

    public List<FuncionarioDTO> findByNome(String nome) {
        var listEntity = repository.findByNomeContainingAndAtivoTrue(nome);
        var listDto = FuncionarioMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(FuncionarioDTO::addWithSelfRel);
        return listDto;
    }

    public List<FuncionarioDTO> findByUsuario(String usuario) {
        var listEntity = repository.findByUsuarioContainingAndAtivoTrue(usuario);
        var listDto = FuncionarioMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(FuncionarioDTO::addWithSelfRel);
        return listDto;
    }

}
