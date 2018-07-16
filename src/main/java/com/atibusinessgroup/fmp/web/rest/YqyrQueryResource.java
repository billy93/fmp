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

import com.atibusinessgroup.fmp.domain.dto.YqyrQueryParam;
import com.atibusinessgroup.fmp.domain.dto.YqyrWrapper;
import com.atibusinessgroup.fmp.repository.custom.AtpcoYqyrCustomRepository;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class YqyrQueryResource {

	private final Logger log = LoggerFactory.getLogger(YqyrQueryResource.class);

	private final AtpcoYqyrCustomRepository atpcoYqyrCustomRepository;
	
    public YqyrQueryResource(AtpcoYqyrCustomRepository atpcoYqyrCustomRepository) {
    	this.atpcoYqyrCustomRepository = atpcoYqyrCustomRepository;
    }
    
    /**
     * POST  /yqyr-queries : get all the yqyr queries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in body
     */
    @PostMapping("/yqyr-queries")
    @Timed
    public ResponseEntity<YqyrWrapper> getAllYqyrs(@RequestBody YqyrQueryParam param) {
        log.debug("REST request to get a page of YqyrQueries: {}", param);
        
        YqyrWrapper result = new YqyrWrapper();
        
        Pageable pageable = new PageRequest(param.getPage(), param.getSize());
        
        YqyrWrapper yqyr = atpcoYqyrCustomRepository.findYqyr(param, pageable);
        
        result.setYqyr(yqyr.getYqyr());
        result.setLastPage(yqyr.isLastPage());
        result.setLastIndex(yqyr.getLastIndex());
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
