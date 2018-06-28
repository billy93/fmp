package com.atibusinessgroup.fmp.repository.custom;

import org.quartz.SchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.DataFeedScheduler;
import com.atibusinessgroup.fmp.domain.dto.DataFeedSchedulerParam;

@Service
public class DataFeedSchedulerCustomRepository {
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	private SchedulerFactory schedFact;
	
	public void setDFScheduler(DataFeedSchedulerParam param) {
		
	}

}
