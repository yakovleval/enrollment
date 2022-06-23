package com.example.market.entity;

import com.example.market.model.Item;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class CategoryEntity {
    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Transient
    private long pricesSum;

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;
        CategoryEntity other = (CategoryEntity) obj;
        return this.getId().equals(other.getId());
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    private long getPricesSum() {
        if (isAverageUpdated)
            return pricesSum;
        pricesSum = 0L;
        if (childCategories != null) {
            for (var category: childCategories) {
                pricesSum += category.getPricesSum();
            }
        }
        if (childOffers != null) {
            for (var offer: childOffers) {
                pricesSum += offer.getPrice();
            }
        }
        return pricesSum;
    }
    @Transient
    private long offersNumber;

    private long getOffersNumber() {
        if (isAverageUpdated)
            return offersNumber;
        offersNumber = 0L;
        if (childCategories != null) {
            for (var category: childCategories) {
                offersNumber += category.getOffersNumber();
            }
        }
        offersNumber += childOffers == null ? 0 : childOffers.size();
        return offersNumber;
    }
    @Transient
    private boolean isAverageUpdated;

    public Long getPrice() {
        long offersNumber = getOffersNumber();
        Long result;
        if (offersNumber != 0) {
            result = getPricesSum() / getOffersNumber();
        }
        else
            result = null;
        isAverageUpdated = true;
        return result;
    }
    @Column(name = "updateDate", nullable = false)
    private String updateDate;
    @ManyToOne(targetEntity = CategoryEntity.class)
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    private CategoryEntity parent;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<CategoryVersion> versions;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private List<CategoryEntity> childCategories = new ArrayList<>();
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "parent")
    private List<OfferEntity> childOffers = new ArrayList<>();
    public CategoryEntity() {
    }
    public CategoryEntity(Item item, CategoryEntity parent, String updateDate) {
        setId(item.getId());
        setName(item.getName());
        setParent(parent);
        setUpdateDate(updateDate);
        isAverageUpdated = false;
    }

    public List<CategoryVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<CategoryVersion> versions) {
        this.versions = versions;
    }

    public List<CategoryEntity> getChildCategories() {
        return childCategories;
    }

    public void setChildCategories(List<CategoryEntity> childCategories) {
        this.childCategories = childCategories;
    }

    public List<OfferEntity> getChildOffers() {
        return childOffers;
    }

    public void setChildOffers(List<OfferEntity> childOffers) {
        this.childOffers = childOffers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public long getSum() {
//        return sum;
//    }
//
//    public void setSum(long sum) {
//        this.sum = sum;
//    }
//
//    public long getOffersNumber() {
//        return offersNumber;
//    }
//
//    public void setOffersNumber(long offersNumber) {
//        this.offersNumber = offersNumber;
//    }

    public CategoryEntity getParent() {
        return parent;
    }

    public void setParent(CategoryEntity parentId) {
        this.parent = parentId;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
