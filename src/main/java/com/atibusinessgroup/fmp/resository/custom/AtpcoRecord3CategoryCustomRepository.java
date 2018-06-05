package com.atibusinessgroup.fmp.resository.custom;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord3CategoryWithDataTable;
import com.atibusinessgroup.fmp.domain.dto.TextTable;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class AtpcoRecord3CategoryCustomRepository {	
	
	@Autowired
    MongoTemplate mongoTemplate;

	public List<AtpcoRecord3CategoryWithDataTable> findAllRecord3ByDataTable(String collectionName, List<String> tableNos) {
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject tableNo = new BasicDBObject();
				tableNo.append("tbl_no", new BasicDBObject("$in", tableNos));
				match.append("$match", tableNo);
				return match;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject();
				project.append("$project", new BasicDBObject("category", "$$ROOT"));
				return project;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject projectResult = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("_id", 0);
				projectResult.append("$project", query);
				return projectResult;
			}
		});
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		List<AtpcoRecord3CategoryWithDataTable> result = mongoTemplate.aggregate(aggregation, collectionName, AtpcoRecord3CategoryWithDataTable.class).getMappedResults();
		
		return result;
	}
	
	public TextTable findRecord3TextTable(String tableNo) {
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject no = new BasicDBObject();
				no.append("tbl_no", tableNo);
				match.append("$match", no);
				return match;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject();
				group.append("$group", new BasicDBObject("_id", "$tbl_no").append("text", new BasicDBObject("$push", "$text")));
				return group;
			}
		});
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		TextTable result = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_TEXT_TABLE_996, TextTable.class).getUniqueMappedResult();
		
		return result;
	}
}
