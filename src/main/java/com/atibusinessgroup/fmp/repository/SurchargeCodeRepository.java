package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterSurchargeCode;

/**
 * Spring Data MongoDB repository for the SurchargeCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SurchargeCodeRepository extends MongoRepository<AtpcoMasterSurchargeCode, String> {

	AtpcoMasterSurchargeCode findOneByCode(String trim);

}
