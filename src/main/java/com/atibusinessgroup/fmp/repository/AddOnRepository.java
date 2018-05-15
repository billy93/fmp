package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.atibusinessgroup.fmp.domain.AddOn;


/**
 * Spring Data MongoDB repository for the AddOn entity.
 */
public interface AddOnRepository extends MongoRepository<AddOn,String> {
	
}
