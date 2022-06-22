package com.example.market.exception;

public class IdDoesntExistException extends RuntimeException {
    public IdDoesntExistException(String message) {
        super(message);
    }
}
