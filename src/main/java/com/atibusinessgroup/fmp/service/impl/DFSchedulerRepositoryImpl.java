package com.atibusinessgroup.fmp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.DataFeedScheduler;
import com.atibusinessgroup.fmp.repository.DFSchedulerRepository;
import com.atibusinessgroup.fmp.service.DFSchedulerService;

@Service
public class DFSchedulerRepositoryImpl implements DFSchedulerService{
	
	private final Logger log = LoggerFactory.getLogger(DFSchedulerRepositoryImpl.class);
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	private final DFSchedulerRepository dfSchedulerRepository;
	
	public DFSchedulerRepositoryImpl(DFSchedulerRepository dfSchedulerRepository) {
        this.dfSchedulerRepository = dfSchedulerRepository;
    }

	@Override
	public DataFeedScheduler save(DataFeedScheduler dataFeedScheduler) {
		log.debug("Request to save DataFeedScheduler : {}", dataFeedScheduler);
        return dfSchedulerRepository.save(dataFeedScheduler);
	}

	@Override
	public Page<DataFeedScheduler> findAll(Pageable pageable) {
		log.debug("Request to get all DataFeedSchedulers");
        return dfSchedulerRepository.findAll(pageable);
	}

	@Override
	public DataFeedScheduler findOne(String id) {
		log.debug("Request to get DataFeedScheduler : {}", id);
        return dfSchedulerRepository.findOne(id);
	}

	@Override
	public void delete(String id) {
		log.debug("Request to delete DataFeedScheduler : {}", id);
		dfSchedulerRepository.delete(id);
	}

	@Override
	public DataFeedScheduler findDfScheduler(String type) {
		log.debug("Request to get DataFeedScheduler : {}", type);
		Query query = new Query();
		query.addCriteria(Criteria.where("type").is(type));
		
		return mongoTemplate.findOne(query, DataFeedScheduler.class);
	}

}
