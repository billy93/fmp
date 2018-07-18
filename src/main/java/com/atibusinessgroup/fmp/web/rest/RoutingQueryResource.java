package com.atibusinessgroup.fmp.web.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.RoutingQuery;
import com.atibusinessgroup.fmp.domain.RoutingQueryRestriction;
import com.atibusinessgroup.fmp.domain.RoutingQueryTextRestriction;
import com.atibusinessgroup.fmp.domain.WorkPackage.Attachment;
import com.atibusinessgroup.fmp.domain.dto.RoutingQueryExportParam;
import com.atibusinessgroup.fmp.domain.dto.RoutingQueryParam;
import com.atibusinessgroup.fmp.service.RoutingQueryService;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Routingquery.
 */
@RestController
@RequestMapping("/api")
public class RoutingQueryResource {

    private final Logger log = LoggerFactory.getLogger(RoutingQueryResource.class);

    private static final String ENTITY_NAME = "routingquery";

//    private final RoutingqueryRepository routingqueryRepository;
    //add comment by ridho-07062018
    private final RoutingQueryService routingQueryService;

    public RoutingQueryResource(RoutingQueryService routingQueryService) {
        this.routingQueryService = routingQueryService;
    }

    /**
     * POST  /routingqueries : Create a new routingquery.
     *
     * @param routingquery the routingquery to create
     * @return the ResponseEntity with status 201 (Created) and with body the new routingquery, or with status 400 (Bad Request) if the routingquery has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/routingqueries2")
    @Timed
    public ResponseEntity<RoutingQuery> createRoutingquery(@RequestBody RoutingQuery routingquery) throws URISyntaxException {
        log.debug("REST request to save Routingquery : {}", routingquery);
        if (routingquery.getId() != null) {
            throw new BadRequestAlertException("A new routingquery cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RoutingQuery result = routingQueryService.save(routingquery);
        return ResponseEntity.created(new URI("/api/routingqueries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /routingqueries : Updates an existing routingquery.
     *
     * @param routingquery the routingquery to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated routingquery,
     * or with status 400 (Bad Request) if the routingquery is not valid,
     * or with status 500 (Internal Server Error) if the routingquery couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/routingqueries")
    @Timed
    public ResponseEntity<RoutingQuery> updateRoutingquery(@RequestBody RoutingQuery routingquery) throws URISyntaxException {
        log.debug("REST request to update Routingquery : {}", routingquery);
        if (routingquery.getId() == null) {
            return createRoutingquery(routingquery);
        }
        RoutingQuery result = routingQueryService.save(routingquery);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, routingquery.getId().toString()))
            .body(result);
    }

    /**
     * GET  /routingqueries : get all the routingqueries.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of routingqueries in body
     */
    @PostMapping("/routingqueries")
    @Timed
    public ResponseEntity<List<RoutingQuery>> getAllRoutingqueries(@RequestBody RoutingQueryParam routingQueryParam) {
        log.debug("REST request to get a page of Routingqueries");
        log.debug(routingQueryParam.toString());
        Pageable pageable = new PageRequest(routingQueryParam.getPage(), routingQueryParam.getSize());
//        Page<Routingquery> page = routingQueryService.findAll(pageable);
//        Page<RoutingQuery> page = routingQueryService.findCustom(routingQueryParam, pageable);
        Page<RoutingQuery> page = routingQueryService.findCustomJoin(routingQueryParam, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/routingqueries");
        for (RoutingQuery routingQuery : page.getContent()) {
        	routingQuery = headerConditionalData(routingQuery);
		}
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /routingqueries/:id : get the "id" routingquery.
     *
     * @param id the id of the routingquery to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the routingquery, or with status 404 (Not Found)
     */
    @GetMapping("/routingqueries/{id}")
    @Timed
    public ResponseEntity<RoutingQuery> getRoutingquery(@PathVariable String id) {
        log.debug("REST request to get Routingquery : {}", id);
        RoutingQuery routingQuery = routingQueryService.findOne(id);
        routingQuery = headerConditionalData(routingQuery);
        routingQuery = routingQueryService.getFullRouteDetails(routingQuery);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(routingQuery));
    }
    
    /**
	 * GET /routingqueries/details : get detail maps of routingquery.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the detail maps in body
	 */
	@GetMapping("/routingqueries/details")
	@Timed
	public ResponseEntity<String[][]> getRouteDetails(RoutingQuery routingquery) {
		log.debug("REST request to routing query details: {}", routingquery);

		String[][] routeDetails = routingQueryService.getRouteDetails(routingquery);

		return new ResponseEntity<>(routeDetails, HttpStatus.OK);
	}
	
	/**
     * GET  /routingqueries/:id : get the "id" routingquery.
     *
     * @param id the id of the routingquery to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the routingquery, or with status 404 (Not Found)
     */
	@GetMapping("/routingqueries/getmaps/{tarno}/{crx}/{rtg}")
    @Timed
    public ResponseEntity<String[][]> getMaps(@PathVariable String tarno, @PathVariable String crx, @PathVariable String rtg) {
        log.debug("REST request to get maps : tarno : "+tarno+", crx "+crx+", rtg "+rtg);
        RoutingQueryParam routingQueryParam = new RoutingQueryParam();
        routingQueryParam.setTarNo(tarno);
        routingQueryParam.setCarrier(crx);
        routingQueryParam.setRoutingNo(rtg);
        
        RoutingQuery routingquery = routingQueryService.findOneCustom(routingQueryParam);
        String[][] detailMaps = null;
        if(routingquery != null) {
        	detailMaps = routingQueryService.getRouteDetails(routingquery);
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(detailMaps));
    }
    
    private RoutingQuery headerConditionalData(RoutingQuery routingQuery) {
    	if(routingQuery.getDiscontinuedDate().equals("indef")) routingQuery.setDiscontinuedDate("None");
    	
    	if(routingQuery.getDrv().equals("")) routingQuery.setDrv("Does Not Apply");
    	else if(routingQuery.getDrv().equals("1")) routingQuery.setDrv("Does Apply");
    	else routingQuery.setDrv("Any Online Point Allowed");
    	
    	if(routingQuery.getCpi().equals("")) routingQuery.setCpi("Does Not Apply");
    	else routingQuery.setCpi("Does Apply");
    	
    	if(routingQuery.getDi().equals("")) routingQuery.setDi("Read in Either Direction");
    	else routingQuery.setDi("Read Left to Right");
    	
    	if(routingQuery.getIntPt().equals("")) routingQuery.setIntPt("Must match to entry/exit points");
    	else routingQuery.setIntPt("Must match to entry/exit or intermediate points");
    	
    	if(routingQuery.getUntPt().equals("")) routingQuery.setUntPt("Validate ticketed points only");
    	else routingQuery.setUntPt("Validate ticketed and unticketed points");
    	
    	return routingQuery;
    }
    
    @PostMapping("/routingqueries/exportQueue")
    @Timed
    public ResponseEntity<Attachment> exportData(@RequestBody RoutingQueryExportParam routingQueryExportParam) {
    	RoutingQuery routingQuery = routingQueryExportParam.getRoutingQuery();
    	LinkedHashMap<String, Object> mapData = new LinkedHashMap<>();
    	LinkedHashMap<String, Object> restrictionsData = new LinkedHashMap<>();
    	LinkedHashMap<String, Object> textData = new LinkedHashMap<>();
    	
    	mapData.put("No.", new ArrayList<>());
    	String[][] maps = routingQuery.getDetails();
    	if(maps != null && maps.length > 0) {
    		String[] mapsFirstIndex = maps[0];
    		for (int i = 0; i < mapsFirstIndex.length; i++) {
    			mapData.put(String.valueOf(i), new ArrayList<>());
			}
    		
    		for (int i = 0; i < maps.length; i++) {
    			putValue(mapData.get("No."), String.valueOf(i+1));
    			for (int j = 0; j < maps[i].length; j++) {
    				putValue(mapData.get(String.valueOf(j)), maps[i][j]);
    			}
			}
    	}
    	
    	restrictionsData.put("No.", new ArrayList<>());
    	restrictionsData.put("Rest No", new ArrayList<>());
		int restrictionsNo = 0;
		for (RoutingQueryRestriction routingQueryRestriction : routingQuery.getRestrictions()) {
			restrictionsNo++;
			putValue(restrictionsData.get("No."), String.valueOf(restrictionsNo));
			putValue(restrictionsData.get("Rest No"), routingQueryRestriction.getRestNo());
		}
		
		textData.put("No.", new ArrayList<>());
		textData.put("Text", new ArrayList<>());
		int textNo = 0;
		for (RoutingQueryTextRestriction routingQueryTextRestriction : routingQuery.getTexts()) {
			textNo++;
			putValue(textData.get("No."), String.valueOf(textNo));
			putValue(textData.get("Text"), routingQueryTextRestriction.getText());
		}
    	
    	XSSFWorkbook workbook = new XSSFWorkbook();
    	addWorkSheet(workbook, "Routing Query Map", mapData, false);
    	addWorkSheet(workbook, "Routing Query Restrictions", restrictionsData, true);
    	addWorkSheet(workbook, "Routing Query Text", textData, true);
    	
    	ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
			workbook.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		}
        Attachment att = new Attachment();
        att.setFile(output.toByteArray());
        
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("Routing Query", ""))
            .body(att);
    }
    
    private void putValue(Object obj, String status) {
    	List<Object> object = (List<Object>) obj;
		if(status != null) {
			object.add(status);
		}
		else {
			object.add(null);
		}
	}
    
    private void addWorkSheet(XSSFWorkbook workbook, String sheetName, LinkedHashMap<String, Object> data, boolean withHeader) {
        XSSFSheet spreadsheet = workbook.createSheet(sheetName);
        
        int index = 0;
    	XSSFRow row = spreadsheet.createRow(0);
    	for(Map.Entry<String, Object> entry : data.entrySet()) {
    		Object value = entry.getValue();
    		
    		if(withHeader) {
    			String key = entry.getKey();
                XSSFCell cell = row.createCell(index);
                cell.setCellValue(key);
    		}

            if(value != null) {
            	List<Object> val = (List<Object>) value;
            	for(int x=0;x<val.size();x++) {
            		XSSFRow rows = null;
            		if(withHeader) {
            			if(spreadsheet.getRow(x+1) != null) {
                			rows = spreadsheet.getRow(x+1);
                		} else {
                			rows = spreadsheet.createRow(x+1);
                		}
            		} else {
            			if(spreadsheet.getRow(x) != null) {
                			rows = spreadsheet.getRow(x);
                		} else {
                			rows = spreadsheet.createRow(x);
                		}
            		}
            		
            		XSSFCell cellData =  rows.createCell(0+index);
            		cellData.setCellValue(val.get(x) != null ? val.get(x).toString() : "");
            	}
            }
            index++;
    	}
    }
    
    private Attachment createWorkbook(String sheetName, LinkedHashMap<String, Object> data) {
    	XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet spreadsheet = workbook.createSheet(sheetName);
    	
        int index = 0;
    	XSSFRow row = spreadsheet.createRow(0);
    	for(Map.Entry<String, Object> entry : data.entrySet()) {
    		Object value = entry.getValue();
    		String key = entry.getKey();
            XSSFCell cell;

            cell = row.createCell(index);
            cell.setCellValue(key);

            if(value != null) {
            	List<Object> val = (List<Object>) value;
            	for(int x=0;x<val.size();x++) {
            		XSSFRow rows = null;
            		if(spreadsheet.getRow(x+1) != null) {
            			rows = spreadsheet.getRow(x+1);
            		} else {
            			rows = spreadsheet.createRow(x+1);
            		}
            		XSSFCell cellData =  rows.createCell(0+index);
            		cellData.setCellValue(val.get(x) != null ? val.get(x).toString() : "");
            	}
            }
            index++;
    	}


        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
			workbook.write(output);
		} catch (IOException e) {
			e.printStackTrace();
		}

        Attachment att = new Attachment();
        att.setFile(output.toByteArray());

        return att;
    }
}
