package br.com.matheus.gerenciadordetreinamentos.dto.save;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ProfessorSaveDTO(

        String nome,
        String usuario,
        String senha,
        String email,
        String telefone,
        LocalDate dataNascimento

) {}
