package com.atibusinessgroup.fmp.web.rest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
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

import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.dto.AfdQuery;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryParam;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryWrapper;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteQueryGroup;
import com.atibusinessgroup.fmp.domain.dto.Category;
import com.atibusinessgroup.fmp.repository.AtpcoRecord0Repository;
import com.atibusinessgroup.fmp.repository.custom.AtpcoFareCustomRepository;
import com.atibusinessgroup.fmp.repository.custom.CompetitorMonitoringCustomRepository;
import com.atibusinessgroup.fmp.service.AtpcoRecordService;
import com.atibusinessgroup.fmp.service.mapper.AfdQueryMapper;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class CompetitorMonitoringResource {

	private final LinkedHashMap<String, String> fareCategories = new LinkedHashMap<>();
	private final LinkedHashMap<String, String> fareFootnotes = new LinkedHashMap<>();
	private final LinkedHashMap<String, String> categories = new LinkedHashMap<>();
	private final LinkedHashMap<String, String> footnotes = new LinkedHashMap<>();
	private final String[] ruleCategories = new String[] {"003", "005", "006", "007", "014", "015"};
	
//	private final Logger log = LoggerFactory.getLogger(CompetitorMonitoringResource.class);

	private final AtpcoRecord0Repository atpcoRecord0Repository;
	
	private final AtpcoFareCustomRepository atpcoFareCustomRepository;
	
	private final CompetitorMonitoringCustomRepository competitorMonitoringCustomRepository;
	
	private final AfdQueryMapper afdQueryMapper;
	
	private final AtpcoRecordService atpcoRecordService;
	
	private final AfdQueryResource afdQueryResource;
	
    public CompetitorMonitoringResource(AtpcoRecord0Repository atpcoRecord0Repository, AtpcoFareCustomRepository atpcoFareCustomRepository, AfdQueryMapper afdQueryMapper, AtpcoRecordService atpcoRecordService, CompetitorMonitoringCustomRepository competitorMonitoringCustomRepository, AfdQueryResource afdQueryResource) {
    	this.atpcoRecord0Repository = atpcoRecord0Repository;
    	this.atpcoFareCustomRepository = atpcoFareCustomRepository;
    	this.afdQueryMapper = afdQueryMapper;
    	this.atpcoRecordService = atpcoRecordService;
    	this.competitorMonitoringCustomRepository = competitorMonitoringCustomRepository;
    	this.afdQueryResource = afdQueryResource;
    	
    }
    
    /**
     * POST  /competitor-monitoring : get all the afd queries.
     *
     * @param query params, pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in body
     */
    @PostMapping("/competitor-monitoring")
    @Timed
    public ResponseEntity<List<AfdQuery>> getAllQueries(@RequestBody AfdQueryParam param) {
    	
    	Pageable pageable = new PageRequest(param.getPage(), param.getSize());
    	Page<AfdQuery> page = competitorMonitoringCustomRepository.getCompetitorQueries(param, pageable);
    	HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/footnote-queries");
    	
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
//    /**
//     * POST  /competitor-monitoring : get all the competitor queries.
//     *
//     * @param query params, pageable the pagination information
//     * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in body
//     */
//    @PostMapping("/competitor-monitoring")
//    @Timed
//    public ResponseEntity<String> getCompetitorQueries(@RequestBody AfdQueryParam param) {
//    	String result = "";
//    	
//    	return new ResponseEntity<>(result, HttpStatus.OK);
//    }
    
    
    /**
     * GET  /competitor-monitoring/rules : get afd query rules.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of rules in body
     */
    @GetMapping("/competitor-monitoring/rules")
    @Timed
    public ResponseEntity<List<Category>> getAfdQueryRules(AfdQuery afdQuery) {
        return afdQueryResource.getAfdQueryRules(afdQuery);
    }
    
    /**
     * GET  /competitor-monitoring/chart : get chart data.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of rules in body
     */
    @GetMapping("/competitor-monitoring/chart")
    @Timed
    public ResponseEntity<String> getChartData(AfdQueryParam param) {
    	
    	JSONArray listObj = new JSONArray();
//    	for (AfdQuery afd : afdQueryResource.getAllAfdQueries(param).getBody()) {
//    		JSONObject json = new JSONObject();
//    		try {
//    			json.put("carrierCode", afd.getCarrierCode());
//    			json.put("cabin", afd.getCabin());
//    			json.put("bookingClass", afd.getBookingClass());
//    			json.put("amount", afd.getBaseAmount());
//    			listObj.put(json);
//    		}catch (Exception e) {
//				// TODO: handle exception
//			}
//    	}
    	
    	String result = listObj.toString();
    	return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
