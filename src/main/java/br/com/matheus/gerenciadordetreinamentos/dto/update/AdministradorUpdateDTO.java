package br.com.matheus.gerenciadordetreinamentos.dto.update;

import jakarta.validation.constraints.NotBlank;

public record AdministradorUpdateDTO(

        Long id,
        String nome,
        String usuario,
        String senha

) {}
