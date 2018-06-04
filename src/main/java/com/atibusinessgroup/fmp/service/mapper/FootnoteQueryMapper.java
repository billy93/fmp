package com.atibusinessgroup.fmp.service.mapper;

import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoFootnoteRecord2;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteRecord2GroupByFtntCxrTarNo;
import com.atibusinessgroup.fmp.domain.dto.FootnoteQuery;

@Service
public class FootnoteQueryMapper {
	
	
	public FootnoteQuery convertAndGroupFootnote(AtpcoFootnoteRecord2GroupByFtntCxrTarNo ftntRec2) {

		FootnoteQuery result = new FootnoteQuery();
		result.setCxr(ftntRec2.getCarrierCode());
		result.setFtnt(ftntRec2.getFtnt());
		result.setTarNo(ftntRec2.getTariffNumber());
		result.setTarCd("");
		result.setFareAddon("Fare");
		result.setFareAddonCount("0");
		
		return result;
	}
}
