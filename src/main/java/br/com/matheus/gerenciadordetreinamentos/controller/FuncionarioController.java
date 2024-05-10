package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.FuncionarioSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<FuncionarioDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> save(@Valid FuncionarioSaveDTO data) {
        return ResponseEntity.ok(service.save(data));
    }
}
