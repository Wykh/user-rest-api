package com.winds.crud.exceptions;

public class IncorrectEmailException extends RuntimeException{
    public IncorrectEmailException() {
    }

    public IncorrectEmailException(String message) {
        super(message);
    }

    public IncorrectEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
