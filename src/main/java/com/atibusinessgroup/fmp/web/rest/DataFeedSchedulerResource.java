package com.atibusinessgroup.fmp.web.rest;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.DataFeedScheduler;
import com.atibusinessgroup.fmp.domain.dto.DataFeedSchedulerParam;
import com.atibusinessgroup.fmp.service.DFSchedulerService;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class DataFeedSchedulerResource {

	private final Logger log = LoggerFactory.getLogger(DataFeedFareBasisGroupMappingResource.class);

	private static final String ENTITY_NAME = "data feed scheduler";
	
	private final DFSchedulerService dfSchedulerService;

    public DataFeedSchedulerResource(DFSchedulerService dfSchedulerService) {
        this.dfSchedulerService = dfSchedulerService;
    }
    
    @GetMapping("/data-feed-scheduler")
    @Timed
    public ResponseEntity<Map<String, DataFeedScheduler>> getAllDFScheduler() {
        log.debug("REST request to get all Data Feed Scheduler");
        Map<String, DataFeedScheduler> dfSchedulers = new HashMap<>();
        DataFeedScheduler automaticDf = dfSchedulerService.findDfScheduler("automatic");
        DataFeedScheduler manualDf = dfSchedulerService.findDfScheduler("manual");
        dfSchedulers.put("automatic", automaticDf);
        dfSchedulers.put("manual", manualDf);
        
        return new ResponseEntity<>(dfSchedulers, null, HttpStatus.OK);
    }

	@PutMapping("/data-feed-scheduler")
	@Timed
	public ResponseEntity<DataFeedScheduler> setDFScheduler(@RequestBody DataFeedSchedulerParam param) {
		log.debug("REST request to update Data Feed Scheduler : {}", param);
		
		DataFeedScheduler df = dfSchedulerService.findDfScheduler(param.getType());
		df.setStartDate(ZonedDateTime.ofInstant(param.getStartDate().toInstant(), ZoneId.systemDefault()));
		if(param.getType().equals("automatic")) {
			df.setStartTime(param.getTime());
		} else {
			df.setDelayDays(param.getDelayDays());
			df.setDelayHours(param.getDelayHours());
			df.setDelayMinutes(param.getDelayMinutes());
			df.setEndDate(ZonedDateTime.ofInstant(param.getEndDate().toInstant(), ZoneId.systemDefault()));
			df.setAtpcoFares(param.isAtpcoFares());
			df.setMarketFares(param.isMarketFares());
		}
		dfSchedulerService.save(df);

		return ResponseEntity.ok()
	            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, param.getType()))
	            .body(df);
	}
}
