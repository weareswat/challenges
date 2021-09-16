package com.Joao_Branco.listpostsbyrating.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NewPostException extends RuntimeException {

    public NewPostException(String message) {
        super(message);
    }
}
