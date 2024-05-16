package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.GrupoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.FuncionarioSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.FuncionarioUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.service.FuncionarioService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/funcionario")
@Tag(name = "Funcionario", description = "Endpoints para gerenciar Funcionarios")
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

    @GetMapping("/nome")
    public ResponseEntity<List<FuncionarioDTO>> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.findByNome(nome));
    }

    @GetMapping("/usuario")
    public ResponseEntity<List<FuncionarioDTO>> findByUsuario(@RequestParam String usuario) {
        return ResponseEntity.ok(service.findByUsuario(usuario));
    }

    @GetMapping("/cpf")
    public ResponseEntity<FuncionarioDTO> findByCpf(@RequestParam String cpf) {
        return ResponseEntity.ok(service.findByCpf(cpf));
    }

    @GetMapping("/email")
    public ResponseEntity<List<FuncionarioDTO>> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @GetMapping("/telefone")
    public ResponseEntity<FuncionarioDTO> findByTelefone(@RequestParam String telefone) {
        return ResponseEntity.ok(service.findByTelefone(telefone));
    }

    @GetMapping("/presencas/{id}")
    public ResponseEntity<List<PresencaDTO>> presencasBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.presencasBy(id));
    }

    @GetMapping("/grupos/{id}")
    public ResponseEntity<List<GrupoDTO>> gruposBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.gruposBy(id));
    }

    @GetMapping("/treinamentos/{id}")
    public ResponseEntity<List<TreinamentoDTO>> treinamentosBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.treinamentosBy(id));
    }

    @PostMapping
    public ResponseEntity<FuncionarioDTO> save(@Valid @RequestBody FuncionarioSaveDTO data) {
        return ResponseEntity.ok(service.save(data));
    }

    @PutMapping
    public ResponseEntity<FuncionarioDTO> update(@Valid @RequestBody FuncionarioUpdateDTO data) {
        return ResponseEntity.ok(service.update(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
