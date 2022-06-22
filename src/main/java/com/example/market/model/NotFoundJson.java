package com.example.market.model;

public class NotFoundJson {
    int code = 404;
    String message = "Item not found";

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public NotFoundJson() {
    }
}
