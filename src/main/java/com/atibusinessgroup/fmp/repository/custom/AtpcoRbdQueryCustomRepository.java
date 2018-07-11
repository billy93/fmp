package com.atibusinessgroup.fmp.repository.custom;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperation;
import org.springframework.data.mongodb.core.aggregation.AggregationOperationContext;
import org.springframework.data.mongodb.core.aggregation.LimitOperation;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.RbdQuery;
import com.atibusinessgroup.fmp.domain.dto.RuleQueryParam;
import com.atibusinessgroup.fmp.service.util.DateUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class AtpcoRbdQueryCustomRepository {

	@Autowired
	MongoTemplate mongoTemplate;

	public Page<RbdQuery> getRbdByParams(RuleQueryParam params, Pageable pageable) {

		List<AggregationOperation> aggregationOperation = new ArrayList<>();
		aggregationOperation.add(new AggregationOperation() {
			
			@Override
			public DBObject toDBObject(AggregationOperationContext arg0) {
				// TODO Auto-generated method stub
				BasicDBObject match = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				if(params.getCxr() != null && !params.getCxr().isEmpty()) {
					query.append("cxr_code", params.getCxr());
				}else {
					query.append("cxr_code", new BasicDBObject("$ne",""));
				}
				
				if (params.getRuleTarNo() != null && !params.getRuleTarNo().isEmpty()) {
					query.append("rules_tar", params.getRuleTarNo());
				}else {
					query.append("rules_tar", new BasicDBObject("$ne",""));
				}
				
				if (params.getRuleNo() != null && !params.getRuleNo().isEmpty()) {
					query.append("rule_no", params.getRuleNo());
				}else {
					query.append("rule_no", new BasicDBObject("$ne",""));
				}
				
				if (params.isChart1()) {
					query.append("conv", "1");
				}
				
				if (params.isChart2()) {
					query.append("conv", "2");
				}
				
				if (params.getEffectiveDateFrom() != null) {
					Date paramFrom = DateUtil.convertObjectToDate(params.getEffectiveDateFrom());					
					
					Calendar cal = Calendar.getInstance(); 
					cal.setTime(paramFrom); 
					cal.add(Calendar.DATE, 1);
					
					query.append("dates_eff", new BasicDBObject("$gte", paramFrom).append("$lt", cal.getTime()));
					
				}

				match.append("$match", query);
				return match;
			}
		});
		
		SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
		LimitOperation limit = new LimitOperation(pageable.getPageSize());
		
		Aggregation aggregation = newAggregation(aggregationOperation);
		
		aggregationOperation.add(skip);

		aggregationOperation.add(limit);

		Aggregation aggregationPagination = newAggregation(aggregationOperation);
		
		
		List<RbdQuery> result = mongoTemplate.aggregate(aggregationPagination, "rules_record_6", RbdQuery.class).getMappedResults();
		return new PageImpl<>(result, pageable, mongoTemplate.aggregate(aggregation, "rules_record_6", RbdQuery.class).getMappedResults().size());
	}
}
