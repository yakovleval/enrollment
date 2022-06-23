package com.example.market.controller;

import com.example.market.exception.IdDoesntExistException;
import com.example.market.model.BadRequestJson;
import com.example.market.model.NotFoundJson;
import com.example.market.service.NodesService;
import com.example.market.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/nodes")
public class NodesController {
    @Autowired
    NodesService nodesService;
    @Autowired
    ValidationService validationService;
    @GetMapping("/{id}")
    public ResponseEntity getNode(@PathVariable String id) {
        try {
            validationService.validateId(id);
            return ResponseEntity.ok(nodesService.response(id));
        }
        catch (IdDoesntExistException e) {
//            throw e;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundJson());
        }
    }
}
