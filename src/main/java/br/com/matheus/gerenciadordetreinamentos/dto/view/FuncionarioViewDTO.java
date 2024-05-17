package br.com.matheus.gerenciadordetreinamentos.dto.view;

import br.com.matheus.gerenciadordetreinamentos.domain.enums.Genero;

import java.time.LocalDate;
import java.util.List;

public record FuncionarioViewDTO(

        Long key,
        String nome,
        String email,
        String usuario,
        String telefone,
        String cpf,
        Genero genero,
        LocalDate dataNascimento,

        List<Long>grupos,
        List<Long> presencas

) { }
