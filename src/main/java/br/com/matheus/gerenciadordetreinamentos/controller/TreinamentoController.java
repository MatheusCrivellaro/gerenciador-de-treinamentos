package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.GrupoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.TreinamentoSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.TreinamentoUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.service.TreinamentoOperationService;
import br.com.matheus.gerenciadordetreinamentos.service.TreinamentoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/treinamento")
public class TreinamentoController {

    @Autowired
    private TreinamentoService service;

    @Autowired
    private TreinamentoOperationService treinamentoOperationService;

    @GetMapping("/{id}")
    public ResponseEntity<TreinamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TreinamentoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/nome")
    public ResponseEntity<List<TreinamentoDTO>> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.findByNome(nome));
    }

    @GetMapping("/grupos/{id}")
    public ResponseEntity<List<GrupoDTO>> gruposBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.gruposBy(id));
    }

    @GetMapping("/presencas/{id}")
    public ResponseEntity<List<PresencaDTO>> presencasBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.presencasBy(id));
    }

    @PostMapping("/end/{id}")
    public ResponseEntity<?> end(@PathVariable Long id) {
        treinamentoOperationService.endTreinamento(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<TreinamentoDTO> save(@Valid @RequestBody TreinamentoSaveDTO data) {
        return ResponseEntity.ok(service.save(data));
    }

    @PutMapping
    public ResponseEntity<TreinamentoDTO> update(@Valid @RequestBody TreinamentoUpdateDTO data) {
        return ResponseEntity.ok(service.update(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
