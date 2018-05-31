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

import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord3CategoryWithDataTable;
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
				BasicDBObject projectResult = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("_id", 0);
				query.append("category", "$$ROOT");
				projectResult.append("$project", query);
				return projectResult;
			}
		});
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		List<AtpcoRecord3CategoryWithDataTable> result = mongoTemplate.aggregate(aggregation, collectionName, AtpcoRecord3CategoryWithDataTable.class).getMappedResults();
		
		return result;
	}
}
