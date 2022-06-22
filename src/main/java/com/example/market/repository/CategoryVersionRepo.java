package com.example.market.repository;

import com.example.market.entity.CategoryVersion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryVersionRepo extends CrudRepository<CategoryVersion, String> {
}
