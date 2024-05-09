package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.Service.AdministradorService;
import br.com.matheus.gerenciadordetreinamentos.dto.AdministradorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adm")
public class AdministradorController {

    @Autowired
    private AdministradorService service;

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

}
