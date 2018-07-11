package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.TariffNumber;

/**
 * Spring Data MongoDB repository for the TariffNumber entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TariffNumberRepository extends MongoRepository<TariffNumber, String> {

	TariffNumber findOneByTarNo(String valueOf);

	TariffNumber findOneByTarCd(String valueOf);

	TariffNumber findOneByGlobal(String valueOf);

	TariffNumber findOneByTarNoAndType(String tariffNo, String type);

}
