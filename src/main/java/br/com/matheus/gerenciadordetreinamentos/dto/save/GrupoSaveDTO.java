package br.com.matheus.gerenciadordetreinamentos.dto.save;

import java.util.List;

public record GrupoSaveDTO(

        String nome,
        String descricao,

        List<Long>funcionarios
        
) {}
