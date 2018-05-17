package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.AtpcoFare;

/**
 * Spring Data MongoDB repository for the AtpcoFare entity.
 */
@Repository
public interface AtpcoFareRepository extends MongoRepository<AtpcoFare, String> {
	
}
