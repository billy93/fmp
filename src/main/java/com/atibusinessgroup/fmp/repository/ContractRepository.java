package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.atibusinessgroup.fmp.domain.Contract;


/**
 * Spring Data MongoDB repository for the Contract entity.
 */
public interface ContractRepository extends MongoRepository<Contract,String>{

	Contract findOneById(String idContract);

	List<Contract> findAllByContractType(String string);
	
}
