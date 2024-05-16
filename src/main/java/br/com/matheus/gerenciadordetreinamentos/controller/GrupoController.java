package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.GrupoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.GrupoSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.GrupoUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.service.GrupoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

    @Autowired
    private GrupoService service;

    @GetMapping("/{id}")
    public ResponseEntity<GrupoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<GrupoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/nome")
    public ResponseEntity<List<GrupoDTO>> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.findByNome(nome));
    }

    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<List<FuncionarioDTO>> funcionariosBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.funcionariosBy(id));
    }

    @GetMapping("/treinamentos/{id}")
    public ResponseEntity<List<TreinamentoDTO>> treinamentosBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.treinamentosBy(id));
    }

    @PostMapping
    public ResponseEntity<GrupoDTO> save(@Valid @RequestBody GrupoSaveDTO data) {
        return ResponseEntity.ok(service.save(data));
    }

    @PutMapping
    public ResponseEntity<GrupoDTO> update(@Valid @RequestBody GrupoUpdateDTO data) {
        return ResponseEntity.ok(service.update(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
