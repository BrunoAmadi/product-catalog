package com.example.catalog.controllers.exceptions;

import com.example.catalog.services.exceptions.DatabaseException;
import com.example.catalog.services.exceptions.NotFoundException;
import com.example.catalog.services.exceptions.InvalidUuidException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.Instant;

@ControllerAdvice
@ResponseStatus(HttpStatus.NOT_FOUND)
@ResponseBody
public class ControllerExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public StandardError entityNotFound(NotFoundException e, HttpServletRequest request) {
        final var error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setError(e.getMessage());
        error.setPath(request.getRequestURI());
        return error;
    }

    @ExceptionHandler(InvalidUuidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardError invalidUIID(InvalidUuidException e, HttpServletRequest request) {
        final var error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError(e.getMessage());
        error.setPath(request.getRequestURI());
        return error;
    }


    @ExceptionHandler(DatabaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public StandardError database(DatabaseException e , HttpServletRequest request){
        final var error = new StandardError();
        error.setTimestamp(Instant.now());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setError(e.getMessage());
        error.setPath(request.getRequestURI());
        return error;
    }

}
