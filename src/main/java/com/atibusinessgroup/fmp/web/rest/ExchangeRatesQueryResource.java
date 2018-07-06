package com.atibusinessgroup.fmp.web.rest;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.Currency;
import com.atibusinessgroup.fmp.domain.ExchangeRatesQuery;
import com.atibusinessgroup.fmp.domain.dto.ExchangeRatesQueryParam;
import com.atibusinessgroup.fmp.service.ExchangeRatesQueryService;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class ExchangeRatesQueryResource {
	
	private final Logger log = LoggerFactory.getLogger(ExchangeRatesQueryResource.class);
	
	private final ExchangeRatesQueryService exchangeRatesQueryService;
	
	public ExchangeRatesQueryResource(ExchangeRatesQueryService exchangeRatesQueryService) {
		this.exchangeRatesQueryService = exchangeRatesQueryService;
	}

	@PostMapping("/exchange-rates-query")
    @Timed
    public ResponseEntity<List<ExchangeRatesQuery>> getAllExchangeRatesQueries(@RequestBody ExchangeRatesQueryParam param) {
        log.debug("REST request to get a page of ExchangeRatesQuery "+param);
        Pageable pageable = new PageRequest(param.getPage(), param.getSize());
        Page<ExchangeRatesQuery> page = exchangeRatesQueryService.findCustomJoin(param, pageable);
        for (ExchangeRatesQuery exchangeRatesQuery : page.getContent()) {
        	exchangeRatesQuery = setDataView(exchangeRatesQuery);
		}
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/exchange-rates-query");
        
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
	
	private ExchangeRatesQuery setDataView(ExchangeRatesQuery exchangeRatesQuery) {
		Date dateFrom = new Date(exchangeRatesQuery.getBatchDate().getTime());
		dateFrom.setDate(1);
		exchangeRatesQuery.setDateFrom(dateFrom);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(exchangeRatesQuery.getBatchDate());
		Date dateTo = new Date(exchangeRatesQuery.getBatchDate().getTime());
		dateTo.setDate(cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		exchangeRatesQuery.setDateTo(dateTo);
		
		String exchangeRateValue = "1 "+exchangeRatesQuery.getCurrencyCodeFrom()+" = "+exchangeRatesQuery.getRateExchange()+" "+exchangeRatesQuery.getCurrencyCodeTo();
		exchangeRatesQuery.setRateExchangeValue(exchangeRateValue);
		
		return exchangeRatesQuery;
	}
}
