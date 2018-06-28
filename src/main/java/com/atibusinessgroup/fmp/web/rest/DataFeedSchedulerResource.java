package com.atibusinessgroup.fmp.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.dto.DataFeedSchedulerParam;
import com.atibusinessgroup.fmp.repository.custom.DataFeedSchedulerCustomRepository;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class DataFeedSchedulerResource {

	private final Logger log = LoggerFactory.getLogger(DataFeedFareBasisGroupMappingResource.class);

	private static final String ENTITY_NAME = "dataFeedScheduler";

	private final DataFeedSchedulerCustomRepository dataFeedSchedulerCustomRepository;

	public DataFeedSchedulerResource(DataFeedSchedulerCustomRepository dataFeedSchedulerCustomRepository) {
		this.dataFeedSchedulerCustomRepository = dataFeedSchedulerCustomRepository;
	}

	@PostMapping("/data-feed-scheduler")
	@Timed
	public ResponseEntity<Void> setDFScheduler(@RequestBody DataFeedSchedulerParam param) {
		
		dataFeedSchedulerCustomRepository.setDFScheduler(param);

		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, param.toString())).build();

	}
}
