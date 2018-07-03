package com.atibusinessgroup.fmp.repository.custom;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoCcfParcity;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class AtpcoCcfParcityCustomRepository {	
	
	@Autowired
    MongoTemplate mongoTemplate;

	public AtpcoCcfParcity findOneByTypesAndLocations(String t1, String l1, String t2, String l2) {
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject and = new BasicDBObject();
				List<Document> queries = new ArrayList<>();
				
				Document one = convertTypeAndLocationToDBObject(t1, l1);
				if (one != null) {
					queries.add(one);
				}
				
				Document two = convertTypeAndLocationToDBObject(t2, l2);
				if (two != null) {
					queries.add(two);
				}
				
				if (queries.size() > 0) {
					and.append("$and", queries);
				}
				
				match.append("$match", and);
				
				return match;
			}
		});
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		AtpcoCcfParcity result = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_CCF_PARCITY, AtpcoCcfParcity.class).getUniqueMappedResult();
		
		return result;
	}
	
	private Document convertTypeAndLocationToDBObject(String t, String l) {
		Document result = null;
		
		switch (t) {
			case "A":
				result = new Document();
				result.append("city_area", l);
				break;
			case "Z":
				result = new Document();
				result.append("city_zone", l);
				break;
			case "N":
				result = new Document();
				result.append("city_country", l);
				break;
			case "S":
				result = new Document();
				result.append("city_state", l);
				break;
			default:
				result = new Document();
				result.append("city_code", l);
				break;
		}
		
		return result;
	}
}
