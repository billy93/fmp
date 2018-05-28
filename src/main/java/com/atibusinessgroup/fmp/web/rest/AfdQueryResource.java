package com.atibusinessgroup.fmp.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord1;
import com.atibusinessgroup.fmp.domain.dto.AfdQuery;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryParam;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFareWithRecord1;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.Categories;
import com.atibusinessgroup.fmp.resository.custom.AtpcoFareCustomRepository;
import com.atibusinessgroup.fmp.service.AtpcoRecordService;
import com.atibusinessgroup.fmp.service.mapper.AfdQueryMapper;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class AfdQueryResource {

	private final Logger log = LoggerFactory.getLogger(AfdQueryResource.class);

	private final AtpcoFareCustomRepository atpcoFareCustomRepository;
	
	private final AfdQueryMapper afdQueryMapper;
	
	private final AtpcoRecordService atpcoRecordService;
	
    public AfdQueryResource(AtpcoFareCustomRepository atpcoFareCustomRepository, AfdQueryMapper afdQueryMapper, AtpcoRecordService atpcoRecordService) {
    	this.atpcoFareCustomRepository = atpcoFareCustomRepository;
    	this.afdQueryMapper = afdQueryMapper;
    	this.atpcoRecordService = atpcoRecordService;
    }
    
    /**
     * GET  /afd-queries : get all the afd queries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in body
     */
    @GetMapping("/afd-queries")
    @Timed
    public ResponseEntity<List<AfdQuery>> getAllAfdQueries(AfdQueryParam queryParam, Pageable pageable) {
        log.debug("REST request to get a page of AfdQueries: {}", queryParam);
        
        //ATPCO
        Page<AtpcoFareWithRecord1> page = atpcoFareCustomRepository.findAtpcoFareWithRecord1(pageable);
        List<AtpcoFareWithRecord1> a1fares = page.getContent();
        
        List<AfdQuery> result = new ArrayList<>();
        
        for (AtpcoFareWithRecord1 a1fare:a1fares) {
        	AtpcoFare afare = a1fare.getAtpcoFare();
        	AtpcoRecord1 validRecord1 = null;
        	
        	List<AtpcoRecord1> record1s = a1fare.getAtpcoRecord1();
        	
        	for (AtpcoRecord1 record1:record1s) {
        		boolean matched = atpcoRecordService.compareMatchingFareAndRecord("C", afare.getOriginCity(), "C", afare.getDestinationCity(), afare.getOwrt(), afare.getRoutingNo(), afare.getFootnote(), afare.getTariffEffectiveDateObject(), afare.getDiscontinueDateObject(),
        				record1.getGeoType1(), record1.getGeoLoc1(), record1.getGeoType2(), record1.getGeoLoc2(), record1.getOwrt(), record1.getRoutingNo(), record1.getFootnote(), record1.getEffectiveDateObject(), record1.getDiscontinueDateObject());
        	
        		if (matched) {
        			validRecord1 = record1;
        			break;
        		}
        	}
        	
        	result.add(afdQueryMapper.convertAtpcoFare(afare, validRecord1));
        }
        
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/afd-queries");
        
        return new ResponseEntity<>(result, headers, HttpStatus.OK);
    }
    
    /**
     * GET  /afd-queries/rules : get afd query rules.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of rules in body
     */
    @GetMapping("/afd-queries/rules")
    @Timed
    public ResponseEntity<Categories> getAfdQueryRules(AfdQuery afdQuery) {
        log.debug("REST request to get AfdQueries rules: {}", afdQuery);
        
        Categories result = new Categories();
        
        String recordId = afdQuery.getTariffNo() + afdQuery.getCarrierCode() + afdQuery.getRuleNo() + "";
        
        List<AtpcoRecord2GroupByCatNo> arecords2 = atpcoFareCustomRepository.findAtpcoRecord2ByRecordId(recordId);
        
        for (AtpcoRecord2GroupByCatNo arecord2:arecords2) {
        	
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
