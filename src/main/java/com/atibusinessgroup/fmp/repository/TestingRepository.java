package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.Testing;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Testing entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TestingRepository extends MongoRepository<Testing, String> {

}
