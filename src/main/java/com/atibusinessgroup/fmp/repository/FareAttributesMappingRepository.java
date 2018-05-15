package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.FareAttributesMapping;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the FareAttributesMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FareAttributesMappingRepository extends MongoRepository<FareAttributesMapping, String> {

}
