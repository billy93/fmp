package com.atibusinessgroup.fmp.repository.custom;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.bson.Document;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.VariableOperators.Map;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.dto.SpecifiedConstructed;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryParam;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFareAfdQueryWithRecords;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteQueryGroup;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord3CategoryWithDataTable;
import com.atibusinessgroup.fmp.service.util.DateUtil;
import com.mongodb.AggregationOptions;
import com.mongodb.BasicDBObject;
import com.mongodb.Cursor;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;

@Service
public class CompetitorMonitoringCustomRepository {	
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	public Page<AtpcoFare> getCompetitorQueries(AfdQueryParam param, Pageable pageable) {
		
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
				
				queries.add(new BasicDBObject("fare_class_cd", new BasicDBObject("$exists", "true")));
				
				if(param.getOrigin() != null && !param.getOrigin().isEmpty()) {
					int dash = param.getOrigin().indexOf("-");
					String orig = param.getOrigin().substring(0, dash);
					String dest = param.getOrigin().substring(dash+1, param.getOrigin().length());
					
					queries.add(new BasicDBObject("origin_city", orig));
					queries.add(new BasicDBObject("destination_city", dest));
					
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
					ruleNo.append("rules_no", param.getRuleNo());
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
		
		listAggregationOps.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("tarNo", "$tar_no");
				query.append("cxr", "$cxr_cd");
				query.append("ruleNo", "$rules_no");
				query.append("ftnt", "$ftnt");
				
				group.append("_id", query);
				return group;
			}
		});
		
		listAggregationOps.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject();
				project.append("_id", 0);
				project.append("record_id_1", new BasicDBObject("$concat", Arrays.asList("$_id.tarNo","$_id.cxr","$_id.ruleNo","$_id.fare_class")));
				project.append("record_id_2", new BasicDBObject("$cond", 
						new BasicDBObject("if", 
								new BasicDBObject("$ne", Arrays.asList("$_id.ftnt", ""))
								)
						)
						.append("then", new BasicDBObject("$concat", null))
						.append("else", new BasicDBObject("$concat", Arrays.asList("$_id.tarNo","$_id.cxr","$_id.ruleNo")))
					);
				project.append("record_id_2_ftnt", new BasicDBObject("$cond", 
						new BasicDBObject("if", 
								new BasicDBObject("$ne", Arrays.asList("$_id.ftnt", ""))
								)
						)
						.append("then", new BasicDBObject("$concat", Arrays.asList("$_id.tarNo","$_id.cxr","$_id.ftnt")))
						.append("else", new BasicDBObject("$concat", null))
					);
				return project;
			}
		});
		
		Aggregation aggregation = newAggregation(listAggregationOps);
		
