package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.Passenger;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Passenger entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PassengerRepository extends MongoRepository<Passenger, String> {

}
