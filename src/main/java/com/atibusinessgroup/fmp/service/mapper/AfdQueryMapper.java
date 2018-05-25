package com.atibusinessgroup.fmp.service.mapper;

import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord1;
import com.atibusinessgroup.fmp.domain.dto.AfdQuery;

@Service
public class AfdQueryMapper {
	
	public AfdQuery convertAtpcoFare(AtpcoFare afare, AtpcoRecord1 record1) {

		AfdQuery result = new AfdQuery();
		result.setAtpcoFareId(afare.getId());
		result.setSource(afare.getSource());
		result.setSc("S");
		result.setTariffNo(afare.getTariffNo());
		result.setCarrierCode(afare.getCarrierCode());
		result.setOriginCity(afare.getOriginCity());
		result.setOriginCountry(afare.getOriginCountry());
		result.setDestinationCity(afare.getDestinationCity());
		result.setDestinationCountry(afare.getDestinationCountry());
		result.setFareClassCode(afare.getFareClassCode());
		result.setOwrt(afare.getOwrt());
		result.setFootnote(afare.getFootnote());
		result.setRoutingNo(afare.getRoutingNo());
		result.setRuleNo(afare.getRuleNo());
		result.setMaximumPermittedMileage(afare.getMaximumPermittedMileage());
		result.setCurrencyCode(afare.getFareOriginCurrencyCode());
		result.setBaseAmount(afare.getFareOriginAmount().bigDecimalValue().doubleValue());
		result.setEffectiveDate(afare.getTariffEffectiveDateObject());
		result.setDiscontinueDate(afare.getDiscontinueDateObject());
		result.setGlobalIndicator(afare.getGlobalIndicator());
		result.setSaleStartDate(afare.getFirstSaleDateObject());
		result.setSaleEndDate(afare.getLastSaleDateObject());
		result.setFareType(afare.getFareType());
		
		return result;
	}
}
