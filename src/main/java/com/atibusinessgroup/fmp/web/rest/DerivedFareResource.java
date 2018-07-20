package com.atibusinessgroup.fmp.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.dto.DerivedFareParam;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class DerivedFareResource {

	private final Logger log = LoggerFactory.getLogger(DerivedFareResource.class);

    public DerivedFareResource() {
    	
    }
    
    /**
     * POST  /derived-fares/afd-query : get all the derived fares from afd query page.
     *
     * @param query params, pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in body
     */
    @PostMapping("/derived-fare/fares")
    @Timed
    public ResponseEntity<String> getAllDerivedFares(@RequestBody DerivedFareParam param) {
        log.debug("REST request to process derived fares: {}", param);
        
        if (param != null && param.getFares().size() > 0) {
        	
        }
        
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
