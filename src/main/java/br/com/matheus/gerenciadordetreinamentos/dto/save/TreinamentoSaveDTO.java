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

        LocalDate data,

        LocalTime abertura,

        LocalTime encerramento,

        List<Long>grupos,
        Long professor

) {}
