package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.ConfirmPresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.view.FuncionarioViewDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.view.PresencaViewDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.view.TreinamentoViewDTO;
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
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = PresencaViewDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<PresencaViewDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id).toView());
    }

    @Operation(summary = "Busca todos os Presencas", description = "Busca todos os Presencas, a excessão dos Presencas desativos, que são considerados excuidos pela API.", tags = {"Presenca"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PresencaViewDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<PresencaViewDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(PresencaDTO::toView).toList());
    }

    @Operation(summary = "Busca o funcionario da Presença", description = "Busca o funcionario da presença, encontrada pelo ID, que é passado via Path.", tags = {"Presenca"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = FuncionarioViewDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<FuncionarioViewDTO> funcionarioBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.funcionariosBy(id).toView());
    }

    @Operation(summary = "Busca o treinamento da Presença", description = "Busca o treinamento da Presença, encontrada pelo ID, que é passado via Path.", tags = {"Presenca"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = TreinamentoViewDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/treinamentos/{id}")
    public ResponseEntity<TreinamentoViewDTO> treinamentoBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.treinamentoBy(id).toView());
    }

    @Operation(summary = "Confirma Presença na aula.", description = "Confirma a presença de um funcionario pelo codigo da aula, e pelo id do funcionario, os quais são passados via body. Esse metodo possui varias validações internas, consulte a documentação interna para saber mais.", tags = {"Presenca"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = PresencaViewDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @PostMapping("/confirm")
    public ResponseEntity<PresencaViewDTO> confirm(@RequestBody @Valid ConfirmPresencaDTO data) {
        return ResponseEntity.ok().body(service.save(data).toView());
    }
}
