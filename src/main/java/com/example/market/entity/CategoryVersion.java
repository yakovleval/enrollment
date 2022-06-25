package com.example.market.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name="categoryVersions")
public class CategoryVersion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int number;

//    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(targetEntity = CategoryEntity.class)
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private CategoryEntity id;
    @Column(nullable = false)
    private String name;
    @Column(name = "updateDate", nullable = false)
    private String updateDate;
    @Column(name = "parentId")
    private String parentId;
    @Column(name = "price")
    private Long price;

    public CategoryVersion() {
    }

    public CategoryVersion(CategoryEntity category) {
        setId(category);
        setName(category.getName());
        setUpdateDate(category.getUpdateDate());
        if (category.getParent() != null)
            setParentId(category.getParent().getId());
        setPrice(category.getPrice());
    }

    public CategoryEntity getId() {
        return id;
    }

    public void setId(CategoryEntity id) {
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

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
