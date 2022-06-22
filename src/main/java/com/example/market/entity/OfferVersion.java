package com.example.market.entity;

import javax.persistence.*;

@Entity
@Table(name = "offerVersions")
public class OfferVersion {
    @ManyToOne(targetEntity = OfferEntity.class)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private OfferEntity id;
    @Column(nullable = false)
    private String name;
    @Column(name = "updateDate", nullable = false)
    @Id
    private String updateDate;
    @Column(name = "parentId")
    private String parentId;
    @Column(name = "price", nullable = false)
    private long price;

    public OfferVersion() {
    }

    public OfferVersion(OfferEntity offer) {
        setId(offer);
        setName(offer.getName());
        setUpdateDate(offer.getUpdateDate());
        if (offer.getParent() != null)
            setParentId(offer.getParent().getId());
        setPrice(offer.getPrice());
    }

    public OfferEntity getId() {
        return id;
    }

    public void setId(OfferEntity id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}