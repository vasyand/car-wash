package ru.lieague.carwash.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseStatus(BAD_REQUEST)
public class FreeBoxNotFoundException extends RuntimeException{
    public FreeBoxNotFoundException(String message) {
        super(message);
    }
}
