package com.atibusinessgroup.fmp.resository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atibusinessgroup.fmp.domain.dto.AtpcoFareWithRecord1;

public interface AtpcoFareCustomRepository {
	
	Page<AtpcoFareWithRecord1> findAtpcoFareWithRecord1(Pageable pageable);
	
}
