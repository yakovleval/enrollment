package com.example.market.entity;

import com.example.market.model.Item;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "offers")
public class OfferEntity {
    @Column(unique = true, nullable = false)
    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private long price;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<OfferVersion> versions;
//    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(targetEntity = CategoryEntity.class)
    @JoinColumn(name = "parentId", referencedColumnName = "id")
    private CategoryEntity parent;
    @Column(name = "updateDate", nullable = false)
    private String updateDate;

    public String getUpdateDate() {
        return updateDate;
    }

    public List<OfferVersion> getVersions() {
        return versions;
    }

    public void setVersions(List<OfferVersion> versions) {
        this.versions = versions;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public OfferEntity() {
    }

    public OfferEntity(Item item, CategoryEntity parent, String updateDate) {
        setId(item.getId());
        setName(item.getName());
        setParent(parent);
        setPrice(item.getPrice());
        setUpdateDate(updateDate);
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

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public CategoryEntity getParent() {
        return parent;
    }

    public void setParent(CategoryEntity parent) {
        this.parent = parent;
    }
}
