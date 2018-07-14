package com.atibusinessgroup.fmp.service.mapper;

import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoYqyr;
import com.atibusinessgroup.fmp.domain.dto.Yqyr;

@Service
public class YqyrQueryMapper {
	
	public Yqyr convertYqyr(AtpcoYqyr ayqyr) {
		Yqyr result = new Yqyr();
		
		result.setCarrierCode(ayqyr.getCxr_code());
		
		return result;
	}
}
