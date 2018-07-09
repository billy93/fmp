package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.VoltrasFare;

/**
 * Spring Data MongoDB repository for the Voltras Fare entity.
 */
@Repository
public interface VoltrasFareRepository extends MongoRepository<VoltrasFare, String> {
	
}
