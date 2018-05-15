package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.Agencies;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Agencies entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AgenciesRepository extends MongoRepository<Agencies, String> {

}
