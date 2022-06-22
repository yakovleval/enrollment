package com.example.market.exception;

public class NullPriceException extends RuntimeException {
    public NullPriceException(String message) {
        super(message);
    }
}
