package com.example.market.service;

import com.example.market.exception.*;
import com.example.market.model.ImportsJson;
import com.example.market.model.Item;
import com.example.market.repository.CategoryRepo;
import com.example.market.repository.OfferRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ValidationService {

    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private OfferRepo offerRepo;
    public void validateDate(String date) {
        var formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime.parse(date, formatter); // throws DateTimeParseException
    }

    public void validateStatisticRequest(String id, String dateStart, String dateEnd) {
        var formatter = DateTimeFormatter.ISO_DATE_TIME;
        var start = LocalDateTime.parse(dateStart, formatter);
        var end = LocalDateTime.parse(dateEnd, formatter);
        if (start.isEqual(end) || start.isAfter(end))
            throw new IncorrectIntervalException("interval bounds are incorrect");
        validateId(id);
    }

    public void validateId(String id) {
        if (!categoryRepo.existsById(id) &&
        !offerRepo.existsById(id)) {
            throw new IdDoesntExistException("this id doesn't exist");
        }
    }

    private void validateType(Item item) {
        if (!item.getType().equals("CATEGORY") &&
        !item.getType().equals("OFFER")) {
            throw new IncorrectTypeException("'type' field is incorrect");
        }
    }

    private void validatUniqunessAndTypes(List<Item> items) {
        Set<String> ids = new HashSet<>();
        for (Item item: items) {
            validateType(item);
            if (ids.contains(item.getId())) {
                throw new IdDuplicationException("'id' field is not unique");
            }
            else {
                ids.add(item.getId());
            }
        }
    }

    private void validateNames(List<Item> items) {
        for (var item: items) {
            if (item.getName() == null) {
                throw new NullNameException("'name' field can't be null");
            }
        }
    }

    private void validatePrices(List<Item> items) {
        for (var item: items) {
            if (item.getType().equals("OFFER") && item.getPrice() == null) {
                throw new NullPriceException("offer's 'price' field can't be null");
            }
        }
    }

    private List<Item> topSort(List<Item> items) {

        List<Item> sorted = new ArrayList<>();
        List<String> parents = new ArrayList<>();
        boolean added = true;

        while (added) {
            added = false;
            List<Item> copy = new ArrayList<>(items);
            for (var item: items) {
                if (item.getParentId() == null ||
                        categoryRepo.existsById(item.getParentId()) ||
                        parents.contains(item.getParentId())) {
                    sorted.add(item);
                    copy.remove(item);
                    added = true;
                    parents.add(item.getId());
                }
            }
            items = copy;
        }
        return sorted;
    }


    public List<Item> validateAndSortImportsJson(ImportsJson json) {
        List<Item> items = json.getItems();

        validateDate(json.getUpdateDate());
        validatUniqunessAndTypes(items);
        validateNames(items);
        validatePrices(items);

        List<Item> sortedItems = topSort(items);
        if (items.size() != sortedItems.size()) {
            throw new ParentDoesntExistException("parent with this id doesn't exist");
        }
        return sortedItems;
    }
}
