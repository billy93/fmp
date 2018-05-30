package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.State;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the State entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StateRepository extends MongoRepository<State, String> {

}
