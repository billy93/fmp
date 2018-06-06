package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.atibusinessgroup.fmp.domain.Counter;


/**
 * Spring Data MongoDB repository for the Counter entity.
 */
public interface CounterRepository extends MongoRepository<Counter,String> {

	Counter findOneByIdAndYear(String string, String formattedDate);
	
}
