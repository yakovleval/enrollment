package com.example.market.controller;

import com.example.market.exception.IdDoesntExistException;
import com.example.market.exception.IncorrectIntervalException;
import com.example.market.exception.ItemNotFoundException;
import com.example.market.model.BadRequestJson;
import com.example.market.model.Item;
import com.example.market.model.NotFoundJson;
import com.example.market.model.StatisticJson;
import com.example.market.service.StatisticService;
import com.example.market.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@RestController
@RequestMapping("/node")
public class StatisticController {
    @Autowired
    ValidationService validationService;
    @Autowired
    StatisticService statisticService;
    @GetMapping("/{id}/statistic")
    public ResponseEntity getStatistic(@PathVariable String id, @RequestParam String dateStart, @RequestParam String dateEnd) {
        try {
            validationService.validateStatisticRequest(id, dateStart, dateEnd);
            List<Item> items = statisticService.getStatistic(id, dateStart, dateEnd);
            return ResponseEntity.ok(new StatisticJson(items));
        }
        catch (DateTimeParseException | IncorrectIntervalException e) {
            return ResponseEntity.badRequest().body(new BadRequestJson());
//            throw e;
        }
        catch (IdDoesntExistException e) {
//            throw new ItemNotFoundException();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new NotFoundJson());
        }

    }
}
