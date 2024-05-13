package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.FuncionarioSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.FuncionarioUpdateDTO;
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

//    @GetMapping("/grupo/{id}")
//    public ResponseEntity<List<FuncionarioDTO>> findByGrupo(@PathVariable Long id) {
//        return ResponseEntity.ok(service.findByGrupo(id));
//    }
//
//    @GetMapping("/treinamento/{id}")
//    public ResponseEntity<List<FuncionarioDTO>> findByTreinamento(@PathVariable Long id) {
//        return ResponseEntity.ok(service.findByTreinamento(id));
//    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> save(@Valid FuncionarioSaveDTO data) {
        return ResponseEntity.ok(service.save(data));
    }

    @PutMapping
    public ResponseEntity<FuncionarioDTO> update(@Valid FuncionarioUpdateDTO data) {
        return ResponseEntity.ok(service.update(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
