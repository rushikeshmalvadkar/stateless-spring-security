package com.stateless.security.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends ApiBaseException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
