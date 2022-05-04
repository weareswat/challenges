package com.cocus.bahamasapi.handler.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RecordConflictingException extends RuntimeException {
    public RecordConflictingException(String exception) {
        super(exception);
    }
}