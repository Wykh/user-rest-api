package com.winds.crud.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class WebControllerAdvice {
    @ExceptionHandler({IncorrectEmailException.class, IncorrectPhoneNumberException.class})
    public ResponseEntity<String> handleIncorrectUserDateException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler({UserNotFoundInDatabaseException.class})
    public ResponseEntity<String> handleUserNotFoundException(Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
