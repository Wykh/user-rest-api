package com.winds.crud.exceptions;

public class IncorrectPhoneNumberException extends RuntimeException {
    public IncorrectPhoneNumberException() {
    }

    public IncorrectPhoneNumberException(String message) {
        super(message);
    }

    public IncorrectPhoneNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
