package com.atibusinessgroup.fmp.web.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.atibusinessgroup.fmp.constant.CategoryName;
import com.atibusinessgroup.fmp.constant.CategoryType;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord1;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.dto.AfdQuery;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryParam;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFareWithRecord1;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.Category;
import com.atibusinessgroup.fmp.resository.custom.AtpcoFareCustomRepository;
import com.atibusinessgroup.fmp.service.AtpcoRecordService;
import com.atibusinessgroup.fmp.service.mapper.AfdQueryMapper;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class AfdQueryResource {

	private final Map<String, String> categories = new HashMap<>();
	
	private final Logger log = LoggerFactory.getLogger(AfdQueryResource.class);

	private final AtpcoFareCustomRepository atpcoFareCustomRepository;
	
	private final AfdQueryMapper afdQueryMapper;
	
	private final AtpcoRecordService atpcoRecordService;
	
    public AfdQueryResource(AtpcoFareCustomRepository atpcoFareCustomRepository, AfdQueryMapper afdQueryMapper, AtpcoRecordService atpcoRecordService) {
    	this.atpcoFareCustomRepository = atpcoFareCustomRepository;
    	this.afdQueryMapper = afdQueryMapper;
    	this.atpcoRecordService = atpcoRecordService;
    	
    	categories.put("001", CategoryName.CAT_001);
    	categories.put("002", CategoryName.CAT_002);
    	categories.put("003", CategoryName.CAT_003);
    	categories.put("004", CategoryName.CAT_004);
    	categories.put("005", CategoryName.CAT_005);
    	categories.put("006", CategoryName.CAT_006);
    	categories.put("007", CategoryName.CAT_007);
    	categories.put("008", CategoryName.CAT_008);
    	categories.put("009", CategoryName.CAT_009);
    	categories.put("010", CategoryName.CAT_010);
    	categories.put("011", CategoryName.CAT_011);
    	categories.put("012", CategoryName.CAT_012);
    	categories.put("013", CategoryName.CAT_013);
    	categories.put("014", CategoryName.CAT_014);
    	categories.put("015", CategoryName.CAT_015);
    	categories.put("016", CategoryName.CAT_016);
    	categories.put("017", CategoryName.CAT_017);
    	categories.put("018", CategoryName.CAT_018);
    	categories.put("019", CategoryName.CAT_019);
    	categories.put("020", CategoryName.CAT_020);
    	categories.put("021", CategoryName.CAT_021);
    	categories.put("022", CategoryName.CAT_022);
    	categories.put("023", CategoryName.CAT_023);
    	categories.put("026", CategoryName.CAT_026);
    	categories.put("027", CategoryName.CAT_027);
    	categories.put("028", CategoryName.CAT_028);
    	categories.put("029", CategoryName.CAT_029);
    	categories.put("031", CategoryName.CAT_031);
    	categories.put("033", CategoryName.CAT_033);
    	categories.put("035", CategoryName.CAT_035);
    	categories.put("050", CategoryName.CAT_050);
    }
    
    /**
     * POST  /afd-queries : get all the afd queries.
     *
     * @param query params, pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in body
     */
    @PostMapping("/afd-queries")
    @Timed
    public ResponseEntity<List<AfdQuery>> getAllAfdQueries(@RequestBody AfdQueryParam param) {
        log.debug("REST request to get a page of AfdQueries: {}", param);
        
        Pageable pageable = new PageRequest(param.getPage(), param.getSize());
        
        //ATPCO
        Page<AtpcoFareWithRecord1> page = atpcoFareCustomRepository.findAtpcoFareWithRecord1(param, pageable);
        List<AtpcoFareWithRecord1> a1fares = page.getContent();
        
        List<AfdQuery> result = new ArrayList<>();
        
        for (AtpcoFareWithRecord1 a1fare:a1fares) {
        	AtpcoFare afare = a1fare.getAtpcoFare();
        	AtpcoRecord1 matchedRecord1 = null;
        	
        	for (AtpcoRecord1 record1:a1fare.getAtpcoRecord1()) {
        		boolean matched = atpcoRecordService.compareMatchingFareAndRecord("C", afare.getOriginCity(), "C", afare.getDestinationCity(), afare.getOwrt(), afare.getRoutingNo(), afare.getFootnote(), afare.getTariffEffectiveDateObject(), afare.getDiscontinueDateObject(),
        				record1.getGeoType1(), record1.getGeoLoc1(), record1.getGeoType2(), record1.getGeoLoc2(), record1.getOwrt(), record1.getRoutingNo(), record1.getFootnote(), record1.getEffectiveDateObject(), record1.getDiscontinueDateObject());
        	
        		if (matched) {
        			matchedRecord1 = record1;
        			break;
        		}
        	}
        	
        	AfdQuery afdQuery = afdQueryMapper.convertAtpcoFare(afare, matchedRecord1);
        	
        	
        	result.add(afdQuery);
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
    public ResponseEntity<List<Category>> getAfdQueryRules(AfdQuery afdQuery) {
        log.debug("REST request to get AfdQueries rules: {}", afdQuery);
        
        List<Category> result = new ArrayList<>();
        
        String recordId = afdQuery.getTariffNo() + afdQuery.getCarrierCode() + afdQuery.getRuleNo() + "";
        
        List<AtpcoRecord2GroupByCatNo> arecords2 = atpcoFareCustomRepository.findAtpcoRecord2ByRecordId(recordId);
        
        for (Map.Entry<String, String> entry : categories.entrySet()) {
        	AtpcoRecord2 matchedRecord2 = null;
        	
        	for (AtpcoRecord2GroupByCatNo arecord2:arecords2) {
            	if (arecord2.getCatNo().contentEquals(entry.getKey())) {
            		for (AtpcoRecord2 record2:arecord2.getRecords2()) {
            			boolean matched = atpcoRecordService.compareMatchingFareAndRecord("C", afdQuery.getOriginCity(), "C", afdQuery.getDestinationCity(), afdQuery.getOwrt(), afdQuery.getRoutingNo(), afdQuery.getFootnote(), afdQuery.getEffectiveDate(), afdQuery.getDiscontinueDate(),
            					record2.getGeoType1(), record2.getGeoLoc1(), record2.getGeoType2(), record2.getGeoLoc2(), record2.getOwrt(), record2.getRoutingNo(), record2.getFootnote(), record2.getEffectiveDateObject(), record2.getDiscontinueDateObject());
                    	
                		if (matched) {
                			matchedRecord2 = record2;
                			break;
                		} 
            		}
            		
            		break;
            	}
            }
        	
        	Category cat = new Category();
        	cat.setCatName(entry.getValue());
        	cat.setType(CategoryType.RULE);
        	
        	try {
        		cat.setCatNo(Integer.parseInt(entry.getKey()));
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        	
        	if (matchedRecord2 != null && matchedRecord2.getDataTables() != null && matchedRecord2.getDataTables().size() > 0) {
        		cat.setAttributes(atpcoRecordService.getAndConvertCategoryDataTable(entry.getKey(), matchedRecord2.getDataTables()));
        	}
        	
        	result.add(cat);
        }
        
        Collections.sort(result, Category.ASCENDING_COMPARATOR);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
