package com.botbrains.exceptions;

public class InvalidStartupArgumentsException extends RuntimeException {
    public InvalidStartupArgumentsException(String message) {
        super(message);
    }

    public InvalidStartupArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }
}
