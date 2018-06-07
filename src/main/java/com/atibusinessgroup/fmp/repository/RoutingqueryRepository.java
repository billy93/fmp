package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.RoutingQuery;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the RoutingQuery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RoutingqueryRepository extends MongoRepository<RoutingQuery, String> {

}