//		mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_FARE, BasicDBObject.class).forEach(action);
		 
		
		List<AtpcoFare> data = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_FARE, AtpcoFare.class).getMappedResults();
		
		return new PageImpl<>(data, pageable, mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_FARE, AtpcoFare.class).getMappedResults().size());
	}
		
	public String findChartData(AfdQueryParam param) {
		
		
		List<BasicDBObject> queries = new ArrayList<>();
		BasicDBObject and = new BasicDBObject();
		BasicDBObject match = new BasicDBObject();
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		if (param.getCarrier() != null && !param.getCarrier().isEmpty()) {
			BasicDBObject carrier = new BasicDBObject();
			carrier.append("cxr_cd", new BasicDBObject("$in",  Arrays.stream(param.getCarrier().split(",")).map(String::trim).toArray(String[]::new)));
			queries.add(carrier);
		}
		
		if ((param.getOrigin() != null && !param.getOrigin().isEmpty()) && (param.getDestination() != null && !param.getDestination().isEmpty())) {
			BasicDBObject origin = new BasicDBObject("origin_city", param.getOrigin());
			BasicDBObject destination = new BasicDBObject("destination_city", param.getDestination());
			queries.add(origin);
			queries.add(destination);
		}
		
		if (param.getOwrt() != null && !param.getOwrt().isEmpty()) {
			BasicDBObject owrt = new BasicDBObject();
			owrt.append("ow_rt", param.getOwrt());
			queries.add(owrt);
		}
		
		if (param.getRuleNo() != null && !param.getRuleNo().isEmpty()) {
			BasicDBObject ruleNo = new BasicDBObject();
			ruleNo.append("rules_no", param.getRuleNo());
			queries.add(ruleNo);
		}
		
		if (param.getFareBasis() != null && !param.getFareBasis().isEmpty()) {
			BasicDBObject fbc = new BasicDBObject();
			fbc.append("fare_class_cd", param.getFareBasis());
			queries.add(fbc);
		}
		
		if (queries.size() > 0) {
			and.append("$and", queries);
		}
		
		
//		List<JSONObject> listJson = new ArrayList<JSONObject>();
//		
//		DBCursor fareCursor = mongoTemplate.getCollection(CollectionName.ATPCO_FARE).find(and);
//		
//		for (DBObject fare : fareCursor) {
//			String recordId = fare.get("tar_no").toString()+fare.get("cxr_cd").toString()+fare.get("rules_no")+fare.get("fare_class_cd");
//			
//			JSONObject json = new JSONObject();
//			json.append("carrierCode", fare.get("cxr_cd").toString());
//			json.append("originCity", fare.get("origin_city").toString());
//			json.append("destinationCity", fare.get("destination_city").toString());
//			json.append("travelStart", fare.get("destination_city").toString());
//			json.append("travelEnd", fare.get("destination_city").toString());
//			
//			BasicDBObject findByRecId = new BasicDBObject("record_id", recordId);
//			DBCursor record1Cursor = mongoTemplate.getCollection(CollectionName.ATPCO_FARE).find(findByRecId);
//			
//			for (DBObject rec1 : record1Cursor) {
//				
//			}
//		}
		
		
		
//		BasicDBObject lookup = new BasicDBObject();
//		BasicDBObject query = new BasicDBObject();
//		query.append("from", CollectionName.ATPCO_RECORD_1);
//		query.append("let", new BasicDBObject("recordId", new BasicDBObject("$concat", Arrays.asList("$atpco_fare.tar_no", "$atpco_fare.cxr_cd", "$atpco_fare.rules_no", "$atpco_fare.fare_class_cd"))));
//		BasicDBObject match2 = new BasicDBObject();
//		match2.append("$match", new BasicDBObject("$expr", new BasicDBObject("$eq", Arrays.asList("$record_id", "$$recordId"))));
//		query.append("pipeline", Arrays.asList(new BasicDBObject(match2)));
//		query.append("as", "atpco_record_1");
//		lookup.append("$lookup", query);
		
//		BasicDBObject projectResult = new BasicDBObject();
//		BasicDBObject query2 = new BasicDBObject();
//		query2.append("_id", 0);
//		query2.append("atpco_fare", "$atpco_fare");
//		query2.append("atpco_record_1", "$atpco_record_1");
//		query2.append("atpco_record_2", "$atpco_record_2");
//		query2.append("footnote_record", "$footnote_record");
//		projectResult.append("$project", query);
		
		
//		mongoTemplate.aggregate(aggregationPagination, CollectionName.ATPCO_FARE, AtpcoFareAfdQueryWithRecords.class).
//		
//		
//		 MongoClient mc = new MongoClient();
//		 MongoDatabase db = mc.getDatabase(mongoTemplate.getDb().getName());
//		 MongoCollection<Document> collection = db.getCollection(CollectionName.ATPCO_FARE);
//		 mongoTemplate.get
//		 AggregateIterable<Document> output = collection.aggregate(Arrays.asList(
//			        new Document("$match", and),
//			        new Document("$lookup", query),
//			        new Document("$project", query2),
//			        new Document("$limit", 200)
//			        ));
////		 
////
		 StringBuilder sb = new StringBuilder();
////		 
//		 List<JSONObject> listJson = new ArrayList<>();
//		 java.util.Map<String, String> categories = new HashMap<>();
//		 sb.append("[");
//		 for (Document document : output) {
//			 
//			sb.append("{"+"}");
//			 
//		 }
//		 
//		 sb.append("]");
		 

		
		return sb.toString();
	}
}
