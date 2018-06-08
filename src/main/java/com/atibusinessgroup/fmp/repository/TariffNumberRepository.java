package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.TariffNumber;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the TariffNumber entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TariffNumberRepository extends MongoRepository<TariffNumber, String> {

	TariffNumber findOneByTarNo(String valueOf);

	TariffNumber findOneByTarCd(String valueOf);

	TariffNumber findOneByGlobal(String valueOf);

}
