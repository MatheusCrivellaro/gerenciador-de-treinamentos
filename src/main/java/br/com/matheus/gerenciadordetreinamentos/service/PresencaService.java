package br.com.matheus.gerenciadordetreinamentos.service;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Presenca;
import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.ConfirmPresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.exceptions.expecific.DataNotFoundException;
import br.com.matheus.gerenciadordetreinamentos.mapeador.mapStruct.PresencaMapper;
import br.com.matheus.gerenciadordetreinamentos.repository.PresencaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresencaService {

    private final String notFoundText = "Presenca not found";

    @Autowired
    private PresencaRepository repository;

    @Autowired
    private TreinamentoOperationService treinamentoOperation;

    public List<PresencaDTO> findAll() {
        var listEntity = repository.findAll();
        return listEntity.stream().map(Presenca::buildDTO).toList();
    }

    public PresencaDTO findById(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.buildDTO();
    }

    public FuncionarioDTO funcionariosBy(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.getFuncionario().buildDTO();
    }

    public TreinamentoDTO treinamentoBy(Long id) {
        var entity = repository.findById(id).orElseThrow(() -> new DataNotFoundException(notFoundText));
        return entity.getTreinamento().buildDTO();
    }

    public PresencaDTO save(PresencaDTO data) {
        var entity = PresencaMapper.INSTANCE.toEntity(data);
        return repository.save(entity).buildDTO();
    }

    public PresencaDTO save(ConfirmPresencaDTO data) {
        return treinamentoOperation.confirmPresenca(data.code(), data.id());
    }
}
