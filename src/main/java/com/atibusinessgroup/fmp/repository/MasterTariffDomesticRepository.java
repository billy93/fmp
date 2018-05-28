package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.MasterTariffDomestic;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the MasterTariff entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MasterTariffDomesticRepository extends MongoRepository<MasterTariffDomestic, String> {

}
