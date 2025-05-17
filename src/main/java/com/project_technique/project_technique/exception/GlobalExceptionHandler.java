package com.project_technique.project_technique.exception;


import io.swagger.v3.oas.annotations.Hidden;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleDuplicateEmail(IllegalArgumentException ex) {

        return ResponseEntity.status(HttpStatus.CONFLICT).body("error:  "+ex.getMessage());
    }

    // Optional: utility method to unwrap the root cause
    private Throwable getRootCause(Throwable ex) {
        Throwable cause = ex;
        while (cause.getCause() != null && cause.getCause() != cause) {
            cause = cause.getCause();
        }
        return cause;
    }


}
