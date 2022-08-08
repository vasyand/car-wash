package ru.lieague.carwash.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ResponseStatus(NOT_FOUND)
public class FreeBoxNotFoundException extends RuntimeException{
    public FreeBoxNotFoundException(String message) {
        super(message);
    }
}
