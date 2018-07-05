package com.atibusinessgroup.fmp.service;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

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
		List<AggregationOperation> aggregationOperations = getAggregationOperation(param);
		Aggregation aggregation = newAggregation(aggregationOperations);
		SkipOperation skip = new SkipOperation(pageable.getPageNumber() * pageable.getPageSize());
		aggregationOperations.add(skip);
		LimitOperation limit = new LimitOperation(pageable.getPageSize());
		aggregationOperations.add(limit);
		Aggregation aggregationPagination = newAggregation(aggregationOperations);
		
		List<ExchangeRatesQuery> result = mongoTemplate.aggregate(aggregationPagination, ExchangeRatesQuery.class, ExchangeRatesQuery.class).getMappedResults();
		long allResultCount = mongoTemplate.aggregate(aggregation, ExchangeRatesQuery.class, ExchangeRatesQuery.class).getMappedResults().size();
		
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
				project.append("currency_code_from", 
						new BasicDBObject("$arrayElemAt", Arrays.asList(
								new BasicDBObject("$split", Arrays.asList("$alpha_code", "/")), 1
							)
						)
				);
				BasicDBObject cond = new BasicDBObject();
				cond.append("if", new BasicDBObject("$eq", Arrays.asList("$note", "D")));
				cond.append("then", "USD");
				
				BasicDBObject condElse = new BasicDBObject();
				condElse.append("if", new BasicDBObject("$eq", Arrays.asList("$note", "E")));
				condElse.append("then", "EUR");
				condElse.append("else", "");
				cond.append("else", new BasicDBObject("$cond", condElse));
				
				project.append("currency_code_to", new BasicDBObject("$cond", cond));
				
				return new BasicDBObject("$project", project);
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject lookup = new BasicDBObject();
				lookup.append("from", "currency");
				lookup.append("localField", "currency_code_from");
				lookup.append("foreignField", "currency_code");
				lookup.append("as", "currency_from");
				
				return new BasicDBObject("$lookup", lookup);
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject lookup = new BasicDBObject();
				lookup.append("from", "currency");
				lookup.append("localField", "currency_code_to");
				lookup.append("foreignField", "currency_code");
				lookup.append("as", "currency_to");
				
				return new BasicDBObject("$lookup", lookup);
			}
		});
		
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
				project.append("year", "$year");
				project.append("month", "$month");
				project.append("currency_code_from", "$currency_code_from");
				BasicDBObject fromCond = new BasicDBObject();
				fromCond.append("if", new BasicDBObject("$eq", Arrays.asList(new BasicDBObject("$size", "$currency_from.currency_name"), 1)));
				fromCond.append("then", new BasicDBObject("$arrayElemAt", Arrays.asList("$currency_from.currency_name", 0)));
				fromCond.append("else", "");
				project.append("currency_name_from", new BasicDBObject("$cond", fromCond));
				project.append("currency_code_to", "$currency_code_to");
				BasicDBObject toCond = new BasicDBObject();
				toCond.append("if", new BasicDBObject("$eq", Arrays.asList(new BasicDBObject("$size", "$currency_to.currency_name"), 1)));
				toCond.append("then", new BasicDBObject("$arrayElemAt", Arrays.asList("$currency_to.currency_name", 0)));
				toCond.append("else", "");
				project.append("currency_name_to", new BasicDBObject("$cond", toCond));
				
				return new BasicDBObject("$project", project);
			}
		});
		
		aggregationOperations.add(new AggregationOperation() {
			@Override
			public DBObject toDBObject(AggregationOperationContext context) {
				BasicDBObject match = new BasicDBObject();
				
				match.append("note", new BasicDBObject("$exists", true));
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
					match.append("currency_code_from", param.getCurrencyCodeFrom());
				}
				
				if (param.getCurrencyCodeTo() != null && !param.getCurrencyCodeTo().isEmpty()) {
					match.append("currency_code_to", param.getCurrencyCodeTo());
				}
				
				if (param.getCurrencyNameFrom() != null && !param.getCurrencyNameFrom().isEmpty()) {
					match.append("currency_name_from", Pattern.compile(".*"+param.getCurrencyNameFrom()+".*" , Pattern.CASE_INSENSITIVE));
				}
				
				if (param.getCurrencyNameTo() != null && !param.getCurrencyNameTo().isEmpty()) {
					match.append("currency_name_to", Pattern.compile(".*"+param.getCurrencyNameTo()+".*" , Pattern.CASE_INSENSITIVE));
				}
				
				return new BasicDBObject("$match", match);
			}
		});
		
		return aggregationOperations;
	}
}
