package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.YQYRQuery;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the YQYRQuery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface YQYRQueryRepository extends MongoRepository<YQYRQuery, String> {

}
