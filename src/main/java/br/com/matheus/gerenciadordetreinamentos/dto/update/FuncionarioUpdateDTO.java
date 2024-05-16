package br.com.matheus.gerenciadordetreinamentos.dto.update;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record FuncionarioUpdateDTO(

        @NotBlank
        Long id,
        String nome,
        String email,
        String usuario,
        String senha,
        String telefone,

        List<Long> grupos
        
) {}
