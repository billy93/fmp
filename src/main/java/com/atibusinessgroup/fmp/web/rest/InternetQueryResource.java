package com.atibusinessgroup.fmp.web.rest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.WorkPackage.Attachment;
import com.atibusinessgroup.fmp.domain.dto.InternetQuery;
import com.atibusinessgroup.fmp.domain.dto.InternetQueryExportParam;
import com.atibusinessgroup.fmp.domain.dto.InternetQueryParam;
import com.atibusinessgroup.fmp.domain.dto.MasterWebsite;
import com.atibusinessgroup.fmp.service.InternetQueryService;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class InternetQueryResource {

	private final Logger log = LoggerFactory.getLogger(InternetQueryResource.class);
	private final InternetQueryService internetQueryService;
	
	public InternetQueryResource(InternetQueryService internetQueryService) {
		this.internetQueryService = internetQueryService;
	}
	
	@PostMapping("/internet-query")
    @Timed
    public ResponseEntity<List<InternetQuery>> getAllInternetQueries(@RequestBody InternetQueryParam param) {
		Pageable pageable = new PageRequest(param.getPage(), param.getSize());
		Page<InternetQuery> result = null;
		
		if(param.getSummarizeType() == 0) {
			result = internetQueryService.getSummarizeCaptDateQueries(param, pageable);
		} else if(param.getSummarizeType() == 1) {
			result = internetQueryService.getSummarizeDeptDateQueries(param, pageable);
		} else if(param.getSummarizeType() == 2) {
			result = internetQueryService.getDontSummarizeQueries(param, pageable);
		}
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(result, "/api/internet-queries");
		
		return new ResponseEntity<>(result.getContent(), headers, HttpStatus.OK);
	}
	
	@GetMapping("/master-website/getAll")
    @Timed
    public ResponseEntity<List<MasterWebsite>> getAllCarriers() {
        List<MasterWebsite> result = internetQueryService.findAllMasterWebsite();
        
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
	
	@PostMapping("/internet-query/exportQueue")
    @Timed
    public ResponseEntity<Attachment> printExport(@RequestBody InternetQueryExportParam internetQueryExportParam) {
		InternetQueryParam internetQueryParam = internetQueryExportParam.getInternetQueryParam();
		Pageable pageable = new PageRequest(internetQueryParam.getPage(), internetQueryParam.getSize());
		Page<InternetQuery> result = null;
		LinkedHashMap<String, Object> data = new LinkedHashMap<>();
		
		if(internetQueryParam.getSummarizeType() == 0) {
			result = internetQueryService.getSummarizeCaptDateQueries(internetQueryParam, pageable);
			List<InternetQuery> internetQueries = result.getContent();
			
			data.put("Cxr", new ArrayList<>());
			data.put("Orig", new ArrayList<>());
			data.put("Dest", new ArrayList<>());
			data.put("A/P Days", new ArrayList<>());
			data.put("Website", new ArrayList<>());
			
			if(internetQueries.size() > 0) {
				List<String> dateList = new ArrayList<>();
				for(Map.Entry<String, BigDecimal> entry : internetQueries.get(0).getDatePrice().entrySet()) {
					String key = entry.getKey();
					dateList.add(key);
					data.put(key, new ArrayList<>());
				}
				
				for (InternetQuery internetQuery : internetQueries) {
					putValue(data.get("Cxr"), internetQuery.getCxr());
					putValue(data.get("Orig"), internetQuery.getOrigin());
					putValue(data.get("Dest"), internetQuery.getDestination());
					putValue(data.get("A/P Days"), String.valueOf(internetQuery.getApDays()));
					putValue(data.get("Website"), internetQuery.getWebsite());
					
					for (String dateStr : dateList) {
						BigDecimal aif = internetQuery.getDatePrice().get(dateStr);
						putValue(data.get(dateStr), aif != null ? aif.toString() : null);
					}
				}
			}
		} else if(internetQueryParam.getSummarizeType() == 1) {
			result = internetQueryService.getSummarizeDeptDateQueries(internetQueryParam, pageable);
			List<InternetQuery> internetQueries = result.getContent();
			
			data.put("Cxr", new ArrayList<>());
			data.put("Orig", new ArrayList<>());
			data.put("Dest", new ArrayList<>());
			data.put("A/P Days", new ArrayList<>());
			data.put("Website", new ArrayList<>());
			
			if(internetQueries.size() > 0) {
				List<String> dateList = new ArrayList<>();
				for(Map.Entry<String, BigDecimal> entry : internetQueries.get(0).getDatePrice().entrySet()) {
					String key = entry.getKey();
					dateList.add(key);
					data.put(key, new ArrayList<>());
				}
				
				for (InternetQuery internetQuery : internetQueries) {
					putValue(data.get("Cxr"), internetQuery.getCxr());
					putValue(data.get("Orig"), internetQuery.getOrigin());
					putValue(data.get("Dest"), internetQuery.getDestination());
					putValue(data.get("A/P Days"), String.valueOf(internetQuery.getApDays()));
					putValue(data.get("Website"), internetQuery.getWebsite());
					
					for (String dateStr : dateList) {
						BigDecimal aif = internetQuery.getDatePrice().get(dateStr);
						putValue(data.get(dateStr), aif != null ? aif.toString() : null);
					}
				}
			}
		} else if(internetQueryParam.getSummarizeType() == 2) {
			result = internetQueryService.getDontSummarizeQueries(internetQueryParam, pageable);
			List<InternetQuery> internetQueries = result.getContent();
			
			data.put("Website", new ArrayList<>());
	    	data.put("Capture Date", new ArrayList<>());
	    	data.put("A/P Days", new ArrayList<>());
	    	data.put("Cxr", new ArrayList<>());
	    	data.put("Airline", new ArrayList<>());
	    	data.put("Trip Type", new ArrayList<>());
	    	data.put("Orig", new ArrayList<>());
	    	data.put("Orig. Name", new ArrayList<>());
	    	data.put("Dest", new ArrayList<>());
	    	data.put("Dest. Name", new ArrayList<>());
	    	data.put("Fare Basis", new ArrayList<>());
	    	data.put("Departure", new ArrayList<>());
	    	data.put("Depart Time", new ArrayList<>());
	    	data.put("Return", new ArrayList<>());
	    	data.put("Return Time", new ArrayList<>());
	    	data.put("Flight No", new ArrayList<>());
	    	data.put("Base Amt", new ArrayList<>());
	    	data.put("Taxes", new ArrayList<>());
	    	data.put("AIF", new ArrayList<>());
	    	data.put("Curr", new ArrayList<>());
	    	data.put("Ref Amt (IDR)", new ArrayList<>());
	    	
	    	SimpleDateFormat dateSDF = new SimpleDateFormat("dd MMM yyyy");
	    	SimpleDateFormat timeSDF = new SimpleDateFormat("HH:mm:ss");
	    	for (InternetQuery internetQuery : internetQueries) {
	    		putValue(data.get("Website"), internetQuery.getWebsite());
	    		putValue(data.get("Capture Date"), internetQuery.getCaptureDateTime() != null ? dateSDF.format(internetQuery.getCaptureDateTime()) : null);
	    		putValue(data.get("A/P Days"), String.valueOf(internetQuery.getApDays()));
	    		putValue(data.get("Cxr"), internetQuery.getCxr());
	    		putValue(data.get("Airline"), internetQuery.getAirline());
	    		putValue(data.get("Trip Type"), internetQuery.getTripType());
	    		putValue(data.get("Orig"), internetQuery.getOrigin());
	    		putValue(data.get("Orig. Name"), internetQuery.getOriginName());
	    		putValue(data.get("Dest"), internetQuery.getDestination());
	    		putValue(data.get("Dest. Name"), internetQuery.getDestinationName());
	    		putValue(data.get("Fare Basis"), internetQuery.getFareBasis());
	    		putValue(data.get("Departure"), internetQuery.getDepartDateTime() != null ? dateSDF.format(internetQuery.getDepartDateTime()) : null);
	    		putValue(data.get("Depart Time"), internetQuery.getDepartDateTime() != null ? timeSDF.format(internetQuery.getDepartDateTime()) : null);
	    		putValue(data.get("Return"), (internetQuery.getReturnDateTime() != null && !internetQuery.getReturnDateTime().equals("indef")) ? dateSDF.format(internetQuery.getReturnDateTime()) : null);
	    		putValue(data.get("Return Time"), (internetQuery.getReturnDateTime() != null && !internetQuery.getReturnDateTime().equals("indef")) ? timeSDF.format(internetQuery.getReturnDateTime()) : null);
	    		putValue(data.get("Flight No"), internetQuery.getFlightNumber());
	    		putValue(data.get("Base Amt"), internetQuery.getBaseAmtBD().toString());
	    		putValue(data.get("Taxes"), internetQuery.getTaxesBD().toString());
	    		putValue(data.get("AIF"), internetQuery.getAifBD().toString());
	    		putValue(data.get("Curr"), internetQuery.getCurrency());
	    		putValue(data.get("Ref Amt (IDR)"), internetQuery.getRefAmt());
	    	}
		}
    	
		Attachment att = createWorkbook("Internet Query", data);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert("Internet Query", ""))
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
