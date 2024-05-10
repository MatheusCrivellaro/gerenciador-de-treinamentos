package br.com.matheus.gerenciadordetreinamentos.dto.update;

import br.com.matheus.gerenciadordetreinamentos.domain.enums.Genero;

import java.time.LocalDate;
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
