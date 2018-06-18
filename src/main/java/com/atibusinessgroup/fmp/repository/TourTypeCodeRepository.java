package com.atibusinessgroup.fmp.repository;

import java.io.Serializable;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterSurchargeCode;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoMasterTourTypeCode;

/**
 * Spring Data MongoDB repository for the TourTypeCode entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TourTypeCodeRepository extends MongoRepository<AtpcoMasterTourTypeCode, String> {

	AtpcoMasterTourTypeCode findOneByCode(String code);

}
