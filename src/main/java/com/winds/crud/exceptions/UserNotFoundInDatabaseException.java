package com.winds.crud.exceptions;

public class UserNotFoundInDatabaseException extends RuntimeException{
    public UserNotFoundInDatabaseException() {
    }

    public UserNotFoundInDatabaseException(String message) {
        super(message);
    }

    public UserNotFoundInDatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
