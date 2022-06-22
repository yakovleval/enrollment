package com.example.market.exception;

public class IdDuplicationException extends RuntimeException {
    public IdDuplicationException(String message) {
        super(message);
    }
}
