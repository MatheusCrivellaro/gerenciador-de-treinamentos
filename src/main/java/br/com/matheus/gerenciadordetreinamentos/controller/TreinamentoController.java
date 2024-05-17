package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.GrupoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.TreinamentoSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.TreinamentoUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.service.TreinamentoOperationService;
import br.com.matheus.gerenciadordetreinamentos.service.TreinamentoService;
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
@RequestMapping("/treinamento")
@Tag(name = "Treinamento", description = "Endpoints para gerenciar Treinamentos")
public class TreinamentoController {

    @Autowired
    private TreinamentoService service;

    @Autowired
    private TreinamentoOperationService treinamentoOperationService;

    @Operation(summary = "Busca um Treinamento pelo ID", description = "Busca um Treinamento pelo ID, que é enviado via Path, retornando seus dados.", tags = {"Treinamento"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = TreinamentoDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<TreinamentoDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Busca todos os Treinamentos", description = "Busca todos os Treinamentos, a excessão dos Treinamentos desativos, que são considerados excuidos pela API.", tags = {"Treinamento"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TreinamentoDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<TreinamentoDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Busca Treinamentos por nome", description = "Busca todos os Treinamentos que possuem a String buscada em seu nome, independente de onde está localizada essa String.Essa String é recebida através de um parametro chamado nome", tags = {"Treinamento"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TreinamentoDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/nome")
    public ResponseEntity<List<TreinamentoDTO>> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.findByNome(nome));
    }

    @Operation(summary = "Busca os grupos do treinamento", description = "Busca todos os grupos do treinamento, encontrado pelo ID, que é passado via Path.", tags = {"Treinamento"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GrupoDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/grupos/{id}")
    public ResponseEntity<List<GrupoDTO>> gruposBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.gruposBy(id));
    }

    @Operation(summary = "Busca as presenças do treinamento", description = "Busca as presenças do treinamento, encontrado pelo ID, que é passado via Path. Durante o periodo de aula as faltas não estarão presentes, ja que são criadas após o termino da aula. Uma saida possível para obter os alunos que ainda não enviaram a presença é comparando o retorno desse método com o retorno do método alunosBy (Path: /funcionarios/{id}) , desse mesmo controller", tags = {"Treinamento"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PresencaDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/presencas/{id}")
    public ResponseEntity<List<PresencaDTO>> presencasBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.presencasBy(id));
    }

    @Operation(summary = "Busca os funcionarios do treinamento", description = "Busca os funcionarios desse treinamento, encontrado pelo ID, que é passado via Path.", tags = {"Treinamento"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FuncionarioDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<List<FuncionarioDTO>> funcionariosBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.funcionariosBy(id));
    }

    @Operation(summary = "Finaliza o treinamento", description = "Finaliza o treinamento, encontrado pelo ID, que é passado pelo Path. Após finalizar o treinamento a lista de presença completa será gerada, portanto as faltas serão contabilizadas após a execução desse código.", tags = {"Treinamento"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200"),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @PostMapping("/end/{id}")
    public ResponseEntity<?> end(@PathVariable Long id) {
        treinamentoOperationService.endTreinamento(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Salva um Treinamento no banco de dados", description = "Salva um Treinamento no banco de dados, os dados recebidos são baseados na classe TreinamentoSaveDTO.Esses dados devem ser recebidos via body.Alguns campos são obrigatórios no processo, caso não sejam devidamente fornecidos retornarão um erro.", tags = {"Treinamento"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = TreinamentoDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @PostMapping
    public ResponseEntity<TreinamentoDTO> save(@Valid @RequestBody TreinamentoSaveDTO data) {
        return ResponseEntity.ok(service.save(data));
    }

    @Operation(summary = "Atualiza um Treinamento no banco de dados", description = "Atualiza um Treinamento no banco de dados, os dados recebidos são baseados na classe TreinamentoUpdateDTO.Esses dados devem ser recebidos via body.Alguns campos são obrigatórios no processo, caso não sejam devidamente fornecidos retornarão um erro.", tags = {"Treinamento"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = TreinamentoDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @PutMapping
    public ResponseEntity<TreinamentoDTO> update(@Valid @RequestBody TreinamentoUpdateDTO data) {
        return ResponseEntity.ok(service.update(data));
    }

    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200"),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @Operation(summary = "Desativa um Treinamento", description = "Desativa um Treinamento pelo ID, que é recebido pelo Path, os dados permanecerão no banco de dados, porém serão desconsiderados na maior parte das operações, via de regra apenas Administradores tem acesso a esses dados.", tags = {"Treinamento"})
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
