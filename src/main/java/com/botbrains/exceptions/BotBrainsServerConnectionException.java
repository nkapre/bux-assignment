package com.botbrains.exceptions;

public class BotBrainsServerConnectionException extends RuntimeException {
    public BotBrainsServerConnectionException(String message) {
        super(message);
    }

    public BotBrainsServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}