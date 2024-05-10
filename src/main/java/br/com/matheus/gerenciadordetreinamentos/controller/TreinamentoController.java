package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.ProfessorDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.ProfessorSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.TreinamentoSaveDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<TreinamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<TreinamentoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<TreinamentoDTO> save(@Valid TreinamentoSaveDTO data) {
        return ResponseEntity.ok(service.save(data));
    }
}
