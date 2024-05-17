package br.com.matheus.gerenciadordetreinamentos.dto.view;

import java.util.List;

public record GrupoViewDTO(

        Long key,
        String nome,
        String descricao,

        List<Long> funcionarios,
        List<Long> treinamentos
        
) { }
