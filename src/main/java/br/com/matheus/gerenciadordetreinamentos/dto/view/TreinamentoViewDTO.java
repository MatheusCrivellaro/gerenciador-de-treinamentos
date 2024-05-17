package br.com.matheus.gerenciadordetreinamentos.dto.view;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public record TreinamentoViewDTO(

        Long key,
        String nome,
        String descricao,
        String codigo,
        LocalDate data,
        LocalTime abertura,
        LocalTime encerramento,

        List<Long>grupos,
        List<Long> presencas,
        Long professor

) { }
