package com.atibusinessgroup.fmp.web.rest;

import java.util.ArrayList;
import java.util.Iterator;
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
import com.atibusinessgroup.fmp.domain.AtpcoMasterTariff;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFootnoteRecord2;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteQueryDetails;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteQueryGroup;
import com.atibusinessgroup.fmp.domain.dto.AtpcoFootnoteRecord2GroupByCatNo;
import com.atibusinessgroup.fmp.domain.dto.Category;
import com.atibusinessgroup.fmp.domain.dto.CategoryTextFormatAndAttribute;
import com.atibusinessgroup.fmp.domain.dto.DataTable;
import com.atibusinessgroup.fmp.domain.dto.FootnoteQuery;
import com.atibusinessgroup.fmp.domain.dto.FootnoteQueryParam;
import com.atibusinessgroup.fmp.repository.AtpcoMasterTariffRepository;
import com.atibusinessgroup.fmp.repository.custom.AtpcoFareCustomRepository;
import com.atibusinessgroup.fmp.repository.custom.AtpcoFootnoteQueryCustomRepository;
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

	private final AtpcoMasterTariffRepository tariffNumberRepository;
	
	public FootnoteQueryResource(AtpcoFootnoteQueryCustomRepository atpcoFootnoteQueryCustomRepository,
			FootnoteQueryMapper footnoteQueryMapper, AtpcoFareCustomRepository atpcoFareCustomRepository, AtpcoRecordService atpcoRecordService,
			AtpcoMasterTariffRepository tariffNumberRepository) {
		this.atpcoFootnoteQueryCustomRepository = atpcoFootnoteQueryCustomRepository;
		this.footnoteQueryMapper = footnoteQueryMapper;

		this.atpcoFareCustomRepository = atpcoFareCustomRepository;
		this.atpcoRecordService = atpcoRecordService;

		this.tariffNumberRepository = tariffNumberRepository;
		
		categories.put("003", CategoryName.CAT_003);
		categories.put("011", CategoryName.CAT_011);
		categories.put("014", CategoryName.CAT_014);
		categories.put("015", CategoryName.CAT_015);
		categories.put("023", CategoryName.CAT_023);
		
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

		Page<AtpcoFootnoteQueryGroup> page = atpcoFootnoteQueryCustomRepository.groupingFootnoteQueryAll(param, pageable);
		List<FootnoteQuery> result = new ArrayList<>();

		if (page != null) {
			
			for (AtpcoFootnoteQueryGroup ftntData : page) {
				FootnoteQuery rq = footnoteQueryMapper.convertAndGroupFootnote(ftntData, param.getCatNo());
				result.add(rq);
			}
		}

		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/footnote-queries");

		return new ResponseEntity<>(result, headers, HttpStatus.OK);
	}

	@PostMapping("/footnote-queries/available")
	@Timed
	public ResponseEntity<List<FootnoteQuery>> getAllFootnoteQueriesAvailable(@RequestBody FootnoteQueryParam param) {
		log.debug("REST request to get a page of Footnote Queries: {}", param);

		Pageable pageable = new PageRequest(param.getPage(), param.getSize());

		Page<AtpcoFootnoteQueryGroup> page = atpcoFootnoteQueryCustomRepository.groupingFootnoteQueryAvailable(param, pageable);
		List<FootnoteQuery> result = new ArrayList<>();

		if (page != null) {
			
			for (AtpcoFootnoteQueryGroup ftntData : page) {
				FootnoteQuery rq = footnoteQueryMapper.convertAndGroupFootnote(ftntData, param.getCatNo());
				result.add(rq);
			}
		}

		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/footnote-queries");

		return new ResponseEntity<>(result, headers, HttpStatus.OK);
	}
	
	@PostMapping("/footnote-queries/expired")
	@Timed
	public ResponseEntity<List<FootnoteQuery>> getAllFootnoteQueriesExpired(@RequestBody FootnoteQueryParam param) {
		log.debug("REST request to get a page of Footnote Queries: {}", param);

		Pageable pageable = new PageRequest(param.getPage(), param.getSize());

		Page<AtpcoFootnoteQueryGroup> page = atpcoFootnoteQueryCustomRepository.groupingFootnoteQueryExpired(param, pageable);
		List<FootnoteQuery> result = new ArrayList<>();
		if (page != null) {
			
			for (AtpcoFootnoteQueryGroup ftntData : page) {
				FootnoteQuery rq = footnoteQueryMapper.convertAndGroupFootnote(ftntData,  param.getCatNo());
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
	public ResponseEntity<List<AtpcoFootnoteQueryDetails>> getFootnoteQueryRules(FootnoteQuery footnoteQuery) {
//		log.debug("REST request to get footnoteQueries rules: {}", footnoteQuery);

		String recordId = footnoteQuery.getTarNo() + footnoteQuery.getCxr() + footnoteQuery.getFtnt() + "";

		List<AtpcoFootnoteQueryDetails> arecords2 = atpcoFootnoteQueryCustomRepository.getListFtntRecord2(recordId);

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

		AtpcoMasterTariff ftntTn = tariffNumberRepository.findOneByTarNoAndType(footnoteQuery.getTarNo(), "FARE RULE");
		String ftntTcd = null;
		
		if (ftntTn != null) {
			ftntTcd = ftntTn.getTarCd();
		}
		
		for (Map.Entry<String, String> entry : categories.entrySet()) {
			Category cat = new Category();
			cat.setCatName(entry.getValue());

			try {
				cat.setCatNo(Integer.parseInt(entry.getKey()));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String type = "", allText = "";
			
			for (AtpcoFootnoteRecord2GroupByCatNo arecord2 : arecords2) {
				if (arecord2.getCatNo().contentEquals(entry.getKey())) {
					for (AtpcoFootnoteRecord2 frecord2:arecord2.getRecords2()) {
						List<DataTable> dataTables = frecord2.getDataTables();
						
	        			for (Iterator<DataTable> iterator = dataTables.iterator(); iterator.hasNext();) {
	        				DataTable dt = iterator.next();
	        				if (!dt.getCatNo().contentEquals(entry.getKey())) {
	        					iterator.remove();
	        				}
	        			}
	        			
	        			if (dataTables.size() > 0) {
							type = CategoryType.FOOTNOTE;
				        	String textFormat = atpcoRecordService.generateCategoryTextHeader(CategoryType.FOOTNOTE, footnoteQuery.getTarNo(), ftntTcd, footnoteQuery.getFtnt(), frecord2.getSequenceNo(), frecord2.getEffectiveDateObject());
			        		CategoryTextFormatAndAttribute ctfa = atpcoRecordService.getAndConvertCategoryDataTable(entry.getKey(), dataTables, CategoryType.FOOTNOTE);
			        		textFormat += ctfa.getTextFormat();
			        		cat.getCatAttributes().addAll(ctfa.getAttributes());
			        		
			        		allText += textFormat + "\n";
						}
					}
					
					break;
				}
			}
			
			if (!type.isEmpty()) {
				cat.setType(type);
			}
			
			cat.setTextFormat(allText);
			
			result.add(cat);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
