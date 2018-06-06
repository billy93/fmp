package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.AtpcoMasterFareMatrix;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the AtpcoMasterFareMatrix entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AtpcoMasterFareMatrixRepository extends MongoRepository<AtpcoMasterFareMatrix, String> {

}
