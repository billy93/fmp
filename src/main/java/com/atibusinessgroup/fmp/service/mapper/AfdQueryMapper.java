package com.atibusinessgroup.fmp.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.dto.AfdQuery;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFareWithRecord1;

@Service
public class AfdQueryMapper {
	
	public List<AfdQuery> convertAtpcoFare(List<AtpcoFareWithRecord1> a1fares) {
		List<AfdQuery> result = new ArrayList<>();
		
		for (AtpcoFareWithRecord1 a1fare:a1fares) {
			AtpcoFare afare = a1fare.getAtpcoFare();
			
			AfdQuery afd = new AfdQuery();
			afd.setAtpcoFareId(afare.getId());
			afd.setSource(afare.getSource());
			afd.setSc("S");
			afd.setTariffNo(afare.getTariffNo());
			afd.setCarrierCode(afare.getCarrierCode());
			afd.setOriginCity(afare.getOriginCity());
			afd.setOriginCountry(afare.getOriginCountry());
			afd.setDestinationCity(afare.getDestinationCity());
			afd.setDestinationCountry(afare.getDestinationCountry());
			afd.setFareClassCode(afare.getFareClassCode());
			afd.setOwrt(afare.getOwrt());
			afd.setFootnote(afare.getFootnote());
			afd.setRoutingNo(afare.getRoutingNo());
			afd.setRuleNo(afare.getRuleNo());
			afd.setMaximumPermittedMileage(afare.getMaximumPermittedMileage());
			afd.setCurrencyCode(afare.getFareOriginCurrencyCode());
			afd.setBaseAmount(afare.getFareOriginAmount().bigDecimalValue().doubleValue());
			afd.setEffectiveDate(afare.getTariffEffectiveDateObject());
			afd.setDiscontinueDate(afare.getDiscontinueDateObject());
			afd.setGlobalIndicator(afare.getGlobalIndicator());
			afd.setSaleStartDate(afare.getFirstSaleDateObject());
			afd.setSaleEndDate(afare.getLastSaleDateObject());
			afd.setFareType(afare.getFareType());
			
			result.add(afd);
		}
		
		return result;
	}
}
