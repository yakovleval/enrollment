package com.example.market.repository;

import com.example.market.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends CrudRepository<CategoryEntity, String> {
//    CategoryEntity[] findByParentId(String parentid);
}
