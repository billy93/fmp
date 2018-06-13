package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.DataFeedPublishedFareRates;

@Repository
public interface PublishedFareCommissionRatesRepository extends MongoRepository<DataFeedPublishedFareRates, String> {

}
