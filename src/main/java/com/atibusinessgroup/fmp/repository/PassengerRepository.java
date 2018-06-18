package com.atibusinessgroup.fmp.repository;

import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterPassengerTypeCode;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Passenger entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PassengerRepository extends MongoRepository<AtpcoMasterPassengerTypeCode, String> {

	AtpcoMasterPassengerTypeCode findOneByCode(String ptc);

}
