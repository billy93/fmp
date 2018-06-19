package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.TariffNumberAddOn;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the TariffNumberAddOn entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TariffNumberAddOnRepository extends MongoRepository<TariffNumberAddOn, String> {

}
