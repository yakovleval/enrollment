package com.example.market.service;

import com.example.market.model.Item;
import com.example.market.repository.CategoryRepo;
import com.example.market.repository.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NodesService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private OfferRepo offerRepo;

    public Item response(String id) {
        if (categoryRepo.findById(id).isPresent()) {
            return new Item(categoryRepo.findById(id).get());
        }
        else {
            return new Item(offerRepo.findById(id).get());
        } //TODO: exception if id doesnt exists
    }
}
