package com.atibusinessgroup.fmp.web.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.CcfCarrier;
import com.atibusinessgroup.fmp.domain.dto.InternetQuery;
import com.atibusinessgroup.fmp.domain.dto.InternetQueryParam;
import com.atibusinessgroup.fmp.domain.dto.MasterWebsite;
import com.atibusinessgroup.fmp.service.InternetQueryService;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class InternetQueryResource {

	private final Logger log = LoggerFactory.getLogger(InternetQueryResource.class);
	private final InternetQueryService internetQueryService;
	
	public InternetQueryResource(InternetQueryService internetQueryService) {
		this.internetQueryService = internetQueryService;
	}
	
	@PostMapping("/internet-query")
    @Timed
    public ResponseEntity<List<InternetQuery>> getAllInternetQueries(@RequestBody InternetQueryParam param) {
		Pageable pageable = new PageRequest(param.getPage(), param.getSize());
		Page<InternetQuery> result = null;
		
		if(param.getSummarizeType() == 0) {
			result = internetQueryService.getSummarizeCaptDateQueries(param, pageable);
		} else if(param.getSummarizeType() == 1) {
			result = internetQueryService.getSummarizeDeptDateQueries(param, pageable);
		} else if(param.getSummarizeType() == 2) {
			result = internetQueryService.getDontSummarizeQueries(param, pageable);
		}
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(result, "/api/internet-queries");
		
		return new ResponseEntity<>(result.getContent(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/master-website/getAll")
    @Timed
    public ResponseEntity<List<MasterWebsite>> getAllCarriers() {
        List<MasterWebsite> result = internetQueryService.findAllMasterWebsite();
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
}
