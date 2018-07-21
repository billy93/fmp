package com.atibusinessgroup.fmp.repository.custom;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregationOptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoTaxX1;
import com.atibusinessgroup.fmp.domain.dto.Tax;
import com.atibusinessgroup.fmp.domain.dto.TaxQueryParam;
import com.atibusinessgroup.fmp.domain.dto.TaxWrapper;
import com.atibusinessgroup.fmp.service.mapper.TaxQueryMapper;

@Service
public class AtpcoTaxCustomRepository {	
	
	private final TaxQueryMapper taxQueryMapper;
	private final MongoTemplate mongoTemplate;
	
	public AtpcoTaxCustomRepository(TaxQueryMapper taxQueryMapper, MongoTemplate mongoTemplate) {
		this.taxQueryMapper = taxQueryMapper;
		this.mongoTemplate = mongoTemplate;
	}
	
	public TaxWrapper findTax(TaxQueryParam param, Pageable pageable) {
		
		TaxWrapper result = new TaxWrapper();
		
		List<Tax> taxes = new ArrayList<>();
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
//		aggregationOperations.add(new AggregationOperation() {
//			@Override
//			public DBObject toDBObject(AggregationOperationContext context) {
//				BasicDBObject match = new BasicDBObject();
//				BasicDBObject and = new BasicDBObject();
//				List<BasicDBObject> queries = new ArrayList<>();
//				
//				if (param.getCarrier() != null && !param.getCarrier().isEmpty()) {
//					BasicDBObject carrier = new BasicDBObject();
//					carrier.append("cxr_code", new BasicDBObject("$in",  Arrays.stream(param.getCarrier().split(",")).map(String::trim).toArray(String[]::new)));
//					queries.add(carrier);
//				} else {
//					BasicDBObject carrier = new BasicDBObject();
//					carrier.append("cxr_code", new BasicDBObject("$exists", "true"));
//					queries.add(carrier);
//				}
//				
//				if (queries.size() > 0) {
//					and.append("$and", queries);
//				}
//				
//				match.append("$match", and);
//				
//				return match;
//			}
//		});
		
//		aggregationOperations.add(new AggregationOperation() {
//			@Override
//			public DBObject toDBObject(AggregationOperationContext context) {
//				BasicDBObject sort = new BasicDBObject();
//				sort.append("$sort", new BasicDBObject("cxr_code", 1).append("service_type_tax", 1).append("service_type_sub_code", 1).append("seq_no", 1));
//				return sort;
//			}
//		});
		
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
    		
    		Aggregation aggregation = newAggregation(aggregationOperations).withOptions(newAggregationOptions().allowDiskUse(true).build());
    		
    		List<AtpcoTaxX1> ataxes = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_TAX_X1, AtpcoTaxX1.class).getMappedResults();
        	
    		System.out.println(ataxes.size());
    		
    		if (ataxes.size() == 0) {
    			isCompleted = true;
    			isLastPage = true;
    			index = 0;
    		} else {
    			index = 1;
    		}
    		
        	for (AtpcoTaxX1 atax:ataxes) {
        		taxes.add(taxQueryMapper.convertTax(atax));
        		
        		if (taxes.size() == pageable.getPageSize()) {
    				isCompleted = true;
    				break;
    			}
    			
    			index++;
        	}
        	
        	currentAggregationLoop++;
		}
		
		if (!isLastPage && taxes.size() < pageable.getPageSize()) {
    		isLastPage = true;
    	}
    	
    	result.setLastPage(isLastPage);
    	result.setLastIndex(skipSize + index);
    	result.setTax(taxes);
		
		return result;
	}
}
