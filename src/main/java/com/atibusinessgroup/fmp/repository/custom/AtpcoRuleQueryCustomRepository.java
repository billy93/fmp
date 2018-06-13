package com.atibusinessgroup.fmp.repository.custom;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
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
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByRuleNoCxrTarNo;
import com.atibusinessgroup.fmp.domain.dto.RuleQueryParam;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class AtpcoRuleQueryCustomRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<AtpcoRecord2> getListRecord2ById(String recordId, String catNo) {
		List<AtpcoRecord2> result = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("record_id").is(recordId));
		result = mongoTemplate.find(query, AtpcoRecord2.class);
		return result;
	}

	public Page<AtpcoRecord2> findByRuleQueryParam(RuleQueryParam param, Pageable pageable) {

		try {

			List<AggregationOperation> aggregationOperations = getAggregationRuleQuery(param);

			Aggregation aggregation = newAggregation(aggregationOperations);

			SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
			aggregationOperations.add(skip);

			LimitOperation limit = new LimitOperation(pageable.getPageSize());
			aggregationOperations.add(limit);

			Aggregation aggregationPagination = newAggregation(aggregationOperations);

			List<AtpcoRecord2> result = mongoTemplate
					.aggregate(aggregationPagination, "atpco_record_2", AtpcoRecord2.class).getMappedResults();

			return new PageImpl<>(result, pageable, mongoTemplate
					.aggregate(aggregation, "atpco_record_2", AtpcoRecord2.class).getMappedResults().size());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<AggregationOperation> getAggregationRuleQuery(RuleQueryParam param) {
		List<AggregationOperation> aggregationOperations = new ArrayList<>();

		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject andQuery = new BasicDBObject();

				List<BasicDBObject> and = new ArrayList<>();

				if (param.getCxr() != null && !param.getCxr().isEmpty()) {
					and.add(new BasicDBObject("cxr_code", param.getCxr()));
				}

				if (param.getRuleTarNo() != null && !param.getRuleTarNo().isEmpty()) {
					and.add(new BasicDBObject("rule_tar_no", param.getRuleTarNo()));
				}

				if (param.getRuleNo() != null && !param.getRuleNo().isEmpty()) {
					and.add(new BasicDBObject("rule_no", param.getRuleNo()));
				}
				
				if (param.getCatNo() != null && !param.getCatNo().isEmpty()) {
					and.add(new BasicDBObject("cat_no", param.getCatNo()));
				}

				if (and.size() > 0) {
					andQuery.append("$and", and);
				}

				match.append("$match", andQuery);

				return match;
			}
		});

		return aggregationOperations;
	}

	public Page<AtpcoRecord2GroupByRuleNoCxrTarNo> groupingRuleQuery(RuleQueryParam param, Pageable pageable) {

		List<AggregationOperation> aggregationOperations = getAggregationGrouping(param);
		
		Aggregation aggregation = newAggregation(aggregationOperations);

		SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
		aggregationOperations.add(skip);

		LimitOperation limit = new LimitOperation(pageable.getPageSize());
		aggregationOperations.add(limit);

		Aggregation aggregationPagination = newAggregation(aggregationOperations);

		SortOperation sort = new SortOperation(new Sort(Direction.ASC, "rule_no"));
		aggregationOperations.add(sort);

		List<AtpcoRecord2GroupByRuleNoCxrTarNo> result = mongoTemplate.aggregate(aggregationPagination, "atpco_record_2", AtpcoRecord2GroupByRuleNoCxrTarNo.class).getMappedResults();
		
		return new PageImpl<>(result, pageable, mongoTemplate.aggregate(aggregation, "atpco_record_2", AtpcoRecord2GroupByRuleNoCxrTarNo.class).getMappedResults().size());
	}

	public List<AggregationOperation> getAggregationGrouping(RuleQueryParam param) {

		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		aggregationOperations = getAggregationRuleQuery(param);
		

		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject();
				BasicDBObject groupId = new BasicDBObject();
				BasicDBObject groupList = new BasicDBObject();
				groupList.put("rule_no", "$rule_no");
				groupList.put("cxr_code", "$cxr_code");
				groupList.put("rule_tar_no", "$rule_tar_no");
//				groupList.put("cat_no", "$cat_no");
				groupId.append("_id", groupList);
				group.append("$group", groupId);
				return group;
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject replace = new BasicDBObject();
				replace.append("$replaceRoot", new BasicDBObject().append("newRoot", "$_id"));
				return replace;
			}
		});

		return aggregationOperations;
	}

}
