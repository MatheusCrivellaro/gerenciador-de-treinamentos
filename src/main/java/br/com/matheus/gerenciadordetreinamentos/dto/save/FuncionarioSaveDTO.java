package br.com.matheus.gerenciadordetreinamentos.dto.save;

import br.com.matheus.gerenciadordetreinamentos.domain.enums.Genero;

import java.time.LocalDate;
import java.util.List;

public record FuncionarioSaveDTO(
        
        String nome,
        String email,
        String usuario,
        String senha,
        String telefone,
        String cpf,
        Genero genero,
        LocalDate dataNascimento,

        List<Long> grupos
        
) {}
