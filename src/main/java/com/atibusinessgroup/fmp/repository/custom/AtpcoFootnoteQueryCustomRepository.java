package com.atibusinessgroup.fmp.repository.custom;

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
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.aggregation.SortOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.constant.CollectionName;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFootnoteRecord2;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteRecord2GroupByFtntCxrTarNo;
import com.atibusinessgroup.fmp.domain.dto.FootnoteQueryParam;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class AtpcoFootnoteQueryCustomRepository {

	@Autowired
	MongoTemplate mongoTemplate;
	
	public List<AtpcoFootnoteRecord2> getListFtntRecord2(String recordId) {
		List<AtpcoFootnoteRecord2> result = new ArrayList<>();
		Query query = new Query();
		query.addCriteria(Criteria.where("record_id").is(recordId));
		result = mongoTemplate.find(query, AtpcoFootnoteRecord2.class);
		return result;
	}

	public Page<AtpcoFootnoteRecord2> findByFootnoteQueryParam(FootnoteQueryParam param, Pageable pageable) {

		try {

			List<AggregationOperation> aggregationOperations = getAggregationFootnoteQuery(param);

			Aggregation aggregation = newAggregation(aggregationOperations);

			SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
			aggregationOperations.add(skip);

			LimitOperation limit = new LimitOperation(pageable.getPageSize());
			aggregationOperations.add(limit);

			Aggregation aggregationPagination = newAggregation(aggregationOperations);

			List<AtpcoFootnoteRecord2> result = mongoTemplate
					.aggregate(aggregationPagination, "atpco_footnote_record_2", AtpcoFootnoteRecord2.class).getMappedResults();

			return new PageImpl<>(result, pageable, mongoTemplate
					.aggregate(aggregation, "atpco_footnote_record_2", AtpcoFootnoteRecord2.class).getMappedResults().size());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<AggregationOperation> getAggregationFootnoteQuery(FootnoteQueryParam param) {
		List<AggregationOperation> aggregationOperations = new ArrayList<>();

		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				BasicDBObject andQuery = new BasicDBObject();

				List<BasicDBObject> and = new ArrayList<>();

				if (param.getCxr() != null && !param.getCxr().isEmpty()) {
					and.add(new BasicDBObject("cxr_code", param.getCxr()));
				} else {
					and.add(new BasicDBObject("cxr_code", new BasicDBObject("$exists", "true")));
				}

				if (param.getTarNo()!= null && !param.getTarNo().isEmpty()) {
					and.add(new BasicDBObject("fare_tar_no", param.getTarNo()));
				} else {
					and.add(new BasicDBObject("fare_tar_no", new BasicDBObject("$exists", "true")));
				}


				if (param.getFtnt() != null && !param.getFtnt().isEmpty()) {
					and.add(new BasicDBObject("ftnt", param.getFtnt()));
				} else {
					and.add(new BasicDBObject("ftnt", new BasicDBObject("$exists", "true")));
				}

				
				if (param.getCatNo() != null && !param.getCatNo().isEmpty()) {
					if(param.getCatNo().equalsIgnoreCase("comb")) {
								
						and.add(new BasicDBObject("cat_no", new BasicDBObject("$in", Arrays.asList("014","015"))));
						
						System.out.println(and.toString());
					} else {
						and.add(new BasicDBObject("cat_no", param.getCatNo()));
					}
					
				} else {
					and.add(new BasicDBObject("cat_no", new BasicDBObject("$exists", "true")));
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

	public Page<AtpcoFootnoteRecord2GroupByFtntCxrTarNo> groupingFootnoteQuery(FootnoteQueryParam param, Pageable pageable) {

		List<AggregationOperation> aggregationOperations = getAggregationGrouping(param);
		
		Aggregation aggregation = newAggregation(aggregationOperations);

		SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
		aggregationOperations.add(skip);

		LimitOperation limit = new LimitOperation(pageable.getPageSize());
		aggregationOperations.add(limit);

		Aggregation aggregationPagination = newAggregation(aggregationOperations);

		SortOperation sort = new SortOperation(new Sort(Direction.ASC, "ftnt"));
		aggregationOperations.add(sort);

		List<AtpcoFootnoteRecord2GroupByFtntCxrTarNo> result = mongoTemplate.aggregate(aggregationPagination, "atpco_footnote_record_2", AtpcoFootnoteRecord2GroupByFtntCxrTarNo.class).getMappedResults();
		
		return new PageImpl<>(result, pageable, mongoTemplate.aggregate(aggregation, "atpco_footnote_record_2", AtpcoFootnoteRecord2GroupByFtntCxrTarNo.class).getMappedResults().size());
	}

	public List<AggregationOperation> getAggregationGrouping(FootnoteQueryParam param) {

		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		aggregationOperations = getAggregationFootnoteQuery(param);
		

		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject();
				BasicDBObject groupId = new BasicDBObject();
				BasicDBObject groupList = new BasicDBObject();
				groupList.put("ftnt", "$ftnt");
				groupList.put("cxr_code", "$cxr_code");
				groupList.put("fare_tar_no", "$fare_tar_no");
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
	
	public List<AtpcoFootnoteRecord2GroupByCatNo> groupFootnoteByRecordId(String recordId) {
		
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		MatchOperation match = new MatchOperation(new Criteria("record_id").is(recordId));
		aggregationOperations.add(match);
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject group = new BasicDBObject();
				BasicDBObject query = new BasicDBObject();
				query.append("_id", "$cat_no");
				query.append("record_2", new BasicDBObject("$push", "$$ROOT"));
				group.append("$group", query);
				return group;
			}
		});
		
		SortOperation sort = new SortOperation(new Sort(Direction.ASC, "_id"));
		aggregationOperations.add(sort);
		
		Aggregation aggregation = newAggregation(aggregationOperations);
		
		List<AtpcoFootnoteRecord2GroupByCatNo> result = mongoTemplate.aggregate(aggregation, CollectionName.ATPCO_FOOTNOTE_RECORD_2, AtpcoFootnoteRecord2GroupByCatNo.class).getMappedResults();
		
		return result;
	}

}
