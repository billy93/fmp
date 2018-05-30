 	package com.atibusinessgroup.fmp.repository.custom.impl;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryParam;
import com.atibusinessgroup.fmp.domain.dto.RuleQueryParam;
import com.atibusinessgroup.fmp.repository.AtpcoRecord2Repository;
import com.atibusinessgroup.fmp.resository.custom.AtpcoRuleQueryCustomRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class AtpcoRuleQueryCustomRepositoryImpl implements AtpcoRuleQueryCustomRepository {	
	
	@Autowired
    MongoTemplate mongoTemplate;

	@Override
	public Page<AtpcoRecord2> findByRuleQueryParam(RuleQueryParam param, Pageable pageable) {
		return null;
	}

}
