package com.atibusinessgroup.fmp.repository.custom;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord3Cat50GeoLocation;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord8;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByRuleNoCxrTarNo;
import com.atibusinessgroup.fmp.domain.dto.Rec8Param;
import com.atibusinessgroup.fmp.domain.dto.RuleQueryParam;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class AtpcoRuleQueryCustomRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
	
	public Page<AtpcoRecord2GroupByRuleNoCxrTarNo> getGroupingRuleQueries(RuleQueryParam param, Pageable pageable) {
		
		if(param.getType() != null && !param.getType().isEmpty()) {
			
			return getQueryByType(param, pageable);
			
		} else {
			
			return getAllTypeQueries(param, pageable);
			
		}
	}
	
	public Page<AtpcoRecord2GroupByRuleNoCxrTarNo> getAllTypeQueries(RuleQueryParam param, Pageable pageable) {
		
		SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
		LimitOperation limit = new LimitOperation(pageable.getPageSize());
		
		List<AggregationOperation> aggregationOperations = getAggregationGroupingAllType(param); 
		
		
		List<AggregationOperation> aggregationCount = new ArrayList<>();

		aggregationCount.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject andQuery = new BasicDBObject();

				List<BasicDBObject> and = new ArrayList<>();
				
				Date today = getCalendarDate(0);
				Date twoYearsBefore = getCalendarDate(1);

				if (param.getCxr() != null && !param.getCxr().isEmpty()) {
					and.add(new BasicDBObject("cxr_code", param.getCxr()));
				} else {
					and.add(new BasicDBObject("cxr_code", new BasicDBObject("$ne", "")));
				}
				
				if (param.getRuleNo() != null && !param.getRuleNo().isEmpty()) {
					and.add(new BasicDBObject("rule_no", param.getRuleNo()));
				} else {
					and.add(new BasicDBObject("rule_no", new BasicDBObject("$ne", "")));
				}

				if (param.getRuleTarNo() != null && !param.getRuleTarNo().isEmpty()) {
					and.add(new BasicDBObject("rule_tar_no", param.getRuleTarNo()));
				} else {
					and.add(new BasicDBObject("rule_tar_no", new BasicDBObject("$ne", "")));
				}
				
				if (param.getCatNo() != null && !param.getCatNo().isEmpty()) {
					and.add(new BasicDBObject("cat_no", param.getCatNo()));
				} else {
					and.add(new BasicDBObject("cat_no", new BasicDBObject("$ne", "")));
				}
				
				if(param.isIncludeDisc()) {
					and.add(new BasicDBObject("$or", Arrays.asList(
							new BasicDBObject("dates_disc", new BasicDBObject("$lte", today).append("$gte", twoYearsBefore)), 
							new BasicDBObject("dates_disc", "indef"))));
					
				} else {
					and.add(new BasicDBObject("$or", Arrays.asList(
							new BasicDBObject("dates_disc", new BasicDBObject("$gte", today)), 
							new BasicDBObject("dates_disc", "indef"))));
				}

				if (and.size() > 0) {
					andQuery.append("$and", and);
				}

				match.append("$match", andQuery);

				return match;
			}
		});
		
		aggregationCount.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject();
				BasicDBObject groupId = new BasicDBObject();
				BasicDBObject groupList = new BasicDBObject();
				groupList.put("rule_no", "$rule_no");
				groupList.put("cxr_code", "$cxr_code");
				groupList.put("rule_tar_no", "$rule_tar_no");
				groupId.append("_id", groupList);
				group.append("$group", groupId);
				return group;
			}
		});
	
		Aggregation aggregation = newAggregation(aggregationCount);

		aggregationOperations.add(skip);

		aggregationOperations.add(limit);

		Aggregation aggregationPagination = newAggregation(aggregationOperations);

		List<AtpcoRecord2GroupByRuleNoCxrTarNo> result = mongoTemplate.aggregate(aggregationPagination, "atpco_record_2", AtpcoRecord2GroupByRuleNoCxrTarNo.class).getMappedResults();
		return new PageImpl<>(result, pageable, mongoTemplate.aggregate(aggregation, "atpco_record_2", AtpcoRecord2GroupByRuleNoCxrTarNo.class).getMappedResults().size());
		
	}
	
	public Page<AtpcoRecord2GroupByRuleNoCxrTarNo> getQueryByType(RuleQueryParam param, Pageable pageable) {
		
		List<AggregationOperation> aggregationOperations = getAggregationGroupingByType(param);
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		

		SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
		aggregationOperations.add(skip);

		LimitOperation limit = new LimitOperation(pageable.getPageSize());
		aggregationOperations.add(limit);

		Aggregation aggregationPagination = newAggregation(aggregationOperations);

		List<AtpcoRecord2GroupByRuleNoCxrTarNo> result = mongoTemplate.aggregate(aggregationPagination, CollectionName.ATPCO_RECORD_2, AtpcoRecord2GroupByRuleNoCxrTarNo.class).getMappedResults();
		
		return new PageImpl<>(result, pageable, mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_RECORD_2, AtpcoRecord2GroupByRuleNoCxrTarNo.class).getMappedResults().size());
	}
	
	public List<AtpcoRecord2> getRuleDetails(RuleQueryParam param) {
		
		System.out.println(param.toString());
		
		List<AtpcoRecord2> rec2 = new ArrayList<>();
		
		Date today = getCalendarDate(0);
		Date twoYearsBefore = getCalendarDate(1);
		
		String recordId = param.getRuleTarNo()+param.getCxr()+param.getRuleNo();
		Query query = new Query();

		query.addCriteria(Criteria.where("record_id").is(recordId));
		
		Criteria includeDisc1 = Criteria.where("dates_disc").lte(today).gte(twoYearsBefore);
		Criteria includeDisc2 = Criteria.where("dates_disc").is("indef");
		Criteria orInclude1 = new Criteria();
		orInclude1.orOperator(includeDisc1, includeDisc2);
		
		Criteria notIncludeDisc1 = Criteria.where("dates_disc").gte(today);
		Criteria orInclude2 = new Criteria();
		orInclude2.orOperator(notIncludeDisc1, includeDisc2);
		
		if(param.isIncludeDisc()) {
			query.addCriteria(orInclude1);
		} else {
			query.addCriteria(orInclude2);
		}
		
		
		rec2 = mongoTemplate.find(query, AtpcoRecord2.class);
		
		return rec2;
		
	}

	
	public List<AtpcoRecord2> getListRecord2ById(String recordId, String catNo) {
		List<AtpcoRecord2> result = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("record_id").is(recordId));
		result = mongoTemplate.find(query, AtpcoRecord2.class);
		return result;
	}
	
	// GET RECORD 8
	public Page<AtpcoRecord8> getListRec8(Rec8Param param, Pageable pageable) {
		
		SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
		LimitOperation limit = new LimitOperation(pageable.getPageSize());
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();

		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject andQuery = new BasicDBObject();

				List<BasicDBObject> and = new ArrayList<>();
				
				Date today = getCalendarDate(0);
				Date twoYearsBefore = getCalendarDate(1);

				if (param.getCxr() != null && !param.getCxr().isEmpty()) {
					and.add(new BasicDBObject("cxr_code", param.getCxr()));
				} else {
					and.add(new BasicDBObject("cxr_code", new BasicDBObject("$ne", "")));
				}
				
				if (param.getRuleNo() != null && !param.getRuleNo().isEmpty()) {
					and.add(new BasicDBObject("rule_no", param.getRuleNo()));
				} else {
					and.add(new BasicDBObject("rule_no", new BasicDBObject("$ne", "")));
				}

				if (param.getRuleTarNo() != null && !param.getRuleTarNo().isEmpty()) {
					and.add(new BasicDBObject("tariff", param.getRuleTarNo()));
				} else {
					and.add(new BasicDBObject("tariff", new BasicDBObject("$ne", "")));
				}
				
				if (param.getAccountCode() != null && !param.getAccountCode().isEmpty()) {
					and.add(new BasicDBObject("account_code", param.getAccountCode()));
				} else {
					and.add(new BasicDBObject("account_code", new BasicDBObject("$exists", true)));
				}
				
				
				if(param.isIncludeDisc()) {
					and.add(new BasicDBObject("$or", Arrays.asList(
							new BasicDBObject("dates_disc", new BasicDBObject("$lte", today).append("$gte", twoYearsBefore)), 
							new BasicDBObject("dates_disc", "indef"))));
					
				} else {
					and.add(new BasicDBObject("$or", Arrays.asList(
							new BasicDBObject("dates_disc", new BasicDBObject("$gte", today)), 
							new BasicDBObject("dates_disc", "indef"))));
				}

				if (and.size() > 0) {
					andQuery.append("$and", and);
				}

				match.append("$match", andQuery);

				return match;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {

			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				
				BasicDBObject lookup = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
			
				query.append("from", CollectionName.ATPCO_MASTER_TARIFF);
				
				query.append("let", new BasicDBObject("tariff" ,"$tariff"));
				
				query.append("pipeline",  Arrays.asList(
						new BasicDBObject("$match", new BasicDBObject("$expr", new BasicDBObject("$and", Arrays.asList(
								new BasicDBObject("$eq", Arrays.asList("$tar_no", "$$tariff")), 
								new BasicDBObject("$eq", Arrays.asList("$type", "FARE BY RULE")))
						)))
				));
				

				query.append("as", "m_tariff");
				
				lookup.append("$lookup", query);
				
				return lookup;
			}
			
		});
		
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject unwind = new BasicDBObject();
				unwind.append("$unwind", new BasicDBObject("path", "$m_tariff").append("preserveNullAndEmptyArrays", true));
				return unwind;
			}
		});
		
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject projection = new BasicDBObject();
				projection.append("$project", 
						new BasicDBObject("cxr_code", "$cxr_code")
						.append("tariff", "$tariff")
						.append("tar_cd", "$m_tariff.tar_cd")
						.append("tar_desc", "$m_tariff.description")
						.append("rule_no", "$rule_no")
						.append("cat35", "$cat35")
						.append("prim_pass_type", "$prim_pass_type")
						.append("account_code", "$account_code")
						.append("fare_geo_scope_global", "$fare_geo_scope_global")
						.append("fare_geo_scope_loc_1", "$fare_geo_scope_loc_1")
						.append("fare_geo_scope_loc_2", "$fare_geo_scope_loc_2")
						.append("dates_eff","$dates_eff")
						.append("dates_disc", "$dates_disc")
						);
				return projection;
			}
		});
		
		
		Aggregation aggregation = newAggregation(aggregationOperations);

		aggregationOperations.add(skip);

		aggregationOperations.add(limit);

		Aggregation aggregationPagination = newAggregation(aggregationOperations);

		List<AtpcoRecord8> result = mongoTemplate.aggregate(aggregationPagination, CollectionName.ATPCO_RECORD_8, AtpcoRecord8.class).getMappedResults();
		
		return new PageImpl<>(result, pageable, mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_RECORD_8, AtpcoRecord8.class).getMappedResults().size());
	
	}
	
	/* 	
 	==========================================================================================================================================	
	Aggregation Type
	==========================================================================================================================================
	*/
	
	//ALL
	public List<AggregationOperation> getAggregationGroupingAllType(RuleQueryParam param) {

		List<AggregationOperation> aggregationOperations = new ArrayList<>();

		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject andQuery = new BasicDBObject();

				List<BasicDBObject> and = new ArrayList<>();
				
				Date today = getCalendarDate(0);
				Date twoYearsBefore = getCalendarDate(1);

				if (param.getCxr() != null && !param.getCxr().isEmpty()) {
					and.add(new BasicDBObject("cxr_code", param.getCxr()));
				} else {
					and.add(new BasicDBObject("cxr_code", new BasicDBObject("$ne", "")));
				}
				
				if (param.getRuleNo() != null && !param.getRuleNo().isEmpty()) {
					and.add(new BasicDBObject("rule_no", param.getRuleNo()));
				} else {
					and.add(new BasicDBObject("rule_no", new BasicDBObject("$ne", "")));
				}

				if (param.getRuleTarNo() != null && !param.getRuleTarNo().isEmpty()) {
					and.add(new BasicDBObject("rule_tar_no", param.getRuleTarNo()));
				} else {
					and.add(new BasicDBObject("rule_tar_no", new BasicDBObject("$ne", "")));
				}
				
				if (param.getCatNo() != null && !param.getCatNo().isEmpty()) {
					and.add(new BasicDBObject("cat_no", param.getCatNo()));
				} else {
					and.add(new BasicDBObject("cat_no", new BasicDBObject("$ne", "")));
				}
				
				if(param.isIncludeDisc()) {
					and.add(new BasicDBObject("$or", Arrays.asList(
							new BasicDBObject("dates_disc", new BasicDBObject("$lte", today).append("$gte", twoYearsBefore)), 
							new BasicDBObject("dates_disc", "indef"))));
					
				} else {
					and.add(new BasicDBObject("$or", Arrays.asList(
							new BasicDBObject("dates_disc", new BasicDBObject("$gte", today)), 
							new BasicDBObject("dates_disc", "indef"))));
				}

				if (and.size() > 0) {
					andQuery.append("$and", and);
				}

				match.append("$match", andQuery);

				return match;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject();
				BasicDBObject groupId = new BasicDBObject();
				BasicDBObject groupList = new BasicDBObject();
				groupList.put("rule_no", "$rule_no");
				groupList.put("cxr_code", "$cxr_code");
				groupList.put("rule_tar_no", "$rule_tar_no");
				groupId.append("_id", groupList);
				group.append("$group", groupId);
				return group;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {

			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				
				BasicDBObject lookupRec0 = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				
				//RECORD 0 LOOKUP
				query.append("from", CollectionName.ATPCO_RECORD_0);
				
				query.append("let", new BasicDBObject("cxrCode" ,"$_id.cxr_code").append("ruleNo" , "$_id.rule_no").append("tarNo" , "$_id.rule_tar_no"));
				
				query.append("pipeline",  Arrays.asList(
						new BasicDBObject("$match", new BasicDBObject("$expr", new BasicDBObject("$eq", Arrays.asList( "$cxr_code", "$$cxrCode")))), 
						new BasicDBObject("$unwind", "$general_rule_application"), 
						new BasicDBObject("$match", new BasicDBObject("$expr", new BasicDBObject("$and", Arrays.asList(
								new BasicDBObject("$eq", Arrays.asList("$general_rule_application.rule_no", "$$ruleNo")), 
								new BasicDBObject("$eq", Arrays.asList("$general_rule_application.src_tar", "$$tarNo"))))
						))
				));
				

				query.append("as", "atpco_record_0");
				
				lookupRec0.append("$lookup", query);
				
				return lookupRec0;
			}
			
		});
		
		aggregationOperations.add(new AggregationOperation() {

			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				
				BasicDBObject lookupRec8 = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				
				//RECORD 8 LOOKUP
				query.append("from", CollectionName.ATPCO_RECORD_8);
				
				query.append("let", new BasicDBObject("cxrCode" ,"$_id.cxr_code").append("ruleNo" , "$_id.rule_no").append("tarNo" , "$_id.rule_tar_no"));
				
				query.append("pipeline",  Arrays.asList(new BasicDBObject("$match", 
						new BasicDBObject("$expr", 
								new BasicDBObject("$and", Arrays.asList(
										new BasicDBObject("$eq", Arrays.asList("$cxr_code","$$cxrCode")),
										new BasicDBObject("$eq", Arrays.asList("$rule_no","$$ruleNo")),
										new BasicDBObject("$eq", Arrays.asList("$tariff", "$$tarNo"))
								)
							)
						)
				)));
				
				query.append("as", "atpco_record_8");
				lookupRec8.append("$lookup", query);
				return lookupRec8;
			}
			
		});
		
		
		aggregationOperations.add(new AggregationOperation() {

			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				
				BasicDBObject projection = new BasicDBObject();
				
				//PROJECT
				projection.append("$project", new BasicDBObject("_id", 0)
						.append("cxr_code","$_id.cxr_code")
						.append("rule_no","$_id.rule_no")
						.append("rule_tar_no","$_id.rule_tar_no")
						.append("type",new BasicDBObject("$cond", new BasicDBObject("if", 
										new BasicDBObject("$gt", Arrays.asList(new BasicDBObject("$size", "$atpco_record_0"), 0)))
								.append("then", "General Rule")
								.append("else", new BasicDBObject("$cond", new BasicDBObject("if", 
										new BasicDBObject("$gt", Arrays.asList(new BasicDBObject("$size", "$atpco_record_8"), 0)))
										.append("then", "Fare By Rule")
										.append("else", "Rule")
								))
					))
				);
				return projection;
			}
			
		});

		return aggregationOperations;
	}
	
	//BY TYPE
	public List<AggregationOperation> getAggregationGroupingByType(RuleQueryParam param) {
		
		String type = "";
		if(param.getType().equals("1")) {
			type = "FARE BY RULE";
		} else if(param.getType().equals("2")) {
			type = "GENERAL RULE";
		} if(param.getType().equals("3")) {
			type = "RULE";
		}
		
		final String typeFinal = type;

		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject andQuery = new BasicDBObject();

				List<BasicDBObject> and = new ArrayList<>();
				
				Date today = getCalendarDate(0);
				Date twoYearsBefore = getCalendarDate(1);

				if (param.getCxr() != null && !param.getCxr().isEmpty()) {
					and.add(new BasicDBObject("cxr_code", param.getCxr()));
				} else {
					and.add(new BasicDBObject("cxr_code", new BasicDBObject("$ne", "")));
				}
				
				if (param.getRuleNo() != null && !param.getRuleNo().isEmpty()) {
					and.add(new BasicDBObject("rule_no", param.getRuleNo()));
				} else {
					and.add(new BasicDBObject("rule_no", new BasicDBObject("$ne", "")));
				}

				if (param.getRuleTarNo() != null && !param.getRuleTarNo().isEmpty()) {
					and.add(new BasicDBObject("rule_tar_no", param.getRuleTarNo()));
				} else {
					and.add(new BasicDBObject("rule_tar_no", new BasicDBObject("$ne", "")));
				}
				
				if (param.getCatNo() != null && !param.getCatNo().isEmpty()) {
					and.add(new BasicDBObject("cat_no", param.getCatNo()));
				} else {
					and.add(new BasicDBObject("cat_no", new BasicDBObject("$ne", "")));
				}
				
				if(param.isIncludeDisc()) {
					and.add(new BasicDBObject("$or", Arrays.asList(
							new BasicDBObject("dates_disc", new BasicDBObject("$lte", today).append("$gte", twoYearsBefore)), 
							new BasicDBObject("dates_disc", "indef"))));
					
				} else {
					and.add(new BasicDBObject("$or", Arrays.asList(
							new BasicDBObject("dates_disc", new BasicDBObject("$gte", today)), 
							new BasicDBObject("dates_disc", "indef"))));
				}

				if (and.size() > 0) {
					andQuery.append("$and", and);
				}

				match.append("$match", andQuery);

				return match;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject();
				BasicDBObject groupId = new BasicDBObject();
				BasicDBObject groupList = new BasicDBObject();
				groupList.put("cxr_code", "$cxr_code");
				groupList.put("rule_no", "$rule_no");
				groupList.put("rule_tar_no", "$rule_tar_no");
				groupList.put("type", "$type");
				groupId.append("_id", groupList);
				group.append("$group", groupId);
				return group;
			}
		});
		
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject lookup = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("from", CollectionName.ATPCO_MASTER_TARIFF);
				query.append("let", new BasicDBObject("tariff", "$_id.rule_tar_no"));
				query.append("pipeline", Arrays.asList(
						new BasicDBObject("$match", 
								new BasicDBObject("$expr", 
										new BasicDBObject("$and", Arrays.asList(
												new BasicDBObject("$eq", Arrays.asList("$tar_no", "$$tariff")), 
												new BasicDBObject("$eq", Arrays.asList("$type", typeFinal))
												)
											)
										)
									)
						));
				query.append("as", "m_tariff");
				lookup.append("$lookup", query);
				return lookup;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject unwind = new BasicDBObject("$unwind", "$m_tariff");
				return unwind;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject();
				BasicDBObject groupId = new BasicDBObject();
				BasicDBObject groupList = new BasicDBObject();
				groupList.put("cxr_code", "$_id.cxr_code");
				groupList.put("rule_no", "$_id.rule_no");
				groupList.put("rule_tar_no", "$_id.rule_tar_no");
				groupList.put("type", "$m_tariff.type");
				groupId.append("_id", groupList);
				group.append("$group", groupId);
				return group;
			}
		});
		

		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				
				BasicDBObject projection = new BasicDBObject();
				
				//PROJECT
				projection.append("$project", new BasicDBObject("_id", 0)
						.append("cxr_code","$_id.cxr_code")
						.append("rule_no","$_id.rule_no")
						.append("rule_tar_no","$_id.rule_tar_no")
						.append("type", typeFinal)
				);
				return projection;
			}
			
		});


		return aggregationOperations;
	}	
	
/* 	
 	==========================================================================================================================================	
	Get Today And 2 Years Before
	==========================================================================================================================================
*/
	
	public Date getCalendarDate(int beforeAfter) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		Date dt = null;
		
		if(beforeAfter == 0) {
			dt = cal.getTime();
		} else if(beforeAfter == 1) {
			cal.add(Calendar.YEAR, -2); 
			dt = cal.getTime();
		}
		
		return dt;
	}

}
