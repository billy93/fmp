package com.atibusinessgroup.fmp.service.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.AtpcoFare;
import com.atibusinessgroup.fmp.domain.dto.AfdQuery;

@Service
public class AfdQueryMapper {
	
	public List<AfdQuery> convertAtpcoFare(List<AtpcoFare> afares) {
		List<AfdQuery> result = new ArrayList<>();
		
		for (AtpcoFare afare:afares) {
			AfdQuery afd = new AfdQuery();
			afd.setSource(afare.getSource());
			afd.setSc("S");
			afd.setTariffNo(afare.getTariffNo());
			afd.setCarrierCode(afare.getCarrierCode());
			afd.setOriginCity(afare.getOriginCity());
			afd.setOriginCountry(afare.getOriginCountry());
			afd.setDestinationCity(afare.getDestinationCity());
			afd.setDestinationCountry(afare.getDestinationCountry());
			afd.setFareClassCode(afare.getFareClassCode());
			
			
			
			
			result.add(afd);
		}
		
		return result;
	}
}
