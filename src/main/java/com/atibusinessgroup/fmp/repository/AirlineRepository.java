package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.Airline;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Airline entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AirlineRepository extends MongoRepository<Airline, String> {

}
