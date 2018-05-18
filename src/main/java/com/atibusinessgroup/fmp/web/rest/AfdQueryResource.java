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
import com.atibusinessgroup.fmp.domain.dto.AfdQuery;
import com.atibusinessgroup.fmp.repository.AtpcoFareRepository;
import com.atibusinessgroup.fmp.service.mapper.AfdQueryMapper;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class AfdQueryResource {

	private final Logger log = LoggerFactory.getLogger(AfdQueryResource.class);

	private final AtpcoFareRepository atpcoFareRepository;
	
	private final AfdQueryMapper afdQueryMapper;
	
    public AfdQueryResource(AtpcoFareRepository atpcoFareRepository, AfdQueryMapper afdQueryMapper) {
    	this.atpcoFareRepository = atpcoFareRepository;
    	this.afdQueryMapper = afdQueryMapper;
    }
    
    /**
     * GET  /afd-queries : get all the afd queries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in body
     */
    @GetMapping("/afd-queries")
    @Timed
    public ResponseEntity<List<AtpcoFare>> getAllAfdQueries(Pageable pageable) {
        log.debug("REST request to get a page of AfdQueries");
        
        Page<AtpcoFare> page = atpcoFareRepository.findAll(pageable);
        
        List<AfdQuery> result = new ArrayList<>();
        result.addAll(afdQueryMapper.convertAtpcoFare(page.getContent()));
        
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/afd-queries");
        
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
