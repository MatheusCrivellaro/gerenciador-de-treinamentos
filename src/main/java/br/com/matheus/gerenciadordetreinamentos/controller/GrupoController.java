package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.GrupoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.GrupoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.GrupoSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.GrupoUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.service.GrupoService;
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
@RequestMapping("/grupo")
@Tag(name = "Grupo", description = "Endpoints para gerenciar Grupos de funcionarios")
public class GrupoController {

    @Autowired
    private GrupoService service;

    @Operation(summary = "Busca um Grupo pelo ID", description = "Busca um Grupo pelo ID, que é enviado via Path, retornando seus dados.", tags = {"Grupo"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = GrupoDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<GrupoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Busca todos os Grupos", description = "Busca todos os Grupos, a excessão dos Grupos desativos, que são considerados excuidos pela API.", tags = {"Grupo"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GrupoDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<GrupoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Busca Grupos por nome", description = "Busca todos os grupos que possuem a String buscada no campo nome. Essa String deve ser passada por um param nome.")
    @GetMapping("/nome")
    public ResponseEntity<List<GrupoDTO>> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.findByNome(nome));
    }

    @Operation(summary = "Busca os funcionarios do grupo", description = "Busca todos os funcionarios que fazem parte do grupo, buscado pelo ID, que é passavo pelo Path.")
    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<List<FuncionarioDTO>> funcionariosBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.funcionariosBy(id));
    }

    @Operation(summary = "Busca os Treinamentos do Grupo", description = "Busca todos os treinamentos que o grupo faz parte, grupo no qual é buscado pelo ID, que é passado pelo Path.")
    @GetMapping("/treinamentos/{id}")
    public ResponseEntity<List<TreinamentoDTO>> treinamentosBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.treinamentosBy(id));
    }

    @Operation(summary = "Salva um Grupo no banco de dados", description = "Salva um Grupo no banco de dados, os dados recebidos são baseados na classe GrupoSaveDTO.Esses dados devem ser recebidos via body.Alguns campos são obrigatórios no processo, caso não sejam devidamente fornecidos retornarão um erro.", tags = {"Grupo"})
    @PostMapping
    public ResponseEntity<GrupoDTO> save(@Valid @RequestBody GrupoSaveDTO data) {
        return ResponseEntity.ok(service.save(data));
    }

    @Operation(summary = "Atualiza um Grupo no banco de dados", description = "Atualiza um Grupo no banco de dados, os dados recebidos são baseados na classe GrupoUpdateDTO.Esses dados devem ser recebidos via body.Alguns campos são obrigatórios no processo, caso não sejam devidamente fornecidos retornarão um erro.", tags = {"Grupo"})
    @PutMapping
    public ResponseEntity<GrupoDTO> update(@Valid @RequestBody GrupoUpdateDTO data) {
        return ResponseEntity.ok(service.update(data));
    }

    @Operation(summary = "Desativa um Grupo", description = "Desativa um Grupo pelo ID, que é recebido pelo Path, os dados permanecerão no banco de dados, porém serão desconsiderados na maior parte das operações, via de regra apenas Grupos tem acesso a esses dados.", tags = {"Grupo"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
