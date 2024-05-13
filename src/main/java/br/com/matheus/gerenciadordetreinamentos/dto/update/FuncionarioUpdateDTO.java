package br.com.matheus.gerenciadordetreinamentos.dto.update;

import java.util.List;

public record FuncionarioUpdateDTO(

        Long id,
        String nome,
        String email,
        String usuario,
        String senha,
        String telefone,

        List<Long> grupos
        
) {}
