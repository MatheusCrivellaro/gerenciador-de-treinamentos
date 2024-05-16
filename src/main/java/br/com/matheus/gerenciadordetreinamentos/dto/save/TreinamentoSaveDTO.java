package br.com.matheus.gerenciadordetreinamentos.dto.save;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record TreinamentoSaveDTO(

        @NotBlank
        String nome,

        @NotBlank
        String descricao,

        @NotBlank
        LocalDate data,

        @NotBlank
        LocalTime abertura,

        @NotBlank
        LocalTime encerramento,

        @NotBlank
        List<Long>grupos,
        Long professor

) {}
