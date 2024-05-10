package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Grupo;
import br.com.matheus.gerenciadordetreinamentos.dto.GrupoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.GrupoSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.GrupoMapper;
import br.com.matheus.gerenciadordetreinamentos.repository.FuncionarioRepository;
import br.com.matheus.gerenciadordetreinamentos.repository.GrupoRepository;
import br.com.matheus.gerenciadordetreinamentos.repository.TreinamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupoService {

    @Autowired
    private GrupoRepository repository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private TreinamentoRepository treinamentoRepository;

    public List<GrupoDTO> findAll() {
        var listEntity = repository.findAllByAtivoTrue();
        return buildDTO(listEntity);
    }

    public GrupoDTO findById(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Grupo not found"));
        return buildDTO(entity);
    }

    public List<GrupoDTO> findByNome(String nome) {
        var listEntity = repository.findByNomeContainingAndAtivoTrue(nome);
        return buildDTO(listEntity);
    }

    public List<GrupoDTO> findByFuncionario(Long id) {
        return buildDTO(funcionarioRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Funcionario not found")).getGrupos());
    }

    public List<GrupoDTO> findByTreinamento(Long id) {
        return buildDTO(treinamentoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Treinamento not found")).getGrupos());
    }

    public GrupoDTO save(GrupoSaveDTO data) {
        var funcinarios = data.funcionarios()
                .stream().map(
                        funcionarioId -> funcionarioRepository.findByIdAndAtivoTrue(funcionarioId).orElseThrow(() -> new DataNotFoundException("Funcionario not found"))
                ).toList();
        var entity = new Grupo(data.nome(), data.descricao(), true, funcinarios);
        return buildDTO(repository.save(entity));
    }

    public GrupoDTO update(GrupoDTO data) {
        if(repository.findByIdAndAtivoTrue(data.getKey()).isEmpty())
            throw new DataNotFoundException("Grupo not found");
        var entity = GrupoMapper.INSTANCE.toEntity(data);
        return buildDTO(repository.save(entity));
    }

    public void delete(Long id) {
        var entity = repository.findByIdAndAtivoTrue(id).orElseThrow(() -> new DataNotFoundException("Grupo not found"));
        entity.setAtivo(false);
        repository.save(entity);
    }

    public List<GrupoDTO> buildDTO(List<Grupo> listEntity) {
        var listDto = GrupoMapper.INSTANCE.toDTOList(listEntity);
        listDto.forEach(GrupoDTO::addWithSelfRel);
        return listDto;
    }

    public GrupoDTO buildDTO(Grupo entity) {
        var dto = GrupoMapper.INSTANCE.toDTO(entity);
        dto.addWithSelfRel();
        return dto;
    }

}
