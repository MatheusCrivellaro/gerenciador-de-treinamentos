package br.com.matheus.gerenciadordetreinamentos.exceptions.expecific;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TreinamentoEncerradoException extends RuntimeException {

    public TreinamentoEncerradoException(String message) {
        super(message);
    }
}
