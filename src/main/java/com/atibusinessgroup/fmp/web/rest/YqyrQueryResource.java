package com.atibusinessgroup.fmp.web.rest;

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

import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class YqyrQueryResource {

	private final Logger log = LoggerFactory.getLogger(YqyrQueryResource.class);

    public YqyrQueryResource() {
    	
    }
    
    /**
     * GET  /yqyr-queries : get all the afd queries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in body
     */
    @GetMapping("/yqyr-queries")
    @Timed
    public ResponseEntity<List<String>> getAllAfdQueries(Pageable pageable) {
        log.debug("REST request to get a page of YqyrQueries");
        
        Page<String> page = null;
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/yqyr-queries");
        
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
}
