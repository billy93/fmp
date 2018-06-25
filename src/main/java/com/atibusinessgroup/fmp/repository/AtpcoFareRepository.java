package com.atibusinessgroup.fmp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.Agent;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoCcfParcity;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;

/**
 * Spring Data MongoDB repository for the AtpcoCcfParcity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AtpcoFareRepository extends MongoRepository<AtpcoFare, String> {
//	ori,dest,fareclass,owrt,footnote,routingnumber
	Optional<AtpcoFare> findOneByCarrierCodeAndTariffNoAndOriginCityAndDestinationCityAndFareOriginCurrencyCodeAndFareClassCodeAndOwrtAndFootnoteAndRoutingNoAndRuleNo(String carrier, String tarno, String origin, String dest, String currency, String fareClass, String owrt, String footnote, String rtgno, String ruleno);
	Optional<AtpcoFare> findOneByCarrierCodeAndTariffNo(String carrier, String tarno);
	Optional<AtpcoFare> findOneByCarrierCodeAndTariffNoAndOriginCityAndDestinationCity(String carrier, String tarno, String origin, String dest);
}
