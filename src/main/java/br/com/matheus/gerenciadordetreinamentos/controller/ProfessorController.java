package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.ProfessorDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.ProfessorSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.ProfessorUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.service.ProfessorService;
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
@RequestMapping("/professor")
@Tag(name = "Professor", description = "Endpoints para gerenciar Professores")
public class ProfessorController  {

    @Autowired
    private ProfessorService service;

    @Operation(summary = "Busca um Professor pelo ID", description = "Busca um Professor pelo ID, que é enviado via Path, retornando seus dados.", tags = {"Professor"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = ProfessorDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Busca todos os Professores", description = "Busca todos os Professores, a excessão dos Professores desativos, que são considerados excuidos pela API.", tags = {"Professor"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProfessorDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Operation(summary = "Busca Professores pelo Nome", description = "Busca todos os Professores que possuem a String buscada em seu nome, independente de onde está localizada essa String.Essa String é recebida através de um parametro chamado nome", tags = {"Professor"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProfessorDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/nome")
    public ResponseEntity<List<ProfessorDTO>> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.findByNome(nome));
    }

    @Operation(summary = "Busca Professores pelo Usuario", description = "Busca todos os Professores que possuem a String buscada em seu Usuario, independente de onde está localizada essa String.Essa String é recebida através de um parametro chamado usuario", tags = {"Professor"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProfessorDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/usuario")
    public ResponseEntity<List<ProfessorDTO>> findByUsuario(@RequestParam String usuario) {
        return ResponseEntity.ok(service.findByUsuario(usuario));
    }

    @Operation(summary = "Busca Professores pelo Email", description = "Busca todos os Professores que possuem a String buscada no campo email, que é recebida por um parametro na URL", tags = {"Professor"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProfessorDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/email")
    public ResponseEntity<List<ProfessorDTO>> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(service.findByEmail(email));
    }

    @Operation(summary = "Busca um Professor pelo telefone", description = "Busca um único Professor pelo email, que é recebido por um parametro na URL.", tags = {"Professor"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = ProfessorDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/telefone")
    public ResponseEntity<ProfessorDTO> findByTelefone(@RequestParam String telefone) {
        return ResponseEntity.ok(service.findByTelefone(telefone));
    }

    @Operation(summary = "Busca os Treinamentos do professor", description = "Busca os treinamentos do professor, encontrado pelo ID, que é passado pelo Path.", tags = {"Professor"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TreinamentoDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/treinamentos/{id}")
    public ResponseEntity<List<TreinamentoDTO>> treinamentosBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.treinamentosBy(id));
    }

    @Operation(summary = "Salva um Professor no banco de dados", description = "Salva um Professor no banco de dados, os dados recebidos são baseados na classe ProfessorSaveDTO.Esses dados devem ser recebidos via body.Alguns campos são obrigatórios no processo, caso não sejam devidamente fornecidos retornarão um erro.", tags = {"Professor"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = ProfessorDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @PostMapping
    public ResponseEntity<ProfessorDTO> save(@Valid @RequestBody ProfessorSaveDTO data) {
        return ResponseEntity.ok(service.save(data));
    }

    @Operation(summary = "Atualiza um Professor no banco de dados", description = "Atualiza um Professor no banco de dados, os dados recebidos são baseados na classe ProfessorUpdateDTO.Esses dados devem ser recebidos via body.Alguns campos são obrigatórios no processo, caso não sejam devidamente fornecidos retornarão um erro.", tags = {"Professor"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = ProfessorDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @PutMapping
    public ResponseEntity<ProfessorDTO> update(@Valid @RequestBody ProfessorUpdateDTO data) {
        return ResponseEntity.ok(service.update(data));
    }

    @Operation(summary = "Desativa um Professor", description = "Desativa um Professor pelo ID, que é recebido pelo Path, os dados permanecerão no banco de dados, porém serão desconsiderados na maior parte das operações, via de regra apenas Administradores tem acesso a esses dados.", tags = {"Professor"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200"),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

}
