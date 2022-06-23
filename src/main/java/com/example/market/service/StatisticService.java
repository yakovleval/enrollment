package com.example.market.service;

import com.example.market.entity.CategoryEntity;
import com.example.market.entity.CategoryVersion;
import com.example.market.model.Item;
import com.example.market.repository.CategoryRepo;
import com.example.market.repository.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService {
    @Autowired
    CategoryRepo categoryRepo;
    @Autowired
    OfferRepo offerRepo;
    public List<Item> getStatistic(String id, String dateStart, String dateEnd) {
        var formatter = DateTimeFormatter.ISO_DATE_TIME;
        var start = LocalDateTime.parse(dateStart, formatter);
        var end = LocalDateTime.parse(dateEnd, formatter);
        if (categoryRepo.existsById(id)) {
            var versions = categoryRepo.findById(id).get().getVersions();
            var itemsList = new ArrayList<Item>();
            for (var version: versions) {
                var time = LocalDateTime.parse(version.getUpdateDate(), formatter);
                if (start.isBefore(time) && end.isAfter(time) || start.isEqual(time))
                    itemsList.add(new Item(version));
            }
            return itemsList;
        }
        else {
            var versions = offerRepo.findById(id).get().getVersions();
            var itemsList = new ArrayList<Item>();
            for (var version: versions) {
                var time = LocalDateTime.parse(version.getUpdateDate(), formatter);
                if (start.isBefore(time) && end.isAfter(time) || start.isEqual(time))
                    itemsList.add(new Item(version));
            }
            return itemsList;
        }
    }
}
