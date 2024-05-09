package br.com.matheus.gerenciadordetreinamentos.exceptions;

import java.time.LocalDateTime;

public class ExceptionResponse {

    private final LocalDateTime timestamp;
    private final String message;
    private final String details;

    public ExceptionResponse(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }
}