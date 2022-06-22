package com.example.market.service;

import com.example.market.entity.CategoryEntity;
import com.example.market.entity.CategoryVersion;
import com.example.market.entity.OfferEntity;
import com.example.market.entity.OfferVersion;
import com.example.market.exception.IncorrectDateException;
import com.example.market.exception.IncorrectTypeException;
import com.example.market.model.Item;
import com.example.market.repository.CategoryRepo;
import com.example.market.repository.CategoryVersionRepo;
import com.example.market.repository.OfferRepo;
import com.example.market.model.ImportsJson;
import com.example.market.repository.OfferVersionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ImportsService {
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private OfferRepo offerRepo;
    @Autowired
    private CategoryVersionRepo categoryVersionRepo;
    @Autowired
    private OfferVersionRepo offerVersionRepo;

    private Set<CategoryEntity> categoriesToUpdateAverage;
    private Set<OfferEntity> offersToUpdateAverage;

    private void updateParents(CategoryEntity category, String updateDate) {
        if (category == null)
            return;
//        CategoryEntity category = categoryRepo.findById(id).get();
        categoriesToUpdateAverage.add(category);
        category.setUpdateDate(updateDate);
        categoryRepo.save(category);
        if (category.getParent() != null)
            updateParents(category.getParent(), updateDate);
    }

    public void importItems(List<Item> items, String updateDate) {
        categoriesToUpdateAverage = new HashSet<>();
        offersToUpdateAverage = new HashSet<>();
        for (Item item: items) {
            if (categoryRepo.existsById(item.getId())) {
                CategoryEntity category = categoryRepo.findById(item.getId()).get();
                updateParents(category.getParent(), updateDate);
            }
            if (offerRepo.existsById(item.getId())) {
                OfferEntity offer = offerRepo.findById(item.getId()).get();
                updateParents(offer.getParent(), updateDate);
            }
            CategoryEntity parent = item.getParentId() == null ? null :
                    categoryRepo.findById(item.getParentId()).get();
            if (item.getType().equals("CATEGORY")) {
//                addOrUpdateCategory(item, updateDate);
                CategoryEntity newCategory = new CategoryEntity(item, parent, updateDate);
                updateParents(newCategory.getParent(), updateDate);
                categoryRepo.save(newCategory);
                categoriesToUpdateAverage.add(newCategory);
            }
            else if (item.getType().equals("OFFER")) {
//                addOrUpdateOffer(item, updateDate);
                OfferEntity newOffer = new OfferEntity(item, parent, updateDate);
                updateParents(newOffer.getParent(), updateDate);
                offerRepo.save(newOffer);
                offersToUpdateAverage.add(newOffer);
            }
            else {
                throw new IncorrectTypeException("'type' field is incorrect");
            }
        }
        for (var category: categoriesToUpdateAverage) {
            CategoryVersion newCategoryVersion = new CategoryVersion(category);
            categoryVersionRepo.save(newCategoryVersion);
        }
        for (var offer: offersToUpdateAverage) {
            OfferVersion newOfferVersion = new OfferVersion(offer);
            offerVersionRepo.save(newOfferVersion);
        }
    }
}
