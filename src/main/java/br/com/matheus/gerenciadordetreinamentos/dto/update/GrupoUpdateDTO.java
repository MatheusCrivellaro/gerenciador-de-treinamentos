package br.com.matheus.gerenciadordetreinamentos.dto.update;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record GrupoUpdateDTO(

        @NotBlank
        Long id,
        String nome,
        String descricao,

        List<Long> funcionarios,
        List<Long> treinamentos
        
) {}
