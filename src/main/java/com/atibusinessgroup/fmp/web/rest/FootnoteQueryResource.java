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
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFootnoteRecord2;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteRecord2GroupByFtntCxrTarNo;
import com.atibusinessgroup.fmp.domain.dto.Category;
import com.atibusinessgroup.fmp.domain.dto.DataTable;
import com.atibusinessgroup.fmp.domain.dto.FootnoteQuery;
import com.atibusinessgroup.fmp.domain.dto.FootnoteQueryParam;
import com.atibusinessgroup.fmp.resository.custom.AtpcoFareCustomRepository;
import com.atibusinessgroup.fmp.resository.custom.AtpcoFootnoteQueryCustomRepository;
import com.atibusinessgroup.fmp.service.AtpcoRecordService;
import com.atibusinessgroup.fmp.service.mapper.FootnoteQueryMapper;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class FootnoteQueryResource {

	private final LinkedHashMap<String, String> categories = new LinkedHashMap<>();

	private final Logger log = LoggerFactory.getLogger(FootnoteQueryResource.class);

	private final AtpcoFootnoteQueryCustomRepository atpcoFootnoteQueryCustomRepository;

	private final FootnoteQueryMapper footnoteQueryMapper;

	private final AtpcoFareCustomRepository atpcoFareCustomRepository;

	private final AtpcoRecordService atpcoRecordService;

	public FootnoteQueryResource(AtpcoFootnoteQueryCustomRepository atpcoFootnoteQueryCustomRepository,
			FootnoteQueryMapper footnoteQueryMapper, AtpcoFareCustomRepository atpcoFareCustomRepository, AtpcoRecordService atpcoRecordService) {
		this.atpcoFootnoteQueryCustomRepository = atpcoFootnoteQueryCustomRepository;
		this.footnoteQueryMapper = footnoteQueryMapper;

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
	 * POST /footnote-queries : get all the footnote queries.
	 *
	 * @param footnote
	 *            params, pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of footnoteQueries
	 *         in body
	 */
	@PostMapping("/footnote-queries")
	@Timed
	public ResponseEntity<List<FootnoteQuery>> getAllFootnoteQueries(@RequestBody FootnoteQueryParam param) {
		log.debug("REST request to get a page of Footnote Queries: {}", param);

		Pageable pageable = new PageRequest(param.getPage(), param.getSize());

		Page<AtpcoFootnoteRecord2GroupByFtntCxrTarNo> page = atpcoFootnoteQueryCustomRepository.groupingFootnoteQuery(param, pageable);
		List<FootnoteQuery> result = new ArrayList<>();

		if (page != null) {
			for (AtpcoFootnoteRecord2GroupByFtntCxrTarNo record2Group : page.getContent()) {
				FootnoteQuery rq = footnoteQueryMapper.convertAndGroupFootnote(record2Group);
				result.add(rq);
			}
		}

		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/footnote-queries");

		return new ResponseEntity<>(result, headers, HttpStatus.OK);
	}

	/**
	 * GET /footnote-queries/rules : get footnote query rules.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of footnotes in body
	 */
	@GetMapping("/footnote-queries/rules")
	@Timed
	public ResponseEntity<List<AtpcoFootnoteRecord2>> getFootnoteQueryRules(FootnoteQuery footnoteQuery) {
		log.debug("REST request to get footnoteQueries rules: {}", footnoteQuery);

		String recordId = footnoteQuery.getTarNo() + footnoteQuery.getCxr() + footnoteQuery.getFtnt();

		List<AtpcoFootnoteRecord2> arecords2 = atpcoFootnoteQueryCustomRepository.getListFtntRecord2(recordId);

		return new ResponseEntity<>(arecords2, HttpStatus.OK);
	}

	/**
	 * GET /footnote-queries/rules : get footnote query rules.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of footnotes in body
	 */
	@GetMapping("/footnote-queries/rules2")
	@Timed
	public ResponseEntity<List<Category>> getFootnoteQueryRules2(FootnoteQuery footnoteQuery) {
		log.debug("REST request to get footnoteQueries rules: {}", footnoteQuery);

		List<Category> result = new ArrayList<>();

		String recordId = footnoteQuery.getTarNo() + footnoteQuery.getCxr() + footnoteQuery.getFtnt() + "";

		List<AtpcoFootnoteRecord2GroupByCatNo> arecords2 = atpcoFootnoteQueryCustomRepository.groupFootnoteByRecordId(recordId);

		for (Map.Entry<String, String> entry : categories.entrySet()) {
			List<DataTable> dataTables = new ArrayList<>();
			
			for (AtpcoFootnoteRecord2GroupByCatNo arecord2 : arecords2) {
				if (arecord2.getCatNo().contentEquals(entry.getKey())) {
					for (AtpcoFootnoteRecord2 record2 : arecord2.getRecords2()) {
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
			cat.setType(CategoryType.FOOTNOTE);

			try {
				cat.setCatNo(Integer.parseInt(entry.getKey()));
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (dataTables.size() > 0) {
				cat.setCatAttributes(atpcoRecordService.getAndConvertCategoryDataTable(entry.getKey(), dataTables, CategoryType.FOOTNOTE));
			}

			result.add(cat);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}