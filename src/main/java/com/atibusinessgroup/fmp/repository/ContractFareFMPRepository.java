package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.atibusinessgroup.fmp.domain.ContractFareFMP;


/**
 * Spring Data MongoDB repository for the Contract entity.
 */
public interface ContractFareFMPRepository extends MongoRepository<ContractFareFMP,String> {

	List<ContractFareFMP> findAllByIdContract(String id);

}
