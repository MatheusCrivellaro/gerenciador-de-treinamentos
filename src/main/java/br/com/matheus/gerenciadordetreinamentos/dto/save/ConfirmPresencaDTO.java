package br.com.matheus.gerenciadordetreinamentos.dto.save;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConfirmPresencaDTO(

        @NotBlank
        String code,

        @NotNull
        Long id

) { }
