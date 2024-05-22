package br.com.matheus.gerenciadordetreinamentos.dto.update;

import java.util.List;

public record GrupoUpdateDTO(

        Long id,
        String nome,
        String descricao,

        List<Long> funcionarios,
        List<Long> treinamentos
        
) {}
