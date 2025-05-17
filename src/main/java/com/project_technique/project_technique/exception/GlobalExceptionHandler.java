package com.project_technique.project_technique.exception;


import io.swagger.v3.oas.annotations.Hidden;

import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@Hidden
@ControllerAdvice(basePackages = "com.projecttechnique") // Adapte selon ton package
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Throwable rootCause = getRootCause(ex);
        if (rootCause instanceof PSQLException) {
            String message = rootCause.getMessage();
            if (message != null && message.contains("users_email_key")) {
                return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Cet email existe déjà. Veuillez en utiliser un autre.");
            }
        }

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body("Erreur d'intégrité des données.");
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
