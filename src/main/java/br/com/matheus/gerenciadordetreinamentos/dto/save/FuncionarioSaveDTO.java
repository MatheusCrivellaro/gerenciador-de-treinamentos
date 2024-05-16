package br.com.matheus.gerenciadordetreinamentos.dto.save;

import br.com.matheus.gerenciadordetreinamentos.domain.enums.Genero;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;

public record FuncionarioSaveDTO(

        @NotBlank
        String nome,

        @NotBlank
        String email,

        @NotBlank
        String usuario,

        @NotBlank
        String senha,

        @NotBlank
        String telefone,

        @NotBlank
        String cpf,

        @NotNull
        Genero genero,

        @NotBlank
        LocalDate dataNascimento,

        List<Long> grupos
        
) {}
