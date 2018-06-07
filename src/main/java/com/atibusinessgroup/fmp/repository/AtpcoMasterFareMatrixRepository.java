package com.atibusinessgroup.fmp.repository;

import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterFareMatrix;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the AtpcoMasterFareMatrix entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AtpcoMasterFareMatrixRepository extends MongoRepository<AtpcoMasterFareMatrix, String> {

}
