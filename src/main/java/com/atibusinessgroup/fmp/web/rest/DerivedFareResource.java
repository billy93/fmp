package com.atibusinessgroup.fmp.web.rest;

import java.util.LinkedHashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.dto.DerivedFareParam;
import com.atibusinessgroup.fmp.domain.dto.SpecifiedConstructed;
import com.atibusinessgroup.fmp.repository.FbrReportRepository;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class DerivedFareResource {

	private final Logger log = LoggerFactory.getLogger(DerivedFareResource.class);

	private final FbrReportRepository fbrReportRepository;
	
    public DerivedFareResource(FbrReportRepository fbrReportRepository) {
    	this.fbrReportRepository = fbrReportRepository;
    	
    }
    
    /**
     * POST  /derived-fares/fares : get all the derived fares from afd query page.
     *
     * @param query params, pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in body
     */
    @PostMapping("/derived-fares/fares")
    @Timed
    public ResponseEntity<String> getAllDerivedFares(@RequestBody DerivedFareParam param) {
        log.debug("REST request to process derived fares: {}", param);
        
        if (param != null) {
        	Set<String> carriers = new LinkedHashSet<>();
        	Set<String> tariffs = new LinkedHashSet<>();
        	Set<String> ruleNos = new LinkedHashSet<>();
        	
        	for (SpecifiedConstructed fare:param.getFares()) {
        		carriers.add(fare.getCarrierCode());
        		tariffs.add(fare.getTariffNo());
        		ruleNos.add(fare.getRuleNo());
        		
        	}
        }
        
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
//    private void generateReport(String name, String carrier, String tariff, String ruleNo, String ruleTitle) {
//    	FbrReport report = new FbrReport();
//    	report.setCreatedDate(Instant.now());
//    	report.setCarrier(carrier);
//    	report.setCreator(SecurityUtils.getCurrentUserLogin().get());
//    	report.setReportName(name);
//    	report.setRuleNo(ruleNo);
//    	report.setRuleTitle(ruleTitle);
//    	report.setTariff(tariff);
//    	report.setFareCount(0);
//    	report.setError(false);
//    	report.setProgress(0);
//    	fbrReportRepository.save(report);
//    }
}
