package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.BusinessArea;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the BusinessArea entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BusinessAreaRepository extends MongoRepository<BusinessArea, String> {

	BusinessArea findOneByName(String string);

}
