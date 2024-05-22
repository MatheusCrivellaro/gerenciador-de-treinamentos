package br.com.matheus.gerenciadordetreinamentos.dto.update;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record TreinamentoUpdateDTO(

        Long id,
        String nome,
        String descricao,
        LocalDate data,
        LocalTime abertura,
        LocalTime encerramento,

        List<Long>grupos,
        Long professor

) {}
