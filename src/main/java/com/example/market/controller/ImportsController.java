package com.example.market.controller;

import com.example.market.model.BadRequestJson;
import com.example.market.model.Item;
import com.example.market.service.ImportsService;
import com.example.market.model.ImportsJson;
import com.example.market.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imports")
public class ImportsController {
    @Autowired
    ImportsService importsService;
    @Autowired
    ValidationService validationService;
    @PostMapping
    public ResponseEntity importItems(@RequestBody ImportsJson importsJson) {
        try {
            String updateDate = importsJson.getUpdateDate();
            validationService.validateDate(updateDate);
            List<Item> items = validationService.validateAndSortImportsJson(importsJson);
            importsService.importItems(items, updateDate);
            return ResponseEntity.ok("");
        } catch (Exception e) {
            throw e;
//            return ResponseEntity.badRequest().body(new BadRequestJson());
        }
    }
}
