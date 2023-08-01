package com.viit0r.cursospring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedMathOperationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnsupportedMathOperationException(String message) {
        super(message);
    }
}
