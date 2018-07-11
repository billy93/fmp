package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.AtpcoMasterTariff;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the TariffNumber entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AtpcoMasterTariffRepository extends MongoRepository<AtpcoMasterTariff, String> {

	AtpcoMasterTariff findOneByTarNo(String valueOf);

	AtpcoMasterTariff findOneByTarCd(String valueOf);

	AtpcoMasterTariff findOneByGlobal(String valueOf);

	AtpcoMasterTariff findOneByTarNoAndType(String tariffNo, String type);

}
