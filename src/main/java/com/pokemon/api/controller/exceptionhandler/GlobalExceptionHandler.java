package com.pokemon.api.controller.exceptionhandler;

import com.pokemon.api.exception.InvalidAuthenticationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = InvalidAuthenticationException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleAuthenticationException(InvalidAuthenticationException exception) {
        return new ErrorDTO(exception.getMessage());
    }


}
