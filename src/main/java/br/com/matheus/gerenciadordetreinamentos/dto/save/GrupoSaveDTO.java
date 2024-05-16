package br.com.matheus.gerenciadordetreinamentos.dto.save;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record GrupoSaveDTO(

        @NotBlank
        String nome,

        @NotBlank
        String descricao,

        List<Long>funcionarios
        
) {}
