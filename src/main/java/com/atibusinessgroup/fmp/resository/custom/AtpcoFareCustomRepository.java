package com.atibusinessgroup.fmp.resository.custom;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atibusinessgroup.fmp.domain.dto.AfdQueryParam;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFareWithRecord1;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByCatNo;

public interface AtpcoFareCustomRepository {
	
	Page<AtpcoFareWithRecord1> findAtpcoFareWithRecord1(AfdQueryParam param, Pageable pageable);
	
	List<AtpcoRecord2GroupByCatNo> findAtpcoRecord2ByRecordId(String recordId);
}
