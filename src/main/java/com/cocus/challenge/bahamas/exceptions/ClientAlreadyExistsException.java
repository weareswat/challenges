package com.cocus.challenge.bahamas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY, reason = "Client already exists")
public class ClientAlreadyExistsException extends RuntimeException{}
