package com.atibusinessgroup.fmp.web.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.dto.AfdQuery;
import com.atibusinessgroup.fmp.service.ProsDatafeedService;
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
	@PostMapping("/pros-datafeed")
	@Timed
	public ResponseEntity<List<AfdQuery>> getProsDatafeed(@RequestBody String param) {
		log.debug("REST request to get a page of getProsDatafeed: {}", param);
			
		List<AfdQuery> result = new ArrayList<>();

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/**
	 * GET /afd-queries/rules : get afd query rules.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of rules in body
	 */
	@GetMapping("/pros-datafeed")
	@Timed
	public ResponseEntity<String> generateProsDatafeed() {
		log.debug("REST request to get getProsDatafeed rules: {}");
		String result = prosDatafeedService.getFaresProsDatafeed();
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
