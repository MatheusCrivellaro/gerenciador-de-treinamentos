package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.AdministradorDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.AdministradorSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.service.AdministradorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adm")
public class AdministradorController {

    @Autowired
    private AdministradorService service;

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AdministradorDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @PostMapping
    public ResponseEntity<AdministradorDTO> save(@Valid AdministradorSaveDTO data) {
        return ResponseEntity.ok(service.save(data));
    }

//    @PutMapping
//    public ResponseEntity<AdministradorDTO> update(@Valid AdministradorUpdateDTO data) {
//        return ResponseEntity.ok(service.update(data));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
