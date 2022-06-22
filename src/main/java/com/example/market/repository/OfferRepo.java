package com.example.market.repository;

import com.example.market.entity.OfferEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferRepo extends CrudRepository<OfferEntity, String> {
}
