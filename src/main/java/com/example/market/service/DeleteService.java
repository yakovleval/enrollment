package com.example.market.service;

import com.example.market.repository.CategoryRepo;
import com.example.market.repository.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private OfferRepo offerRepo;
    public void deleteItem(String id) {
        if (categoryRepo.existsById(id)) {
            categoryRepo.deleteById(id);
        }
        else
            offerRepo.deleteById(id); //TODO: exception if id doesnt exists
    }
}
