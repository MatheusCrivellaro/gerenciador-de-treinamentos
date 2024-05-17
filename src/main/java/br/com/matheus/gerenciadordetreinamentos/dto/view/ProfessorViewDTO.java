package br.com.matheus.gerenciadordetreinamentos.dto.view;

import java.time.LocalDateTime;
import java.util.List;

public record ProfessorViewDTO(

        Long key,
        String nome,
        String usuario,
        String email,
        String telefone,
        LocalDateTime dataNascimento,

        List<Long>treinamentos

) { }
