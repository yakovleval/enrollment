package com.example.market.model;

import com.example.market.entity.OfferEntity;
import com.example.market.repository.OfferRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SalesJson {
    private List<Item> items;
    public SalesJson(Iterable<OfferEntity> offers, String date) {
        items = new ArrayList<>();
//        var offers = offerRepo.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime to = LocalDateTime.parse(date, formatter);
        LocalDateTime from = to.minusHours(24);
        for (var offer: offers) {
            LocalDateTime current = LocalDateTime.parse(offer.getUpdateDate(), formatter);
            if (current.isAfter(from) &&
            current.isBefore(to) ||
            current.isEqual(from)) {
                items.add(new Item(offer));
            }
        }
//        items = items.toArray(new Item[0]);
    }

    public SalesJson() {
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
