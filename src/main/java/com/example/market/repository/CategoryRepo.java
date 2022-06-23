package com.example.market.repository;

import com.example.market.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepo extends CrudRepository<CategoryEntity, String> {
    List<CategoryEntity> findAllByParentId(String parentId);
}
