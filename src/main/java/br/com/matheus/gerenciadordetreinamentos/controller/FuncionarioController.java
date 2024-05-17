package br.com.matheus.gerenciadordetreinamentos.controller;

import br.com.matheus.gerenciadordetreinamentos.dto.FuncionarioDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.GrupoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.PresencaDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.TreinamentoDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.save.FuncionarioSaveDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.update.FuncionarioUpdateDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.view.FuncionarioViewDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.view.GrupoViewDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.view.PresencaViewDTO;
import br.com.matheus.gerenciadordetreinamentos.dto.view.TreinamentoViewDTO;
import br.com.matheus.gerenciadordetreinamentos.service.FuncionarioService;
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
@RequestMapping("/funcionario")
@Tag(name = "Funcionario", description = "Endpoints para gerenciar Funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @Operation(summary = "Busca um Funcionario pelo ID", description = "Busca um Funcionario pelo ID, que é enviado via Path, retornando seus dados.", tags = {"Funcionario"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = FuncionarioViewDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<FuncionarioViewDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id).toView());
    }

    @Operation(summary = "Busca todos os Funcionarios", description = "Busca todos os Funcionarios, a excessão dos Funcionarios desativos, que são considerados excuidos pela API.", tags = {"Funcionario"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FuncionarioViewDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<FuncionarioViewDTO>> findAll() {
        return ResponseEntity.ok(service.findAll().stream().map(FuncionarioDTO::toView).toList());
    }

    @Operation(summary = "Busca Funcionarios pelo Nome", description = "Busca todos os Funcionarios que possuem a String buscada em seu nome, independente de onde está localizada essa String.Essa String é recebida através de um parametro chamado nome", tags = {"Funcionario"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FuncionarioViewDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/nome")
    public ResponseEntity<List<FuncionarioViewDTO>> findByNome(@RequestParam String nome) {
        return ResponseEntity.ok(service.findByNome(nome).stream().map(FuncionarioDTO::toView).toList());
    }

    @Operation(summary = "Busca Funcionarios pelo Usuario", description = "Busca todos os Funcionarios que possuem a String buscada em seu Usuario, independente de onde está localizada essa String.Essa String é recebida através de um parametro chamado usuario", tags = {"Funcionario"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FuncionarioViewDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/usuario")
    public ResponseEntity<List<FuncionarioViewDTO>> findByUsuario(@RequestParam String usuario) {
        return ResponseEntity.ok(service.findByUsuario(usuario).stream().map(FuncionarioDTO::toView).toList());
    }

    @Operation(summary = "Busca um Funcionario pelo CPF", description = "Busca um único funcionario pelo CPF, que é recebido por um parametro na URL", tags = {"Funcionario"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = FuncionarioViewDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/cpf")
    public ResponseEntity<FuncionarioViewDTO> findByCpf(@RequestParam String cpf) {
        return ResponseEntity.ok(service.findByCpf(cpf).toView());
    }

    @Operation(summary = "Busca Funcionarios pelo Email", description = "Busca todos os funcionarios que possuem a String buscada no campo email, que é recebida por um parametro na URL", tags = {"Funcionario"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = FuncionarioViewDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/email")
    public ResponseEntity<List<FuncionarioViewDTO>> findByEmail(@RequestParam String email) {
        return ResponseEntity.ok(service.findByEmail(email).stream().map(FuncionarioDTO::toView).toList());
    }

    @Operation(summary = "Busca um Funcionario pelo telefone", description = "Busca um único funcionario pelo email, que é recebido por um parametro na URL.", tags = {"Funcionario"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = FuncionarioViewDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/telefone")
    public ResponseEntity<FuncionarioViewDTO> findByTelefone(@RequestParam String telefone) {
        return ResponseEntity.ok(service.findByTelefone(telefone).toView());
    }

    @Operation(summary = "Busca as Presenças de um funcionario", description = "Busca todas as presenças de um funcionario, independente do treinamento. Esse funcionario é buscado pelo ID, que deve ser passado pelo Path.", tags = {"Funcionario"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = PresencaViewDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/presencas/{id}")
    public ResponseEntity<List<PresencaViewDTO>> presencasBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.presencasBy(id).stream().map(PresencaDTO::toView).toList());
    }

    @Operation(summary = "Busca os Grupos de um funcionario", description = "Busca todos os grupos que um funcionario, buscado pelo ID, que deve ser passado no Path, faz parte.", tags = {"Funcionario"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = GrupoViewDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/grupos/{id}")
    public ResponseEntity<List<GrupoViewDTO>> gruposBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.gruposBy(id).stream().map(GrupoDTO::toView).toList());
    }

    @Operation(summary = "Busca os Treinamentos de um funcionario", description = "Busca todos os treinamentos que o funcionario, buscado pelo ID, que deve ser passado pelo Path, faz parte.", tags = {"Funcionario"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = {
                    @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TreinamentoViewDTO.class)))
            }),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @GetMapping("/treinamentos/{id}")
    public ResponseEntity<List<TreinamentoViewDTO>> treinamentosBy(@PathVariable Long id) {
        return ResponseEntity.ok(service.treinamentosBy(id).stream().map(TreinamentoDTO::toView).toList());
    }

    @Operation(summary = "Salva um Funcionario no banco de dados", description = "Salva um Funcionario no banco de dados, os dados recebidos são baseados na classe FuncionarioSaveDTO.Esses dados devem ser recebidos via body.Alguns campos são obrigatórios no processo, caso não sejam devidamente fornecidos retornarão um erro.", tags = {"Funcionario"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = FuncionarioViewDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @PostMapping
    public ResponseEntity<FuncionarioViewDTO> save(@Valid @RequestBody FuncionarioSaveDTO data) {
        return ResponseEntity.ok(service.save(data).toView());
    }

    @Operation(summary = "Atualiza um Funcionario no banco de dados", description = "Atualiza um Funcionario no banco de dados, os dados recebidos são baseados na classe FuncionarioUpdateDTO.Esses dados devem ser recebidos via body.Alguns campos são obrigatórios no processo, caso não sejam devidamente fornecidos retornarão um erro.", tags = {"Funcionario"})
    @ApiResponses(value = {
            @ApiResponse(description = "Sucess", responseCode = "200", content = @Content(schema = @Schema(implementation = FuncionarioViewDTO.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content)
    })
    @PutMapping
    public ResponseEntity<FuncionarioViewDTO> update(@Valid @RequestBody FuncionarioUpdateDTO data) {
        return ResponseEntity.ok(service.update(data).toView());
    }

    @Operation(summary = "Desativa um Funcionario", description = "Desativa um Funcionario pelo ID, que é recebido pelo Path, os dados permanecerão no banco de dados, porém serão desconsiderados na maior parte das operações, via de regra apenas Administradores tem acesso a esses dados.", tags = {"Funcionario"})
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
