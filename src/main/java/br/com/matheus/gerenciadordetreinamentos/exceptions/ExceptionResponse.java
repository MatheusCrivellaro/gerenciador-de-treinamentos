package br.com.matheus.gerenciadordetreinamentos.exceptions;

import java.time.LocalDateTime;

public record ExceptionResponse(LocalDateTime timestamp, String message, String details) {

}
