package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.RMDataFeeds;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the RMDataFeeds entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RMDataFeedsRepository extends MongoRepository<RMDataFeeds, String> {

}
