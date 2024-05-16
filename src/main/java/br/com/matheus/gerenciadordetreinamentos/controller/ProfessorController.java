package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.ProfessorDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.ProfessorSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.ProfessorUpdateDTO;
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

    @GetMapping("/nome")
    public ResponseEntity<List<ProfessorDTO>> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.findByNome(nome));
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<ProfessorDTO>> findByUsuario(@RequestParam String usuario) {
        return ResponseEntity.ok(service.findByUsuario(usuario));
    }

    @GetMapping("/email")
    public ResponseEntity<List<ProfessorDTO>> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @GetMapping("/telefone")
    public ResponseEntity<ProfessorDTO> findByTelefone(@RequestParam String telefone) {
        return ResponseEntity.ok(service.findByTelefone(telefone));
    }

    @GetMapping("/treinamentos/{id}")
    public ResponseEntity<List<TreinamentoDTO>> treinamentosBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.treinamentosBy(id));
    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> save(@Valid @RequestBody ProfessorSaveDTO data) {
        return ResponseEntity.ok(service.save(data));
    }

    @PutMapping
    public ResponseEntity<ProfessorDTO> update(@Valid @RequestBody ProfessorUpdateDTO data) {
        return ResponseEntity.ok(service.update(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
