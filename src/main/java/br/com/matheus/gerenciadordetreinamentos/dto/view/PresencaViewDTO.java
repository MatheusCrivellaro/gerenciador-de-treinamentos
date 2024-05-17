package br.com.matheus.gerenciadordetreinamentos.dto.view;

import java.time.LocalDateTime;

public record PresencaViewDTO(

        Long key,
        LocalDateTime dataPublicacao,
        boolean presente,

        Long funcionario,
        Long treinamento

) { }
