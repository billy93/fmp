package com.atibusinessgroup.fmp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;

import afu.org.checkerframework.checker.units.qual.m;

@Service
public class ProsDatafeedService {
	
	@Autowired
    MongoTemplate mongoTemplate;

	public ProsDatafeedService(MongoTemplate mongoTemplate) {
		this.mongoTemplate = mongoTemplate;
	}


	public String getFaresProsDatafeed() {
		System.out.println(mongoTemplate.getDb());
		return mongoTemplate.getDb().toString();
	}
	

}
