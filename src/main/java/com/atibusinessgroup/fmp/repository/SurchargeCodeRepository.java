package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.SurchargeCode;

/**
 * Spring Data MongoDB repository for the SurchargeCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SurchargeCodeRepository extends MongoRepository<SurchargeCode, String> {

	SurchargeCode findOneByCode(String trim);

}
