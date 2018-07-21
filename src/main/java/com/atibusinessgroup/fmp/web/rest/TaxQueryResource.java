package com.atibusinessgroup.fmp.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.dto.TaxQueryParam;
import com.atibusinessgroup.fmp.domain.dto.TaxWrapper;
import com.atibusinessgroup.fmp.repository.custom.AtpcoTaxCustomRepository;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class TaxQueryResource {

	private final Logger log = LoggerFactory.getLogger(TaxQueryResource.class);

	private final AtpcoTaxCustomRepository atpcoTaxCustomRepository;
	
    public TaxQueryResource(AtpcoTaxCustomRepository atpcoTaxCustomRepository) {
    	this.atpcoTaxCustomRepository = atpcoTaxCustomRepository;
    }
    
    /**
     * POST  /tax-queries : get all the tax queries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in body
     */
    @PostMapping("/tax-queries")
    @Timed
    public ResponseEntity<TaxWrapper> getAllTaxes(@RequestBody TaxQueryParam param) {
        log.debug("REST request to get a page of TaxQueries: {}", param);
        
        Pageable pageable = new PageRequest(param.getPage(), param.getSize());
        
        TaxWrapper result = atpcoTaxCustomRepository.findTax(param, pageable);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
