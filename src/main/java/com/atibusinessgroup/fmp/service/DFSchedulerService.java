package com.atibusinessgroup.fmp.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atibusinessgroup.fmp.domain.DataFeedScheduler;

public interface DFSchedulerService {

	DataFeedScheduler save(DataFeedScheduler dataFeedScheduler);
	Page<DataFeedScheduler> findAll(Pageable pageable);
	DataFeedScheduler findOne(String id);
	void delete(String id);
	DataFeedScheduler findDfScheduler(String type);
	
}
