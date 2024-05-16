package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.ConfirmPresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.service.PresencaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presenca")
public class PresencaController {

    @Autowired
    private PresencaService service;

    @GetMapping("/{id}")
    public ResponseEntity<PresencaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<FuncionarioDTO> funcionarioBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.funcionariosBy(id));
    }
    @GetMapping("/treinamentos/{id}")
    public ResponseEntity<TreinamentoDTO> treinamentoBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.treinamentoBy(id));
    }

    @GetMapping
    public ResponseEntity<List<PresencaDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping("/confirm")
    public ResponseEntity<PresencaDTO> confirm(@RequestBody @Valid ConfirmPresencaDTO data) {
        return ResponseEntity.ok().body(service.save(data));
    }
}
