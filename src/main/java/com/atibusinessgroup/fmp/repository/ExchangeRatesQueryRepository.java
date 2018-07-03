package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.ExchangeRatesQuery;

/**
 * Spring Data MongoDB repository for the ExchangeRatesQuery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ExchangeRatesQueryRepository extends MongoRepository<ExchangeRatesQuery, String>{

}
