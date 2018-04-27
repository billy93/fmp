package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.SystemParameter;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the SystemParameter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SystemParameterRepository extends MongoRepository<SystemParameter, String> {

	SystemParameter findOneByName(String name);

}
