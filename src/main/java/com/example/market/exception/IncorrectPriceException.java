package com.example.market.exception;

public class IncorrectPriceException extends RuntimeException {
    public IncorrectPriceException(String message) {
        super(message);
    }
}
