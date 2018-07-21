package com.atibusinessgroup.fmp.service.mapper;

import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoTaxX1;
import com.atibusinessgroup.fmp.domain.dto.Tax;

@Service
public class TaxQueryMapper {
	
	public Tax convertTax(AtpcoTaxX1 atax) {
		Tax result = new Tax();
		
		result.setId(atax.get_id());
		result.setCarrierCode(atax.getTax_carrier());
		
		return result;
	}
}
