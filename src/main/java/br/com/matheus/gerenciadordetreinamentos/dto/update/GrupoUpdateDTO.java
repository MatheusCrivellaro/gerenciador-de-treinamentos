package br.com.matheus.gerenciadordetreinamentos.dto.update;

import br.com.matheus.gerenciadordetreinamentos.domain.model.Treinamento;

import java.util.List;

public record GrupoUpdateDTO(

        Long id,
        String nome,
        String descricao,

        List<Long> funcionarios,
        List<Long> treinamentos
        
) {}
