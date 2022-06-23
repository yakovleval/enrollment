package com.example.market.repository;

import com.example.market.entity.CategoryEntity;
import com.example.market.entity.OfferEntity;
import com.example.market.entity.OfferVersion;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepo extends CrudRepository<OfferEntity, String> {
    List<OfferEntity> findAllByParentId(String id);
}
