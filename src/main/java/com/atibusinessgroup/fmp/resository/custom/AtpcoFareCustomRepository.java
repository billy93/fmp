package com.atibusinessgroup.fmp.resository.custom;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.dto.AfdQueryParam;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFareWithRecord1;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByCatNo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class AtpcoFareCustomRepository {	
	
	@Autowired
    MongoTemplate mongoTemplate;

	public Page<AtpcoFareWithRecord1> findAtpcoFareWithRecord1(AfdQueryParam param, Pageable pageable) {
		
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
				}
				
				if (param.getOrigin() != null && !param.getOrigin().isEmpty()) {
					BasicDBObject origin = new BasicDBObject();
					origin.append("origin_city", new BasicDBObject("$in",  Arrays.stream(param.getOrigin().split(",")).map(String::trim).toArray(String[]::new)));
					queries.add(origin);
				}
				
				if (param.getDestination() != null && !param.getDestination().isEmpty()) {
					BasicDBObject destination = new BasicDBObject();
					destination.append("destination_city", new BasicDBObject("$in",  Arrays.stream(param.getOrigin().split(",")).map(String::trim).toArray(String[]::new)));
					queries.add(destination);
				}
				
				if (param.getOwrt() != null && !param.getOwrt().isEmpty()) {
					BasicDBObject owrt = new BasicDBObject();
					owrt.append("ow_rt", param.getOwrt());
					queries.add(owrt);
				}
				
				if (param.getFootnote() != null && !param.getFootnote().isEmpty()) {
					BasicDBObject fnt = new BasicDBObject();
					fnt.append("ftnt", param.getFootnote());
					queries.add(fnt);
				}
				
//				if (param.getEffectiveDateFrom() == null) {
//					param.setEffectiveDateFrom(new Date());
//				}
//				
//				BasicDBObject effectiveFrom = new BasicDBObject();
//				effectiveFrom.append("$or", Arrays.asList(new BasicDBObject("tar_eff_date", "indef"), new BasicDBObject("tar_eff_date", new BasicDBObject("$gte", param.getEffectiveDateFrom()))));
//				queries.add(effectiveFrom);
//				
//				if (param.getEffectiveDateTo() == null) {
//					param.setEffectiveDateTo(new Date());
//				}
//				
//				BasicDBObject effectiveTo = new BasicDBObject();
//				effectiveTo.append("$or", Arrays.asList(new BasicDBObject("tar_eff_date", "indef"), new BasicDBObject("tar_eff_date", new BasicDBObject("$lte", param.getEffectiveDateTo()))));
//				queries.add(effectiveTo);
				
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
				query.append("from", "atpco_record_1");
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
				BasicDBObject projectResult = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("_id", 0);
				query.append("atpco_fare", "$atpco_fare");
				query.append("atpco_record_1", "$atpco_record_1");
				projectResult.append("$project", query);
				return projectResult;
			}
		});
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
		aggregationOperations.add(skip);
		
		LimitOperation limit = new LimitOperation(pageable.getPageSize());
		aggregationOperations.add(limit);
	
		Aggregation aggregationPagination = newAggregation(aggregationOperations);
				
		List<AtpcoFareWithRecord1> result = mongoTemplate.aggregate(aggregationPagination, "atpco_fare", AtpcoFareWithRecord1.class).getMappedResults();
		
		return new PageImpl<>(result, pageable, mongoTemplate.aggregate(aggregation, "atpco_fare", AtpcoFareWithRecord1.class).getMappedResults().size());
	}

	public List<AtpcoRecord2GroupByCatNo> findAtpcoRecord2ByRecordId(String recordId) {
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		MatchOperation match = new MatchOperation(new Criteria("record_id").is(recordId));
		aggregationOperations.add(match);
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject();
				group.append("_id", "cat_no");
				group.append("record_2", new BasicDBObject("$push", "$$ROOT"));
				return group;
			}
		});
		
		SortOperation sort = new SortOperation(new Sort(Direction.ASC, "_id"));
		aggregationOperations.add(sort);
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		List<AtpcoRecord2GroupByCatNo> result = mongoTemplate.aggregate(aggregation, "atpco_record_2", AtpcoRecord2GroupByCatNo.class).getMappedResults();
		
		return result;
	}
}
