package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.AtpcoMasterFareType;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the AtpcoMasterFareType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AtpcoMasterFareTypeRepository extends MongoRepository<AtpcoMasterFareType, String> {

}
