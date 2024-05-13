package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.ProfessorDTO;
import br.com.matheus.gerenciadordetreinamentos.service.ProfessorService;
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

//    @PostMapping
//    public ResponseEntity<ProfessorDTO> save(@Valid ProfessorSaveDTO data) {
//        return ResponseEntity.ok(service.save(data));
//    }
//    @PutMapping
//    public ResponseEntity<ProfessorDTO> update(@Valid ProfessorUpdateDTO data) {
//        return ResponseEntity.ok(service.update(data));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
