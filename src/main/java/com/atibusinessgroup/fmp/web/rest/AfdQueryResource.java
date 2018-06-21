package com.atibusinessgroup.fmp.web.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFootnoteRecord2;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord0;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord1;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2Cat10;
import com.atibusinessgroup.fmp.domain.dto.AfdQuery;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryParam;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFareAfdQueryWithRecords;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.Category;
import com.atibusinessgroup.fmp.domain.dto.CategoryObject;
import com.atibusinessgroup.fmp.domain.dto.CategoryTextFormatAndAttribute;
import com.atibusinessgroup.fmp.domain.dto.DataTable;
import com.atibusinessgroup.fmp.domain.dto.GeneralRuleApplication;
import com.atibusinessgroup.fmp.repository.AtpcoRecord0Repository;
import com.atibusinessgroup.fmp.repository.custom.AtpcoFareCustomRepository;
import com.atibusinessgroup.fmp.service.AtpcoRecordService;
import com.atibusinessgroup.fmp.service.mapper.AfdQueryMapper;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class AfdQueryResource {

	private final LinkedHashMap<String, String> fareCategories = new LinkedHashMap<>();
	private final LinkedHashMap<String, String> fareFootnotes = new LinkedHashMap<>();
	private final LinkedHashMap<String, String> categories = new LinkedHashMap<>();
	private final LinkedHashMap<String, String> footnotes = new LinkedHashMap<>();
	private final String[] ruleCategories = new String[] {"003", "005", "006", "007", "014", "015"};
	
	private final Logger log = LoggerFactory.getLogger(AfdQueryResource.class);

	private final AtpcoRecord0Repository atpcoRecord0Repository;
	
	private final AtpcoFareCustomRepository atpcoFareCustomRepository;
	
	private final AfdQueryMapper afdQueryMapper;
	
	private final AtpcoRecordService atpcoRecordService;
	
    public AfdQueryResource(AtpcoRecord0Repository atpcoRecord0Repository, AtpcoFareCustomRepository atpcoFareCustomRepository, AfdQueryMapper afdQueryMapper, AtpcoRecordService atpcoRecordService) {
    	this.atpcoRecord0Repository = atpcoRecord0Repository;
    	this.atpcoFareCustomRepository = atpcoFareCustomRepository;
    	this.afdQueryMapper = afdQueryMapper;
    	this.atpcoRecordService = atpcoRecordService;
    	
    	//Fares
    	fareCategories.put("003", CategoryName.CAT_003);
    	fareCategories.put("005", CategoryName.CAT_005);
    	fareCategories.put("006", CategoryName.CAT_006);
    	fareCategories.put("007", CategoryName.CAT_007);
    	fareCategories.put("014", CategoryName.CAT_014);
    	fareCategories.put("015", CategoryName.CAT_015);
    	fareFootnotes.put("014", CategoryName.CAT_014);
    	fareFootnotes.put("015", CategoryName.CAT_015);
    	
    	//Rules
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
    	footnotes.put("014", CategoryName.CAT_014);
    	footnotes.put("015", CategoryName.CAT_015);
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
        Page<AtpcoFareAfdQueryWithRecords> page = atpcoFareCustomRepository.findAtpcoFareAfdQueryWithRecords(param, ruleCategories, pageable);
        List<AtpcoFareAfdQueryWithRecords> a1fares = page.getContent();

        List<AfdQuery> result = new ArrayList<>();
        
        Date focusDate = null;
        
        atpcoRecordService.compareFareClass(null, null);
        
        for (AtpcoFareAfdQueryWithRecords a1fare:a1fares) {
        	AtpcoFare afare = a1fare.getAtpcoFare();
        	AtpcoRecord1 matchedRecord1 = null;
        	
        	focusDate = atpcoRecordService.resolveFocusDate(param.getEffectiveDateTo(), afare.getTariffEffectiveDateObject(), afare.getDiscontinueDateObject());
        	
        	for (AtpcoRecord1 record1:a1fare.getAtpcoRecord1()) {
        		boolean matched = atpcoRecordService.compareMatchingFareAndRecord("C", afare.getOriginCity(), "C", afare.getDestinationCity(), afare.getOwrt(), afare.getRoutingNo(), afare.getFootnote(), focusDate,
        				record1.getGeoType1(), record1.getGeoLoc1(), record1.getGeoType2(), record1.getGeoLoc2(), record1.getOwrt(), record1.getRoutingNo(), record1.getFootnote(), record1.getEffectiveDateObject(), record1.getDiscontinueDateObject());
        		
        		if (matched) {
        			matchedRecord1 = record1;
        			break;
        		}
        	}
        	
        	//Rule
        	List<CategoryObject> cat03s = null;
        	List<CategoryObject> cat05s = null;
        	List<CategoryObject> cat06s = null;
        	List<CategoryObject> cat07s = null;
        	List<CategoryObject> cat14s = null;
        	List<CategoryObject> cat15s = null;
        	
        	for (Map.Entry<String, String> entry : fareCategories.entrySet()) {
        		AtpcoRecord2 matchedRecord2 = null;
        		
        		for (AtpcoRecord2GroupByCatNo arecord2:a1fare.getAtpcoRecord2()) {
                	if (arecord2.getCatNo().contentEquals(entry.getKey())) {
                		for (AtpcoRecord2 record2:arecord2.getRecords2()) {
                			boolean matched = atpcoRecordService.compareMatchingFareAndRecord("C", afare.getOriginCity(), "C", afare.getDestinationCity(), afare.getFareClassCode(), afare.getFareType(), matchedRecord1 != null ? matchedRecord1.getSeasonType() : null, matchedRecord1 != null ? matchedRecord1.getDayOfWeekType() : null, afare.getOwrt(), afare.getRoutingNo(), afare.getFootnote(), focusDate,
                					record2.getGeoType1(), record2.getGeoLoc1(), record2.getGeoType2(), record2.getGeoLoc2(), record2.getFareClass(), record2.getFareType(), record2.getSeasonType(), record2.getDayOfWeekType(), record2.getOwrt(), record2.getRoutingNo(), record2.getFootnote(), record2.getEffectiveDateObject(), record2.getDiscontinueDateObject());
                        	
                    		if (matched) {
                    			matchedRecord2 = record2;
                    			break;
                    		} 
                		}
                		
                		break;
                	}
                }	
        		
        		if (matchedRecord2 != null && matchedRecord2.getDataTables() != null && matchedRecord2.getDataTables().size() > 0) {
            		List<CategoryObject> rules = atpcoRecordService.getAndConvertCategoryObjectDataTable(entry.getKey(), matchedRecord2.getDataTables(), "Rule");
            		
            		switch (entry.getKey()) {
	            		case "003": cat03s = rules;
									break;	
            			case "005": cat05s = rules;
									break;	
            			case "006": cat06s = rules;
            						break;
            			case "007": cat07s = rules;
									break;
            			case "014": cat14s = rules;
									break;
            			case "015": cat15s = rules;
									break;
            		}
            	}
        	}
        	
        	//Footnote
        	List<CategoryObject> footnote14s = null;
        	List<CategoryObject> footnote15s = null;
        	
        	for (Map.Entry<String, String> entry : footnotes.entrySet()) {
        		AtpcoFootnoteRecord2 matchedRecord2 = null;
            	
            	for (AtpcoFootnoteRecord2GroupByCatNo arecord2:a1fare.getFootnoteRecord()) {
                	if (arecord2.getCatNo().contentEquals(entry.getKey())) {
                		for (AtpcoFootnoteRecord2 record2:arecord2.getRecords2()) {
                			boolean matched = atpcoRecordService.compareMatchingFareAndRecord("C", afare.getOriginCity(), "C", afare.getDestinationCity(), afare.getOwrt(), afare.getRoutingNo(), afare.getFootnote(), focusDate,
                					record2.getGeoType1(), record2.getGeoLoc1(), record2.getGeoType2(), record2.getGeoLoc2(), record2.getOwrt(), record2.getRoutingNo(), record2.getFootnote(), record2.getEffectiveDateObject(), record2.getDiscontinueDateObject());
                        	
                    		if (matched) {
                    			matchedRecord2 = record2;
                    			break;
                    		} 
                		}
                		
                		break;
                	}
                }	
            	
            	if (matchedRecord2 != null && matchedRecord2.getDataTables() != null && matchedRecord2.getDataTables().size() > 0) {
            		List<CategoryObject> footnotes = atpcoRecordService.getAndConvertCategoryObjectDataTable(entry.getKey(), matchedRecord2.getDataTables(), "Footnote");
            		
            		switch (entry.getKey()) {
            			case "014": footnote14s = footnotes;
            						break;
            			case "015": footnote15s = footnotes;
									break;
            		}
            	}
        	}
        	
        	AfdQuery afdQuery = afdQueryMapper.convertAtpcoFare(afare, matchedRecord1, cat03s, cat05s, cat06s, cat07s, cat14s, cat15s, 
        			footnote14s, footnote15s, focusDate);
        	
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
        
        List<AtpcoRecord0> arecords0 = atpcoRecord0Repository.findAllByRecordId(afdQuery.getTariffNo() + afdQuery.getCarrierCode() + "");
        List<AtpcoRecord2GroupByCatNo> arecords2 = atpcoFareCustomRepository.findAtpcoRecord2ByRecordId(afdQuery.getTariffNo() + afdQuery.getCarrierCode() + afdQuery.getRuleNo() + "");
        List<AtpcoRecord2Cat10> arecords210 = atpcoFareCustomRepository.findAtpcoRecord2Cat10ByRecordId(afdQuery.getTariffNo() + afdQuery.getCarrierCode() + afdQuery.getRuleNo() + "");
        List<AtpcoFootnoteRecord2GroupByCatNo> frecords2 = atpcoFareCustomRepository.findAtpcoFootnoteRecord2ByRecordId(afdQuery.getTariffNo() + afdQuery.getCarrierCode() + afdQuery.getFootnote() + "");
        
        for (Map.Entry<String, String> entry : categories.entrySet()) {
        	AtpcoRecord2 matchedGeneralRecord2 = null;
        	AtpcoRecord2 matchedRecord2 = null;
        	AtpcoRecord2Cat10 matchedRecord2Cat10 = null;
        	AtpcoFootnoteRecord2 matchedFRecord2 = null;
        	GeneralRuleApplication matchedGeneral = null;

        	all : for (AtpcoRecord2GroupByCatNo arecord2:arecords2) {
            	if (arecord2.getCatNo().contentEquals(entry.getKey())) {
            		for (AtpcoRecord2 record2:arecord2.getRecords2()) {
            			boolean matched = atpcoRecordService.compareMatchingFareAndRecord("C", afdQuery.getOriginCity(), "C", afdQuery.getDestinationCity(), afdQuery.getFareClassCode(), afdQuery.getFareType(), afdQuery.getSeason(), afdQuery.getDayOfWeekType(), afdQuery.getOwrt(), afdQuery.getRoutingNo(), afdQuery.getFootnote(), afdQuery.getFocusDate(),
            					record2.getGeoType1(), record2.getGeoLoc1(), record2.getGeoType2(), record2.getGeoLoc2(), record2.getFareClass(), record2.getFareType(), record2.getSeasonType(), record2.getDayOfWeekType(), record2.getOwrt(), record2.getRoutingNo(), record2.getFootnote(), record2.getEffectiveDateObject(), record2.getDiscontinueDateObject());
                    	
                		if (matched) {
                			matchedRecord2 = record2;
                			break all;
                		} 
            		}
            	}
            }	
        	
        	all : for (AtpcoFootnoteRecord2GroupByCatNo frecord2:frecords2) {
            	if (frecord2.getCatNo().contentEquals(entry.getKey())) {
            		for (AtpcoFootnoteRecord2 record2:frecord2.getRecords2()) {
            			boolean matched = atpcoRecordService.compareMatchingFareAndRecord("C", afdQuery.getOriginCity(), "C", afdQuery.getDestinationCity(), afdQuery.getOwrt(), afdQuery.getRoutingNo(), afdQuery.getFootnote(), afdQuery.getFocusDate(),
            					record2.getGeoType1(), record2.getGeoLoc1(), record2.getGeoType2(), record2.getGeoLoc2(), record2.getOwrt(), record2.getRoutingNo(), record2.getFootnote(), record2.getEffectiveDateObject(), record2.getDiscontinueDateObject());
                    	
                		if (matched) {
                			matchedFRecord2 = record2;
                			break all;
                		} 
            		}
            	}
            }	
            
            if (entry.getKey().contentEquals("010")) {
            	all : for (AtpcoRecord2Cat10 arecord210:arecords210) {
            		boolean matched = atpcoRecordService.compareMatchingFareAndRecord(afdQuery.getOwrt(), afdQuery.getRoutingNo(), afdQuery.getFootnote(), afdQuery.getFocusDate(),
            				arecord210.getOwrt(), arecord210.getRtg_no(), arecord210.getFt_nt(), arecord210.getDates_eff(), arecord210.getDates_disc());
                	
            		if (matched) {
            			matchedRecord2Cat10 = arecord210;
            			break all;
            		} 
                }	
            }
            
    		all : for (AtpcoRecord0 arecord0:arecords0) {
    			for (GeneralRuleApplication gra:arecord0.getGeneralRuleApplications()) {
    				if (entry.getKey().contentEquals(gra.getCatNo())) {
    					matchedGeneral = gra;
    					break all;
    				}
    			}
    		}
        	
        	Category cat = new Category();
        	cat.setCatName(entry.getValue());
        	
        	String type = "";
        	String textFormat = "";
        	
        	try {
        		cat.setCatNo(Integer.parseInt(entry.getKey()));
        	} catch (Exception e) {
        		e.printStackTrace();
        	}
        	
        	if (matchedFRecord2 != null && matchedFRecord2.getDataTables() != null && matchedFRecord2.getDataTables().size() > 0) {
        		type += CategoryType.FOOTNOTE;
        		textFormat += atpcoRecordService.generateCategoryTextHeader(CategoryType.FOOTNOTE, afdQuery.getTariffNo(), afdQuery.getTariffCode(), afdQuery.getRuleNo(), matchedFRecord2.getSequenceNo(), matchedFRecord2.getEffectiveDateObject());
        		CategoryTextFormatAndAttribute ctfa = atpcoRecordService.getAndConvertCategoryDataTable(entry.getKey(), matchedFRecord2.getDataTables(), CategoryType.FOOTNOTE);
        		textFormat += ctfa.getTextFormat();
        		cat.getCatAttributes().addAll(ctfa.getAttributes());
        	}
        	
        	if (matchedRecord2 != null && matchedRecord2.getDataTables() != null && matchedRecord2.getDataTables().size() > 0) {
        		type += CategoryType.RULE;
        		textFormat += atpcoRecordService.generateCategoryTextHeader(CategoryType.RULE, afdQuery.getTariffNo(), afdQuery.getTariffCode(), afdQuery.getRuleNo(), matchedRecord2.getSequenceNo(), matchedRecord2.getEffectiveDateObject());
        		CategoryTextFormatAndAttribute ctfa = atpcoRecordService.getAndConvertCategoryDataTable(entry.getKey(), matchedRecord2.getDataTables(), CategoryType.RULE);
        		textFormat += ctfa.getTextFormat();
        		cat.getCatAttributes().addAll(ctfa.getAttributes());
        	}
        	
        	if (matchedRecord2Cat10 != null && matchedRecord2Cat10.getData_segs() != null && matchedRecord2Cat10.getData_segs().size() > 0) {
        		type += CategoryType.RULE;
        		textFormat += atpcoRecordService.generateCategoryTextHeader(CategoryType.RULE, afdQuery.getTariffNo(), afdQuery.getTariffCode(), afdQuery.getRuleNo(), matchedRecord2Cat10.getSeq_no(), matchedRecord2Cat10.getDates_eff());
        		
        		LinkedHashMap<String, List<DataTable>> groupedCat10DataTables = new LinkedHashMap<>();
        		
        		for (DataTable dt:matchedRecord2Cat10.getData_segs()) {
        			if (!groupedCat10DataTables.containsKey(dt.getCatNo())) {
        				List<DataTable> dts = new ArrayList<>();
        				dts.add(dt);
        				groupedCat10DataTables.put(dt.getCatNo(), dts);
        			} else {
        				groupedCat10DataTables.get(dt.getCatNo()).add(dt);
        			}
        		}
        		
        		for (Map.Entry<String, List<DataTable>> dtEntry:groupedCat10DataTables.entrySet()) {
        			CategoryTextFormatAndAttribute ctfa = atpcoRecordService.getAndConvertCategoryDataTable(dtEntry.getKey(), dtEntry.getValue(), CategoryType.RULE);
        			textFormat += ctfa.getTextFormat();
        			cat.getCatAttributes().addAll(ctfa.getAttributes());
        		}
        	}
        	
        	if (matchedGeneral != null) {
    			List<AtpcoRecord2GroupByCatNo> generalRecords2 = atpcoFareCustomRepository.findAtpcoRecord2ByRecordId(matchedGeneral.getSourceTariff() + afdQuery.getCarrierCode() + matchedGeneral.getRuleNo() + "");
        		
        		all : for (AtpcoRecord2GroupByCatNo grecord2:generalRecords2) {
                	if (grecord2.getCatNo().contentEquals(entry.getKey())) {
                		for (AtpcoRecord2 record2:grecord2.getRecords2()) {
                			boolean matched = atpcoRecordService.compareMatchingFareAndRecord("C", afdQuery.getOriginCity(), "C", afdQuery.getDestinationCity(), afdQuery.getFareClassCode(), afdQuery.getFareType(), afdQuery.getSeason(), afdQuery.getDayOfWeekType(), afdQuery.getOwrt(), afdQuery.getRoutingNo(), afdQuery.getFootnote(), afdQuery.getFocusDate(),
                					record2.getGeoType1(), record2.getGeoLoc1(), record2.getGeoType2(), record2.getGeoLoc2(), record2.getFareClass(), record2.getFareType(), record2.getSeasonType(), record2.getDayOfWeekType(), record2.getOwrt(), record2.getRoutingNo(), record2.getFootnote(), record2.getEffectiveDateObject(), record2.getDiscontinueDateObject());
                        	
                    		if (matched) {
                    			matchedGeneralRecord2 = record2;
                    			break all;
                    		} 
                		}
                	}
                }	
        		
        		if (matchedGeneralRecord2 != null && matchedGeneralRecord2.getDataTables() != null && matchedGeneralRecord2.getDataTables().size() > 0) {
        			type += CategoryType.GENERAL_RULE;
        			textFormat += atpcoRecordService.generateCategoryTextHeader(CategoryType.GENERAL_RULE, afdQuery.getTariffNo(), afdQuery.getTariffCode(), afdQuery.getRuleNo(), matchedGeneralRecord2.getSequenceNo(), matchedGeneralRecord2.getEffectiveDateObject());
        			CategoryTextFormatAndAttribute ctfa = atpcoRecordService.getAndConvertCategoryDataTable(entry.getKey(), matchedGeneralRecord2.getDataTables(), CategoryType.GENERAL_RULE);
            		textFormat += ctfa.getTextFormat();
            		cat.getCatAttributes().addAll(ctfa.getAttributes());
        		}
    		}
        	
        	cat.setType(type);
        	cat.setTextFormat(textFormat);
        	
        	result.add(cat);
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
