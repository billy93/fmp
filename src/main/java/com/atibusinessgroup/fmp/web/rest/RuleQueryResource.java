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
import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord2;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.AtpcoRecord2GroupByRuleNoCxrTarNo;
import com.atibusinessgroup.fmp.domain.dto.Category;
import com.atibusinessgroup.fmp.domain.dto.DataTable;
import com.atibusinessgroup.fmp.domain.dto.RuleQuery;
import com.atibusinessgroup.fmp.domain.dto.RuleQueryParam;
import com.atibusinessgroup.fmp.repository.custom.AtpcoFareCustomRepository;
import com.atibusinessgroup.fmp.repository.custom.AtpcoRuleQueryCustomRepository;
import com.atibusinessgroup.fmp.service.AtpcoRecordService;
import com.atibusinessgroup.fmp.service.mapper.RuleQueryMapper;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class RuleQueryResource {

	private final LinkedHashMap<String, String> categories = new LinkedHashMap<>();

	private final Logger log = LoggerFactory.getLogger(RuleQueryResource.class);

	private final AtpcoRuleQueryCustomRepository atpcoRuleQueryCustomRepository;

	private final RuleQueryMapper ruleQueryMapper;

	private final AtpcoFareCustomRepository atpcoFareCustomRepository;

	private final AtpcoRecordService atpcoRecordService;

	public RuleQueryResource(AtpcoRuleQueryCustomRepository atpcoRuleQueryCustomRepository,
			RuleQueryMapper ruleQueryMapper, AtpcoFareCustomRepository atpcoFareCustomRepository, AtpcoRecordService atpcoRecordService) {
		this.atpcoRuleQueryCustomRepository = atpcoRuleQueryCustomRepository;
		this.ruleQueryMapper = ruleQueryMapper;

		this.atpcoFareCustomRepository = atpcoFareCustomRepository;
		this.atpcoRecordService = atpcoRecordService;

		categories.put("001", CategoryName.CAT_001);
		categories.put("002", CategoryName.CAT_002);
		categories.put("003", CategoryName.CAT_003);
		categories.put("004", CategoryName.CAT_004);
		categories.put("005", CategoryName.CAT_005);
		categories.put("006", CategoryName.CAT_006);
		categories.put("007", CategoryName.CAT_007);
		categories.put("008", CategoryName.CAT_008);
		categories.put("009", CategoryName.CAT_009);
		categories.put("010", CategoryName.CAT_010);
		categories.put("011", CategoryName.CAT_011);
		categories.put("012", CategoryName.CAT_012);
		categories.put("013", CategoryName.CAT_013);
		categories.put("014", CategoryName.CAT_014);
		categories.put("015", CategoryName.CAT_015);
		categories.put("016", CategoryName.CAT_016);
		categories.put("017", CategoryName.CAT_017);
		categories.put("018", CategoryName.CAT_018);
		categories.put("019", CategoryName.CAT_019);
		categories.put("020", CategoryName.CAT_020);
		categories.put("021", CategoryName.CAT_021);
		categories.put("022", CategoryName.CAT_022);
		categories.put("023", CategoryName.CAT_023);
		categories.put("026", CategoryName.CAT_026);
		categories.put("027", CategoryName.CAT_027);
		categories.put("028", CategoryName.CAT_028);
		categories.put("029", CategoryName.CAT_029);
		categories.put("031", CategoryName.CAT_031);
		categories.put("033", CategoryName.CAT_033);
		categories.put("035", CategoryName.CAT_035);
		categories.put("050", CategoryName.CAT_050);
	}

	/**
	 * POST /rule-queries : get all the rule queries.
	 *
	 * @param rule
	 *            params, pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of ruleQueries
	 *         in body
	 */
	@PostMapping("/rule-queries")
	@Timed
	public ResponseEntity<List<RuleQuery>> getAllRuleQueries(@RequestBody RuleQueryParam param) {
		log.debug("REST request to get a page of RuleQueries: {}", param);

		Pageable pageable = new PageRequest(param.getPage(), param.getSize());

		Page<AtpcoRecord2GroupByRuleNoCxrTarNo> page = atpcoRuleQueryCustomRepository.getGroupingRuleQueries(param, pageable);

		List<RuleQuery> result = new ArrayList<>();

		if (page != null) {
			for (AtpcoRecord2GroupByRuleNoCxrTarNo record2Group : page.getContent()) {
				RuleQuery rq = ruleQueryMapper.convertAtpcoRecord2GroupByRuleNoCxrTarNo(record2Group);
				result.add(rq);
			}
		}

		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/rule-queries");

		return new ResponseEntity<>(result, headers, HttpStatus.OK);
	}

	/**
	 * GET /rule-queries/rules : get rule query rules.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of rules in body
	 */
	@GetMapping("/rule-queries/rules")
	@Timed
	public ResponseEntity<List<AtpcoRecord2>> getRuleQueryRules(RuleQuery ruleQuery) {
		log.debug("REST request to get ruleQueries rules: {}", ruleQuery);

		String recordId = ruleQuery.getTarNo() + ruleQuery.getCxr() + ruleQuery.getRuleNo();

		List<AtpcoRecord2> arecords2 = atpcoRuleQueryCustomRepository.getListRecord2ById(recordId,
				ruleQuery.getCatNo());

		return new ResponseEntity<>(arecords2, HttpStatus.OK);
	}

	/**
	 * GET /rule-queries/rules : get rule query rules.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of rules in body
	 */
	@GetMapping("/rule-queries/rules2")
	@Timed
	public ResponseEntity<List<Category>> getRuleQueryRules2(RuleQuery ruleQuery) {
		log.debug("REST request to get ruleQueries rules: {}", ruleQuery);

		List<Category> result = new ArrayList<>();

		String recordId = ruleQuery.getTarNo() + ruleQuery.getCxr() + ruleQuery.getRuleNo() + "";

		List<AtpcoRecord2GroupByCatNo> arecords2 = atpcoFareCustomRepository.findAtpcoRecord2ByRecordId(recordId);

		for (Map.Entry<String, String> entry : categories.entrySet()) {
			List<DataTable> dataTables = new ArrayList<>();
			
			for (AtpcoRecord2GroupByCatNo arecord2 : arecords2) {
				if (arecord2.getCatNo().contentEquals(entry.getKey())) {
					for (AtpcoRecord2 record2 : arecord2.getRecords2()) {
						for (DataTable dt:record2.getDataTables()) {
							if (!dataTables.contains(dt)) {
								dataTables.add(dt);
							}
						}
					}
					break;
				}
			}
			
			Category cat = new Category();
			cat.setCatName(entry.getValue());
			cat.setType(CategoryType.RULE);

			try {
				cat.setCatNo(Integer.parseInt(entry.getKey()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (dataTables.size() > 0) {
				cat.setCatAttributes(atpcoRecordService.getAndConvertCategoryDataTable(entry.getKey(), dataTables, CategoryType.RULE).getAttributes());
			}

			result.add(cat);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
