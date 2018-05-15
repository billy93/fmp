package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.atibusinessgroup.fmp.domain.ContractFMP;


/**
 * Spring Data MongoDB repository for the Contract entity.
 */
public interface ContractFMPRepository extends MongoRepository<ContractFMP,String>{

	ContractFMP findOneById(String idContract);

	List<ContractFMP> findAllByContractType(String string);
	
}
