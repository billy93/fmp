package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.MatchScoreWeight;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the MatchScoreWeight entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MatchScoreWeightRepository extends MongoRepository<MatchScoreWeight, String> {

}
