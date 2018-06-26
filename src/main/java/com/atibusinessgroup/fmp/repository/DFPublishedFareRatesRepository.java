package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.DFPublishedFareRates;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the DFPublishedFareRates entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DFPublishedFareRatesRepository extends MongoRepository<DFPublishedFareRates, String> {

}