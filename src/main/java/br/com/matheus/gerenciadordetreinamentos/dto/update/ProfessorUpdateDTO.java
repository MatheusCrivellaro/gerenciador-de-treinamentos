package br.com.matheus.gerenciadordetreinamentos.dto.update;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record ProfessorUpdateDTO(

        Long id,
        String nome,
        String usuario,
        String senha,
        String email,
        String telefone,
        List<Long> treinamentos

) {}
