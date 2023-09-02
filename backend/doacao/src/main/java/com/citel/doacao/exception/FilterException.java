package com.citel.doacao.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FilterException extends RuntimeException{
    public FilterException() {
        super("Não foi possivel filtrar os tipos sanguineos");
    }
}
