package br.com.matheus.gerenciadordetreinamentos.dto.save;

import jakarta.validation.constraints.NotBlank;

public record AdministradorSaveDTO(

        @NotBlank
        String nome,

        @NotBlank
        String usuario,

        @NotBlank
        String senha

) {}
