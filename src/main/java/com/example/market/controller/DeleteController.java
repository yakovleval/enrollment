package com.example.market.controller;

import com.example.market.exception.IdDoesntExistException;
import com.example.market.exception.ItemNotFoundException;
import com.example.market.model.BadRequestJson;
import com.example.market.model.NotFoundJson;
import com.example.market.service.DeleteService;
import com.example.market.service.ImportsService;
import com.example.market.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delete")
public class DeleteController {
    @Autowired
    DeleteService deleteService;
    @Autowired
    ValidationService validationService;
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id) {
        try {
            validationService.validateId(id);
            deleteService.deleteItem(id);
            return ResponseEntity.ok("");
        }
        catch (IdDoesntExistException e) {
//            throw e;
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundJson());
        }
    }
}
