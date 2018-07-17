package com.atibusinessgroup.fmp.repository.custom;

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
import com.atibusinessgroup.fmp.domain.dto.CarrierApplicationTable;
import com.atibusinessgroup.fmp.domain.dto.CarrierTable;
import com.atibusinessgroup.fmp.domain.dto.TextTable;
import com.atibusinessgroup.fmp.domain.dto.UserZoneTable;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class AtpcoYqyrTableRepository {	
	
	@Autowired
    MongoTemplate mongoTemplate;

	public TextTable findTextTable196(String tableNo) {
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
		
		TextTable result = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_YQYR_TEXT_TABLE_196, TextTable.class).getUniqueMappedResult();
		
		return result;
	}

	public List<UserZoneTable> findUserTable178(String tableNo) {
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
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		List<UserZoneTable> result = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_YQYR_USER_ZONE_TABLE_178, UserZoneTable.class).getMappedResults();
		
		return result;
	}

	public CarrierTable findCarrierTable186(String tableNo) {
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
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		CarrierTable result = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_YQYR_CARRIER_TABLE_186, CarrierTable.class).getUniqueMappedResult();
		
		return result;
	}

	public CarrierApplicationTable getCarrierApplicationTable190(String tableNo) {
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
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		CarrierApplicationTable result = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_YQYR_CARRIER_APPLICATION_TABLE_190, CarrierApplicationTable.class).getUniqueMappedResult();
		
		return result;
	}
}
