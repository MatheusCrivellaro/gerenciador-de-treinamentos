package br.com.matheus.gerenciadordetreinamentos.dto.view;

import java.time.LocalDate;
import java.util.List;

public record ProfessorViewDTO(

        Long key,
        String nome,
        String usuario,
        String email,
        String telefone,
        LocalDate dataNascimento,

        List<Long>treinamentos

) { }
