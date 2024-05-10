package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.ProfessorDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.PresencaSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.ProfessorSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorController  {

    @Autowired
    private ProfessorService service;

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> save(@Valid ProfessorSaveDTO data) {
        return ResponseEntity.ok(service.save(data));
    }
}
