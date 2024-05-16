package br.com.matheus.gerenciadordetreinamentos.dto.save;

import jakarta.validation.constraints.NotBlank;

public record ConfirmPresencaDTO(

        @NotBlank
        String code,

        @NotBlank
        Long id

) { }
