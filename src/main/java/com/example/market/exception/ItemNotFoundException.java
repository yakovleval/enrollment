package com.example.market.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Item not found")
public class ItemNotFoundException extends RuntimeException {
}
