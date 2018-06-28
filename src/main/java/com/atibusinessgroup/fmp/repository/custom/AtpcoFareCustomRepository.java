package com.atibusinessgroup.fmp.repository.custom;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CategoryName;
import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFootnoteRecord2;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord1;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2Cat10;
import com.atibusinessgroup.fmp.domain.dto.AfdQuery;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryParam;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryWrapper;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFareAfdQueryWithRecords;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.CategoryObject;
import com.atibusinessgroup.fmp.service.AtpcoRecordService;
import com.atibusinessgroup.fmp.service.mapper.AfdQueryMapper;
import com.atibusinessgroup.fmp.service.util.DateUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class AtpcoFareCustomRepository {	
	
	private final AfdQueryMapper afdQueryMapper;
	
	private final AtpcoRecordService atpcoRecordService;
	
	private final MongoTemplate mongoTemplate;

	public AtpcoFareCustomRepository(AfdQueryMapper afdQueryMapper, AtpcoRecordService atpcoRecordService, MongoTemplate mongoTemplate) {
		this.afdQueryMapper = afdQueryMapper;
		this.atpcoRecordService = atpcoRecordService;
		this.mongoTemplate = mongoTemplate;
	}
	
	public AfdQueryWrapper findAtpcoFareAfdQueryWithRecords(AfdQueryParam param, String[] ruleCategories, Pageable pageable) {
		AfdQueryWrapper result = new AfdQueryWrapper();
		
		List<AfdQuery> afdQueries = new ArrayList<>();
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		aggregationOperations.add(new AggregationOperation() {
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
				
				if (param.getTariff() != null && !param.getTariff().isEmpty()) {
					BasicDBObject tariff = new BasicDBObject();
					tariff.append("tar_no", param.getTariff());
					queries.add(tariff);
				} else {
					BasicDBObject tariff = new BasicDBObject();
					tariff.append("tar_no", new BasicDBObject("$exists", "true"));
					queries.add(tariff);
				}
				
				if (param.getFareBasis() != null && !param.getFareBasis().isEmpty()) {
					BasicDBObject fareBasis = new BasicDBObject();
					String fbc = param.getFareBasis();
					
					if (param.getFareBasis().contains("*")) {
						fbc = "^" + param.getFareBasis().replace("*", ".*") + "$";
						fareBasis.append("fare_class_cd", new BasicDBObject("$regex", Pattern.compile(fbc)));
					} else if (param.getFareBasis().contains("-")) {
						fbc = "^" + param.getFareBasis().replace("-", "[A-Z0-9]{1}")  + "$";
						System.out.println(fbc);
						fareBasis.append("fare_class_cd", new BasicDBObject("$regex", Pattern.compile(fbc)));
					} else {
						fareBasis.append("fare_class_cd", fbc);
					}
					
					queries.add(fareBasis);
				} else {
					BasicDBObject fareBasis = new BasicDBObject();
					fareBasis.append("fare_class_cd", new BasicDBObject("$exists", "true"));
					queries.add(fareBasis);
				}
				
				if (param.getOrigin() != null && !param.getOrigin().isEmpty()) {
					BasicDBObject origin = new BasicDBObject();
					origin.append("origin_city", new BasicDBObject("$in",  Arrays.stream(param.getOrigin().split(",")).map(String::trim).toArray(String[]::new)));
					queries.add(origin);
				} else {
					BasicDBObject origin = new BasicDBObject();
					origin.append("origin_city", new BasicDBObject("$exists", "true"));
					queries.add(origin);
				}
				
				if (param.getDestination() != null && !param.getDestination().isEmpty()) {
					BasicDBObject destination = new BasicDBObject();
					destination.append("destination_city", new BasicDBObject("$in",  Arrays.stream(param.getDestination().split(",")).map(String::trim).toArray(String[]::new)));
					queries.add(destination);
				} else {
					BasicDBObject destination = new BasicDBObject();
					destination.append("destination_city", new BasicDBObject("$exists", "true"));
					queries.add(destination);
				}
				
				if (param.getOwrt() != null && !param.getOwrt().isEmpty()) {
					BasicDBObject owrt = new BasicDBObject();
					owrt.append("ow_rt", param.getOwrt());
					queries.add(owrt);
				} else {
					BasicDBObject owrt = new BasicDBObject();
					owrt.append("ow_rt", new BasicDBObject("$exists", "true"));
					queries.add(owrt);
				}
				
				if (param.getFootnote() != null && !param.getFootnote().isEmpty()) {
					BasicDBObject fnt = new BasicDBObject();
					fnt.append("ftnt", param.getFootnote());
					queries.add(fnt);
				} else {
					BasicDBObject fnt = new BasicDBObject();
					fnt.append("ftnt", new BasicDBObject("$exists", "true"));
					queries.add(fnt);
				}
				
				if (param.getRuleNo() != null && !param.getRuleNo().isEmpty()) {
					BasicDBObject ruleNo = new BasicDBObject();
					ruleNo.append("rules_no", param.getRuleNo());
					queries.add(ruleNo);
				} else {
					BasicDBObject ruleNo = new BasicDBObject();
					ruleNo.append("rules_no", new BasicDBObject("$exists", "true"));
					queries.add(ruleNo);
				}
				
				if (param.getRoutingNo() != null && !param.getRoutingNo().isEmpty()) {
					BasicDBObject routingNo = new BasicDBObject();
					routingNo.append("rtg_no", param.getRoutingNo());
					queries.add(routingNo);
				} else {
					BasicDBObject routingNo = new BasicDBObject();
					routingNo.append("rtg_no", new BasicDBObject("$exists", "true"));
					queries.add(routingNo);
				}
				
				Date paramFrom = DateUtil.convertObjectToDate(param.getEffectiveDateFrom());
				Date paramTo = DateUtil.convertObjectToDate(param.getEffectiveDateTo());

				if (param.getEffectiveDateOption() != null && param.getEffectiveDateOption().contentEquals("A")) {
					if (paramFrom != null && paramTo != null) {
						BasicDBObject effective = new BasicDBObject();
						effective.append("$and", Arrays.asList(new BasicDBObject("tar_eff_date", new BasicDBObject("$lte", paramFrom)), 
								new BasicDBObject("dates_discontinue", new BasicDBObject("$gte", paramTo))));
						queries.add(effective);
					} else {
						if (paramFrom == null && paramTo != null) {
							BasicDBObject effective = new BasicDBObject();
							effective.append("dates_discontinue", new BasicDBObject("$gte", paramTo));
							queries.add(effective);
						} else if (paramFrom != null && paramTo == null) {
							BasicDBObject effective = new BasicDBObject();
							effective.append("tar_eff_date", new BasicDBObject("$lte", paramFrom));
							queries.add(effective);
						}
					}
				} else if (param.getEffectiveDateOption() != null && param.getEffectiveDateOption().contentEquals("E")) {
					BasicDBObject effective = new BasicDBObject();
					effective.append("$and", Arrays.asList(new BasicDBObject("tar_eff_date", new BasicDBObject("$eq", paramFrom)), 
							new BasicDBObject("dates_discontinue", new BasicDBObject("$eq", paramTo))));
					queries.add(effective);
				}
				
				if (queries.size() > 0) {
					and.append("$and", queries);
				}
				
				match.append("$match", and);
				
				return match;
			}
		});
		
		ProjectionOperation projectRoot = new ProjectionOperation().and("$$ROOT").as("atpco_fare");
		aggregationOperations.add(projectRoot);
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject lookup = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("from", CollectionName.ATPCO_RECORD_1);
				query.append("let", new BasicDBObject("recordId", new BasicDBObject("$concat", Arrays.asList("$atpco_fare.tar_no", "$atpco_fare.cxr_cd", "$atpco_fare.rules_no", "$atpco_fare.fare_class_cd"))));
				BasicDBObject match = new BasicDBObject();
				match.append("$match", new BasicDBObject("$expr", new BasicDBObject("$eq", Arrays.asList("$record_id", "$$recordId"))));
				query.append("pipeline", Arrays.asList(new BasicDBObject(match)));
				query.append("as", "atpco_record_1");
				lookup.append("$lookup", query);
				return lookup;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject lookup = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("from", CollectionName.ATPCO_RECORD_2);
				query.append("let", new BasicDBObject("recordId", new BasicDBObject("$concat", Arrays.asList("$atpco_fare.tar_no", "$atpco_fare.cxr_cd", "$atpco_fare.rules_no"))));
				BasicDBObject match = new BasicDBObject();
				BasicDBObject and = new BasicDBObject();
				and.append("$and", Arrays.asList(new BasicDBObject("cat_no", new BasicDBObject("$in", ruleCategories)), new BasicDBObject("$expr", new BasicDBObject("$eq", Arrays.asList("$record_id", "$$recordId")))));
				match.append("$match", and);
				BasicDBObject group = new BasicDBObject();
				group.append("$group", new BasicDBObject("_id", "$cat_no").append("record_2", new BasicDBObject("$push", "$$ROOT")));
				BasicDBObject sort = new BasicDBObject();
				sort.append("$sort", new BasicDBObject("_id", 1));
				query.append("pipeline", Arrays.asList(match, group, sort));
				query.append("as", "atpco_record_2");
				lookup.append("$lookup", query);
				return lookup;
			}
		});
	    
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject lookup = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("from", CollectionName.ATPCO_FOOTNOTE_RECORD_2);
				query.append("let", new BasicDBObject("recordId", new BasicDBObject("$concat", Arrays.asList("$atpco_fare.tar_no", "$atpco_fare.cxr_cd", "$atpco_fare.ftnt"))));
				BasicDBObject match = new BasicDBObject();
				match.append("$match", new BasicDBObject("$expr", new BasicDBObject("$eq", Arrays.asList("$record_id", "$$recordId"))));
				BasicDBObject group = new BasicDBObject();
				group.append("$group", new BasicDBObject("_id", "$cat_no").append("record_2", new BasicDBObject("$push", "$$ROOT")));
				BasicDBObject sort = new BasicDBObject();
				sort.append("$sort", new BasicDBObject("_id", 1));
				query.append("pipeline", Arrays.asList(match, group, sort));
				query.append("as", "footnote_record");
				lookup.append("$lookup", query);
				return lookup;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject projectResult = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("_id", 0);
				query.append("atpco_fare", "$atpco_fare");
				query.append("atpco_record_1", "$atpco_record_1");
				query.append("atpco_record_2", "$atpco_record_2");
				query.append("footnote_record", "$footnote_record");
				projectResult.append("$project", query);
				return projectResult;
			}
		});
		
		LinkedHashMap<String, String> fareCategories = new LinkedHashMap<>();
		LinkedHashMap<String, String> fareFootnotes = new LinkedHashMap<>();
		LinkedHashMap<String, String> footnotes = new LinkedHashMap<>();
    	fareCategories.put("003", CategoryName.CAT_003);
    	fareCategories.put("005", CategoryName.CAT_005);
    	fareCategories.put("006", CategoryName.CAT_006);
    	fareCategories.put("007", CategoryName.CAT_007);
    	fareCategories.put("014", CategoryName.CAT_014);
    	fareCategories.put("015", CategoryName.CAT_015);
    	fareFootnotes.put("014", CategoryName.CAT_014);
    	fareFootnotes.put("015", CategoryName.CAT_015);
    	footnotes.put("014", CategoryName.CAT_014);
    	footnotes.put("015", CategoryName.CAT_015);
    	
    	int index = 0, currentAggregationLoop = 0, skipSize = 0, limitSize = pageable.getPageSize();
    	boolean isLastPage = false, isCompleted = false;
    	
    	while (!isCompleted) {
    		skipSize = param.getLastIndex() + (currentAggregationLoop * limitSize);
    		
    		if (currentAggregationLoop > 0) {
    			aggregationOperations.remove(aggregationOperations.size() - 1);
    			aggregationOperations.remove(aggregationOperations.size() - 1);
    		}
    		
    		SkipOperation skip = new SkipOperation(skipSize);
    		aggregationOperations.add(skip);
    		
    		LimitOperation limit = new LimitOperation(limitSize);
    		aggregationOperations.add(limit);
    		
    		Aggregation aggregation = newAggregation(aggregationOperations);
    		
    		AggregationResults<AtpcoFareAfdQueryWithRecords> a1fares = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_FARE, AtpcoFareAfdQueryWithRecords.class);
        	
    		if (a1fares.getMappedResults().size() == 0) {
    			isCompleted = true;
    			isLastPage = true;
    			index = 0;
    		} else {
    			index = 1;
    		}
    		
        	for (AtpcoFareAfdQueryWithRecords a1fare:a1fares) {
        		Date focusDate = null;
    	        
    	        atpcoRecordService.compareFareClass(null, null);
    	        
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
                		List<CategoryObject> ftntCats = atpcoRecordService.getAndConvertCategoryObjectDataTable(entry.getKey(), matchedRecord2.getDataTables(), "Footnote");
                		
                		switch (entry.getKey()) {
                			case "014": footnote14s = ftntCats;
                						break;
                			case "015": footnote15s = ftntCats;
    									break;
                		}
                	}
            	}
            	
            	AfdQuery afdQuery = afdQueryMapper.convertAtpcoFare(afare, matchedRecord1, cat03s, cat05s, cat06s, cat07s, cat14s, cat15s, 
            			footnote14s, footnote15s, focusDate);
            	
            	if (atpcoRecordService.compareAfdQueryWithParamString(afdQuery.getFareType(), param.getFareType()) &&
            			atpcoRecordService.compareAfdQueryWithParamString(afdQuery.getGlobalIndicator(), param.getGlobalIndicator()) &&
            			atpcoRecordService.compareAfdQueryWithParamString(afdQuery.getPaxType(), param.getPaxType()) && 
            			atpcoRecordService.compareAfdQueryWithParamString(afdQuery.getCabin(), param.getCabin()) && 
            			atpcoRecordService.compareAfdQueryWithParamString(afdQuery.getBookingClass(), param.getBookingClass()) &&
            			atpcoRecordService.compareAfdQueryWithParamString(afdQuery.getAdvancePurchase(), param.getAdvancePurchase()) &&
            			atpcoRecordService.compareAfdQueryWithParamString(afdQuery.getMinStay(), param.getMinStay()) &&
            			atpcoRecordService.compareAfdQueryWithParamString(afdQuery.getMaxStay(), param.getMaxStay()) &&
            			atpcoRecordService.compareAfdQueryWithParamString(afdQuery.getWpId(), param.getWoId()) &&
            			atpcoRecordService.compareAfdQueryWithParamString(afdQuery.getTourCode(), param.getTourCode()) &&
            			atpcoRecordService.compareAfdQueryWithParamDate(afdQuery.getSaleStartDate(), afdQuery.getSaleEndDate(), param.getSaleDateFrom(), 
            					param.getSaleDateTo(), param.getSaleDateOption()) &&
            			atpcoRecordService.compareAfdQueryWithParamDate(afdQuery.getTravelStartDate(), afdQuery.getTravelEndDate(), param.getTravelDateFrom(), 
            					param.getTravelDateTo(), param.getTravelDateOption()) &&
            			atpcoRecordService.compareAfdQueryWithParamDate(afdQuery.getFirstSeasonDate(), afdQuery.getLastSeasonDate(), param.getSeasonDateFrom(), 
            					param.getSeasonDateTo(), param.getSeasonDateOption())) {
            		afdQueries.add(afdQuery);
            	}
            	
    			if (afdQueries.size() == pageable.getPageSize()) {
    				isCompleted = true;
    				break;
    			}
    			
    			index++;
        	}
        	
        	currentAggregationLoop++;
    	}
    	
    	if (!isLastPage && afdQueries.size() < pageable.getPageSize()) {
    		isLastPage = true;
    	}
    	
    	result.setLastPage(isLastPage);
    	result.setLastIndex(skipSize + index);
    	result.setAfdQueries(afdQueries);
		
		return result;
	}

	public List<AtpcoRecord2GroupByCatNo> findAtpcoRecord2ByRecordId(String recordId) {
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		MatchOperation match = new MatchOperation(new Criteria("record_id").is(recordId));
		aggregationOperations.add(match);
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("_id", "$cat_no");
				query.append("record_2", new BasicDBObject("$push", "$$ROOT"));
				group.append("$group", query);
				return group;
			}
		});
		
		SortOperation sort = new SortOperation(new Sort(Direction.ASC, "_id"));
		aggregationOperations.add(sort);
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		List<AtpcoRecord2GroupByCatNo> result = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_RECORD_2, AtpcoRecord2GroupByCatNo.class).getMappedResults();
		
		return result;
	}
	
	public List<AtpcoRecord2Cat10> findAtpcoRecord2Cat10ByRecordId(String recordId) {
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		MatchOperation match = new MatchOperation(new Criteria("record_id").is(recordId));
		aggregationOperations.add(match);
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		List<AtpcoRecord2Cat10> result = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_RECORD_2_10, AtpcoRecord2Cat10.class).getMappedResults();
		
		return result;
	}

	public List<AtpcoFootnoteRecord2GroupByCatNo> findAtpcoFootnoteRecord2ByRecordId(String recordId) {
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		MatchOperation match = new MatchOperation(new Criteria("record_id").is(recordId));
		aggregationOperations.add(match);
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("_id", "$cat_no");
				query.append("record_2", new BasicDBObject("$push", "$$ROOT"));
				group.append("$group", query);
				return group;
			}
		});
		
		SortOperation sort = new SortOperation(new Sort(Direction.ASC, "_id"));
		aggregationOperations.add(sort);
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		List<AtpcoFootnoteRecord2GroupByCatNo> result = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_FOOTNOTE_RECORD_2, AtpcoFootnoteRecord2GroupByCatNo.class).getMappedResults();
		
		return result;
	}
}
