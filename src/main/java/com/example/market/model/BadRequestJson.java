package com.example.market.model;

public class BadRequestJson {
    long code = 400;
    String message = "Validation Failed";

    public BadRequestJson() {
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
