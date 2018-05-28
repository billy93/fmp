 	package com.atibusinessgroup.fmp.repository.custom.impl;

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
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFareWithRecord1;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.resository.custom.AtpcoFareCustomRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class AtpcoFareCustomRepositoryImpl implements AtpcoFareCustomRepository {	
	
	@Autowired
    MongoTemplate mongoTemplate;

	@Override
	public Page<AtpcoFareWithRecord1> findAtpcoFareWithRecord1(Pageable pageable) {
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
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
		
		SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
		aggregationOperations.add(skip);
		
		LimitOperation limit = new LimitOperation(pageable.getPageSize());
		aggregationOperations.add(limit);
	
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		List<AtpcoFareWithRecord1> result = mongoTemplate.aggregate(aggregation, "atpco_fare", AtpcoFareWithRecord1.class).getMappedResults();
		
		return new PageImpl<>(result, pageable, mongoTemplate.count(new Query(), AtpcoFare.class));
	}

	@Override
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
