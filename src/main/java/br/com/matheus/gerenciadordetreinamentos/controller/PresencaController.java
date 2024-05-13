package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.service.PresencaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public ResponseEntity<List<PresencaDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

//    @PostMapping
//    public ResponseEntity<PresencaDTO> save(@Valid PresencaSaveDTO data) {
//        return ResponseEntity.ok(service.save(data));
//    }

//    @PostMapping("/confirm/{code}/{id}")
//    public ResponseEntity<PresencaDTO> confirm(@PathVariable String code, @PathVariable Long id) {
//        return ResponseEntity.ok().body(service.confirmPresenca(code, id));
//    }
}
