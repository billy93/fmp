package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.AirportMapping;
import com.atibusinessgroup.fmp.domain.DataFeedAirportMapping;
import com.atibusinessgroup.fmp.domain.DataFeedFareBasisGroupMapping;

@SuppressWarnings("unused")
@Repository
public interface CityCodestoAirportCodesRepository extends MongoRepository<DataFeedAirportMapping, String> {

}
