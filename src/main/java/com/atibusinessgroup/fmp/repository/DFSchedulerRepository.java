package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.City;
import com.atibusinessgroup.fmp.domain.DataFeedScheduler;

@SuppressWarnings("unused")
@Repository
public interface DFSchedulerRepository extends MongoRepository<DataFeedScheduler, String> {

}
