package com.atibusinessgroup.fmp.web.rest;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.constant.CategoryName;
import com.atibusinessgroup.fmp.constant.CategoryType;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFootnoteRecord2;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord0;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord1;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.dto.AfdQuery;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryParam;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFareAfdQueryWithRecords;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.Category;
import com.atibusinessgroup.fmp.domain.dto.CategoryObject;
import com.atibusinessgroup.fmp.domain.dto.GeneralRuleApplication;
import com.atibusinessgroup.fmp.repository.AtpcoRecord0Repository;
import com.atibusinessgroup.fmp.resository.custom.AtpcoFareCustomRepository;
import com.atibusinessgroup.fmp.service.AtpcoRecordService;
import com.atibusinessgroup.fmp.service.ProsDatafeedService;
import com.atibusinessgroup.fmp.service.mapper.AfdQueryMapper;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class ProsDatafeedResource {

	private final Logger log = LoggerFactory.getLogger(ProsDatafeedResource.class);

	private final ProsDatafeedService prosDatafeedService;

	public ProsDatafeedResource(ProsDatafeedService prosDatafeedService) {
		this.prosDatafeedService = prosDatafeedService;
	}
	/**
	 * POST /afd-queries : get all the afd queries.
	 *
	 * @param query
	 *            params, pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of afdQueries in
	 *         body
	 */
	@PostMapping("/afd-queries")
	@Timed
	public ResponseEntity<List<AfdQuery>> getAllAfdQueries(@RequestBody AfdQueryParam param) {
		log.debug("REST request to get a page of AfdQueries: {}", param);

		Pageable pageable = new PageRequest(param.getPage(), param.getSize());
		
		
		
		List<AfdQuery> result = new ArrayList<>();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/**
	 * GET /afd-queries/rules : get afd query rules.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of rules in body
	 */
	@GetMapping("/afd-queries/rules")
	@Timed
	public ResponseEntity<String> getAfdQueryRules(AfdQuery afdQuery) {
		log.debug("REST request to get AfdQueries rules: {}", afdQuery);
		String result = prosDatafeedService.getFaresProsDatafeed();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
