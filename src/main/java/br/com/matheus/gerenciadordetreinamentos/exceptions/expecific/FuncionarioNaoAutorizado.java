package br.com.matheus.gerenciadordetreinamentos.exceptions.expecific;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FuncionarioNaoAutorizado extends RuntimeException {

    public FuncionarioNaoAutorizado(String message) {
        super(message);
    }
}
