package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.AtpcoMasterTariff;

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

	List<AtpcoMasterTariff> findAllByType(String type);
}
