package com.atibusinessgroup.fmp.repository.custom;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CategoryName;
import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFootnoteRecord2;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord1;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryParam;
import com.atibusinessgroup.fmp.domain.dto.CategoryObject;
import com.atibusinessgroup.fmp.domain.dto.DataTable;
import com.atibusinessgroup.fmp.domain.dto.SpecifiedConstructed;
import com.atibusinessgroup.fmp.service.AtpcoRecordService;
import com.atibusinessgroup.fmp.service.mapper.AfdQueryMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class CompetitorMonitoringCustomRepository {	
	
	private final AfdQueryMapper afdQueryMapper;
	private final MongoTemplate mongoTemplate;
	private final AtpcoRecordService atpcoRecordService;
	
	public CompetitorMonitoringCustomRepository(AfdQueryMapper afdQueryMapper, AtpcoRecordService atpcoRecordService, MongoTemplate mongoTemplate) {
		this.afdQueryMapper = afdQueryMapper;
		this.atpcoRecordService = atpcoRecordService;
		this.mongoTemplate = mongoTemplate;
		
	}
	
	public Page<SpecifiedConstructed> getCompetitorQueries(AfdQueryParam param, Pageable pageable) {
		
		List<SpecifiedConstructed> afdQueries = new ArrayList<>();
		
		List<AggregationOperation> listAggregationOps = new ArrayList<>();
		
		listAggregationOps.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject and = new BasicDBObject();
				List<BasicDBObject> queries = new ArrayList<>();
				
				if (param.getCarrier() != null && !param.getCarrier().isEmpty()) {
					BasicDBObject carrier = new BasicDBObject();
					carrier.append("cxr_cd", new BasicDBObject("$in",  Arrays.stream(param.getCarrier().split(",")).map(String::trim).toArray(String[]::new)));
					queries.add(carrier);
				} else {
					BasicDBObject carrier = new BasicDBObject();
					carrier.append("cxr_cd", new BasicDBObject("$exists", "true"));
					queries.add(carrier);
				}
				
				queries.add(new BasicDBObject("tar_no", new BasicDBObject("$exists", "true")));
				
				if (param.getFareBasis() != null && !param.getFareBasis().isEmpty()) {
					BasicDBObject fbcOr = new BasicDBObject();
					String[] fbcParts = param.getFareBasis().split(",");
					List<BasicDBObject> orQueries = new ArrayList<>();
					
					for (String fbc:fbcParts) {
						fbc = fbc.trim();
						BasicDBObject query = new BasicDBObject();
						
						if (fbc.contains("*") && fbc.contains("-")) {
							String convertStar = fbc.replace("*", ".*");
							String convertHyphen = convertStar.replace("-", "[A-Z0-9]{1}");
							fbc = "^" + convertHyphen + "$";
							query.append("fare_class_cd", new BasicDBObject("$regex", Pattern.compile(fbc)));
						} else if (fbc.contains("*")) {
							fbc = "^" + fbc.replace("*", ".*") + "$";
							query.append("fare_class_cd", new BasicDBObject("$regex", Pattern.compile(fbc)));
						} else if (fbc.contains("-")) {
							fbc = "^" + fbc.replace("-", "[A-Z0-9]{1}")  + "$";
							query.append("fare_class_cd", new BasicDBObject("$regex", Pattern.compile(fbc)));
						} else {
							query.append("fare_class_cd", fbc);
						}
						
						orQueries.add(query);
					}
					
					if (orQueries.size() > 0) {
						fbcOr.append("$or", orQueries);
						queries.add(fbcOr);
					}
				} else {
					BasicDBObject fareBasis = new BasicDBObject();
					fareBasis.append("fare_class_cd", new BasicDBObject("$exists", "true"));
					queries.add(fareBasis);
				}
				
				if(param.getOrigin() != null && !param.getOrigin().isEmpty()) {
					List<String> listOrigin = new ArrayList<>();
					List<String> listDest = new ArrayList<>();
					String[] origDestArr = Arrays.stream(param.getOrigin().split(",")).map(String::trim).toArray(String[]::new);
					for(int i=0; i< origDestArr.length; i++) {
						int dash = origDestArr[i].indexOf("-");
						String orig = origDestArr[i].substring(0, dash);
						String dest = origDestArr[i].substring(dash+1, origDestArr[i].length());
						
						listOrigin.add(orig);
						listDest.add(dest);
					}
					
					queries.add(new BasicDBObject("origin_city", new BasicDBObject("$in", listOrigin)));
					queries.add(new BasicDBObject("destination_city", new BasicDBObject("$in", listDest)));
					
					
				} else {
					queries.add(new BasicDBObject("origin_city", new BasicDBObject("$exists", "true")));
					queries.add(new BasicDBObject("destination_city", new BasicDBObject("$exists", "true")));
				}
				
				queries.add(new BasicDBObject("global_indicator", new BasicDBObject("$exists", "true")));
				
				if (param.getOwrt() != null && !param.getOwrt().isEmpty()) {
					BasicDBObject owrt = new BasicDBObject();
					owrt.append("ow_rt", param.getOwrt());
					queries.add(owrt);
				} else {
					BasicDBObject owrt = new BasicDBObject();
					owrt.append("ow_rt", new BasicDBObject("$exists", "true"));
					queries.add(owrt);
				}
				
				queries.add(new BasicDBObject("ftnt", new BasicDBObject("$exists", "true")));
				
				if (param.getRuleNo() != null && !param.getRuleNo().isEmpty()) {
					BasicDBObject ruleNo = new BasicDBObject();
					ruleNo.append("rules_no", new BasicDBObject("$in",  Arrays.stream(param.getRuleNo().split(",")).map(String::trim).toArray(String[]::new)));
					queries.add(ruleNo);
				} else {
					BasicDBObject ruleNo = new BasicDBObject();
					ruleNo.append("rules_no", new BasicDBObject("$exists", "true"));
					queries.add(ruleNo);
				}
				
				queries.add(new BasicDBObject("rtg_no", new BasicDBObject("$exists", "true")));
				
				queries.add(new BasicDBObject("tar_eff_date", new BasicDBObject("$exists", "true")));
				queries.add(new BasicDBObject("dates_discontinue", new BasicDBObject("$exists", "true")));
				
				and.append("$and", queries);
				match.append("$match", and);
				return match;
			}
		});
		
		
		List<String> listPP = new ArrayList<>();
		if(param.getPublicPrivate() != null && !param.getPublicPrivate().isEmpty()) {
			
			listPP.add(param.getPublicPrivate().toLowerCase());
		} else {
			listPP.add("public");
			listPP.add("private");
		}
		
		listAggregationOps.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject lookup = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("from", CollectionName.ATPCO_MASTER_TARIFF);
				query.append("let", new BasicDBObject("tar_no", "$tar_no"));
				query.append("pipeline", Arrays.asList(new BasicDBObject("$match", 
						new BasicDBObject("$expr", 
								new BasicDBObject("$and", 
										Arrays.asList(
												new BasicDBObject("$eq", Arrays.asList("$tar_no","$$tar_no")),
												new BasicDBObject("$in", Arrays.asList("$pp", listPP))
												)
										)
								)
						)));
				query.append("as", "m_tariff");
				lookup.append("$lookup", query);
				return lookup;
			}
		});
		
		if(param.getPublicPrivate() != null && !param.getPublicPrivate().isEmpty()) {
			
			listAggregationOps.add(new AggregationOperation() {
				
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject match = new BasicDBObject();
					match.append("$match", new BasicDBObject("m_tariff", new BasicDBObject("$ne", Arrays.asList())));
					return match;
				}
			});
		}
		
		Aggregation aggregation = newAggregation(listAggregationOps);
		
		
		LinkedHashMap<String, String> fareCategories = new LinkedHashMap<>();
		LinkedHashMap<String, String> fareFootnotes = new LinkedHashMap<>();
		LinkedHashMap<String, String> footnotes = new LinkedHashMap<>();
    	fareCategories.put("014", CategoryName.CAT_014);
    	fareCategories.put("015", CategoryName.CAT_015);
    	fareFootnotes.put("014", CategoryName.CAT_014);
    	fareFootnotes.put("015", CategoryName.CAT_015);
    	footnotes.put("014", CategoryName.CAT_014);
    	footnotes.put("015", CategoryName.CAT_015);
    	
    	List<String> ruleCategories = new ArrayList<>();
    	
    	for (Map.Entry<String, String> entry:fareCategories.entrySet()) {
    		ruleCategories.add(entry.getKey());
    	}
    	
		for (AtpcoFare afare : mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_FARE, AtpcoFare.class).getMappedResults()) {

			List<AggregationOperation> listAgg = new ArrayList<>();
			List<CategoryObject> cat14s = null;
        	List<CategoryObject> cat15s = null;
        	List<CategoryObject> footnote14s = null;
        	List<CategoryObject> footnote15s = null;
			
			listAgg.add(new AggregationOperation() {
				
				@Override
				public DBObject toDBObject(AggregationOperationContext context) {
					BasicDBObject match = new BasicDBObject();
					match.append("$match", new BasicDBObject("record_id", afare.getTariffNo()+afare.getCarrierCode()+afare.getRuleNo()+afare.getFareClassCode()));
					return match;
				}
			});
			
			Aggregation aggRec1 = newAggregation(listAgg);

	    	AtpcoRecord1 matchedRecord1 = null;
			Date focusDate = null;
			focusDate = atpcoRecordService.resolveFocusDate(null, afare.getTariffEffectiveDateObject(), afare.getDiscontinueDateObject());
			
			
			for (AtpcoRecord1 record1 : mongoTemplate.aggregate(aggRec1, CollectionName.ATPCO_RECORD_1, AtpcoRecord1.class).getMappedResults()) {
				boolean matched = atpcoRecordService.compareMatchingFareAndRecord("C", afare.getOriginCity(), "C", afare.getDestinationCity(), afare.getOwrt(), afare.getRoutingNo(), afare.getFootnote(), focusDate,
						record1.getGeoType1(), record1.getGeoLoc1(), record1.getGeoType2(), record1.getGeoLoc2(), record1.getOwrt(), record1.getRoutingNo(), record1.getFootnote(), record1.getEffectiveDateObject(), record1.getDiscontinueDateObject());
	    		
	    		if (matched) {
	    			matchedRecord1 = record1;
	    			break;
	    		}
			}
			
			if(afare.getFootnote() != null && !afare.getFootnote().trim().isEmpty()) {
				
				//Footnote
				
				List<AggregationOperation> listAggFtnt = new ArrayList<>();
				
				listAggFtnt.add(new AggregationOperation() {
					
					@Override
					public DBObject toDBObject(AggregationOperationContext context) {
						BasicDBObject match = new BasicDBObject();
						match.append("$match", new BasicDBObject("record_id", afare.getTariffNo()+afare.getCarrierCode()+afare.getFootnote()));
						return match;
					}
				});
				
				Aggregation aggregateFtnt = newAggregation(listAggFtnt);
				
				for (Map.Entry<String, String> entry : footnotes.entrySet()) {
            		AtpcoFootnoteRecord2 matchedRecord2 = null;
				
					for (AtpcoFootnoteRecord2 record2:mongoTemplate.aggregate(aggregateFtnt, CollectionName.ATPCO_FOOTNOTE_RECORD_2, AtpcoFootnoteRecord2.class).getMappedResults()) {
						
						if (record2.getCatNo().contentEquals(entry.getKey())) {
								boolean matched = atpcoRecordService.compareMatchingFareAndRecord("C", afare.getOriginCity(), "C", afare.getDestinationCity(), afare.getOwrt(), afare.getRoutingNo(), afare.getFootnote(), focusDate,
			        					record2.getGeoType1(), record2.getGeoLoc1(), record2.getGeoType2(), record2.getGeoLoc2(), record2.getOwrt(), record2.getRoutingNo(), record2.getFootnote(), record2.getEffectiveDateObject(), record2.getDiscontinueDateObject());
			                	
			            		if (matched) {
			            			matchedRecord2 = record2;
			            			break;
			            		} 
                    		
                    		break;
                    	}
					}
						
						
						if (matchedRecord2 != null && matchedRecord2.getDataTables() != null && matchedRecord2.getDataTables().size() > 0) {
	                		List<CategoryObject> ftntCats = atpcoRecordService.getAndConvertCategoryObjectDataTable(entry.getKey(), matchedRecord2.getDataTables(), "Footnote");
	                		
	                		
	                		switch (entry.getKey()) {
	                			case "014": 
	                					footnote14s = ftntCats;
	                				break;
	                			case "015": 
	                					footnote15s = ftntCats;
	    							break;
	                		}
	                	}
	        			
				}
				
				
			} else {
				//Rule
				
				List<AggregationOperation> listAggRule  = new ArrayList<>();
				
				listAggRule.add(new AggregationOperation() {
					
					@Override
					public DBObject toDBObject(AggregationOperationContext context) {
						BasicDBObject match = new BasicDBObject();
						match.append("$match", new BasicDBObject("record_id", afare.getTariffNo()+afare.getCarrierCode()+afare.getFareClassCode()));
						return match;
					}
				});
				
				Aggregation aggregateRule = newAggregation(listAggRule);
				
            	for (Map.Entry<String, String> entry : fareCategories.entrySet()) {
            		AtpcoRecord2 matchedRecord2 = null;
            		
            		for (AtpcoRecord2 record2:mongoTemplate.aggregate(aggregateRule, CollectionName.ATPCO_RECORD_2, AtpcoRecord2.class).getMappedResults()) {
            			if (record2.getCatNo().contentEquals(entry.getKey())) {
            			boolean matched = atpcoRecordService.compareMatchingFareAndRecord("C", afare.getOriginCity(), "C", afare.getDestinationCity(), afare.getFareClassCode(), afare.getFareType(), matchedRecord1 != null ? matchedRecord1.getSeasonType() : null, matchedRecord1 != null ? matchedRecord1.getDayOfWeekType() : null, afare.getOwrt(), afare.getRoutingNo(), afare.getFootnote(), focusDate,
            					record2.getGeoType1(), record2.getGeoLoc1(), record2.getGeoType2(), record2.getGeoLoc2(), record2.getFareClass(), record2.getFareType(), record2.getSeasonType(), record2.getDayOfWeekType(), record2.getOwrt(), record2.getRoutingNo(), record2.getFootnote(), record2.getEffectiveDateObject(), record2.getDiscontinueDateObject());
	                    	
	                		if (matched) {
	                			matchedRecord2 = record2;
	                			break;
	                		} 
            			}
            		}
            		
            		if (matchedRecord2 != null && matchedRecord2.getDataTables() != null && matchedRecord2.getDataTables().size() > 0) {
            			List<DataTable> rec2DataTables = matchedRecord2.getDataTables();
            			
            			for (Iterator<DataTable> iterator = rec2DataTables.iterator(); iterator.hasNext();) {
            				DataTable dt = iterator.next();
            				if (!dt.getCatNo().contentEquals(entry.getKey())) {
            					iterator.remove();
            				}
            			}
            			
                		List<CategoryObject> rules = atpcoRecordService.getAndConvertCategoryObjectDataTable(entry.getKey(), rec2DataTables, "Rule");
                		
                		switch (entry.getKey()) {
                		case "014": 
                				cat14s = rules;
							break; 
                		case "015":
                				cat15s = rules;
							break;
                		}
                	}
            	}
			}
//			System.out.println(matchedRecord1.toString());
			
			SpecifiedConstructed afdQuery = afdQueryMapper.convertAtpcoFare(afare, matchedRecord1, null, null, null, null, cat14s, cat15s, null, null, null,
        			footnote14s, footnote15s, focusDate);
        	
			
			if (param.getSaleDateFrom() != null || param.getSaleDateTo() != null || param.getTravelDateFrom() != null || param.getTravelDateTo() != null) {
        		if (atpcoRecordService.compareValueWithParamDate(afdQuery.getSaleDates(), param.getSaleDateFrom(), param.getSaleDateTo(), param.getSaleDateOption()) &&
            		atpcoRecordService.compareValueWithParamDate(afdQuery.getTravelDates(), param.getTravelDateFrom(), param.getTravelDateTo(), param.getTravelDateOption())) {
            		afdQueries.add(afdQuery);
            	}
        	} else {
        		afdQueries.add(afdQuery);
        	}
			
			
		}
		
		System.out.println(aggregation);
		
		return new PageImpl<>(afdQueries, pageable, afdQueries.size());
	}
	
}
