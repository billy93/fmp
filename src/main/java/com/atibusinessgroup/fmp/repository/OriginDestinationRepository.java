package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.DataFeedOriginDestination;

@Repository
public interface OriginDestinationRepository extends MongoRepository<DataFeedOriginDestination, String> {

}
