package com.example.market.model;

import com.example.market.entity.OfferEntity;
import com.example.market.entity.OfferVersion;
import com.example.market.repository.CategoryRepo;
import com.example.market.repository.OfferRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SalesJson {
    private List<Item> items;
    public SalesJson(OfferRepo offerRepo, Iterable<OfferVersion> offers, String date) {
        items = new ArrayList<>();
        Set<String> idsSet = new HashSet<>();
//        var offers = offerRepo.findAll();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        LocalDateTime to = LocalDateTime.parse(date, formatter);
        LocalDateTime from = to.minusHours(24);
        for (var offer: offers) {
            LocalDateTime current = LocalDateTime.parse(offer.getUpdateDate(), formatter);
            if (current.isAfter(from) &&
            current.isBefore(to) ||
            current.isEqual(from) ||
            current.isEqual(to)) {
                idsSet.add(offer.getId().getId());
            }
        }
        for (var id: idsSet) {
            var offer = offerRepo.findById(id).get();
            items.add(new Item(offer));
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
