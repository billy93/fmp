package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.DFOriginDestination;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the DFOriginDestination entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DFOriginDestinationRepository extends MongoRepository<DFOriginDestination, String> {

}
