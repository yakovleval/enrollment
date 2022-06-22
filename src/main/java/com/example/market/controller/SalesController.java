package com.example.market.controller;

import com.example.market.model.BadRequestJson;
import com.example.market.service.SalesService;
import com.example.market.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales")
public class SalesController {
    @Autowired
    SalesService salesService;
    @Autowired
    ValidationService validationService;
    @GetMapping
    public ResponseEntity getSalesList(@RequestParam String date) {
        try {
            validationService.validateDate(date);
            return ResponseEntity.ok(salesService.response(date));
        }
        catch (Exception e) {
//            throw e;
            return ResponseEntity.badRequest().body(new BadRequestJson());
        }
    }
}
