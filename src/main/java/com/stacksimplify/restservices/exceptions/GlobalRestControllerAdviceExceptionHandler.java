package com.stacksimplify.restservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class GlobalRestControllerAdviceExceptionHandler {

    @ExceptionHandler(UserNameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public CustomErrorDetails userNameNotFound(UserNameNotFoundException ex)
    {
        return new CustomErrorDetails(new Date(), "From @RestCOntrollerAdvice NOT FOUND", ex.getMessage());
    }
}
