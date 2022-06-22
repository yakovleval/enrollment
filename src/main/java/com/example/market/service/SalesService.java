package com.example.market.service;

import com.example.market.model.SalesJson;
import com.example.market.repository.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SalesService {
    @Autowired
    private OfferRepo offerRepo;

    public SalesJson response(String date) {
        return new SalesJson(offerRepo.findAll(), date); //TODO: exception if date is incorrect
    }

}
