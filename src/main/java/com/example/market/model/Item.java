package com.example.market.model;

import com.example.market.entity.CategoryEntity;
import com.example.market.entity.CategoryVersion;
import com.example.market.entity.OfferEntity;
import com.example.market.entity.OfferVersion;
import com.example.market.util.Types;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private String type;
    private String name;
    private String id;
    private String parentId;
    private Long price;

    private String date;

    private List<Item> children;

    public Item() {
    }

    public Item(CategoryEntity category) {
        setId(category.getId());
        setName(category.getName());
        setType(Types.CATEGORY.toString());
        if (category.getParent() != null) {
            setParentId(category.getParent().getId());
        }
        setDate(category.getUpdateDate());
        var childCategories = category.getChildCategories();
        var childOffers = category.getChildOffers();
        children = new ArrayList<>();
        for (var childCategory: childCategories) {
            children.add(new Item(childCategory));
        }
        for(var childOffer: childOffers) {
            children.add(new Item(childOffer));
        }
//        children = childrenList.toArray(new Item[0]);
//        setPrice(offersNumber(this) == 0 ? null : pricesSum(this) / offersNumber(this));
        setPrice(category.getPrice());
    }

    public Item(OfferEntity offer) {
        setId(offer.getId());
        setName(offer.getName());
        setType(Types.OFFER.toString());
        if (offer.getParent() != null)
            setParentId(offer.getParent().getId());
        setDate(offer.getUpdateDate());
        setPrice(offer.getPrice());
    }

    public Item(CategoryVersion version) {
        setId(version.getId().getId());
        setName(version.getName());
        setDate(version.getUpdateDate());
        setType(Types.CATEGORY.toString());
        setParentId(version.getParentId());
        setPrice(version.getPrice());
    }

    public Item(OfferVersion version) {
        setId(version.getId().getId());
        setName(version.getName());
        setDate(version.getUpdateDate());
        setType(Types.OFFER.toString());
        setParentId(version.getParentId());
        setPrice(version.getPrice());
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Item> getChildren() {
        return children;
    }

    public void setChildren(List<Item> children) {
        this.children = children;
    }


    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
