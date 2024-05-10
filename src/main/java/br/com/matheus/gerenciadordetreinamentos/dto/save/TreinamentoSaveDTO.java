package br.com.matheus.gerenciadordetreinamentos.dto.save;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record TreinamentoSaveDTO(

        String nome,
        String descricao,
        String codigo,
        LocalDate data,
        LocalTime abertura,
        LocalTime encerramento,

        List<Long>grupos,
        Long professor

) {}
