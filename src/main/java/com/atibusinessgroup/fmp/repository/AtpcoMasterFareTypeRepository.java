package com.atibusinessgroup.fmp.repository;

import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterFareType;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the AtpcoMasterFareType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AtpcoMasterFareTypeRepository extends MongoRepository<AtpcoMasterFareType, String> {

}
