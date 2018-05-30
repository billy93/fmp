package com.atibusinessgroup.fmp.web.rest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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

import com.atibusinessgroup.fmp.constant.CategoryType;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.dto.AfdQuery;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByRuleNoCxrTarNo;
import com.atibusinessgroup.fmp.domain.dto.Category;
import com.atibusinessgroup.fmp.domain.dto.RuleQuery;
import com.atibusinessgroup.fmp.domain.dto.RuleQueryParam;
import com.atibusinessgroup.fmp.repository.AtpcoRecord2Repository;
import com.atibusinessgroup.fmp.resository.custom.AtpcoRuleQueryCustomRepository;
import com.atibusinessgroup.fmp.service.mapper.RuleQueryMapper;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class RuleQueryResource {
	
	private final Map<String, String> categories = new HashMap<>();

	private final Logger log = LoggerFactory.getLogger(RuleQueryResource.class);

	private final AtpcoRuleQueryCustomRepository atpcoRuleQueryCustomRepository;

	private final RuleQueryMapper ruleQueryMapper;

	public RuleQueryResource(AtpcoRuleQueryCustomRepository atpcoRuleQueryCustomRepository, RuleQueryMapper ruleQueryMapper) {
		this.atpcoRuleQueryCustomRepository = atpcoRuleQueryCustomRepository;
		this.ruleQueryMapper = ruleQueryMapper;
	}

	/**
	 * POST /rule-queries : get all the rule queries.
	 *
	 * @param rule
	 *            params, pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of ruleQueries in
	 *         body
	 */
	@PostMapping("/rule-queries")
	@Timed
	public ResponseEntity<List<RuleQuery>> getAllRuleQueries(@RequestBody RuleQueryParam param) {
		log.debug("REST request to get a page of RuleQueries: {}", param);

		Pageable pageable = new PageRequest(param.getPage(), param.getSize());
		
		Page<AtpcoRecord2GroupByRuleNoCxrTarNo> page = atpcoRuleQueryCustomRepository.groupingRuleQuery(param, pageable);
		
		List<RuleQuery> result = new ArrayList<>();
		
		if(page != null) {
			for (AtpcoRecord2GroupByRuleNoCxrTarNo record2Group : page) {
				RuleQuery rq = ruleQueryMapper.convertAtpcoRecord2GroupByRuleNoCxrTarNo(record2Group);
				result.add(rq);
			}
		}
		
		
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rule-queries");

		return new ResponseEntity<>(result, headers, HttpStatus.OK);
	}

    /**
     * GET  /rule-queries/rules : get rule query rules.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of rules in body
     */
    @GetMapping("/rule-queries/rules")
    @Timed
    public ResponseEntity<List<AtpcoRecord2>> getRuleQueryRules(RuleQuery ruleQuery) {
        log.debug("REST request to get ruleQueries rules: {}", ruleQuery);
        
        String recordId = ruleQuery.getTarNo() + ruleQuery.getCxr() + ruleQuery.getRuleNo();
        
        List<AtpcoRecord2> arecords2 = atpcoRuleQueryCustomRepository.getListRecord2ById(recordId, ruleQuery.getCatNo());

        return new ResponseEntity<>(arecords2, HttpStatus.OK);
    }

}
