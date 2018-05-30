package com.atibusinessgroup.fmp.resository.custom;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.dto.RuleQueryParam;

public interface AtpcoRuleQueryCustomRepository {
	
	Page<AtpcoRecord2> findByRuleQueryParam(RuleQueryParam param, Pageable pageable);
}
