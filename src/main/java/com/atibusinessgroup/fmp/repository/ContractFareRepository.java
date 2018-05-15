package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.atibusinessgroup.fmp.domain.ContractFare;


/**
 * Spring Data MongoDB repository for the Contract entity.
 */
public interface ContractFareRepository extends MongoRepository<ContractFare,String> {

}
