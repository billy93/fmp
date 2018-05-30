 	package com.atibusinessgroup.fmp.resository.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.dto.RuleQueryParam;

@Service
public class AtpcoRuleQueryCustomRepository {	
	
	@Autowired
    MongoTemplate mongoTemplate;

	public Page<AtpcoRecord2> findByRuleQueryParam(RuleQueryParam param, Pageable pageable) {
		return null;
	}

}
