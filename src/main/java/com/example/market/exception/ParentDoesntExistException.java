package com.example.market.exception;

public class ParentDoesntExistException extends RuntimeException {
    public ParentDoesntExistException(String message) {
        super(message);
    }
}
