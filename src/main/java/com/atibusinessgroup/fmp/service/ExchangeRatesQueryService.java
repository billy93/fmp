package com.atibusinessgroup.fmp.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.Currency;
import com.atibusinessgroup.fmp.domain.ExchangeRatesQuery;
import com.atibusinessgroup.fmp.domain.dto.ExchangeRatesQueryParam;
import com.atibusinessgroup.fmp.repository.ExchangeRatesQueryRepository;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

@Service
public class ExchangeRatesQueryService {

	private final Logger log = LoggerFactory.getLogger(ExchangeRatesQueryService.class);
	
	@Autowired
    MongoTemplate mongoTemplate;
	
	private final ExchangeRatesQueryRepository exchangeRatesQueryRepository;
	
	public ExchangeRatesQueryService(ExchangeRatesQueryRepository exchangeRatesQueryRepository) {
		this.exchangeRatesQueryRepository = exchangeRatesQueryRepository;
	}
	
	public Page<ExchangeRatesQuery> findCustomJoin(ExchangeRatesQueryParam param, Pageable pageable) {
		List<ExchangeRatesQuery> result = null;
		long allResultCount = 0;
		
		String currencyCodeFrom = param.getCurrencyCodeFrom();
		String currencyCodeTo = param.getCurrencyCodeTo();
		if((currencyCodeFrom != null && !currencyCodeFrom.isEmpty() && currencyCodeFrom.length() < 3) || 
				(currencyCodeTo != null && !currencyCodeTo.isEmpty() && currencyCodeTo.length() < 3)) {
			result = new ArrayList<>();
			allResultCount = 0;
		} else {
			List<AggregationOperation> aggregationOperations = getAggregationOperation(param);
			Aggregation aggregation = newAggregation(aggregationOperations);
			SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
			aggregationOperations.add(skip);
			LimitOperation limit = new LimitOperation(pageable.getPageSize());
			aggregationOperations.add(limit);
			Aggregation aggregationPagination = newAggregation(aggregationOperations);
			
			result = mongoTemplate.aggregate(aggregationPagination, ExchangeRatesQuery.class, ExchangeRatesQuery.class).getMappedResults();
			allResultCount = mongoTemplate.aggregate(aggregation, ExchangeRatesQuery.class, ExchangeRatesQuery.class).getMappedResults().size();
		}
		
		return new PageImpl<>(result, pageable, allResultCount);
	}
	
	public Currency getCurrencyByCode(String code) {
		Query query = new Query();
		query.addCriteria(Criteria.where("currency_code").is(code));
		
		return mongoTemplate.findOne(query, Currency.class);
	}
	
	private List<AggregationOperation> getAggregationOperation(ExchangeRatesQueryParam param) {
		List<AggregationOperation> aggregationOperations = new ArrayList<>();
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject project = new BasicDBObject();
				project.append("_id", "$_id");
				project.append("batch_number", "$batch_number");
				project.append("batch_date", "$batch_date");
				project.append("Country", "$Country");
				project.append("alpha_code", "$alpha_code");
				project.append("rate_exchange", "$rate_exchange");
				project.append("note", "$note");
				project.append("year", new BasicDBObject("$year", "$batch_date"));
				project.append("month", new BasicDBObject("$month", "$batch_date"));
				
				return new BasicDBObject("$project", project);
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				
				if (param.getDateFrom() != null && param.getDateTo() != null) {
					ZonedDateTime dateFrom = ZonedDateTime.ofInstant(param.getDateFrom().toInstant(), ZoneId.systemDefault());
					ZonedDateTime dateTo = ZonedDateTime.ofInstant(param.getDateTo().toInstant(), ZoneId.systemDefault());
					
					BasicDBObject month = new BasicDBObject();
					month.append("$gte", dateFrom.getMonthValue());
					month.append("$lte", dateTo.getMonthValue());
					match.append("month", month);
					
					BasicDBObject year = new BasicDBObject();
					year.append("$gte", dateFrom.getYear());
					year.append("$lte", dateTo.getYear());
					match.append("year", year);
				} else if(param.getDateFrom() != null) {
					ZonedDateTime dateFrom = ZonedDateTime.ofInstant(param.getDateFrom().toInstant(), ZoneId.systemDefault());
					match.append("month", new BasicDBObject("$gte", dateFrom.getMonthValue()));
					match.append("year", new BasicDBObject("$gte", dateFrom.getYear()));
				} else if(param.getDateTo() != null) {
					ZonedDateTime dateTo = ZonedDateTime.ofInstant(param.getDateTo().toInstant(), ZoneId.systemDefault());
					match.append("month", new BasicDBObject("$lte", dateTo.getMonthValue()));
					match.append("year", new BasicDBObject("$lte", dateTo.getYear()));
				}
				
				if (param.getCurrencyCodeFrom() != null && !param.getCurrencyCodeFrom().isEmpty()) {
					match.append("alpha_code", new BasicDBObject("$regex", "(?i:.*"+param.getCurrencyCodeFrom()+".*)"));
				}
				
				if (param.getCurrencyCodeTo() != null && !param.getCurrencyCodeTo().isEmpty()) {
					if(param.getCurrencyCodeTo().equals("USD")) {
						match.append("note", "D");
					} else if(param.getCurrencyCodeTo().equals("EUR")) {
						match.append("note", "E");
					} else {
						match.append("note", "X");
					}
				} else {
					match.append("note", new BasicDBObject("$exists", true));
				}
				
				return new BasicDBObject("$match", match);
			}
		});
		
		return aggregationOperations;
	}
}
