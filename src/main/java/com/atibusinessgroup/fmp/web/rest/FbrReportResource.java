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

import com.atibusinessgroup.fmp.domain.dto.FbrReportParam;
import com.atibusinessgroup.fmp.domain.dto.FbrReportWrapper;
import com.atibusinessgroup.fmp.repository.custom.AtpcoFbrReportCustomRepository;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class FbrReportResource {

	private final Logger log = LoggerFactory.getLogger(FbrReportResource.class);
	
	private final AtpcoFbrReportCustomRepository atpcoFbrReportCustomRepository;
	
    public FbrReportResource(AtpcoFbrReportCustomRepository atpcoFbrReportCustomRepository) {
    	this.atpcoFbrReportCustomRepository = atpcoFbrReportCustomRepository;
    }
    
    /**
     * POST  /fbr-report : get all the FBR report.
     *
     * @param query params, pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in body
     */
    @PostMapping("/fbr-report")
    @Timed
    public ResponseEntity<FbrReportWrapper> getAllDerivedFares(@RequestBody FbrReportParam param) {
        log.debug("REST request to get fbr reports: {}", param);
        
        Pageable pageable = new PageRequest(param.getPage(), param.getSize());
        
        FbrReportWrapper result = atpcoFbrReportCustomRepository.findFbrReport(param, pageable);
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
