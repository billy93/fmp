package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.Currency;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Currency entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CurrencyRepository extends MongoRepository<Currency, String> {

}
