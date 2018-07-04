package com.atibusinessgroup.fmp.service.mapper;

import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteQueryGroup;
import com.atibusinessgroup.fmp.domain.dto.FootnoteQuery;

@Service
public class FootnoteQueryMapper {
	
	
	public FootnoteQuery convertAndGroupFootnote(AtpcoFootnoteQueryGroup ftnt, String catNo) {

		FootnoteQuery result = new FootnoteQuery();
		result.setCxr(ftnt.getCxr());
		result.setFtnt(ftnt.getFtnt());
		result.setTarNo(ftnt.getTarNo());
		result.setTarCd(ftnt.getTarCd());
		result.setFareAddon("Fare");
		result.setFareAddonCount("0");
		result.setCatNo(catNo);
		
		return result;
	}
}
