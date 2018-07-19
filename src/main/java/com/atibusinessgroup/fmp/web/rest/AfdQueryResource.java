package com.atibusinessgroup.fmp.web.rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.constant.CategoryName;
import com.atibusinessgroup.fmp.constant.CategoryType;
import com.atibusinessgroup.fmp.domain.AtpcoMasterTariff;
import com.atibusinessgroup.fmp.domain.VoltrasFare;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFootnoteRecord2;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord0;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2Cat10;
import com.atibusinessgroup.fmp.domain.dto.AddOnParam;
import com.atibusinessgroup.fmp.domain.dto.AddOnsWrapper;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryAddOns;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryParam;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.Category;
import com.atibusinessgroup.fmp.domain.dto.CategoryTextFormatAndAttribute;
import com.atibusinessgroup.fmp.domain.dto.DataTable;
import com.atibusinessgroup.fmp.domain.dto.FbrQuery;
import com.atibusinessgroup.fmp.domain.dto.FbrQueryParam;
import com.atibusinessgroup.fmp.domain.dto.FbrQueryWrapper;
import com.atibusinessgroup.fmp.domain.dto.GeneralRuleApplication;
import com.atibusinessgroup.fmp.domain.dto.RuleQuery;
import com.atibusinessgroup.fmp.domain.dto.SpecifiedConstructed;
import com.atibusinessgroup.fmp.domain.dto.SpecifiedConstructedWrapper;
import com.atibusinessgroup.fmp.domain.dto.WorkPackageMarketFare;
import com.atibusinessgroup.fmp.repository.AtpcoMasterTariffRepository;
import com.atibusinessgroup.fmp.repository.AtpcoRecord0Repository;
import com.atibusinessgroup.fmp.repository.VoltrasFareRepository;
import com.atibusinessgroup.fmp.repository.WorkPackageRepositoryImpl;
import com.atibusinessgroup.fmp.repository.custom.AtpcoFareCustomRepository;
import com.atibusinessgroup.fmp.repository.custom.AtpcoRuleQueryCustomRepository;
import com.atibusinessgroup.fmp.service.AtpcoRecordService;
import com.atibusinessgroup.fmp.service.mapper.AfdQueryMapper;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class AfdQueryResource {

	private final LinkedHashMap<String, String> categories = new LinkedHashMap<>();
	private final Logger log = LoggerFactory.getLogger(AfdQueryResource.class);

	private final AtpcoRecord0Repository atpcoRecord0Repository;
	private final AtpcoFareCustomRepository atpcoFareCustomRepository;
	private final AtpcoRecordService atpcoRecordService;
	private final VoltrasFareRepository voltrasFareRepository;
	private final WorkPackageRepositoryImpl workPackageRepositoryImpl;
	private final AfdQueryMapper afdQueryMapper;
	private final AtpcoMasterTariffRepository tariffNumberRepository;
	private final AtpcoRuleQueryCustomRepository atpcoRuleQueryCustomRepository;
	
    public AfdQueryResource(AtpcoRecord0Repository atpcoRecord0Repository, AtpcoFareCustomRepository atpcoFareCustomRepository, 
    		AtpcoRecordService atpcoRecordService, WorkPackageRepositoryImpl workPackageRepositoryImpl, AfdQueryMapper afdQueryMapper,
    		VoltrasFareRepository voltrasFareRepository, AtpcoMasterTariffRepository tariffNumberRepository, 
    		AtpcoRuleQueryCustomRepository atpcoRuleQueryCustomRepository) {
    	this.atpcoRecord0Repository = atpcoRecord0Repository;
    	this.atpcoFareCustomRepository = atpcoFareCustomRepository;
    	this.atpcoRecordService = atpcoRecordService;
    	this.workPackageRepositoryImpl = workPackageRepositoryImpl;
    	this.afdQueryMapper = afdQueryMapper;
    	this.voltrasFareRepository = voltrasFareRepository;
    	this.tariffNumberRepository = tariffNumberRepository;
    	this.atpcoRuleQueryCustomRepository = atpcoRuleQueryCustomRepository;
    	
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
     * POST  /afd-queries/specified : get all the afd queries.
     *
     * @param query params, pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in body
     */
    @PostMapping("/afd-queries/specified")
    @Timed
    public ResponseEntity<SpecifiedConstructedWrapper> getAllAfdQuerySpecified(@RequestBody AfdQueryParam param) {
        log.debug("REST request to get a page of Specified: {}", param);
        
        SpecifiedConstructedWrapper result = new SpecifiedConstructedWrapper();
        
        if (param != null && param.getSource() != null && !param.isIncludeConstructed()) {
        	Pageable pageable = new PageRequest(param.getPage(), param.getSize());
            
            List<SpecifiedConstructed> fares = new ArrayList<>();
            boolean isLastPage = false;
            int lastIndex = 0;
            
            //ATPCO
            if (param.getSource() != null && param.getSource().contentEquals("A")) {
            	SpecifiedConstructedWrapper atpco = atpcoFareCustomRepository.findSpecifiedFares(param, pageable);
            	fares.addAll(atpco.getSpecifiedConstructed());
                isLastPage = atpco.isLastPage();
                lastIndex = atpco.getLastIndex();
            }
            
            //Market
            if (param.getSource().contentEquals("M")) {
            	Page<WorkPackageMarketFare> rawMarkets = workPackageRepositoryImpl.findAllMarketFare(param, pageable);
            	
            	for (int i = 0; i < rawMarkets.getContent().size(); i++) {
            		WorkPackageMarketFare market = rawMarkets.getContent().get(i);
            		String fareId = market.getId() + i;
            		fares.add(afdQueryMapper.convertMarketFare(market.getFare(), market.getId(), fareId, market.getWoId(), market.getWoName()));
            	}
            	
            	if (rawMarkets.getTotalPages() == pageable.getPageNumber()) {
            		isLastPage = true;
            	}
            }
            
            //Web
            if (param.getSource().contentEquals("W")) {
            	Page<VoltrasFare> rawWebs = voltrasFareRepository.findAll(pageable);
            	
            	for (int i = 0; i < rawWebs.getContent().size(); i++) {
            		VoltrasFare web = rawWebs.getContent().get(i);
            		fares.add(afdQueryMapper.convertVoltrasFare(web));
            	}
            	
            	if (rawWebs.getTotalPages() == pageable.getPageNumber()) {
            		isLastPage = true;
            	}
            }
            
            //Competitor
            if (param.getSource().contentEquals("C")) {
            	
            }
            
            result.setSpecifiedConstructed(fares);
            result.setLastPage(isLastPage);
            result.setLastIndex(lastIndex);
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    /**
     * POST  /afd-queries/specified-constructed : get all the afd queries.
     *
     * @param query params, pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in body
     */
    @PostMapping("/afd-queries/specified-constructed")
    @Timed
    public ResponseEntity<SpecifiedConstructedWrapper> getAllAfdQuerySpecifiedConstructed(@RequestBody AfdQueryParam param) {
        log.debug("REST request to get a page of SpecifiedConstructed: {}", param);
        
        SpecifiedConstructedWrapper result = new SpecifiedConstructedWrapper();
        
        if (param != null && param.getSource() != null && param.isIncludeConstructed()) {
        	Pageable pageable = new PageRequest(param.getPage(), param.getSize());
            
            List<SpecifiedConstructed> fares = new ArrayList<>();
            boolean isLastPage = false;
            int lastIndex = 0;
            
            //ATPCO
            if (param.getSource() != null && param.getSource().contentEquals("A")) {
            	SpecifiedConstructedWrapper atpco = atpcoFareCustomRepository.findSpecifiedConstructedFares(param, pageable);
            	fares.addAll(atpco.getSpecifiedConstructed());
                isLastPage = atpco.isLastPage();
                lastIndex = atpco.getLastIndex();
            }
            
            result.setSpecifiedConstructed(fares);
            result.setLastPage(isLastPage);
            result.setLastIndex(lastIndex);
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    /**
     * POST  /afd-queries/rules : s afd query rules.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of rules in body
     */
    @PostMapping("/afd-queries/rules")
    @Timed
    public ResponseEntity<List<Category>> getAfdQueryRules(@RequestBody SpecifiedConstructed afdQuery) {
        log.debug("REST request to get AfdQueries rules: {}", afdQuery);
        
        List<Category> result = new ArrayList<>();
        
        List<AtpcoRecord0> arecords0 = atpcoRecord0Repository.findAllByRecordId(afdQuery.getTariffNo() + afdQuery.getCarrierCode() + "");
        List<AtpcoRecord2GroupByCatNo> arecords2 = atpcoFareCustomRepository.findAtpcoRecord2ByRecordId(afdQuery.getTariffNo() + afdQuery.getCarrierCode() + afdQuery.getRuleNo() + "");
        List<AtpcoRecord2Cat10> arecords210 = atpcoFareCustomRepository.findAtpcoRecord2Cat10ByRecordId(afdQuery.getTariffNo() + afdQuery.getCarrierCode() + afdQuery.getRuleNo() + "");
        List<AtpcoFootnoteRecord2GroupByCatNo> frecords2 = atpcoFareCustomRepository.findAtpcoFootnoteRecord2ByRecordId(afdQuery.getTariffNo() + afdQuery.getCarrierCode() + afdQuery.getFootnote() + "");
        
        String ruleTcd = null;
        AtpcoMasterTariff generalTn = null;
        AtpcoMasterTariff ruleTn = tariffNumberRepository.findOneByTarNoAndType(afdQuery.getTariffNo(), "FARE RULE");
        
        if (ruleTn != null) {
        	ruleTcd = ruleTn.getTarCd();
        }
        
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
        		textFormat += atpcoRecordService.generateCategoryTextHeader(CategoryType.FOOTNOTE, afdQuery.getTariffNo(), ruleTcd, afdQuery.getFootnote(), matchedFRecord2.getSequenceNo(), matchedFRecord2.getEffectiveDateObject());
        		CategoryTextFormatAndAttribute ctfa = atpcoRecordService.getAndConvertCategoryDataTable(entry.getKey(), matchedFRecord2.getDataTables(), CategoryType.FOOTNOTE);
        		textFormat += ctfa.getTextFormat();
        		cat.getCatAttributes().addAll(ctfa.getAttributes());
        	}
        	
        	if (matchedRecord2 != null && matchedRecord2.getDataTables() != null && matchedRecord2.getDataTables().size() > 0) {
        		type += CategoryType.RULE;
        		textFormat += atpcoRecordService.generateCategoryTextHeader(CategoryType.RULE, afdQuery.getTariffNo(), ruleTcd, afdQuery.getRuleNo(), matchedRecord2.getSequenceNo(), matchedRecord2.getEffectiveDateObject());
        		CategoryTextFormatAndAttribute ctfa = atpcoRecordService.getAndConvertCategoryDataTable(entry.getKey(), matchedRecord2.getDataTables(), CategoryType.RULE);
        		textFormat += ctfa.getTextFormat();
        		cat.getCatAttributes().addAll(ctfa.getAttributes());
        	}
        	
        	if (matchedRecord2Cat10 != null && matchedRecord2Cat10.getData_segs() != null && matchedRecord2Cat10.getData_segs().size() > 0) {
        		type += CategoryType.RULE;
        		textFormat += atpcoRecordService.generateCategoryTextHeader(CategoryType.RULE, afdQuery.getTariffNo(), ruleTcd, afdQuery.getRuleNo(), matchedRecord2Cat10.getSeq_no(), matchedRecord2Cat10.getDates_eff());
        		
        		LinkedHashMap<String, List<DataTable>> groupedCat10DataTables = new LinkedHashMap<>();
        		
        		for (DataTable dt:matchedRecord2Cat10.getData_segs()) {
        			if (!dt.getCatNo().contentEquals(entry.getKey())) {
        				if (!groupedCat10DataTables.containsKey(dt.getCatNo())) {
            				List<DataTable> dts = new ArrayList<>();
            				dts.add(dt);
            				groupedCat10DataTables.put(dt.getCatNo(), dts);
            			} else {
            				groupedCat10DataTables.get(dt.getCatNo()).add(dt);
            			}
        			}
        		}
        		
        		for (Map.Entry<String, List<DataTable>> dtEntry:groupedCat10DataTables.entrySet()) {
        			CategoryTextFormatAndAttribute ctfa = atpcoRecordService.getAndConvertCategoryDataTable(dtEntry.getKey(), dtEntry.getValue(), CategoryType.RULE);
        			textFormat += ctfa.getTextFormat();
        			cat.getCatAttributes().addAll(ctfa.getAttributes());
        		}
        	}
        	
        	if (matchedGeneral != null) {
        		boolean resolveGeneralRule = true;
        		
        		if (matchedRecord2 != null && matchedRecord2.getGeneralRuleApplication() != null && 
        				matchedRecord2.getGeneralRuleApplication().trim().contentEquals("N")) {
        			resolveGeneralRule = false;
        		}
        		
        		if (resolveGeneralRule) {
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
            			String generalTCd = null;
            			if (generalTn != null) {
            				if (!generalTn.getTarNo().contentEquals(matchedGeneral.getSourceTariff())) {
            					generalTn = tariffNumberRepository.findOneByTarNoAndType(matchedGeneral.getSourceTariff(), "GENERAL RULE");
            				}
            				generalTCd = generalTn.getTarCd();
            			}
            			textFormat += atpcoRecordService.generateCategoryTextHeader(CategoryType.GENERAL_RULE, matchedGeneral.getSourceTariff(), generalTCd, matchedGeneral.getRuleNo(), matchedGeneralRecord2.getSequenceNo(), matchedGeneralRecord2.getEffectiveDateObject());
            			CategoryTextFormatAndAttribute ctfa = atpcoRecordService.getAndConvertCategoryDataTable(entry.getKey(), matchedGeneralRecord2.getDataTables(), CategoryType.GENERAL_RULE);
                		textFormat += ctfa.getTextFormat();
                		cat.getCatAttributes().addAll(ctfa.getAttributes());
            		}
        		}
    		}
        	
        	cat.setType(type);
        	cat.setTextFormat(textFormat);
        	
        	result.add(cat);
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    /**
     * POST  /afd-queries/add-ons : get all the afd queries add ons.
     *
     * @param query params, pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of Addons in body
     */
    @PostMapping("/afd-queries/add-ons")
    @Timed
    public ResponseEntity<AddOnsWrapper> getAllAfdQueryAddOns(@RequestBody AddOnParam param) {
        log.debug("REST request to get a page of AfdQuery AddOns: {}", param);
        
        AddOnsWrapper result = new AddOnsWrapper();
        
        if (param != null && param.getSource() != null) {
        	Pageable pageable = new PageRequest(param.getPage(), param.getSize());
            
            List<AfdQueryAddOns> addOns = new ArrayList<>();
            boolean isLastPage = false;
            int lastIndex = 0;
            
            //ATPCO
            if (param.getSource() != null && param.getSource().contentEquals("A")) {
            	AddOnsWrapper atpco = atpcoFareCustomRepository.findAtpcoAddOn(param, pageable);
            	addOns.addAll(atpco.getAddOns());
            	isLastPage = atpco.isLastPage();
                lastIndex = atpco.getLastIndex();
            }
            
            //Market
            if (param.getSource().contentEquals("M")) {
            	
            }
            
            result.setAddOns(addOns);
            result.setLastPage(isLastPage);
            result.setLastIndex(lastIndex);
        }
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("/afd-queries/fbr-query")
    @Timed
    public ResponseEntity<FbrQueryWrapper> getAllAfdQueryFbrs(@RequestBody FbrQueryParam param) {
    	System.out.println("=====================================================================================================================================");
    	log.debug("REST request to get a page of AfdQuery Fbrs: {}", param);
    	
    	Pageable pageable = new PageRequest(param.getPage(), param.getSize());
    	FbrQueryWrapper fbrQueryWrapper = atpcoFareCustomRepository.findAtpcoFbrs(param, pageable);
    	
    	return new ResponseEntity<>(fbrQueryWrapper, HttpStatus.OK);
    }
    
    @GetMapping("/afd-queries/fbr-query/rules")
	@Timed
	public ResponseEntity<List<Category>> getFbrQueryRules(FbrQuery fbrQuery) {
    	log.debug("REST request to get fbrQueries rules: {}", fbrQuery);

		List<Category> result = new ArrayList<>();
		String recordId = fbrQuery.getTarNo() + fbrQuery.getCxr() + fbrQuery.getRuleNo() + "";
		
		List<AtpcoRecord2> arecords2 = atpcoRuleQueryCustomRepository.getListRecord2ById(recordId);
		AtpcoMasterTariff ftntTn = tariffNumberRepository.findOneByTarNo(fbrQuery.getTarNo());
		String ftntTcd = null;
		
		if (ftntTn != null) {
			ftntTcd = ftntTn.getTarCd();
		}
		
		for (Map.Entry<String, String> entry : categories.entrySet()) {
			Category cat = new Category();
			cat.setCatName(entry.getValue());

			try {
				cat.setCatNo(Integer.parseInt(entry.getKey()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String type = "", allText = "";
			
			for (AtpcoRecord2 arecord2 : arecords2) {
				if (arecord2.getCatNo().contentEquals(entry.getKey())) {
        			List<DataTable> dataTables = arecord2.getDataTables();
        			
        			for (Iterator<DataTable> iterator = dataTables.iterator(); iterator.hasNext();) {
        				DataTable dt = iterator.next();
        				if (!dt.getCatNo().contentEquals(entry.getKey())) {
        					iterator.remove();
        				}
        			}
        			
        			if (dataTables.size() > 0) {
        				type = CategoryType.RULE;
        	        	String textFormat = atpcoRecordService.generateCategoryTextHeader(CategoryType.RULE, fbrQuery.getTarNo(), ftntTcd, null, arecord2.getSequenceNo(), arecord2.getEffectiveDateObject());
                		CategoryTextFormatAndAttribute ctfa = atpcoRecordService.getAndConvertCategoryDataTable(entry.getKey(), dataTables, CategoryType.RULE);
                		textFormat += ctfa.getTextFormat();
                		cat.getCatAttributes().addAll(ctfa.getAttributes());
                		allText += textFormat + "\n";
        			}
				}
			}
			
			if (!type.isEmpty()) {
				cat.setType(type);
			}
			
			cat.setTextFormat(allText);
			
			result.add(cat);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
