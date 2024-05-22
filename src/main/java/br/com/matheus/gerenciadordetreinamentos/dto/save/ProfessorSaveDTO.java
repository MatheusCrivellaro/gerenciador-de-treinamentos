package br.com.matheus.gerenciadordetreinamentos.dto.save;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

public record ProfessorSaveDTO(

        @NotBlank
        String nome,

        @NotBlank
        String usuario,

        @NotBlank
        String senha,

        @NotBlank
        String email,

        @NotBlank
        String telefone,

        LocalDate dataNascimento

) {}
