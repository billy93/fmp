package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.CcfCarrier;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the CcfCarrier entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CcfCarrierRepository extends MongoRepository<CcfCarrier, String> {

}
