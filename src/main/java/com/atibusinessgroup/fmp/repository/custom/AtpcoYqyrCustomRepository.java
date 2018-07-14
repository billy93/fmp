package com.atibusinessgroup.fmp.repository.custom;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoYqyr;
import com.atibusinessgroup.fmp.domain.dto.Yqyr;
import com.atibusinessgroup.fmp.domain.dto.YqyrQueryParam;
import com.atibusinessgroup.fmp.domain.dto.YqyrWrapper;
import com.atibusinessgroup.fmp.service.mapper.YqyrQueryMapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class AtpcoYqyrCustomRepository {	
	
	private final YqyrQueryMapper yqyrQueryMapper;
	private final MongoTemplate mongoTemplate;
	
	public AtpcoYqyrCustomRepository(YqyrQueryMapper yqyrQueryMapper, MongoTemplate mongoTemplate) {
		this.yqyrQueryMapper = yqyrQueryMapper;
		this.mongoTemplate = mongoTemplate;
	}
	
	public YqyrWrapper findYqyr(YqyrQueryParam param, Pageable pageable) {
		
		YqyrWrapper result = new YqyrWrapper();
		
		List<Yqyr> yqyrs = new ArrayList<>();
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject and = new BasicDBObject();
				List<BasicDBObject> queries = new ArrayList<>();
				
				if (param.getCarrier() != null && !param.getCarrier().isEmpty()) {
					BasicDBObject carrier = new BasicDBObject();
					carrier.append("cxr_code", new BasicDBObject("$in",  Arrays.stream(param.getCarrier().split(",")).map(String::trim).toArray(String[]::new)));
					queries.add(carrier);
				} else {
					BasicDBObject carrier = new BasicDBObject();
					carrier.append("cxr_code", new BasicDBObject("$exists", "true"));
					queries.add(carrier);
				}
				
				if (queries.size() > 0) {
					and.append("$and", queries);
				}
				
				match.append("$match", and);
				
				return match;
			}
		});
		
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
    		
    		List<AtpcoYqyr> ayqyrs = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_YQYR_S1, AtpcoYqyr.class).getMappedResults();
        	
    		System.out.println(ayqyrs.size());
    		
    		if (ayqyrs.size() == 0) {
    			isCompleted = true;
    			isLastPage = true;
    			index = 0;
    		} else {
    			index = 1;
    		}
    		
        	for (AtpcoYqyr ayqyr:ayqyrs) {
        		yqyrs.add(yqyrQueryMapper.convertYqyr(ayqyr));
        		
        		if (yqyrs.size() == pageable.getPageSize()) {
    				isCompleted = true;
    				break;
    			}
    			
    			index++;
        	}
        	
        	currentAggregationLoop++;
		}
		
		if (!isLastPage && yqyrs.size() < pageable.getPageSize()) {
    		isLastPage = true;
    	}
    	
    	result.setLastPage(isLastPage);
    	result.setLastIndex(skipSize + index);
    	result.setYqyr(yqyrs);
		
		return result;
	}
}
