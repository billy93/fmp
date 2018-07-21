package com.atibusinessgroup.fmp.repository.custom;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregationOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.FbrReport;
import com.atibusinessgroup.fmp.domain.dto.FbrReportParam;
import com.atibusinessgroup.fmp.domain.dto.FbrReportWrapper;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class AtpcoFbrReportCustomRepository {

	@Autowired
    MongoTemplate mongoTemplate;
	
	public FbrReportWrapper findFbrReport(FbrReportParam param, Pageable pageable) {
		
		FbrReportWrapper result = new FbrReportWrapper();
		
		List<FbrReport> reports = new ArrayList<>();
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject and = new BasicDBObject();
				List<BasicDBObject> queries = new ArrayList<>();
				
				if (param.getCarrier() != null && !param.getCarrier().isEmpty()) {
					BasicDBObject carrier = new BasicDBObject();
					carrier.append("carrier", new BasicDBObject("$in",  Arrays.stream(param.getCarrier().split(",")).map(String::trim).toArray(String[]::new)));
					queries.add(carrier);
				} else {
					BasicDBObject carrier = new BasicDBObject();
					carrier.append("carrier", new BasicDBObject("$exists", "true"));
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
    		
    		Aggregation aggregation = newAggregation(aggregationOperations).withOptions(newAggregationOptions().allowDiskUse(true).build());
    		
    		List<FbrReport> fbrReports = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_FBR_REPORT, FbrReport.class).getMappedResults();
        	
    		if (fbrReports.size() == 0) {
    			isCompleted = true;
    			isLastPage = true;
    			index = 0;
    		} else {
    			index = 1;
    		}
    		
        	for (FbrReport fbr:fbrReports) {
        		reports.add(fbr);
        		
        		if (reports.size() == pageable.getPageSize()) {
    				isCompleted = true;
    				break;
    			}
    			
    			index++;
        	}
        	
        	currentAggregationLoop++;
		}
		
		if (!isLastPage && reports.size() < pageable.getPageSize()) {
    		isLastPage = true;
    	}
    	
    	result.setLastPage(isLastPage);
    	result.setLastIndex(skipSize + index);
    	result.setFbrReport(reports);
		
		return result;
	}
}
