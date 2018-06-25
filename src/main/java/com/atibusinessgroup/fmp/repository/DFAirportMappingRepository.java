package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.DFAirportMapping;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the DFAirportMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DFAirportMappingRepository extends MongoRepository<DFAirportMapping, String> {

}
