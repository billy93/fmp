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

import com.atibusinessgroup.fmp.domain.AtpcoFare;
import com.atibusinessgroup.fmp.domain.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.dto.AfdQuery;
import com.atibusinessgroup.fmp.repository.AtpcoFareRepository;
import com.atibusinessgroup.fmp.repository.AtpcoRecord2Repository;
import com.atibusinessgroup.fmp.service.mapper.AfdQueryMapper;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class AfdQueryResource {

	private final Logger log = LoggerFactory.getLogger(AfdQueryResource.class);

	private final AtpcoFareRepository atpcoFareRepository;
	
	private final AtpcoRecord2Repository atpcoRecord2Repository;
	
	private final AfdQueryMapper afdQueryMapper;
	
    public AfdQueryResource(AtpcoFareRepository atpcoFareRepository, AfdQueryMapper afdQueryMapper, AtpcoRecord2Repository atpcoRecord2Repository) {
    	this.atpcoFareRepository = atpcoFareRepository;
    	this.afdQueryMapper = afdQueryMapper;
    	this.atpcoRecord2Repository = atpcoRecord2Repository;
    }
    
    /**
     * GET  /afd-queries : get all the afd queries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in body
     */
    @GetMapping("/afd-queries")
    @Timed
    public ResponseEntity<List<AfdQuery>> getAllAfdQueries(Pageable pageable) {
        log.debug("REST request to get a page of AfdQueries");
        
        Page<AtpcoFare> page = atpcoFareRepository.findAll(pageable);
        
        List<AfdQuery> result = new ArrayList<>();
        result.addAll(afdQueryMapper.convertAtpcoFare(page.getContent()));
        
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
    public ResponseEntity<List<AtpcoRecord2>> getAfdQueryRules(AfdQuery afdQuery) {
        log.debug("REST request to get AfdQueries rules: {}", afdQuery);
        
//        List<AtpcoRecord2> result = atpcoRecord2Repository.findAllBy;
        List<AtpcoRecord2> result = new ArrayList<>();
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
