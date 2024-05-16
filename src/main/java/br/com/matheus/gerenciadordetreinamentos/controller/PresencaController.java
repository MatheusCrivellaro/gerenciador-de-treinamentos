package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.AdministradorDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.ConfirmPresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.service.PresencaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presenca")
@Tag(name = "Presenca", description = "Endpoints para gerenciar as Presenças das aulas")
public class PresencaController {

    @Autowired
    private PresencaService service;

    @Operation(summary = "Busca uma Presença pelo ID", description = "Busca uma Presença pelo ID, que é enviado via Path, retornando seus dados.", tags = {"Presenca"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = AdministradorDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PresencaDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Busca todos os Presencas", description = "Busca todos os Presencas, a excessão dos Presencas desativos, que são considerados excuidos pela API.", tags = {"Presenca"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = AdministradorDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PresencaDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }


    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<FuncionarioDTO> funcionarioBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.funcionariosBy(id));
    }
    @GetMapping("/treinamentos/{id}")
    public ResponseEntity<TreinamentoDTO> treinamentoBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.treinamentoBy(id));
    }

    @Operation(summary = "Confirma Presença na aula.", description = "Confirma a presença de um funcionario pelo codigo da aula, e pelo id do funcionario, os quais são passados via body. Esse metodo possui varias validações internas, consulte a documentação interna para saber mais.")
    @PostMapping("/confirm")
    public ResponseEntity<PresencaDTO> confirm(@RequestBody @Valid ConfirmPresencaDTO data) {
        return ResponseEntity.ok().body(service.save(data));
    }
}
