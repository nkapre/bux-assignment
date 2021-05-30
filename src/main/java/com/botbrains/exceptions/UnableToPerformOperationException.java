package com.botbrains.exceptions;

public class UnableToPerformOperationException extends RuntimeException {
    public UnableToPerformOperationException(String message) {
        super(message);
    }

    public UnableToPerformOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
