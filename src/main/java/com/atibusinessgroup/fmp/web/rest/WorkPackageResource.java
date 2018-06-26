package com.atibusinessgroup.fmp.web.rest;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.Units;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.TariffNumber;
import com.atibusinessgroup.fmp.domain.User;
import com.atibusinessgroup.fmp.domain.WorkPackage;
import com.atibusinessgroup.fmp.domain.WorkPackage.ApproveConfig;
import com.atibusinessgroup.fmp.domain.WorkPackage.Attachment;
import com.atibusinessgroup.fmp.domain.WorkPackage.Comment;
import com.atibusinessgroup.fmp.domain.WorkPackage.ImportFares;
import com.atibusinessgroup.fmp.domain.WorkPackage.WorkPackageFareSheet;
import com.atibusinessgroup.fmp.domain.WorkPackage.WorkPackageFareSheet.FareVersion;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.WorkPackageFare;
import com.atibusinessgroup.fmp.domain.WorkPackageFilter;
import com.atibusinessgroup.fmp.domain.WorkPackageHistory;
import com.atibusinessgroup.fmp.domain.enumeration.Status;
import com.atibusinessgroup.fmp.repository.AtpcoFareRepository;
import com.atibusinessgroup.fmp.repository.ContractFMPRepository;
import com.atibusinessgroup.fmp.repository.ContractFareFMPRepository;
import com.atibusinessgroup.fmp.repository.CounterRepository;
import com.atibusinessgroup.fmp.repository.FormRepository;
import com.atibusinessgroup.fmp.repository.PriorityRepository;
import com.atibusinessgroup.fmp.repository.TariffNumberRepository;
import com.atibusinessgroup.fmp.repository.UserRepository;
import com.atibusinessgroup.fmp.repository.WorkPackageFareHistoryDataRepository;
import com.atibusinessgroup.fmp.repository.WorkPackageFilterRepository;
import com.atibusinessgroup.fmp.repository.WorkPackageHistoryDataRepository;
import com.atibusinessgroup.fmp.security.SecurityUtils;
import com.atibusinessgroup.fmp.service.BusinessAreaService;
import com.atibusinessgroup.fmp.service.MailService;
import com.atibusinessgroup.fmp.service.ReviewLevelService;
import com.atibusinessgroup.fmp.service.TargetDistributionService;
import com.atibusinessgroup.fmp.service.UserService;
import com.atibusinessgroup.fmp.service.WorkPackageFareService;
import com.atibusinessgroup.fmp.service.WorkPackageHistoryService;
import com.atibusinessgroup.fmp.service.WorkPackageService;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing WorkPackage.
 */
@RestController
@RequestMapping("/api")
public class WorkPackageResource {

    private final Logger log = LoggerFactory.getLogger(WorkPackageResource.class);

    private static final String ENTITY_NAME = "workPackage";

    private final WorkPackageService workPackageService;
    private final WorkPackageFareService workPackageFareService;
    private final TargetDistributionService targetDistributionService;
    private final BusinessAreaService businessAreaService;
    private final ReviewLevelService reviewLevelService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final WorkPackageHistoryService workPackageHistoryService;
    private final FormRepository formRepository;
    private final ContractFMPRepository contractFMPRepository;
    private final ContractFareFMPRepository contractFareFMPRepository;
    private final WorkPackageHistoryDataRepository workPackageHistoryDataRepository;
    private final WorkPackageFareHistoryDataRepository workPackageFareHistoryDataRepository;
    private final CounterRepository counterRepository;
    private final PriorityRepository priorityRepository;
    private final MailService mailService;
    private final TariffNumberRepository tariffNumberRepository;
    private final WorkPackageFilterRepository packagefilterRepository;
    private final AtpcoFareRepository atpcoFareRepository;
    
    public WorkPackageResource(WorkPackageService workPackageService, WorkPackageFareService workPackageFareService, TargetDistributionService targetDistributionService, BusinessAreaService businessAreaService, ReviewLevelService reviewLevelService, UserService userService, UserRepository userRepository, WorkPackageHistoryService workPackageHistoryService,
    		ContractFMPRepository contractFMPRepository, FormRepository formRepository, WorkPackageHistoryDataRepository workPackageHistoryDataRepository,
    		WorkPackageFareHistoryDataRepository workPackageFareHistoryDataRepository, ContractFareFMPRepository contractFareFMPRepository, CounterRepository counterRepository, PriorityRepository priorityRepository, MailService mailService, TariffNumberRepository tariffNumberRepository, WorkPackageFilterRepository packagefilterRepository, AtpcoFareRepository atpcoFareRepository) {
        this.workPackageService = workPackageService;
        this.workPackageFareService = workPackageFareService;
        this.targetDistributionService = targetDistributionService;
        this.businessAreaService = businessAreaService;
        this.reviewLevelService = reviewLevelService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.workPackageHistoryService = workPackageHistoryService;  
        this.contractFMPRepository = contractFMPRepository;
        this.formRepository = formRepository;
        this.workPackageHistoryDataRepository = workPackageHistoryDataRepository;
        this.workPackageFareHistoryDataRepository = workPackageFareHistoryDataRepository;
        this.contractFareFMPRepository = contractFareFMPRepository;
        this.counterRepository = counterRepository;
        this.priorityRepository = priorityRepository;
        this.mailService = mailService;
        this.tariffNumberRepository = tariffNumberRepository;
        this.packagefilterRepository=  packagefilterRepository;
        this.atpcoFareRepository = atpcoFareRepository;
    }

    /**
     * POST  /work-packages : Create a new workPackage.
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages")
    @Timed
    public ResponseEntity<WorkPackage> createWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to save WorkPackage : {}", workPackage);
        if (workPackage.getId() != null) {
            throw new BadRequestAlertException("A new workPackage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        
        Optional<User> user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());   
        
        if(user.get().getReviewLevels().size()==1) {
        	workPackage.setReviewLevel(user.get().getReviewLevels().get(0));	
        }else {
        	if(user.get().getReviewLevels().indexOf("HO") > 0){
        		workPackage.setReviewLevel("HO");
        	}else {
        		if(user.get().getReviewLevels().indexOf("LSO") > 0){
            		workPackage.setReviewLevel("LSO");
            	}
        	}
        	
        }
        
        
        if(workPackage.isSpecifiedFares()) {
        	workPackage.setFilingDetails(true);
        }
        else {
        	
        }
        if(workPackage.isAddon()) {
        	
        }
        else {
        	workPackage.getAddonFareSheet().clear();
        }
        if(workPackage.isMarketFares()) {
        	
        } else {
        	workPackage.getMarketFareSheet().clear();
        }
        if(workPackage.isDiscount()) {
        	
        } else {
        	workPackage.getDiscountFareSheet().clear();
        }
        
        workPackage.setStatus(Status.NEW);
        workPackage.setQueuedDate(Instant.now());
        
        WorkPackage result = workPackageService.save(workPackage);
        
        WorkPackageHistory history = new WorkPackageHistory();
        history.setWorkPackage(new ObjectId(result.getId()));
        history.setType("CREATE");
        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
        workPackageHistoryService.save(history);
        
        return ResponseEntity.created(new URI("/api/work-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    static long zonedDateTimeDifference(ZonedDateTime d1, ZonedDateTime d2, ChronoUnit unit){
        return unit.between(d1, d2);
    }
    
    /**
     * POST  /work-packages/discontinue : Discontinue a workPackage.
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/discontinue")
    @Timed
    public ResponseEntity<WorkPackage> discontinueWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to discontinue WorkPackage : {}", workPackage);
        
        workPackage = workPackageService.findOne(workPackage.getId());
        List<WorkPackageFareSheet> sheets = workPackage.getMarketFareSheet();
        for(WorkPackageFareSheet sheet : sheets) {
        	for(WorkPackageFare fare : sheet.getFares()) {
//        		if(fare.getSaleStart() != null) {
//        			if(zonedDateTimeDifference(ZonedDateTime.now(), fare.getSaleStart(), ChronoUnit.DAYS) > 0) {
//        				fare.setSaleStart(ZonedDateTime.now());
//        			}
//        		}
        		
        		if(fare.getSaleEnd() != null) {
        			if(zonedDateTimeDifference(ZonedDateTime.now(), fare.getSaleEnd(), ChronoUnit.DAYS) > 0) {
        				fare.setSaleEnd(ZonedDateTime.now());
        			}
        		}        		
        	}        			
        }
        workPackage.setStatus(Status.DISCONTINUED);
        WorkPackage result = workPackageService.save(workPackage);
        
        WorkPackageHistory history = new WorkPackageHistory();
        history.setWorkPackage(new ObjectId(result.getId()));
        history.setType("DISCONTINUED");
        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
        workPackageHistoryService.save(history);
        
        return ResponseEntity.created(new URI("/api/work-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * POST  /work-packages/reuse : Reuse a new workPackage.
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/reuse")
    @Timed
    public ResponseEntity<WorkPackage> reuseWorkPackage(@RequestBody WorkPackage wp) throws URISyntaxException {
        log.debug("REST request to save reuse WorkPackage : {}", wp);
        
        WorkPackageHistory history = new WorkPackageHistory();
        history.setWorkPackage(new ObjectId(wp.getId()));
        history.setType("REUSE");
        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
        workPackageHistoryService.save(history);
        
        wp.setReuseFrom(wp.getWpid());
        wp.setId(null);
        wp.setWpid(null);
        
        User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get()).get();
        wp.setReviewLevel(user.getReviewLevels().get(0));
        wp.setComment(null);
        wp.setInterofficeComment(null);
        wp.setFilingInstructionData(null);     
        wp.setCreatedBy(null);
        wp.setCreatedDate(null);
        wp.setLocked(false);
    	wp.setLockedBy(null);
    	wp.setLockedSince(null);
        wp.setLastModifiedBy(null);
        wp.setLastModifiedDate(null);
        wp.setFilingInstruction(false);
        wp.setPriority(null);
        
        for(WorkPackageFareSheet wps : wp.getFareSheet()) {
        	for(WorkPackageFare fare : wps.getFares()) {
        		fare.setStatus("PENDING");
        	}
        }
        for(WorkPackageFareSheet wps : wp.getAddonFareSheet()) {
        	for(WorkPackageFare fare : wps.getFares()) {
        		fare.setStatus("PENDING");
        	}
        }
        for(WorkPackageFareSheet wps : wp.getMarketFareSheet()) {
        	for(WorkPackageFare fare : wps.getFares()) {
        		fare.setStatus("PENDING");
        	}
        }
        for(WorkPackageFareSheet wps : wp.getWaiverFareSheet()) {
        	for(WorkPackageFare fare : wps.getFares()) {
        		fare.setStatus("PENDING");
        	}
        }
        for(WorkPackageFareSheet wps : wp.getDiscountFareSheet()) {
        	for(WorkPackageFare fare : wps.getFares()) {
        		fare.setStatus("PENDING");
        	}
        }
        
        wp.setStatus(Status.NEW);
        wp.setQueuedDate(Instant.now());
                
        WorkPackage result = workPackageService.save(wp);
        
        
        return ResponseEntity.created(new URI("/api/work-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /work-packages/replace : Reuse a new workPackage.
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/replace")
    @Timed
    public ResponseEntity<WorkPackage> replaceWorkPackage(@RequestBody WorkPackage wp) throws URISyntaxException {
        log.debug("REST request to save reuse WorkPackage : {}", wp);
        
        WorkPackageHistory history = new WorkPackageHistory();
        history.setWorkPackage(new ObjectId(wp.getId()));
        history.setType("REPLACE");
        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
        workPackageHistoryService.save(history);
               
        wp.setReplaceFrom(wp.getWpid());
        wp.setId(null);
        wp.setWpid(null);
        
        User user = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get()).get();
        wp.setReviewLevel(user.getReviewLevels().get(0));
        wp.setCreatedBy(null);
        wp.setCreatedDate(null);
        wp.setLastModifiedBy(null);
        wp.setLastModifiedDate(null);
        wp.setPriority(null);        
    	wp.setLocked(false);
    	wp.setLockedBy(null);
    	wp.setLockedSince(null);

        
//        if(!workPackage.getReuseReplaceConfig().isAttachment()) {
//        	wp.setAttachment(false);
//        	wp.getAttachmentData().clear();
//        }
        for(WorkPackageFareSheet wps : wp.getFareSheet()) {
        	for(WorkPackageFare fare : wps.getFares()) {
        		fare.setStatus("PENDING");
        	}
        }
        for(WorkPackageFareSheet wps : wp.getAddonFareSheet()) {
        	for(WorkPackageFare fare : wps.getFares()) {
        		fare.setStatus("PENDING");
        	}
        }
        for(WorkPackageFareSheet wps : wp.getMarketFareSheet()) {
        	for(WorkPackageFare fare : wps.getFares()) {
        		fare.setStatus("PENDING");
        	}
        }
        for(WorkPackageFareSheet wps : wp.getWaiverFareSheet()) {
        	for(WorkPackageFare fare : wps.getFares()) {
        		fare.setStatus("PENDING");
        	}
        }
        for(WorkPackageFareSheet wps : wp.getDiscountFareSheet()) {
        	for(WorkPackageFare fare : wps.getFares()) {
        		fare.setStatus("PENDING");
        	}
        }
        
        wp.setStatus(Status.NEW);
        wp.setQueuedDate(Instant.now());
        
        WorkPackage result = workPackageService.save(wp);        
        
        
        return ResponseEntity.created(new URI("/api/work-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    

    /**
     * This method for the type of data in the cell, extracts the data and
     * returns it as a string.
     */
    public static String getCellValueAsString(Cell cell) {
        String strCellValue = null;
        if (cell != null) {
            switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                strCellValue = cell.toString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "dd/MM/yyyy");
                    strCellValue = dateFormat.format(cell.getDateCellValue());
                } else {
                    Double value = cell.getNumericCellValue();
                    Long longValue = value.longValue();
                    strCellValue = new String(longValue.toString());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                strCellValue = new String(new Boolean(
                        cell.getBooleanCellValue()).toString());
                break;
            case Cell.CELL_TYPE_BLANK:
                strCellValue = "";
                break;
            }
        }
        return strCellValue;
    }
    
    
    static class ImportHeader{
    	int index;
    	String name;
    }
    
    public LinkedHashMap<ImportHeader, List<Object>> importExcel(byte[] file) throws IOException{
    	InputStream input = new ByteArrayInputStream(file);
		
		Workbook workbook = new XSSFWorkbook(input);
		Sheet datatypeSheet = workbook.getSheetAt(0);	
		
		Iterator<Row> rowIterator = datatypeSheet.iterator();
		Row row = rowIterator.next();

		LinkedHashMap<ImportHeader, List<Object>> map = new LinkedHashMap<>();
		Map<Integer, ImportHeader> importHeaderList = new HashMap<>();
		int rowIndex = 0;
		
        while (rowIterator.hasNext()){
            if(rowIndex == 0) {
        		log.debug("ROW INDEX : {}", rowIndex);
        		int headerIndex = 0;
	            for (Iterator<Cell> iter = row.cellIterator(); iter.hasNext(); ) {
	            	log.debug("ROW HEADER INDEX : {}", headerIndex);
				    Cell element = iter.next();
				    
				    ImportHeader importHeader = new ImportHeader();
				    importHeader.index = headerIndex;
				    importHeader.name = element.getStringCellValue();
	            	log.debug("ROW HEADER NAME : {}", importHeader.name);
				    importHeaderList.put(headerIndex, importHeader);
				    map.put(importHeader, new ArrayList<>());
				    
				    headerIndex++;
				}
        	}
        	else {
        		row = rowIterator.next();
            	
        		int cellIndex = 0;
        		for (Iterator<Cell> iter = row.cellIterator(); iter.hasNext(); ) {	 
        			Cell element = iter.next();
        			List<Object> value = map.get(importHeaderList.get(cellIndex));
    				value.add(getCellValueAsString(element));
        			cellIndex++;
        		}        		
        	}
            rowIndex++;
        }
        
    	return map;
    }
    
    public Object getElementByIndex(LinkedHashMap map,int index){
        return map.get( (map.keySet().toArray())[ index ] );
    }
    
    
    public static ZonedDateTime toZonedDateTime(Date utilDate) {
        if (utilDate == null) {
          return null;
        }
        final ZoneId systemDefault = ZoneId.systemDefault();
        return ZonedDateTime.ofInstant(utilDate.toInstant(), systemDefault);
      }
    
    /**
     * POST  /work-packages/import-fares : Import a new fares workPackage.
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/import-fares")
    @Timed
    public ResponseEntity<WorkPackage> importFaresWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to save importFaresWorkPackage : {}", workPackage);

        try {
            ImportFares importData = workPackage.getImportFares();
            int importIndex = workPackage.getImportIndex();
            LinkedHashMap<ImportHeader, List<Object>> mapValue = importExcel(importData.getFile());
            
            List<Object> rows = (List<Object>) getElementByIndex(mapValue, 0);
            List<WorkPackageFare> fares = new ArrayList<>();
            for(int i=0;i<rows.size();i++) {
            	fares.add(new WorkPackageFare());
            }
            
            for (Map.Entry<ImportHeader, List<Object>> entry : mapValue.entrySet()) {
                ImportHeader key = entry.getKey();
            	String header = key.name;
                List<Object> value = entry.getValue();
                
                int i=0;
                TariffNumber tfNumber = new TariffNumber();
                for(Object o : value) {
                	if(header.contentEquals("Status")) {
                		fares.get(i).setStatus("PENDING");
                	}
                	else if(header.contentEquals("Carrier")) {
                		fares.get(i).setCarrier(String.valueOf(o));
                	}
                	else if(header.contentEquals("Action")) {
                		fares.get(i).setAction(String.valueOf(o));
                	}
                	else if(header.contentEquals("Tar No")) {
                		try {
	                		if(fares.get(i).getTariffNumber() == null) {
	                			if(String.valueOf(o) != null) {
	                				fares.get(i).setTariffNumber(tariffNumberRepository.findOneByTarNo(String.valueOf(o)));
	                			}                			
	                		}
	                		else {
	                		}
                		}catch(Exception e) {
                			
                		}
                	}
                	else if(header.contentEquals("Tar Cd")) {
                		//fares.get(i).setTarcd(String.valueOf(o));
                		try {
	                		if(fares.get(i).getTariffNumber() == null) {
	                			if(String.valueOf(o) != null) {
	                				fares.get(i).setTariffNumber(tariffNumberRepository.findOneByTarCd(String.valueOf(o)));
	                			}                			
	                		}
	                		else {
	                		}
                		}catch(Exception e) {
                			
                		}
                	}
                	else if(header.contentEquals("Global")) {
                		//fares.get(i).setGlobal(String.valueOf(o));
                		try {
	                		if(fares.get(i).getTariffNumber() == null) {
	                			if(String.valueOf(o) != null) {
	                				fares.get(i).setTariffNumber(tariffNumberRepository.findOneByGlobal(String.valueOf(o)));
	                			}                			
	                		}
	                		else {
	                		}
                		}catch(Exception e) {
                			
                		}
                	}
                	else if(header.contentEquals("Origin")) {
                		fares.get(i).setOrigin(String.valueOf(o));
                	}
                	else if(header.contentEquals("Dest")) {
                		fares.get(i).setDestination(String.valueOf(o));
                	}
                	else if(header.contentEquals("Fare Cls")) {
                		fares.get(i).setFareBasis(String.valueOf(o));
                	}
                	else if(header.contentEquals("Bkg Cls")) {
                		fares.get(i).setBookingClass(String.valueOf(o));
                	}
                	else if(header.contentEquals("Cabin")) {
                		fares.get(i).setCabin(String.valueOf(o));
                	}
                	else if(header.contentEquals("OW/RT")) {
                		fares.get(i).setTypeOfJourney(String.valueOf(o));
                	}
                	else if(header.contentEquals("Ftnt")) {
                		fares.get(i).setFootnote1(String.valueOf(o));
                	}
                	else if(header.contentEquals("Rtg No")) {
                		fares.get(i).setRtgno(String.valueOf(o));
                	}
                	else if(header.contentEquals("Rule No")) {
                		fares.get(i).setRuleno(String.valueOf(o));
                	}
                	else if(header.contentEquals("Curr")) {
                		fares.get(i).setCurrency(String.valueOf(o));
                	}
                	else if(header.contentEquals("Base Amt")) {
                		fares.get(i).setAmount(String.valueOf(o));
                	}
                	else if(header.contentEquals("Target AIF")) {
                		fares.get(i).setAif(String.valueOf(o));
                	}
                	else if(header.contentEquals("Travel Start")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setTravelStart(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Travel End")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setTravelEnd(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Sales Start")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setSaleStart(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Sales End")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setSaleEnd(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Comment")) {
                		fares.get(i).setComment(String.valueOf(o));
                	}
                	else if(header.contentEquals("Travel Complete")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setTravelComplete(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Travel Complete Indicator")) {
                		fares.get(i).setTravelCompleteIndicator(String.valueOf(o));
                	}
                	else if(header.contentEquals("Ratesheet Comment")) {
                		fares.get(i).setRatesheetComment(String.valueOf(o));
                	}
                	i++;
                }
            }

            workPackage.getFareSheet().get(importIndex).getFares().addAll(fares);
            workPackage = workPackageService.save(workPackage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return getWorkPackage(workPackage.getId());
    }
    
    /**
     * POST  /work-packages/import-fares-addon : Import a new fares addon workPackage.
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/import-fares-addon")
    @Timed
    public ResponseEntity<WorkPackage> importFaresAddonWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to save importFaresAddonWorkPackage : {}", workPackage);

        try {
            ImportFares importData = workPackage.getImportFares();
            int importIndex = workPackage.getImportIndex();
            LinkedHashMap<ImportHeader, List<Object>> mapValue = importExcel(importData.getFile());
            
            List<Object> rows = (List<Object>) getElementByIndex(mapValue, 0);
            List<WorkPackageFare> fares = new ArrayList<>();
            for(int i=0;i<rows.size();i++) {
            	fares.add(new WorkPackageFare());
            }
            
            for (Map.Entry<ImportHeader, List<Object>> entry : mapValue.entrySet()) {
                ImportHeader key = entry.getKey();
            	String header = key.name;
                List<Object> value = entry.getValue();
                
                int i=0;
                TariffNumber tfNumber = new TariffNumber();
                for(Object o : value) {
                	if(header.contentEquals("Status")) {
                		fares.get(i).setStatus("PENDING");
                	}
                	else if(header.contentEquals("Carrier")) {
                		fares.get(i).setCarrier(String.valueOf(o));
                	}
                	else if(header.contentEquals("Action")) {
                		fares.get(i).setAction(String.valueOf(o));
                	}
                	else if(header.contentEquals("Tar No")) {
                		try {
	                		if(fares.get(i).getTariffNumber() == null) {
	                			if(String.valueOf(o) != null) {
	                				fares.get(i).setTariffNumber(tariffNumberRepository.findOneByTarNo(String.valueOf(o)));
	                			}                			
	                		}
	                		else {
	                		}
                		}catch(Exception e) {
                			
                		}
                	}
                	else if(header.contentEquals("Tar Cd")) {
                		//fares.get(i).setTarcd(String.valueOf(o));
                		try {
	                		if(fares.get(i).getTariffNumber() == null) {
	                			if(String.valueOf(o) != null) {
	                				fares.get(i).setTariffNumber(tariffNumberRepository.findOneByTarCd(String.valueOf(o)));
	                			}                			
	                		}
	                		else {
	                		}
                		}catch(Exception e) {
                			
                		}
                	}
                	else if(header.contentEquals("Global")) {
                		//fares.get(i).setGlobal(String.valueOf(o));
                		try {
	                		if(fares.get(i).getTariffNumber() == null) {
	                			if(String.valueOf(o) != null) {
	                				fares.get(i).setTariffNumber(tariffNumberRepository.findOneByGlobal(String.valueOf(o)));
	                			}                			
	                		}
	                		else {
	                		}
                		}catch(Exception e) {
                			
                		}
                	}
                	else if(header.contentEquals("Origin")) {
                		fares.get(i).setOrigin(String.valueOf(o));
                	}
                	else if(header.contentEquals("Dest")) {
                		fares.get(i).setDestination(String.valueOf(o));
                	}
                	else if(header.contentEquals("Addon Bucket")) {
                		fares.get(i).setBucket(String.valueOf(o));
                	}
                	else if(header.contentEquals("OW/RT")) {
                		fares.get(i).setTypeOfJourney(String.valueOf(o));
                	}
                	else if(header.contentEquals("Ftnt")) {
                		fares.get(i).setFootnote1(String.valueOf(o));
                	}
                	else if(header.contentEquals("Zone")) {
                		fares.get(i).setZone(String.valueOf(o));
                	}
                	else if(header.contentEquals("Rtg No")) {
                		fares.get(i).setRtgno(String.valueOf(o));
                	}
                	else if(header.contentEquals("Filing Curr")) {
                		fares.get(i).setCurrency(String.valueOf(o));
                	}
                	else if(header.contentEquals("Base Amt")) {
                		fares.get(i).setAmount(String.valueOf(o));
                	}
                	else if(header.contentEquals("Amt Diff")) {
//                		fares.get(i).set(String.valueOf(o));
                	}
                	else if(header.contentEquals("Travel Start")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setTravelStart(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Travel End")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setTravelEnd(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Sales Start")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setSaleStart(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Sales End")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setSaleEnd(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Comment")) {
                		fares.get(i).setComment(String.valueOf(o));
                	}
                	else if(header.contentEquals("Travel Complete")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setTravelComplete(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Travel Complete Indicator")) {
                		fares.get(i).setTravelCompleteIndicator(String.valueOf(o));
                	}
                	i++;
                }
            }

            workPackage.getAddonFareSheet().get(importIndex).getFares().addAll(fares);
            workPackage = workPackageService.save(workPackage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return getWorkPackage(workPackage.getId());
    }
    
    
    /**
     * POST  /work-packages/import-fares-market : Import a new market fares workPackage.
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/import-fares-market")
    @Timed
    public ResponseEntity<WorkPackage> importFaresMarketWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
    	   log.debug("REST request to save importFaresWorkPackage : {}", workPackage);

           try {
               ImportFares importData = workPackage.getImportFares();
               int importIndex = workPackage.getImportIndex();
               LinkedHashMap<ImportHeader, List<Object>> mapValue = importExcel(importData.getFile());
               
               List<Object> rows = (List<Object>) getElementByIndex(mapValue, 0);
               List<WorkPackageFare> fares = new ArrayList<>();
               for(int i=0;i<rows.size();i++) {
               	fares.add(new WorkPackageFare());
               }
               
               for (Map.Entry<ImportHeader, List<Object>> entry : mapValue.entrySet()) {
                   ImportHeader key = entry.getKey();
               	String header = key.name;
                   List<Object> value = entry.getValue();
                   
                   int i=0;
                   TariffNumber tfNumber = new TariffNumber();
                   for(Object o : value) {
                   	if(header.contentEquals("Status")) {
                   		fares.get(i).setStatus("PENDING");
                   	}
                   	else if(header.contentEquals("Carrier")) {
                   		fares.get(i).setCarrier(String.valueOf(o));
                   	}
                   	else if(header.contentEquals("Action")) {
                   		fares.get(i).setAction(String.valueOf(o));
                   	}
                   	else if(header.contentEquals("Origin")) {
                   		fares.get(i).setOrigin(String.valueOf(o));
                   	}
                   	else if(header.contentEquals("Dest")) {
                   		fares.get(i).setDestination(String.valueOf(o));
                   	}
                   	else if(header.contentEquals("Fare Cls")) {
                   		fares.get(i).setFareBasis(String.valueOf(o));
                   	}
                   	else if(header.contentEquals("Bkg Cls")) {
                   		fares.get(i).setBookingClass(String.valueOf(o));
                   	}
                   	else if(header.contentEquals("SSN")) {
                   		fares.get(i).setSsn(String.valueOf(o));
                   	}
                	else if(header.contentEquals("Cabin")) {
                   		fares.get(i).setCabin(String.valueOf(o));
                   	}
                   	else if(header.contentEquals("OW/RT")) {
                   		fares.get(i).setTypeOfJourney(String.valueOf(o));
                   	}
                   	else if(header.contentEquals("Rule No")) {
                   		fares.get(i).setRuleno(String.valueOf(o));
                   	}
                   	else if(header.contentEquals("Curr")) {
                   		fares.get(i).setCurrency(String.valueOf(o));
                   	}
                   	else if(header.contentEquals("Base Amt")) {
                   		fares.get(i).setAmount(String.valueOf(o));
                   	}
                   	else if(header.contentEquals("Travel Start")) {
                   		if(o != null && !String.valueOf(o).contentEquals("")) {
   	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
   	                		fares.get(i).setTravelStart(toZonedDateTime(date1));
                   		}
                   	}
                   	else if(header.contentEquals("Travel End")) {
                   		if(o != null && !String.valueOf(o).contentEquals("")) {
   	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
   	                		fares.get(i).setTravelEnd(toZonedDateTime(date1));
                   		}
                   	}
                   	else if(header.contentEquals("Sales Start")) {
                   		if(o != null && !String.valueOf(o).contentEquals("")) {
   	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
   	                		fares.get(i).setSaleStart(toZonedDateTime(date1));
                   		}
                   	}
                   	else if(header.contentEquals("Sales End")) {
                   		if(o != null && !String.valueOf(o).contentEquals("")) {
   	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
   	                		fares.get(i).setSaleEnd(toZonedDateTime(date1));
                   		}
                   	}
                   	else if(header.contentEquals("Comment")) {
                   		fares.get(i).setComment(String.valueOf(o));
                   	}
                   	else if(header.contentEquals("Travel Complete")) {
                   		if(o != null && !String.valueOf(o).contentEquals("")) {
   	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
   	                		fares.get(i).setTravelComplete(toZonedDateTime(date1));
                   		}
                   	}
                   	else if(header.contentEquals("Travel Complete Indicator")) {
                   		fares.get(i).setTravelCompleteIndicator(String.valueOf(o));
                   	}
                   	else if(header.contentEquals("Ratesheet Comment")) {
                   		fares.get(i).setRatesheetComment(String.valueOf(o));
                   	}
                   	i++;
                   }
               }

               workPackage.getMarketFareSheet().get(importIndex).getFares().addAll(fares);
               workPackage = workPackageService.save(workPackage);
   		} catch (Exception e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}

        return getWorkPackage(workPackage.getId());
    }
   
    /**
     * POST  /work-packages/import-fares-discount : Import a new discount fares workPackage.
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/import-fares-discount")
    @Timed
    public ResponseEntity<WorkPackage> importFaresDiscountWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
    	log.debug("REST request to save importFaresDiscountWorkPackage : {}", workPackage);

        try {
            ImportFares importData = workPackage.getImportFares();
            int importIndex = workPackage.getImportIndex();
            LinkedHashMap<ImportHeader, List<Object>> mapValue = importExcel(importData.getFile());
            
            List<Object> rows = (List<Object>) getElementByIndex(mapValue, 0);
            List<WorkPackageFare> fares = new ArrayList<>();
            for(int i=0;i<rows.size();i++) {
            	fares.add(new WorkPackageFare());
            }
            
            for (Map.Entry<ImportHeader, List<Object>> entry : mapValue.entrySet()) {
                ImportHeader key = entry.getKey();
            	String header = key.name;
                List<Object> value = entry.getValue();
                
                int i=0;
                TariffNumber tfNumber = new TariffNumber();
                for(Object o : value) {
                	if(header.contentEquals("Status")) {
                		fares.get(i).setStatus("PENDING");
                	}
                	else if(header.contentEquals("FBR Tariff Code")) {
                		fares.get(i).setTarcd(String.valueOf(o));
                	}
                	else if(header.contentEquals("Loc 1 Type")) {
                		fares.get(i).setLoc1Type(String.valueOf(o));
                	}
                	else if(header.contentEquals("Loc 1")) {
                		fares.get(i).setLoc1(String.valueOf(o));
                	}
                	else if(header.contentEquals("Loc 2 Type")) {
                		fares.get(i).setLoc2Type(String.valueOf(o));
                	}
                	else if(header.contentEquals("Loc 2")) {
                		fares.get(i).setLoc2(String.valueOf(o));
                	}
                	else if(header.contentEquals("Base FareCls")) {
                		fares.get(i).setBaseFareBasis(String.valueOf(o));
                	}
                	else if(header.contentEquals("Base Rule No")) {
                		fares.get(i).setBaseRuleNo(String.valueOf(o));
                	}
                	else if(header.contentEquals("Base Tariff Code")) {
                		fares.get(i).setBaseTarcd(String.valueOf(o));
                	}
                	else if(header.contentEquals("Calc Type")) {
                		fares.get(i).setCalcType(String.valueOf(o));
                	}
                	else if(header.contentEquals("% of Base Fare")) {
                		
                	}
                	else if(header.contentEquals("Curr")) {
                		//log.debug("CURRENCY : {}", String.valueOf(o));
                		fares.get(i).setCurrency(String.valueOf(o));
                	}
                	else if(header.contentEquals("Specified Amount")) {
                		fares.get(i).setDiscountSpecifiedAmount(String.valueOf(o));
                	}
                	else if(header.contentEquals("PAX Type")) {
                		fares.get(i).setPassengerType(String.valueOf(o));
                	}
                	else if(header.contentEquals("Fare Type")) {
                		fares.get(i).setFareType(String.valueOf(o));
                	}
                	else if(header.contentEquals("Tkt Code")) {
                		fares.get(i).setTicketCode(String.valueOf(o));
                	}
                	else if(header.contentEquals("Tkt Des")) {
                		fares.get(i).setTicketDesignator(String.valueOf(o));
                	}
                	else if(header.contentEquals("Base Fare OW/RT")) {
                		fares.get(i).setTypeOfJourney(String.valueOf(o));
                	}
                	else if(header.contentEquals("Global")) {
                		fares.get(i).setGlobal(String.valueOf(o));
                	}
                	else if(header.contentEquals("Rtg No")) {
                		fares.get(i).setRtgno(String.valueOf(o));
                	}
                	else if(header.contentEquals("Rtg No Tarno")) {
                		fares.get(i).setRtgnoTarno(String.valueOf(o));
                	}
                	else if(header.contentEquals("New FareCls")) {
                		fares.get(i).setNewFareBasis(String.valueOf(o));
                	}
                	else if(header.contentEquals("New OW/RT")) {
                		fares.get(i).setNewTypeOfJourney(String.valueOf(o));
                	}
                	else if(header.contentEquals("New BkgCd")) {
                		fares.get(i).setNewBookingCode(String.valueOf(o));
                	}

                	else if(header.contentEquals("Travel Start")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setTravelStart(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Travel End")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setTravelEnd(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Sale Start")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setSaleStart(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Sale End")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setSaleEnd(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Comment")) {
                		fares.get(i).setComment(String.valueOf(o));
                	}
                	else if(header.contentEquals("Travel Complete")) {
                		if(o != null && !String.valueOf(o).contentEquals("")) {
	                		Date date1=new SimpleDateFormat("ddMMMyyyy").parse(String.valueOf(o));                  		
	                		fares.get(i).setTravelComplete(toZonedDateTime(date1));
                		}
                	}
                	else if(header.contentEquals("Travel Complete Indicator")) {
                		fares.get(i).setTravelCompleteIndicator(String.valueOf(o));
                	}
                	i++;
                }
            }

            workPackage.getDiscountFareSheet().get(importIndex).getFares().addAll(fares);
            workPackage = workPackageService.save(workPackage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return getWorkPackage(workPackage.getId());
    }
   
    /**
     * POST  /work-packages/import-fares : Import a new fares workPackage.
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/import-fares-waiver")
    @Timed
    public ResponseEntity<WorkPackage> importFaresWaiverWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to save importFaresWaiverWorkPackage : {}", workPackage);

        try {
            ImportFares importData = workPackage.getImportFares();
            int importIndex = workPackage.getImportIndex();
            LinkedHashMap<ImportHeader, List<Object>> mapValue = importExcel(importData.getFile());
            
            List<Object> rows = (List<Object>) getElementByIndex(mapValue, 0);
            List<WorkPackageFare> fares = new ArrayList<>();
            for(int i=0;i<rows.size();i++) {
            	fares.add(new WorkPackageFare());
            }
            
            for (Map.Entry<ImportHeader, List<Object>> entry : mapValue.entrySet()) {
                ImportHeader key = entry.getKey();
            	String header = key.name;
                List<Object> value = entry.getValue();
                
                int i=0;
                TariffNumber tfNumber = new TariffNumber();
                for(Object o : value) {
                	if(header.contentEquals("Type")) {
                		fares.get(i).setWaiverType(String.valueOf(o));
                	}
                	else if(header.contentEquals("Full/Partial")) {
                		fares.get(i).setWaiverFullPartial(String.valueOf(o));
                	}
                	else if(header.contentEquals("PNR")) {
                		fares.get(i).setWaiverPnr(String.valueOf(o));
                	}
                	else if(header.contentEquals("Tkt From")) {
                		fares.get(i).setWaiverTktFrom(String.valueOf(o));
                	}
                	else if(header.contentEquals("Tkt To")) {
                		fares.get(i).setWaiverTktTo(String.valueOf(o));
                	}
                	else if(header.contentEquals("Ori")) {
                		fares.get(i).setWaiverOri(String.valueOf(o));
                	}
                	else if(header.contentEquals("Dest")) {
                		fares.get(i).setWaiverDest(String.valueOf(o));
                	}
                	else if(header.contentEquals("Original Itinerary")) {
                		fares.get(i).setWaiverOriginalItinerary(String.valueOf(o));
                	}
                	else if(header.contentEquals("New Itinerary")) {
                		fares.get(i).setWaiverNewItinerary(String.valueOf(o));
                	}
                	else if(header.contentEquals("Original Basic Fare")) {
                		fares.get(i).setWaiverOriginalBasicFare(String.valueOf(o));
                	}
                	else if(header.contentEquals("New Basic Fare")) {
                		fares.get(i).setWaiverNewBasicFare(String.valueOf(o));
                	}
                	else if(header.contentEquals("Approved Fares")) {
                		fares.get(i).setWaiverApprovedFare(String.valueOf(o));
                	}
                	else if(header.contentEquals("Fare Lost")) {
                		fares.get(i).setWaiverFareLost(String.valueOf(o));
                	}
                	else if(header.contentEquals("Calculated PN")) {
                		fares.get(i).setWaiverCalculatedPn(String.valueOf(o));
                	}
                	else if(header.contentEquals("Original PN")) {
                		fares.get(i).setWaiverOriginalPn(String.valueOf(o));
                	}
                	else if(header.contentEquals("Approved PN")) {
                		fares.get(i).setWaiverApprovedPn(String.valueOf(o));
                	}
                	else if(header.contentEquals("Penalty Lost %")) {
                		fares.get(i).setWaiverPenaltyLostPercent(String.valueOf(o));
                	}
                	else if(header.contentEquals("Penalty Lost Amount")) {
                		fares.get(i).setWaiverPenaltyLostAmount(String.valueOf(o));
                	}
                	else if(header.contentEquals("Currency")) {
                		fares.get(i).setWaiverCurrency(String.valueOf(o));
                	}
                	else if(header.contentEquals("Total Pax")) {
                		fares.get(i).setWaiverTotalPax(String.valueOf(o));
                	}
                	else if(header.contentEquals("Total Lost")) {
                		fares.get(i).setWaiverTotalLost(String.valueOf(o));
                	}
                	else if(header.contentEquals("Approver")) {
                		fares.get(i).setWaiverApprover(String.valueOf(o));
                	}
                	else if(header.contentEquals("Remark")) {
                		fares.get(i).setWaiverRemark(String.valueOf(o));
                	}
                	i++;
                }
            }

            workPackage.getWaiverFareSheet().get(importIndex).getFares().addAll(fares);
            workPackage = workPackageService.save(workPackage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        return getWorkPackage(workPackage.getId());
    }
    

    public Attachment createWorkbook(String sheetName, LinkedHashMap<String, Object> data) {
    	
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
            		}    
            		else {
            			rows = spreadsheet.createRow(x+1);
            		}
            		XSSFCell cellData =  rows.createCell(0+index);
            		cellData.setCellValue(val.get(x) != null ? val.get(x).toString() : "");
            	}
            }
            
//            XSSFRow rows = spreadsheet.createRow(i+2);
//            cell = rows.createCell(1);
            index++;
    	}
    	

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
			workbook.write(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        Attachment att = new Attachment();
        att.setFile(output.toByteArray());
        
        return att;
    }
    
    /**
     * POST  /work-packages/export-fares : Export work package fares
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/export-fares")
    @Timed
    public ResponseEntity<Attachment> exportFaresWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
    	log.debug("REST request to save exportFares : {}", workPackage.getExportIndex());
    	
    	LinkedHashMap<String, Object> data = new LinkedHashMap<>();
    	
    	data.put("Status", new ArrayList<>());
    	data.put("Carrier", new ArrayList<>());
    	data.put("Action", new ArrayList<>());
    	data.put("Tar No", new ArrayList<>());
    	data.put("Tar Cd", new ArrayList<>());
    	data.put("Global", new ArrayList<>());
    	data.put("Origin", new ArrayList<>());
    	data.put("Dest", new ArrayList<>());
    	data.put("Fare Cls", new ArrayList<>());
    	data.put("Bkg Cls", new ArrayList<>());
    	data.put("Cabin", new ArrayList<>());
    	data.put("OW/RT", new ArrayList<>());
    	data.put("Ftnt", new ArrayList<>());
    	data.put("Rtg No", new ArrayList<>());
    	data.put("Rule No", new ArrayList<>());
    	data.put("Curr", new ArrayList<>());
    	data.put("Base Amt", new ArrayList<>());
    	data.put("Target AIF", new ArrayList<>());
    	data.put("Travel Start", new ArrayList<>());
    	data.put("Travel End", new ArrayList<>());
    	data.put("Sales Start", new ArrayList<>());
    	data.put("Sales End", new ArrayList<>());
    	data.put("Comment", new ArrayList<>());
    	data.put("Travel Complete", new ArrayList<>());
    	data.put("Travel Complete Indicator", new ArrayList<>());
    	data.put("Ratesheet Comment", new ArrayList<>());
    	
    	WorkPackage wp = workPackageService.findOne(workPackage.getId());
        List<WorkPackageFare> fares = wp.getFareSheet().get(workPackage.getExportIndex()).getFares();

        DateFormat dfFull = new SimpleDateFormat("ddMMMyyyy"); 
        for(int i=0; i<fares.size(); i++) {
        	putValue(data.get("Status"), fares.get(i).getStatus());
        	putValue(data.get("Carrier"), fares.get(i).getCarrier());
        	putValue(data.get("Action"), fares.get(i).getAction());
        	putValue(data.get("Tar No"), fares.get(i).getTariffNumber() != null ? fares.get(i).getTariffNumber().getTarNo() : null);
        	putValue(data.get("Tar Cd"), fares.get(i).getTariffNumber() != null ?  fares.get(i).getTariffNumber().getTarCd() : null);
        	putValue(data.get("Global"), fares.get(i).getTariffNumber() != null ? fares.get(i).getTariffNumber().getGlobal() : null);
        	putValue(data.get("Origin"), fares.get(i).getOrigin());
        	putValue(data.get("Dest"), fares.get(i).getDestination());
        	putValue(data.get("Fare Cls"), fares.get(i).getFareBasis());
        	putValue(data.get("Bkg Cls"), fares.get(i).getBookingClass());
        	putValue(data.get("Cabin"), fares.get(i).getCabin());
        	putValue(data.get("OW/RT"), fares.get(i).getTypeOfJourney());
        	putValue(data.get("Ftnt"), fares.get(i).getFootnote1());
        	putValue(data.get("Rtg No"), fares.get(i).getRtgno());
        	putValue(data.get("Rule No"), fares.get(i).getRuleno());
        	putValue(data.get("Curr"), fares.get(i).getCurrency());
        	putValue(data.get("Base Amt"), fares.get(i).getAmount());
        	putValue(data.get("Target AIF"), fares.get(i).getAif());
//        	Date.from(
        	
        	putValue(data.get("Travel Start"), fares.get(i).getTravelStart() != null ? dfFull.format(Date.from(fares.get(i).getTravelStart().toInstant())) : null);
        	putValue(data.get("Travel End"), fares.get(i).getTravelEnd() != null ? dfFull.format(Date.from(fares.get(i).getTravelEnd().toInstant())) : null);
        	putValue(data.get("Sales Start"), fares.get(i).getSaleStart() != null ? dfFull.format(Date.from(fares.get(i).getSaleStart().toInstant())) : null);
        	putValue(data.get("Sales End"), fares.get(i).getSaleEnd() != null ? dfFull.format(Date.from(fares.get(i).getSaleEnd().toInstant())) : null);
        	putValue(data.get("Comment"), fares.get(i).getComment());
        	putValue(data.get("Travel Complete"), fares.get(i).getTravelComplete() != null ? dfFull.format(Date.from(fares.get(i).getTravelComplete().toInstant())): null);
        	putValue(data.get("Travel Complete Indicator"), fares.get(i).getTravelCompleteIndicator());
        	putValue(data.get("Ratesheet Comment"), fares.get(i).getRatesheetComment());
        }
    	
    	Attachment att = createWorkbook("Workorder Fare", data);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
            .body(att);
    }
    
    /**
     * POST  /work-packages/export-fares-waiver : Export work package fares waiver
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/export-fares-waiver")
    @Timed
    public ResponseEntity<Attachment> exportFaresWaiverWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
    	log.debug("REST request to save exportFares : {}", workPackage.getExportIndex());
    	
    	LinkedHashMap<String, Object> data = new LinkedHashMap<>();
    	
    	data.put("Type", new ArrayList<>());
    	data.put("Full/Partial", new ArrayList<>());
    	data.put("PNR", new ArrayList<>());
    	data.put("Tkt From", new ArrayList<>());
    	data.put("Tkt To", new ArrayList<>());
    	data.put("Ori", new ArrayList<>());
    	data.put("Dest", new ArrayList<>());
    	data.put("Original Itinerary", new ArrayList<>());
    	data.put("New Itinerary", new ArrayList<>());
    	data.put("Original Basic Fare", new ArrayList<>());
    	data.put("New Basic Fare", new ArrayList<>());
    	data.put("Approved Fares", new ArrayList<>());
    	data.put("Fare Lost", new ArrayList<>());
    	data.put("Calculated PN", new ArrayList<>());
    	data.put("Original PN", new ArrayList<>());
    	data.put("Approved PN", new ArrayList<>());
    	data.put("Penalty Lost %", new ArrayList<>());
    	data.put("Penalty Lost Amount", new ArrayList<>());
    	data.put("Currency", new ArrayList<>());
    	data.put("Total Pax", new ArrayList<>());
    	data.put("Total Lost", new ArrayList<>());
    	data.put("Approver", new ArrayList<>());
    	data.put("Remark", new ArrayList<>());
    	
    	WorkPackage wp = workPackageService.findOne(workPackage.getId());
        List<WorkPackageFare> fares = wp.getWaiverFareSheet().get(workPackage.getExportIndex()).getFares();

        DateFormat dfFull = new SimpleDateFormat("ddMMMyyyy"); 
        for(int i=0; i<fares.size(); i++) {
        	putValue(data.get("Type"), fares.get(i).getWaiverType());
        	putValue(data.get("Full/Partial"), fares.get(i).getWaiverFullPartial());
        	putValue(data.get("PNR"), fares.get(i).getWaiverPnr());
        	putValue(data.get("Tkt From"), fares.get(i).getWaiverTktFrom());
        	putValue(data.get("Tkt To"), fares.get(i).getWaiverTktTo());
        	putValue(data.get("Ori"), fares.get(i).getWaiverOri());
        	putValue(data.get("Dest"), fares.get(i).getWaiverDest());
        	putValue(data.get("Original Itinerary"), fares.get(i).getWaiverOriginalItinerary());
        	putValue(data.get("New Itinerary"), fares.get(i).getWaiverNewItinerary());
        	putValue(data.get("Original Basic Fare"), fares.get(i).getWaiverOriginalBasicFare());
        	putValue(data.get("New Basic Fare"), fares.get(i).getWaiverNewBasicFare());
        	putValue(data.get("Approved Fares"), fares.get(i).getWaiverApprovedFare());
        	putValue(data.get("Fare Lost"), fares.get(i).getWaiverFareLost());
        	putValue(data.get("Calculated PN"), fares.get(i).getWaiverCalculatedPn());
        	putValue(data.get("Original PN"), fares.get(i).getWaiverOriginalPn());
        	putValue(data.get("Approved PN"), fares.get(i).getWaiverApprovedPn());
        	putValue(data.get("Penalty Lost %"), fares.get(i).getWaiverPenaltyLostPercent());
        	putValue(data.get("Penalty Lost Amount"), fares.get(i).getWaiverPenaltyLostAmount());
        	putValue(data.get("Currency"), fares.get(i).getWaiverCurrency());
        	putValue(data.get("Total Pax"), fares.get(i).getWaiverTotalPax());
        	putValue(data.get("Total Lost"), fares.get(i).getWaiverTotalLost());
        	putValue(data.get("Approver"), fares.get(i).getWaiverApprover());
        	putValue(data.get("Remark"), fares.get(i).getWaiverRemark());
        }
    	
    	Attachment att = createWorkbook("Workorder Waiver Fare", data);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
            .body(att);
    }
    
    /**
     * POST  /work-packages/export-fares-addon : Export Addon work package fares
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/export-fares-addon")
    @Timed
    public ResponseEntity<Attachment> exportFaresAddonWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
    	log.debug("REST request to save exportAddonFares : {}", workPackage.getExportIndex());
    	
    	LinkedHashMap<String, Object> data = new LinkedHashMap<>();
    	
    	data.put("Status", new ArrayList<>());
    	data.put("Carrier", new ArrayList<>());
    	data.put("Action", new ArrayList<>());
    	data.put("Tar No", new ArrayList<>());
    	data.put("Tar Cd", new ArrayList<>());
    	data.put("Global", new ArrayList<>());
    	data.put("Origin", new ArrayList<>());
    	data.put("Dest", new ArrayList<>());
    	data.put("Addon Bucket", new ArrayList<>());
    	data.put("OW/RT", new ArrayList<>());
    	data.put("Ftnt", new ArrayList<>());
    	data.put("Zone", new ArrayList<>());
    	data.put("Rtg No", new ArrayList<>());
    	data.put("Filing Curr", new ArrayList<>());
    	data.put("Base Amt", new ArrayList<>());
    	data.put("Amt Diff", new ArrayList<>());
    	data.put("% Amt Diff", new ArrayList<>());
    	data.put("Travel Start", new ArrayList<>());
    	data.put("Travel End", new ArrayList<>());
    	data.put("Sales Start", new ArrayList<>());
    	data.put("Sales End", new ArrayList<>());
    	data.put("Comment", new ArrayList<>());
    	data.put("Travel Complete", new ArrayList<>());
    	data.put("Travel Complete Indicator", new ArrayList<>());
    	
    	WorkPackage wp = workPackageService.findOne(workPackage.getId());
        List<WorkPackageFare> fares = wp.getAddonFareSheet().get(workPackage.getExportIndex()).getFares();

        DateFormat dfFull = new SimpleDateFormat("ddMMMyyyy"); 
        for(int i=0; i<fares.size(); i++) {
        	putValue(data.get("Status"), fares.get(i).getStatus());
        	putValue(data.get("Carrier"), fares.get(i).getCarrier());
        	putValue(data.get("Action"), fares.get(i).getAction());
        	putValue(data.get("Tar No"), fares.get(i).getTariffNumber() != null ? fares.get(i).getTariffNumber().getTarNo() : null);
        	putValue(data.get("Tar Cd"), fares.get(i).getTariffNumber() != null ?  fares.get(i).getTariffNumber().getTarCd() : null);
        	putValue(data.get("Global"), fares.get(i).getTariffNumber() != null ? fares.get(i).getTariffNumber().getGlobal() : null);
        	putValue(data.get("Origin"), fares.get(i).getOrigin());
        	putValue(data.get("Dest"), fares.get(i).getDestination());
        	putValue(data.get("Addon Bucket"), fares.get(i).getBucket());
        	putValue(data.get("OW/RT"), fares.get(i).getTypeOfJourney());
        	putValue(data.get("Ftnt"), fares.get(i).getFootnote1());
        	putValue(data.get("Zone"), fares.get(i).getZone());
        	putValue(data.get("Rtg No"), fares.get(i).getRtgno());
        	putValue(data.get("Filing Curr"), fares.get(i).getCurrency());
        	putValue(data.get("Base Amt"), fares.get(i).getAmount());
        	putValue(data.get("Amt Diff"), fares.get(i).getAmount());
        	putValue(data.get("% Amt Diff"), fares.get(i).getAmount());
        	putValue(data.get("Travel Start"), fares.get(i).getTravelStart() != null ? dfFull.format(Date.from(fares.get(i).getTravelStart().toInstant())) : null);
        	putValue(data.get("Travel End"), fares.get(i).getTravelEnd() != null ? dfFull.format(Date.from(fares.get(i).getTravelEnd().toInstant())) : null);
        	putValue(data.get("Sales Start"), fares.get(i).getSaleStart() != null ? dfFull.format(Date.from(fares.get(i).getSaleStart().toInstant())) : null);
        	putValue(data.get("Sales End"), fares.get(i).getSaleEnd() != null ? dfFull.format(Date.from(fares.get(i).getSaleEnd().toInstant())) : null);
        	putValue(data.get("Comment"), fares.get(i).getComment());
        	putValue(data.get("Travel Complete"), fares.get(i).getTravelComplete() != null ? dfFull.format(Date.from(fares.get(i).getTravelComplete().toInstant())): null);
        	putValue(data.get("Travel Complete Indicator"), fares.get(i).getTravelCompleteIndicator());
        }
    	
    	Attachment att = createWorkbook("Workorder Addon Fare", data);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
            .body(att);
    }
    

    private void putValue(Object obj, String status) {
		// TODO Auto-generated method stub
    	List<Object> object = (List<Object>) obj;
		if(status != null) {
			object.add(status);
		}
		else {
			object.add(null);
		}
	}

	/**
     * POST  /work-packages/export-fares-market : Export work package fares market
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/export-fares-market")
    @Timed
    public ResponseEntity<Attachment> exportFaresMarketWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
    	log.debug("REST request to save exportMarketFares : {}", workPackage.getExportIndex());
    	
    	LinkedHashMap<String, Object> data = new LinkedHashMap<>();
    	
    	data.put("Status", new ArrayList<>());
    	data.put("Carrier", new ArrayList<>());
    	data.put("Action", new ArrayList<>());
    	data.put("Origin", new ArrayList<>());
    	data.put("Dest", new ArrayList<>());
    	data.put("Fare Cls", new ArrayList<>());
    	data.put("Bkg Cls", new ArrayList<>());
    	data.put("SSN", new ArrayList<>());
    	data.put("Cabin", new ArrayList<>());
    	data.put("OW/RT", new ArrayList<>());
    	data.put("Rule No", new ArrayList<>());
    	data.put("Curr", new ArrayList<>());
    	data.put("Base Amt", new ArrayList<>());
    	data.put("Travel Start", new ArrayList<>());
    	data.put("Travel End", new ArrayList<>());
    	data.put("Sales Start", new ArrayList<>());
    	data.put("Sales End", new ArrayList<>());
    	data.put("Comment", new ArrayList<>());
    	data.put("Travel Complete", new ArrayList<>());
    	data.put("Travel Complete Indicator", new ArrayList<>());
    	data.put("Ratesheet Comment", new ArrayList<>());
    	
    	WorkPackage wp = workPackageService.findOne(workPackage.getId());
        List<WorkPackageFare> fares = wp.getMarketFareSheet().get(workPackage.getExportIndex()).getFares();

        DateFormat dfFull = new SimpleDateFormat("ddMMMyyyy"); 
        for(int i=0; i<fares.size(); i++) {
        	putValue(data.get("Status"), fares.get(i).getStatus());
        	putValue(data.get("Carrier"), fares.get(i).getCarrier());
        	putValue(data.get("Action"), fares.get(i).getAction());
        	putValue(data.get("Origin"), fares.get(i).getOrigin());
        	putValue(data.get("Dest"), fares.get(i).getDestination());
        	putValue(data.get("Fare Cls"), fares.get(i).getFareBasis());
        	putValue(data.get("Bkg Cls"), fares.get(i).getBookingClass());
        	putValue(data.get("SSN"), fares.get(i).getSsn());
        	putValue(data.get("Cabin"), fares.get(i).getCabin());
        	putValue(data.get("OW/RT"), fares.get(i).getTypeOfJourney());
        	putValue(data.get("Rule No"), fares.get(i).getRuleno());
        	putValue(data.get("Curr"), fares.get(i).getCurrency());
        	putValue(data.get("Base Amt"), fares.get(i).getAmount());
        	putValue(data.get("Travel Start"), fares.get(i).getTravelStart() != null ? dfFull.format(Date.from(fares.get(i).getTravelStart().toInstant())) : null);
        	putValue(data.get("Travel End"), fares.get(i).getTravelEnd() != null ? dfFull.format(Date.from(fares.get(i).getTravelEnd().toInstant())) : null);
        	putValue(data.get("Sales Start"), fares.get(i).getSaleStart() != null ? dfFull.format(Date.from(fares.get(i).getSaleStart().toInstant())) : null);
        	putValue(data.get("Sales End"), fares.get(i).getSaleEnd() != null ? dfFull.format(Date.from(fares.get(i).getSaleEnd().toInstant())) : null);
        	putValue(data.get("Comment"), fares.get(i).getComment());
        	putValue(data.get("Travel Complete"), fares.get(i).getTravelComplete() != null ? dfFull.format(Date.from(fares.get(i).getTravelComplete().toInstant())): null);
        	putValue(data.get("Travel Complete Indicator"), fares.get(i).getTravelCompleteIndicator());
        	putValue(data.get("Ratesheet Comment"), fares.get(i).getRatesheetComment());
        }
    	
    	Attachment att = createWorkbook("Workorder Addon Fare", data);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
            .body(att);
    }
    

    /**
     * POST  /work-packages/export-fares-discount : Export work package fares discount
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/export-fares-discount")
    @Timed
    public ResponseEntity<Attachment> exportFaresDiscountWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
    	log.debug("REST request to save exportFaresDiscount : {}", workPackage.getExportIndex());
    	
    	LinkedHashMap<String, Object> data = new LinkedHashMap<>();
    	
    	data.put("Status", new ArrayList<>());
    	data.put("FBR Tariff Code", new ArrayList<>());
    	data.put("Loc 1 Type", new ArrayList<>());
    	data.put("Loc 1", new ArrayList<>());
    	data.put("Loc 2 Type", new ArrayList<>());
    	data.put("Loc 2", new ArrayList<>());
    	data.put("Base FareCls", new ArrayList<>());
    	data.put("Base Rule No", new ArrayList<>());
    	data.put("Base Tariff Code", new ArrayList<>());
    	data.put("Calc Type", new ArrayList<>());
    	data.put("% of Base Fare", new ArrayList<>());
    	data.put("Curr", new ArrayList<>());
    	data.put("Specified Amount", new ArrayList<>());
    	data.put("PAX Type", new ArrayList<>());
    	data.put("Fare Type", new ArrayList<>());
    	data.put("Tkt Code", new ArrayList<>());
    	data.put("Tkt Des", new ArrayList<>());
    	data.put("Base Fare OW/RT", new ArrayList<>());
    	data.put("Global", new ArrayList<>());
    	data.put("Rtg No", new ArrayList<>());
    	data.put("Rtg No Tarno", new ArrayList<>());
    	data.put("New FareCls", new ArrayList<>());
    	data.put("New OW/RT", new ArrayList<>());
    	data.put("New BkgCd", new ArrayList<>());
    	data.put("Travel Start", new ArrayList<>());
    	data.put("Travel End", new ArrayList<>());
    	data.put("Sale Start", new ArrayList<>());
    	data.put("Sale End", new ArrayList<>());
    	data.put("Comment", new ArrayList<>());
    	data.put("Travel Complete", new ArrayList<>());
    	data.put("Travel Complete Indicator", new ArrayList<>());
    	
    	WorkPackage wp = workPackageService.findOne(workPackage.getId());
        List<WorkPackageFare> fares = wp.getDiscountFareSheet().get(workPackage.getExportIndex()).getFares();

        DateFormat dfFull = new SimpleDateFormat("ddMMMyyyy"); 
        for(int i=0; i<fares.size(); i++) {
        	putValue(data.get("Status"), fares.get(i).getStatus());
        	putValue(data.get("FBR Tariff Code"), fares.get(i).getTarcd());
        	putValue(data.get("Loc 1 Type"), fares.get(i).getLoc1Type());
        	putValue(data.get("Loc 1"), fares.get(i).getLoc1());
        	putValue(data.get("Loc 2 Type"), fares.get(i).getLoc2Type());
        	putValue(data.get("Loc 2"), fares.get(i).getLoc2());
        	putValue(data.get("Base FareCls"), fares.get(i).getBaseFareBasis());
        	putValue(data.get("Base Rule No"), fares.get(i).getBaseRuleNo());
        	putValue(data.get("Base Tariff Code"), fares.get(i).getBaseTarcd());
        	putValue(data.get("Calc Type"), fares.get(i).getCalcType());
        	
        	putValue(data.get("% of Base Fare"), fares.get(i).getPercentBaseFare());
        	putValue(data.get("Curr"), fares.get(i).getCurrency());
        	putValue(data.get("Specified Amount"), fares.get(i).getDiscountSpecifiedAmount());
        	putValue(data.get("PAX Type"), fares.get(i).getPassengerType());
        	putValue(data.get("Fare Type"), fares.get(i).getFareType());
        	putValue(data.get("Tkt Code"), fares.get(i).getTicketCode());
        	putValue(data.get("Tkt Des"), fares.get(i).getTicketDesignator());
        	putValue(data.get("Base Fare OW/RT"), fares.get(i).getTypeOfJourney());
        	putValue(data.get("Global"), fares.get(i).getGlobal());
        	putValue(data.get("Rtg No"), fares.get(i).getRtgno());
        	putValue(data.get("Rtg No Tarno"), fares.get(i).getRtgnoTarno());
        	putValue(data.get("New FareCls"), fares.get(i).getNewFareBasis());
        	putValue(data.get("New OW/RT"), fares.get(i).getNewTypeOfJourney());
        	putValue(data.get("New BkgCd"), fares.get(i).getNewBookingCode());
        	
        	putValue(data.get("Travel Start"), fares.get(i).getTravelStart() != null ? dfFull.format(Date.from(fares.get(i).getTravelStart().toInstant())) : null);
        	putValue(data.get("Travel End"), fares.get(i).getTravelEnd() != null ? dfFull.format(Date.from(fares.get(i).getTravelEnd().toInstant())) : null);
        	putValue(data.get("Sale Start"), fares.get(i).getSaleStart() != null ? dfFull.format(Date.from(fares.get(i).getSaleStart().toInstant())) : null);
        	putValue(data.get("Sale End"), fares.get(i).getSaleEnd() != null ? dfFull.format(Date.from(fares.get(i).getSaleEnd().toInstant())) : null);
        	putValue(data.get("Comment"), fares.get(i).getComment());
        	putValue(data.get("Travel Complete"), fares.get(i).getTravelComplete() != null ? dfFull.format(Date.from(fares.get(i).getTravelComplete().toInstant())): null);
        	putValue(data.get("Travel Complete Indicator"), fares.get(i).getTravelCompleteIndicator());
        	//putValue(data.get("Ratesheet Comment"), fares.get(i).getRatesheetComment());
        }
    	
    	Attachment att = createWorkbook("Workorder Market Fare", data);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
            .body(att);
    }
    
    public static class WorkPackageExportOption{
    	public WorkPackageFilter workPackageFilter;
    	
    	public String outputTo;
    	public boolean gridLines;
    	public boolean columnHeaders;
    	public boolean onlySelectedRows;
    	
    	
		public WorkPackageFilter getWorkPackageFilter() {
			return workPackageFilter;
		}
		public void setWorkPackageFilter(WorkPackageFilter workPackageFilter) {
			this.workPackageFilter = workPackageFilter;
		}
		public String getOutputTo() {
			return outputTo;
		}
		public void setOutputTo(String outputTo) {
			this.outputTo = outputTo;
		}
		public boolean isGridLines() {
			return gridLines;
		}
		public void setGridLines(boolean gridLines) {
			this.gridLines = gridLines;
		}
		public boolean isColumnHeaders() {
			return columnHeaders;
		}
		public void setColumnHeaders(boolean columnHeaders) {
			this.columnHeaders = columnHeaders;
		}
		public boolean isOnlySelectedRows() {
			return onlySelectedRows;
		}
		public void setOnlySelectedRows(boolean onlySelectedRows) {
			this.onlySelectedRows = onlySelectedRows;
		}
		@Override
		public String toString() {
			return "WorkPackageExportOption [workPackageFilter=" + workPackageFilter + ", outputTo=" + outputTo
					+ ", gridLines=" + gridLines + ", columnHeaders=" + columnHeaders + ", onlySelectedRows="
					+ onlySelectedRows + "]";
		}
		
    }
    /**
     * POST  /work-packages/export-fares : Export work package fares
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/exportQueue")
    @Timed
    public ResponseEntity<Attachment> exportQueueWorkPackage(@RequestBody WorkPackageExportOption workPackageExportOption) throws URISyntaxException {
    	log.debug("REST request to exportWorkqueue : {}", workPackageExportOption);
    	
    	LinkedHashMap<String, Object> data = new LinkedHashMap<>();
    	
    	data.put("WO Type", new ArrayList<>());
    	data.put("Distribution Type", new ArrayList<>());
    	data.put("Status", new ArrayList<>());
    	data.put("WO Id", new ArrayList<>());
    	data.put("WO Name", new ArrayList<>());
    	data.put("Business Area", new ArrayList<>());
    	data.put("Review Level", new ArrayList<>());
    	data.put("Approval Reference", new ArrayList<>());
    	data.put("Reuse From", new ArrayList<>());
    	data.put("Replace From", new ArrayList<>());
    	data.put("Priority", new ArrayList<>());
    	data.put("Filing Date", new ArrayList<>());
    	data.put("Distribution Date", new ArrayList<>());
    	data.put("Fare Type", new ArrayList<>());
    	data.put("Created Date", new ArrayList<>());
    	data.put("Created By", new ArrayList<>());
    	data.put("Locked By", new ArrayList<>());
    	data.put("Locked Since", new ArrayList<>());
    	data.put("Last Modified Date", new ArrayList<>());
    	data.put("Last Modified By", new ArrayList<>());
    	
    	
//    	WorkPackage wp = workPackageService.findOne(workPackage.getId());
//        List<WorkPackageFare> fares = wp.getDiscountFareSheet().get(workPackage.getExportIndex()).getFares();
    	
    	
    	List<WorkPackage> wp = workPackageService.findCustom(workPackageExportOption.getWorkPackageFilter());       
         
        DateFormat dfFull = new SimpleDateFormat("ddMMMyyyy"); 
        for(int i=0; i<wp.size(); i++) {
        	putValue(data.get("WO Type"), wp.get(i).getType().name());
        	putValue(data.get("Distribution Type"), wp.get(i).getTargetDistribution());
        	putValue(data.get("Status"), wp.get(i).getStatus().name());
        	putValue(data.get("WO Id"), wp.get(i).getWpid());
        	putValue(data.get("WO Name"), wp.get(i).getName());
        	putValue(data.get("Business Area"), wp.get(i).getBusinessArea());
        	putValue(data.get("Review Level"), wp.get(i).getReviewLevel());
        	putValue(data.get("Approval Reference"), wp.get(i).getName());
        	putValue(data.get("Reuse From"), wp.get(i).getName());
        	putValue(data.get("Replace From"), wp.get(i).getName());
        	putValue(data.get("Priority"), wp.get(i).getPriority());
        	putValue(data.get("Filing Date"), wp.get(i).getFilingDate() != null ? dfFull.format(Date.from(wp.get(i).getFilingDate().toInstant())) : null);
        	putValue(data.get("Distribution Date"), wp.get(i).getDistributionDate() != null ? dfFull.format(Date.from(wp.get(i).getDistributionDate().toInstant())) : null);
        	putValue(data.get("Created Date"), wp.get(i).getCreatedDate() != null ? dfFull.format(Date.from(wp.get(i).getCreatedDate())) : null);
        	putValue(data.get("Created By"), wp.get(i).getCreatedBy());
        	putValue(data.get("Locked By"), wp.get(i).getLockedBy());
        	putValue(data.get("Locked Since"), wp.get(i).getLockedSince() != null ? dfFull.format(Date.from(wp.get(i).getLockedSince().toInstant())) : null);
        	putValue(data.get("Last Modified Date"), wp.get(i).getLastModifiedDate() != null ? dfFull.format(Date.from(wp.get(i).getLastModifiedDate())) : null);
        	putValue(data.get("Last Modified By"), wp.get(i).getLastModifiedBy());
        	/*
        	putValue(data.get("FBR Tariff Code"), fares.get(i).getTarcd());
        	putValue(data.get("Loc 1 Type"), fares.get(i).getLoc1Type());
        	putValue(data.get("Loc 1"), fares.get(i).getLoc1());
        	putValue(data.get("Loc 2 Type"), fares.get(i).getLoc2Type());
        	putValue(data.get("Loc 2"), fares.get(i).getLoc2());
        	putValue(data.get("Base FareCls"), fares.get(i).getBaseFareBasis());
        	putValue(data.get("Base Rule No"), fares.get(i).getBaseRuleNo());
        	putValue(data.get("Base Tariff Code"), fares.get(i).getBaseTarcd());
        	putValue(data.get("Calc Type"), fares.get(i).getCalcType());
        	
//        	putValue(data.get("% of Base Fare", new ArrayList<>());
        	putValue(data.get("Curr"), fares.get(i).getCurrency());
        	putValue(data.get("Specified Amount"), fares.get(i).getDiscountSpecifiedAmount());
        	putValue(data.get("PAX Type"), fares.get(i).getPassengerType());
        	putValue(data.get("Fare Type"), fares.get(i).getFareType());
        	putValue(data.get("Tkt Code"), fares.get(i).getTicketCode());
        	putValue(data.get("Tkt Des"), fares.get(i).getTicketDesignator());
        	putValue(data.get("Base Fare OW/RT"), fares.get(i).getTypeOfJourney());
        	putValue(data.get("Global"), fares.get(i).getGlobal());
        	putValue(data.get("Rtg No"), fares.get(i).getRtgno());
        	putValue(data.get("Rtg No Tarno"), fares.get(i).getRtgnoTarno());
        	putValue(data.get("New FareCls"), fares.get(i).getNewFareBasis());
        	putValue(data.get("New OW/RT"), fares.get(i).getNewTypeOfJourney());
        	putValue(data.get("New BkgCd"), fares.get(i).getNewBookingCode());
        	
        	putValue(data.get("Travel Start"), fares.get(i).getTravelStart() != null ? dfFull.format(Date.from(fares.get(i).getTravelStart().toInstant())) : null);
        	putValue(data.get("Travel End"), fares.get(i).getTravelEnd() != null ? dfFull.format(Date.from(fares.get(i).getTravelEnd().toInstant())) : null);
        	putValue(data.get("Sale Start"), fares.get(i).getSaleStart() != null ? dfFull.format(Date.from(fares.get(i).getSaleStart().toInstant())) : null);
        	putValue(data.get("Sale End"), fares.get(i).getSaleEnd() != null ? dfFull.format(Date.from(fares.get(i).getSaleEnd().toInstant())) : null);
        	putValue(data.get("Comment"), fares.get(i).getComment());
        	putValue(data.get("Travel Complete"), fares.get(i).getTravelComplete() != null ? dfFull.format(Date.from(fares.get(i).getTravelComplete().toInstant())): null);
        	putValue(data.get("Travel Complete Indicator"), fares.get(i).getTravelCompleteIndicator());
        	//putValue(data.get("Ratesheet Comment"), fares.get(i).getRatesheetComment());
        	 */
        }
    	
    	Attachment att = createWorkbook("Workorder Queue", data);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
            .body(att);
    }
    
    
    /**
     * POST  /work-packages/download-market-rules : Download Market Rules Template
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/download-market-rules")
    @Timed
    public ResponseEntity<Attachment> downloadMarketRules(@RequestBody WorkPackage workPackage) throws URISyntaxException {
    	log.debug("REST request to download Market Rules : {}", workPackage);

    	String namaFile = "MarketRules.xlsx";
//    	String filename = "/Users/billy/Downloads/" + namaFile;
    	String filename = "/home/development/xml/" + namaFile;
    	byte[] templateFile = null;
    	if (new File(filename).exists()) {            
            try {
                templateFile = Files.readAllBytes(Paths.get(filename));
            } catch (IOException e) {
                return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("contract", "Error occured when retrieving template file, please try again later.", "Fail Template File")).body(null);
            }
        }
	    Attachment att = new Attachment();
	    att.setFile(templateFile);
	    return ResponseEntity.ok()
	        .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
	        .body(att);
    }
    
    /**
     * PUT  /work-packages : Updates an existing workPackage.
     *
     * @param workPackage the workPackage to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated workPackage,
     * or with status 400 (Bad Request) if the workPackage is not valid,
     * or with status 500 (Internal Server Error) if the workPackage couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/work-packages")
    @Timed
    public ResponseEntity<WorkPackage> updateWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to update WorkPackage : {}", workPackage.toString());
        
        if (workPackage.getId() == null) {
            return createWorkPackage(workPackage);
        }
    	workPackageService.save(workPackage);
    	if(workPackage.isValidate()) {
    		workPackage = validateWo(workPackage);    		
    	}
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(workPackage));    	
    }
    
    private WorkPackage validateWo(WorkPackage workPackage) {
		// TODO Auto-generated method stub
    	WorkPackage.Validation validation = new WorkPackage.Validation();
		
		
		int errorsCount = 0;
		int warningsCount = 0;
		List<WorkPackage.Validation.Tab> tabs = new ArrayList<WorkPackage.Validation.Tab>();
		
		//Validasi Header
		WorkPackage.Validation.Tab tabHeader = new WorkPackage.Validation.Tab();
		tabHeader.setName("HEADER");
		List<WorkPackage.Validation.Tab.Error> errorHeader = new ArrayList<>();
		
		if(workPackage.getTargetDistribution().toUpperCase().contentEquals("ATPCO")) {
			if(workPackage.getReviewLevel().toUpperCase().contentEquals("LSO")) {
				if(workPackage.getSaleDate() == null) {
					WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
		    		err1.setMessage("Sale start date is required");
		    		errorHeader.add(err1);
				}
				if(workPackage.getBusinessArea() == null || workPackage.getBusinessArea().contentEquals("")) {
					WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
		    		err1.setMessage("Business area is required");
		    		errorHeader.add(err1);
				}
				if(workPackage.getName() == null || workPackage.getName().contentEquals("")) {
					WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
		    		err1.setMessage("Name is required");
		    		errorHeader.add(err1);
				}
			}else if(workPackage.getReviewLevel().toUpperCase().contentEquals("HO")) {
				if(workPackage.getSaleDate() == null) {
					WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
		    		err1.setMessage("Sale start date is required");
		    		errorHeader.add(err1);
				}
				if(workPackage.getBusinessArea() == null || workPackage.getBusinessArea().contentEquals("")) {
					WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
		    		err1.setMessage("Business area is required");
		    		errorHeader.add(err1);
				}
				if(workPackage.getName() == null || workPackage.getName().contentEquals("")) {
					WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
		    		err1.setMessage("Name is required");
		    		errorHeader.add(err1);
				}
				if(workPackage.getFilingDate() == null) {
					WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
		    		err1.setMessage("Filing date is required");
		    		errorHeader.add(err1);
				}
				if(workPackage.getInterofficeComment() == null || workPackage.getInterofficeComment().size() < 1) {
					WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
		    		err1.setMessage("IN is required");
		    		errorHeader.add(err1);
				}
			}
			
			tabHeader.setError(errorHeader);
			
			errorsCount += errorHeader.size();
	    	if(errorHeader.size() > 0 || errorHeader.size() > 0) {
	    		tabs.add(tabHeader);
	    	}
			
		}else if(workPackage.getTargetDistribution().toUpperCase().contentEquals("MARKET")) {
			if(workPackage.getReviewLevel().toUpperCase().contentEquals("LSO")) {
				if(workPackage.getName() == null || workPackage.getName().contentEquals("")) {
					WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
		    		err1.setMessage("Name is required");
		    		errorHeader.add(err1);
				}		
			}else if(workPackage.getReviewLevel().toUpperCase().contentEquals("HO")) {
				if(workPackage.getName() == null || workPackage.getName().contentEquals("")) {
					WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
		    		err1.setMessage("Name is required");
		    		errorHeader.add(err1);
				}
				if(workPackage.getDistributionDate() == null) {
					WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
		    		err1.setMessage("Name is required");
		    		errorHeader.add(err1);
				}
				if(workPackage.getInterofficeComment() == null || workPackage.getInterofficeComment().size() < 1) {
					WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
		    		err1.setMessage("IN is required");
		    		errorHeader.add(err1);
				}
			}
			
			tabHeader.setError(errorHeader);
			
			errorsCount += errorHeader.size();
	    	if(errorHeader.size() > 0 || errorHeader.size() > 0) {
	    		tabs.add(tabHeader);
	    	}
		}
					
		//Validasi Fare
		for(WorkPackageFareSheet wpfs : workPackage.getFareSheet()) {
			WorkPackage.Validation.Tab tab1 = new WorkPackage.Validation.Tab();
	    		tab1.setName(wpfs.getSpecifiedFaresName());
	    		
	    		List<WorkPackage.Validation.Tab.Error> errors = new ArrayList<>();
	    		
		    		List<WorkPackageFare> fares = wpfs.getFares();
		    		
		    		List<String> rejectStatus = new ArrayList<>();
					for(WorkPackageFare fare : fares) {
						if(fare.getStatus() != null || !fare.getStatus().contentEquals("")) {
							if(fare.getStatus().contentEquals("REJECTED")) {
								rejectStatus.add("REJECTED");
							}
						}
						
						if(workPackage.getReviewLevel().contentEquals("LSO")) {
							if(fare.getOrigin() == null || fare.getOrigin().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Origin is required");
					    		errors.add(err1);
							}
							if(fare.getDestination() == null || fare.getDestination().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Destination is required");
					    		errors.add(err1);
							}
							if(fare.getCabin() == null || fare.getCabin().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Cabin is required");
					    		errors.add(err1);
							}
							if(fare.getTypeOfJourney() == null || fare.getTypeOfJourney().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("OW/RT is required");
					    		errors.add(err1);
							}
							if(fare.getCurrency() == null || fare.getCurrency().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Currency is required");
					    		errors.add(err1);
							}
							if(fare.getAmount() == null || fare.getAmount().contentEquals("")) {									
								if(fare.getAif() == null || fare.getAif().contentEquals("")) {
									//List Error
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Either Base Amt or Target AIF must be specified");
						    		errors.add(err1);
								}						    		
							}
							if(fare.getTravelStart() != null && fare.getTravelEnd() != null) {
								if(fare.getTravelStart().isAfter(fare.getTravelEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel Start Date must be less than or equal to Travel End Date");
						    		errors.add(err1);
								}else if(fare.getTravelEnd().isBefore(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel End Date cannot be earlier than Sales End Date");
						    		errors.add(err1);
								}
							}
							if(fare.getSaleStart() != null && fare.getSaleEnd() != null) {
								if(fare.getSaleStart().isAfter(fare.getSaleEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be less than or equal to Sale End Date");
						    		errors.add(err1);
								}
								if(fare.getSaleStart().isAfter(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be before  or equal to Travel Starte Date");
						    		errors.add(err1);
								}
							}
						}else if(workPackage.getReviewLevel().contentEquals("HO")) {
							if(fare.getStatus() == null || fare.getStatus().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Status is required");
					    		errors.add(err1);
							}
							if(fare.getTariffNumber().getTarCd() == null || fare.getTariffNumber().getTarCd().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("TarCd is required");
					    		errors.add(err1);
							}
							if(fare.getTariffNumber().getTarNo() == null || fare.getTariffNumber().getTarNo().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("TarNo is required");
					    		errors.add(err1);
							}
							if(fare.getOrigin() == null || fare.getOrigin().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Origin is required");
					    		errors.add(err1);
							}
							if(fare.getDestination() == null || fare.getDestination().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Destination is required");
					    		errors.add(err1);
							}
							if(fare.getFareType() == null || fare.getFareType().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Fare Cls is required");
					    		errors.add(err1);
							}
							if(fare.getBookingClass() == null || fare.getBookingClass().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Booking Cls is required");
					    		errors.add(err1);
							}
							if(fare.getCabin() == null || fare.getCabin().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Cabin is required");
					    		errors.add(err1);
							}
							if(fare.getTypeOfJourney() == null || fare.getTypeOfJourney().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("OW/RT is required");
					    		errors.add(err1);
							}
							if(fare.getCurrency() == null || fare.getCurrency().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Currency is required");
					    		errors.add(err1);
							}
							if(fare.getAmount() == null || fare.getAmount().contentEquals("")) {									
								if(fare.getAif() == null || fare.getAif().contentEquals("")) {
									//List Error
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Either Base Amt or Target AIF must be specified");
						    		errors.add(err1);
								}						    		
							}
							if(fare.getTravelStart() != null && fare.getTravelEnd() != null) {
								if(fare.getTravelStart().isAfter(fare.getTravelEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel Start Date must be less than or equal to Travel End Date");
						    		errors.add(err1);
								}else if(fare.getTravelEnd().isBefore(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel End Date cannot be earlier than Sales End Date");
						    		errors.add(err1);
								}
							}
							if(fare.getSaleStart() != null && fare.getSaleEnd() != null) {
								if(fare.getSaleStart().isAfter(fare.getSaleEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be less than or equal to Sale End Date");
						    		errors.add(err1);
								}
								if(fare.getSaleStart().isAfter(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be before  or equal to Travel Starte Date");
						    		errors.add(err1);
								}
							}
						}else if(workPackage.getReviewLevel().toUpperCase().contentEquals("DISTRIBUTION")) {
							if(fare.getTariffNumber().getTarCd() == null || fare.getTariffNumber().getTarCd().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("TarCd is required");
					    		errors.add(err1);
							}
							if(fare.getTariffNumber().getTarNo() == null || fare.getTariffNumber().getTarNo().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("TarNo is required");
					    		errors.add(err1);
							}
							if(fare.getFareType() == null || fare.getFareType().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Fare Cls is required");
					    		errors.add(err1);
							}
							if(fare.getCabin() == null || fare.getCabin().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Cabin is required");
					    		errors.add(err1);
							}
							if(fare.getTypeOfJourney() == null || fare.getTypeOfJourney().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("OW/RT is required");
					    		errors.add(err1);
							}
							if(fare.getRtgno() == null || fare.getRtgno().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("RtgNo is required");
					    		errors.add(err1);
							}
							if(fare.getRuleno() == null || fare.getRuleno().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("RuleNo is required");
					    		errors.add(err1);
							}
							if(fare.getCurrency() == null || fare.getCurrency().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Currency is required");
					    		errors.add(err1);
							}
						}						
					}
					if(rejectStatus.size() == fares.size()) {
						WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
			    		err1.setMessage("Worksheet cannot be empty or have all items rejected");
			    		errors.add(err1);
					}
	    		tab1.setError(errors);
	    		
	    		List<WorkPackage.Validation.Tab.Error> warnings = new ArrayList<>();
//	    		
//		    		//List Warning
//		    		WorkPackage.Validation.Tab.Error warn1 = new WorkPackage.Validation.Tab.Error();
//		    		warn1.setMessage("WARNING 1");
//		    		warnings.add(warn1);
//		    		
//	    		tab1.setWarning(warnings);
	    		errorsCount += errors.size();
	    		warningsCount += warnings.size();
	    	if(errors.size() > 0 || warnings.size() > 0) {
	    		tabs.add(tab1);
	    	}
		}
		
		//Validasi Addon Fare
		for(WorkPackageFareSheet wpfs : workPackage.getAddonFareSheet()) {
			WorkPackage.Validation.Tab tab1 = new WorkPackage.Validation.Tab();
	    		tab1.setName(wpfs.getAddonFaresName());
	    		
	    		List<WorkPackage.Validation.Tab.Error> errors = new ArrayList<>();
	    		
	    			List<String> rejectStatus = new ArrayList<>();
		    		List<WorkPackageFare> fares = wpfs.getFares();
					for(WorkPackageFare fare : fares) {
						if(fare.getStatus() != null || !fare.getStatus().contentEquals("")) {
							if(fare.getStatus().contentEquals("REJECTED")) {
								rejectStatus.add("REJECTED");
							}
						}
						
						if(workPackage.getReviewLevel().contentEquals("LSO")) {
							if(wpfs.getAddonFaresName() == null || wpfs.getAddonFaresName().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Description is required");
					    		errors.add(err1);
							}
							if(fare.getOrigin() == null || fare.getOrigin().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Origin is required");
					    		errors.add(err1);
							}
							if(fare.getDestination() == null || fare.getDestination().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Destination is required");
					    		errors.add(err1);
							}
							if(fare.getTypeOfJourney() == null || fare.getTypeOfJourney().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("OW/RT is required");
					    		errors.add(err1);
							}
							if(fare.getCurrency() == null || fare.getCurrency().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Currency is required");
					    		errors.add(err1);
							}
							if(fare.getAmount() == null || fare.getAmount().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Base Amt is required");
					    		errors.add(err1);						    		
							}
							if(fare.getTravelStart() != null && fare.getTravelEnd() != null) {
								if(fare.getTravelStart().isAfter(fare.getTravelEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel Start Date must be less than or equal to Travel End Date");
						    		errors.add(err1);
								}else if(fare.getTravelEnd().isBefore(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel End Date cannot be earlier than Sales End Date");
						    		errors.add(err1);
								}
							}
							if(fare.getSaleStart() != null && fare.getSaleEnd() != null) {
								if(fare.getSaleStart().isAfter(fare.getSaleEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be less than or equal to Sale End Date");
						    		errors.add(err1);
								}
								if(fare.getSaleStart().isAfter(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be before  or equal to Travel Starte Date");
						    		errors.add(err1);
								}
							}
						}else if(workPackage.getReviewLevel().contentEquals("HO")) {
							if(wpfs.getAddonFaresName() == null || wpfs.getAddonFaresName().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Description is required");
					    		errors.add(err1);
							}
							if(fare.getOrigin() == null || fare.getOrigin().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Origin is required");
					    		errors.add(err1);
							}
							if(fare.getDestination() == null || fare.getDestination().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Destination is required");
					    		errors.add(err1);
							}
							if(fare.getTypeOfJourney() == null || fare.getTypeOfJourney().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("OW/RT is required");
					    		errors.add(err1);
							}
							if(fare.getCurrency() == null || fare.getCurrency().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Currency is required");
					    		errors.add(err1);
							}
							if(fare.getAmount() == null || fare.getAmount().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Base Amt is required");
					    		errors.add(err1);						    		
							}
							if(fare.getTravelStart() != null && fare.getTravelEnd() != null) {
								if(fare.getTravelStart().isAfter(fare.getTravelEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel Start Date must be less than or equal to Travel End Date");
						    		errors.add(err1);
								}else if(fare.getTravelEnd().isBefore(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel End Date cannot be earlier than Sales End Date");
						    		errors.add(err1);
								}
							}
							if(fare.getSaleStart() != null && fare.getSaleEnd() != null) {
								if(fare.getSaleStart().isAfter(fare.getSaleEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be less than or equal to Sale End Date");
						    		errors.add(err1);
								}
								if(fare.getSaleStart().isAfter(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be before  or equal to Travel Starte Date");
						    		errors.add(err1);
								}
							}
						}else if(workPackage.getReviewLevel().toUpperCase().contentEquals("DISTRIBUTION")) {
							if(fare.getTariffNumber().getTarNo() == null || fare.getTariffNumber().getTarNo().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Tarno is required");
					    		errors.add(err1);
							}
							if(fare.getTariffNumber().getTarCd() == null || fare.getTariffNumber().getTarCd().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("TarCd is required");
					    		errors.add(err1);
							}
							if(fare.getOrigin() == null || fare.getOrigin().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Origin is required");
					    		errors.add(err1);
							}
							if(fare.getDestination() == null || fare.getDestination().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Destination is required");
					    		errors.add(err1);
							}
							if(fare.getBucket() == null || fare.getBucket().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Add On Bucket is required");
					    		errors.add(err1);
							}
							if(fare.getTypeOfJourney() == null || fare.getTypeOfJourney().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("OW/RT is required");
					    		errors.add(err1);
							}
							if(fare.getZone() == null || fare.getZone().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Zone is required");
					    		errors.add(err1);
							}
							if(fare.getRtgno() == null || fare.getRtgno().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("RtgNo is required");
					    		errors.add(err1);
							}
							if(fare.getCurrency() == null || fare.getCurrency().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Currency is required");
					    		errors.add(err1);
							}
							if(fare.getTravelStart() != null && fare.getTravelEnd() != null) {
								if(fare.getTravelStart().isAfter(fare.getTravelEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel Start Date must be less than or equal to Travel End Date");
						    		errors.add(err1);
								}else if(fare.getTravelEnd().isBefore(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel End Date cannot be earlier than Sales End Date");
						    		errors.add(err1);
								}
							}
							if(fare.getSaleStart() != null && fare.getSaleEnd() != null) {
								if(fare.getSaleStart().isAfter(fare.getSaleEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be less than or equal to Sale End Date");
						    		errors.add(err1);
								}
								if(fare.getSaleStart().isAfter(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be before  or equal to Travel Starte Date");
						    		errors.add(err1);
								}
							}
						}						
					}
					if(rejectStatus.size() == fares.size()) {
						WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
			    		err1.setMessage("Worksheet cannot be empty or have all items rejected");
			    		errors.add(err1);
					}
	    		tab1.setError(errors);
	    		
	    		List<WorkPackage.Validation.Tab.Error> warnings = new ArrayList<>();
	    		errorsCount += errors.size();
	    		warningsCount += warnings.size();
	    	if(errors.size() > 0 || warnings.size() > 0) {
	    		tabs.add(tab1);
	    	}
		}
		
		//Validasi Discount
		for(WorkPackageFareSheet wpfs : workPackage.getDiscountFareSheet()) {
			WorkPackage.Validation.Tab tab1 = new WorkPackage.Validation.Tab();
	    		tab1.setName(wpfs.getDiscountFaresName());
	    		
	    		List<WorkPackage.Validation.Tab.Error> errors = new ArrayList<>();
	    		
		    		List<String> rejectStatus = new ArrayList<>();
		    		List<WorkPackageFare> fares = wpfs.getFares();
					for(WorkPackageFare fare : fares) {
						if(fare.getStatus() != null || !fare.getStatus().contentEquals("")) {
							if(fare.getStatus().contentEquals("REJECTED")) {
								rejectStatus.add("REJECTED");
							}
						}
		    			if(workPackage.getReviewLevel().contentEquals("LSO")) {
							if(wpfs.getDiscountFaresName() == null || wpfs.getDiscountFaresName().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Name is required");
					    		errors.add(err1);
							}
							if(wpfs.getFareType() == null || wpfs.getFareType().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Fare Type is required");
					    		errors.add(err1);
							}
							if(fare.getLoc1Type() == null || fare.getLoc1Type().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Loc 1 Type is required");
					    		errors.add(err1);
							}
							if(fare.getLoc1() == null || fare.getLoc1().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Loc1 is required");
					    		errors.add(err1);
							}
							if(fare.getLoc2Type() == null || fare.getLoc2Type().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Loc 2 Type is required");
					    		errors.add(err1);
							}
							if(fare.getLoc2() == null || fare.getLoc2().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Loc2 is required");
					    		errors.add(err1);
							}
							if(fare.getCalcType() == null || fare.getCalcType().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Calc Type is required");
					    		errors.add(err1);						    		
							}
							if(fare.getCalcType() != null ) {									
								if(fare.getCalcType().contentEquals("C")) {
									if(fare.getPercentBaseFare() == null || fare.getPercentBaseFare().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("% Base Fare is  required when 'Calculated'  Calc Type is used");
							    		errors.add(err1);						    		
									}
								}else if(fare.getCalcType().contentEquals("S")) {										
									if(fare.getCurrency() == null || fare.getCurrency().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Currency is  required when 'Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}
									if(fare.getDiscountSpecifiedAmount() == null || fare.getDiscountSpecifiedAmount().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Specified Amount is  required when 'Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}if(fare.getFareType() == null || fare.getFareType().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Fare Type Code is required when 'Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}if(fare.getTypeOfJourney() == null || fare.getTypeOfJourney().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Base Fare OW/RT is required when 'Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}if(fare.getGlobal() == null || fare.getGlobal().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Global is required when 'Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}
								}else if(fare.getCalcType().contentEquals("M")) {
									if(fare.getPercentBaseFare() == null || fare.getPercentBaseFare().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("% Base Fare is  required when 'Subtract Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}
									if(fare.getCurrency() == null || fare.getCurrency().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Currency is  required when 'Subtract Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}
									if(fare.getDiscountSpecifiedAmount() == null || fare.getDiscountSpecifiedAmount().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Specified Amount is  required when 'Subtract Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}
								}
							}if(fare.getPassengerType() == null || fare.getPassengerType().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("PAX Type is required");
					    		errors.add(err1);						    		
							}
							if(fare.getTravelStart() != null && fare.getTravelEnd() != null) {
								if(fare.getTravelStart().isAfter(fare.getTravelEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel Start Date must be less than or equal to Travel End Date");
						    		errors.add(err1);
								}else if(fare.getTravelEnd().isBefore(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel End Date cannot be earlier than Sales End Date");
						    		errors.add(err1);
								}
							}
							if(fare.getSaleStart() != null && fare.getSaleEnd() != null) {
								if(fare.getSaleStart().isAfter(fare.getSaleEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be less than or equal to Sale End Date");
						    		errors.add(err1);
								}
								if(fare.getSaleStart().isAfter(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be before  or equal to Travel Starte Date");
						    		errors.add(err1);
								}
							}
						}else if(workPackage.getReviewLevel().contentEquals("HO")) {
							if(wpfs.getDiscountFaresName() == null || wpfs.getDiscountFaresName().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Name is required");
					    		errors.add(err1);
							}
							if(wpfs.getFareType() == null || wpfs.getFareType().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Fare Type is required");
					    		errors.add(err1);
							}
							if(fare.getTarcd()== null || fare.getTarcd().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("FBR Tariff Code is required");
					    		errors.add(err1);						    		
							}
							if(fare.getLoc1Type() == null || fare.getLoc1Type().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Loc 1 Type is required");
					    		errors.add(err1);
							}
							if(fare.getLoc1() == null || fare.getLoc1().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Loc1 is required");
					    		errors.add(err1);
							}
							if(fare.getLoc2Type() == null || fare.getLoc2Type().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Loc 2 Type is required");
					    		errors.add(err1);
							}
							if(fare.getLoc2() == null || fare.getLoc2().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Loc2 is required");
					    		errors.add(err1);
							}
							if(fare.getBaseFareBasis() == null || fare.getBaseFareBasis().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Base Fare Cls is required");
					    		errors.add(err1);						    		
							}
							if(fare.getCalcType() == null || fare.getCalcType().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Calc Type is required");
					    		errors.add(err1);						    		
							}
							if(fare.getCalcType() != null ) {									
								if(fare.getCalcType().contentEquals("C")) {
									if(fare.getPercentBaseFare() == null || fare.getPercentBaseFare().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("% Base Fare is  required when 'Calculated'  Calc Type is used");
							    		errors.add(err1);						    		
									}
								}else if(fare.getCalcType().contentEquals("S")) {										
									if(fare.getCurrency() == null || fare.getCurrency().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Currency is  required when 'Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}
									if(fare.getDiscountSpecifiedAmount() == null || fare.getDiscountSpecifiedAmount().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Specified Amount is  required when 'Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}if(fare.getFareType() == null || fare.getFareType().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Fare Type Code is required when 'Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}if(fare.getTypeOfJourney() == null || fare.getTypeOfJourney().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Base Fare OW/RT is required when 'Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}if(fare.getGlobal() == null || fare.getGlobal().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Global is required when 'Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}
								}else if(fare.getCalcType().contentEquals("M")) {
									if(fare.getPercentBaseFare() == null || fare.getPercentBaseFare().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("% Base Fare is  required when 'Subtract Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}
									if(fare.getCurrency() == null || fare.getCurrency().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Currency is  required when 'Subtract Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}
									if(fare.getDiscountSpecifiedAmount() == null || fare.getDiscountSpecifiedAmount().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Specified Amount is  required when 'Subtract Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}
								}
							}if(fare.getPassengerType() == null || fare.getPassengerType().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("PAX Type is required");
					    		errors.add(err1);						    		
							}
							if(fare.getTravelStart() != null && fare.getTravelEnd() != null) {
								if(fare.getTravelStart().isAfter(fare.getTravelEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel Start Date must be less than or equal to Travel End Date");
						    		errors.add(err1);
								}else if(fare.getTravelEnd().isBefore(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel End Date cannot be earlier than Sales End Date");
						    		errors.add(err1);
								}
							}
							if(fare.getSaleStart() != null && fare.getSaleEnd() != null) {
								if(fare.getSaleStart().isAfter(fare.getSaleEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be less than or equal to Sale End Date");
						    		errors.add(err1);
								}
								if(fare.getSaleStart().isAfter(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be before  or equal to Travel Starte Date");
						    		errors.add(err1);
								}
							}
						}else if(workPackage.getReviewLevel().toUpperCase().contentEquals("DISTRIBUTION")) {
							if(fare.getTarcd()== null || fare.getTarcd().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("FBR Tariff Code is required");
					    		errors.add(err1);						    		
							}
							if(fare.getLoc1Type() == null || fare.getLoc1Type().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Loc 1 Type is required");
					    		errors.add(err1);
							}
							if(fare.getLoc1() == null || fare.getLoc1().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Loc1 is required");
					    		errors.add(err1);
							}
							if(fare.getLoc2Type() == null || fare.getLoc2Type().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Loc 2 Type is required");
					    		errors.add(err1);
							}
							if(fare.getLoc2() == null || fare.getLoc2().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Loc2 is required");
					    		errors.add(err1);
							}
							if(fare.getBaseFareBasis() == null || fare.getBaseFareBasis().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Base Fare Cls is required");
					    		errors.add(err1);						    		
							}
							
							if(fare.getCalcType() != null ) {									
								if(fare.getCalcType().contentEquals("S")) {										
									if(fare.getCurrency() == null || fare.getCurrency().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Currency is  required when 'Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}
									if(fare.getFareType() == null || fare.getFareType().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Fare Type Code is required when 'Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}
									if(fare.getTypeOfJourney() == null || fare.getTypeOfJourney().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Base Fare OW/RT is required when 'Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}
									if(fare.getGlobal() == null || fare.getGlobal().contentEquals("")) {									
										//List Error
										WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
							    		err1.setMessage("Global is required when 'Specified'  Calc Type is used");
							    		errors.add(err1);						    		
									}
								}
							}if(fare.getPassengerType() == null || fare.getPassengerType().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("PAX Type is required");
					    		errors.add(err1);						    		
							}
							if(fare.getTravelStart() != null && fare.getTravelEnd() != null) {
								if(fare.getTravelStart().isAfter(fare.getTravelEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel Start Date must be less than or equal to Travel End Date");
						    		errors.add(err1);
								}else if(fare.getTravelEnd().isBefore(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel End Date cannot be earlier than Sales End Date");
						    		errors.add(err1);
								}
							}
							if(fare.getSaleStart() != null && fare.getSaleEnd() != null) {
								if(fare.getSaleStart().isAfter(fare.getSaleEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be less than or equal to Sale End Date");
						    		errors.add(err1);
								}
								if(fare.getSaleStart().isAfter(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be before  or equal to Travel Starte Date");
						    		errors.add(err1);
								}
							}
						}
					}
					if(rejectStatus.size() == fares.size()) {
						WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
			    		err1.setMessage("Worksheet cannot be empty or have all items rejected");
			    		errors.add(err1);
					}
	    		tab1.setError(errors);
	    		
	    		List<WorkPackage.Validation.Tab.Error> warnings = new ArrayList<>();
	    		errorsCount += errors.size();
	    		warningsCount += warnings.size();
	    	if(errors.size() > 0 || warnings.size() > 0) {
	    		tabs.add(tab1);
	    	}
		}
		
		//Validasi Market Fare
		for(WorkPackageFareSheet wpfs : workPackage.getMarketFareSheet()) {
			WorkPackage.Validation.Tab tab1 = new WorkPackage.Validation.Tab();
	    		tab1.setName(wpfs.getMarketFaresName());
	    		
	    		List<WorkPackage.Validation.Tab.Error> errors = new ArrayList<>();
	    		
		    		List<String> rejectStatus = new ArrayList<>();
		    		List<WorkPackageFare> fares = wpfs.getFares();
					for(WorkPackageFare fare : fares) {
						if(fare.getStatus() != null || !fare.getStatus().contentEquals("")) {
							if(fare.getStatus().contentEquals("REJECTED")) {
								rejectStatus.add("REJECTED");
							}
						}
						if(workPackage.getReviewLevel().contentEquals("LSO")) {
							if(wpfs.getMarketFaresName() == null || wpfs.getMarketFaresName().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Description is required");
					    		errors.add(err1);
							}
							if(wpfs.getFareType() == null || wpfs.getFareType().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Fare Type is required");
					    		errors.add(err1);
							}
							if(fare.getOrigin() == null || fare.getOrigin().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Origin is required");
					    		errors.add(err1);
							}
							if(fare.getDestination() == null || fare.getDestination().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Destination is required");
					    		errors.add(err1);
							}
							if(fare.getCabin() == null || fare.getCabin().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Destination is required");
					    		errors.add(err1);
							}
							if(fare.getTypeOfJourney() == null || fare.getTypeOfJourney().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("OW/RT is required");
					    		errors.add(err1);
							}
							if(fare.getCurrency() == null || fare.getCurrency().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Currency is required");
					    		errors.add(err1);
							}
							if(fare.getAmount() == null || fare.getAmount().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Base Amt is required");
					    		errors.add(err1);						    		
							}
							if(fare.getTravelStart() == null) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Travel Start is required");
					    		errors.add(err1);	
							}
							if(fare.getTravelEnd() == null) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Travel End is required");
					    		errors.add(err1);	
							}
							if(fare.getSaleStart() == null) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Sale Start is required");
					    		errors.add(err1);	
							}
							if(fare.getSaleEnd() == null) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Sale End is required");
					    		errors.add(err1);	
							}
							if(fare.getTravelStart() != null && fare.getTravelEnd() != null) {
								if(fare.getTravelStart().isAfter(fare.getTravelEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel Start Date must be less than or equal to Travel End Date");
						    		errors.add(err1);
								}else if(fare.getTravelEnd().isBefore(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel End Date cannot be earlier than Sales End Date");
						    		errors.add(err1);
								}
							}
							if(fare.getSaleStart() != null && fare.getSaleEnd() != null) {
								if(fare.getSaleStart().isAfter(fare.getSaleEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be less than or equal to Sale End Date");
						    		errors.add(err1);
								}
								if(fare.getSaleStart().isAfter(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be before  or equal to Travel Starte Date");
						    		errors.add(err1);
								}
							}
						}else if(workPackage.getReviewLevel().contentEquals("HO")) {
							if(wpfs.getMarketFaresName() == null || wpfs.getMarketFaresName().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Description is required");
					    		errors.add(err1);
							}
							if(wpfs.getFareType() == null || wpfs.getFareType().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Fare Type is required");
					    		errors.add(err1);
							}
							if(fare.getOrigin() == null || fare.getOrigin().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Origin is required");
					    		errors.add(err1);
							}
							if(fare.getDestination() == null || fare.getDestination().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Destination is required");
					    		errors.add(err1);
							}
							if(fare.getFareBasis() == null || fare.getFareBasis().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Fare Class is required");
					    		errors.add(err1);
							}
							if(fare.getBookingClass() == null || fare.getBookingClass().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Booking Class is required");
					    		errors.add(err1);
							}
							if(fare.getCabin() == null || fare.getCabin().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Destination is required");
					    		errors.add(err1);
							}
							if(fare.getTypeOfJourney() == null || fare.getTypeOfJourney().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("OW/RT is required");
					    		errors.add(err1);
							}
							if(fare.getRuleno() == null || fare.getRuleno().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("RuleID is required");
					    		errors.add(err1);
							}
							if(fare.getCurrency() == null || fare.getCurrency().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Currency is required");
					    		errors.add(err1);
							}
							if(fare.getAmount() == null || fare.getAmount().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Base Amt is required");
					    		errors.add(err1);						    		
							}
							if(fare.getTravelStart() == null) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Travel Start is required");
					    		errors.add(err1);	
							}
							if(fare.getTravelEnd() == null) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Travel End is required");
					    		errors.add(err1);	
							}
							if(fare.getSaleStart() == null) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Sale Start is required");
					    		errors.add(err1);	
							}
							if(fare.getSaleEnd() == null) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Sale End is required");
					    		errors.add(err1);	
							}
							if(fare.getTravelStart() != null && fare.getTravelEnd() != null) {
								if(fare.getTravelStart().isAfter(fare.getTravelEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel Start Date must be less than or equal to Travel End Date");
						    		errors.add(err1);
								}else if(fare.getTravelEnd().isBefore(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Travel End Date cannot be earlier than Sales End Date");
						    		errors.add(err1);
								}
							}
							if(fare.getSaleStart() != null && fare.getSaleEnd() != null) {
								if(fare.getSaleStart().isAfter(fare.getSaleEnd())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be less than or equal to Sale End Date");
						    		errors.add(err1);
								}
								if(fare.getSaleStart().isAfter(fare.getTravelStart())) {
									WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
						    		err1.setMessage("Sale Start Date must be before  or equal to Travel Starte Date");
						    		errors.add(err1);
								}
							}
						}
					}
					if(rejectStatus.size() == fares.size()) {
						WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
			    		err1.setMessage("Worksheet cannot be empty or have all items rejected");
			    		errors.add(err1);
					}
	    		tab1.setError(errors);
	    		
	    		List<WorkPackage.Validation.Tab.Error> warnings = new ArrayList<>();
	    		errorsCount += errors.size();
	    		warningsCount += warnings.size();
	    	if(errors.size() > 0 || warnings.size() > 0) {
	    		tabs.add(tab1);
	    	}
		}
		
		//Validasi Waiver Fare
		for(WorkPackageFareSheet wpfs : workPackage.getWaiverFareSheet()) {
			WorkPackage.Validation.Tab tab1 = new WorkPackage.Validation.Tab();
	    		tab1.setName(wpfs.getWaiverFaresName());
	    		
	    		List<WorkPackage.Validation.Tab.Error> errors = new ArrayList<>();
	    		
		    		List<String> rejectStatus = new ArrayList<>();
		    		List<WorkPackageFare> fares = wpfs.getFares();
					for(WorkPackageFare fare : fares) {
						if(fare.getStatus() != null || !fare.getStatus().contentEquals("")) {
							if(fare.getStatus().contentEquals("REJECTED")) {
								rejectStatus.add("REJECTED");
							}
						}
						if(workPackage.getReviewLevel().contentEquals("LSO")) {
							if(wpfs.getWaiverFaresName() == null || wpfs.getWaiverFaresName().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Name is required");
					    		errors.add(err1);
							}
							if(wpfs.getWaiverAgentName() == null || wpfs.getWaiverAgentName().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Agent Name is required");
					    		errors.add(err1);
							}
							if(wpfs.getWaiverIataNo() == null || wpfs.getWaiverIataNo().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("IATA No is required");
					    		errors.add(err1);
							}
							if(fare.getWaiverType() == null || fare.getWaiverType().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Type is required");
					    		errors.add(err1);
							}
							if(fare.getWaiverFullPartial() == null || fare.getWaiverFullPartial().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Full/Partial is required");
					    		errors.add(err1);
							}
							if(fare.getWaiverPnr() == null || fare.getWaiverPnr().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("PNR is required");
					    		errors.add(err1);
							}
							if(fare.getWaiverTktFrom() == null || fare.getWaiverTktFrom().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Tkt From is required");
					    		errors.add(err1);
							}
							if(fare.getWaiverTktTo() == null || fare.getWaiverTktTo().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Tkt To is required");
					    		errors.add(err1);
							}
							if(fare.getWaiverOri() == null || fare.getWaiverOri().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Ori is required");
					    		errors.add(err1);						    		
							}
							if(fare.getWaiverDest() == null|| fare.getWaiverDest().contentEquals("")) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Dest is required");
					    		errors.add(err1);	
							}
							if(fare.getWaiverOriginalItinerary() == null|| fare.getWaiverOriginalItinerary().contentEquals("")) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Original Itinerary Start is required");
					    		errors.add(err1);	
							}
							if(fare.getWaiverOriginalBasicFare() == null|| fare.getWaiverOriginalBasicFare().contentEquals("")) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Original Basic Fare is required");
					    		errors.add(err1);	
							}
							if(fare.getWaiverCalculatedPn() == null|| fare.getWaiverCalculatedPn().contentEquals("")) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Calculated PN is required");
					    		errors.add(err1);	
							}
							if(fare.getWaiverOriginalPn() == null|| fare.getWaiverOriginalPn().contentEquals("")) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Original PN   is required");
					    		errors.add(err1);	
							}
							if(fare.getWaiverApprovedPn() == null|| fare.getWaiverApprovedPn().contentEquals("")) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Aprrove PN is required");
					    		errors.add(err1);	
							}
							if(fare.getWaiverTotalPax() == null|| fare.getWaiverTotalPax().contentEquals("")) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Total Pax is required");
					    		errors.add(err1);	
							}
						}else if(workPackage.getReviewLevel().contentEquals("HO")) {
							if(wpfs.getWaiverFaresName() == null || wpfs.getWaiverFaresName().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Name is required");
					    		errors.add(err1);
							}
							if(wpfs.getWaiverAgentName() == null || wpfs.getWaiverAgentName().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Agent Name is required");
					    		errors.add(err1);
							}
							if(wpfs.getWaiverIataNo() == null || wpfs.getWaiverIataNo().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("IATA No is required");
					    		errors.add(err1);
							}
							if(fare.getWaiverType() == null || fare.getWaiverType().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Type is required");
					    		errors.add(err1);
							}
							if(fare.getWaiverFullPartial() == null || fare.getWaiverFullPartial().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Full/Partial is required");
					    		errors.add(err1);
							}
							if(fare.getWaiverPnr() == null || fare.getWaiverPnr().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("PNR is required");
					    		errors.add(err1);
							}
							if(fare.getWaiverTktFrom() == null || fare.getWaiverTktFrom().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Tkt From is required");
					    		errors.add(err1);
							}
							if(fare.getWaiverTktTo() == null || fare.getWaiverTktTo().contentEquals("")) {
								//List Error
					    		WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Tkt To is required");
					    		errors.add(err1);
							}
							if(fare.getWaiverOri() == null || fare.getWaiverOri().contentEquals("")) {									
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Ori is required");
					    		errors.add(err1);						    		
							}
							if(fare.getWaiverDest() == null|| fare.getWaiverDest().contentEquals("")) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Dest is required");
					    		errors.add(err1);	
							}
							if(fare.getWaiverOriginalItinerary() == null|| fare.getWaiverOriginalItinerary().contentEquals("")) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Original Itinerary Start is required");
					    		errors.add(err1);	
							}
							if(fare.getWaiverOriginalBasicFare() == null|| fare.getWaiverOriginalBasicFare().contentEquals("")) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Original Basic Fare is required");
					    		errors.add(err1);	
							}
							if(fare.getWaiverCalculatedPn() == null|| fare.getWaiverCalculatedPn().contentEquals("")) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Calculated PN is required");
					    		errors.add(err1);	
							}
							if(fare.getWaiverOriginalPn() == null|| fare.getWaiverOriginalPn().contentEquals("")) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Original PN   is required");
					    		errors.add(err1);	
							}
							if(fare.getWaiverApprovedPn() == null|| fare.getWaiverApprovedPn().contentEquals("")) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Aprrove PN is required");
					    		errors.add(err1);	
							}
							if(fare.getWaiverTotalPax() == null|| fare.getWaiverTotalPax().contentEquals("")) {
								//List Error
								WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
					    		err1.setMessage("Total Pax is required");
					    		errors.add(err1);	
							}
						}
					}
					if(rejectStatus.size() == fares.size()) {
						WorkPackage.Validation.Tab.Error err1 = new WorkPackage.Validation.Tab.Error();
			    		err1.setMessage("Worksheet cannot be empty or have all items rejected");
			    		errors.add(err1);
					}
	    		tab1.setError(errors);
	    		
	    		List<WorkPackage.Validation.Tab.Error> warnings = new ArrayList<>();
	    		errorsCount += errors.size();
	    		warningsCount += warnings.size();
	    	if(errors.size() > 0 || warnings.size() > 0) {
	    		tabs.add(tab1);
	    	}
		}
		validation.setTab(tabs);
		validation.setErrorsCount(errorsCount);
		validation.setWarningsCount(warningsCount);
		workPackage.setValidation(validation);
		return workPackage;
	}


    /**
     * GET  /work-packages : get all the workPackages.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of workPackages in body
     */
    @GetMapping("/work-packages")
    @Timed
    public ResponseEntity<List<WorkPackage>> getAllWorkPackages(WorkPackageFilter filter, Pageable pageable) {
        log.debug("REST request to get a page of WorkPackages {}", filter);
        
        Optional<WorkPackageFilter> initFilter = packagefilterRepository.findOneByLoginName(SecurityUtils.getCurrentUserLogin().get());
      
        if(initFilter.isPresent()) {
        	WorkPackageFilter temp = initFilter.get();
        	filter.setLoginName(SecurityUtils.getCurrentUserLogin().get());
        	filter.setId(temp.getId());
        	temp = packagefilterRepository.save(filter);  
        	filter = temp;
        }else {
        	filter.setLoginName(SecurityUtils.getCurrentUserLogin().get());
	        packagefilterRepository.save(filter);
        }
                
//        Page<WorkPackage> page = workPackageService.findAllByOrderByLastModifiedDate(pageable);       
        Page<WorkPackage> page = workPackageService.findCustom(filter, pageable);       
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/work-packages");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /workPackagefilter/:id : get the "id" workPackagefilter.
     *
     * @param id the id of the workPackagefilter to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workPackagefilter, or with status 404 (Not Found)
     */
    @GetMapping("/work-packages/byname")
    @Timed
    public ResponseEntity<WorkPackageFilter> getWorkPackageFilterbyLoginName() {
    	String loginName = SecurityUtils.getCurrentUserLogin().get();
        log.debug("REST request to get workPackagefilter : {}", loginName);
        WorkPackageFilter result = null;
        Optional<WorkPackageFilter> workPackagefilter = packagefilterRepository.findOneByLoginName(loginName);
        if(workPackagefilter.isPresent()) {
        	 result = workPackagefilter.get();
        }
        else {
        	WorkPackageFilter filter = new WorkPackageFilter();    
        		
        	com.atibusinessgroup.fmp.domain.WorkPackageFilter.DistributionType dt = new WorkPackageFilter.DistributionType();
        	dt.setAtpco(true);
        	dt.setMarket(true);
        	dt.setWaiver(true);
        	filter.setDistributionType(dt);
        	
        	com.atibusinessgroup.fmp.domain.WorkPackageFilter.Status s = new WorkPackageFilter.Status();
        	s.setCompleted(true);
        	s.setDistributed(true);        	
        	s.setPending(true);
        	s.setReadyToRelease(true);
        	s.setReferred(true);
        	s.setReplace(false);
        	s.setReuse(false);
        	s.setReviewing(true);
        	s.setWithdrawn(true);
        	s.setNewStatus(true);
        	s.setDiscontinued(true);
        	filter.setStatus(s);
        	
        	com.atibusinessgroup.fmp.domain.WorkPackageFilter.Type t = new WorkPackageFilter.Type();
        	t.setDiscount(true);
        	t.setRegular(true);
        	t.setWaiver(true);        	
        	filter.setType(t);
        	
        	com.atibusinessgroup.fmp.domain.WorkPackageFilter.ReviewLevel rl = new WorkPackageFilter.ReviewLevel();
        	rl.setDistribution(false);
	    	rl.setHo(false);
	    	rl.setLso(false);
	    	rl.setRouteManagement(false); 
        	Optional<User> userOptional = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get());
        	if(userOptional.isPresent()) {
            	User user = userOptional.get();
	        	for(String reviewLevel : user.getReviewLevels()) {
		        	if(reviewLevel.equals("LSO")) {
		        		rl.setLso(true);
		        	}else if(reviewLevel.equals("HO")) {
		        		rl.setHo(true);
		        	}else if(reviewLevel.equals("DISTRIBUTION")) {
		        		rl.setDistribution(true);
		        	}else if(reviewLevel.equals("ROUTE_MANAGEMENT")) {
		        		rl.setRouteManagement(true); 
		        	}
	        	}
        	}	        
        	filter.setReviewLevel(rl);
        	
        	filter.setApprovalReference(null);
        	filter.setCreatedTime("10");
        	filter.setLoginName(SecurityUtils.getCurrentUserLogin().get());
        	result = packagefilterRepository.save(filter);
        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(result));
    }

    /**
     * GET  /work-packages/:id : get the "id" workPackage.
     *
     * @param id the id of the workPackage to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workPackage, or with status 404 (Not Found)
     */
    @GetMapping("/work-packages/{id}")
    @Timed
    public ResponseEntity<WorkPackage> getWorkPackage(@PathVariable String id) {
        log.debug("REST request to get WorkPackage : {}", id);
        WorkPackage workPackage = workPackageService.findOne(id);
        if(workPackage.getStatus() == Status.PENDING || workPackage.getStatus() == Status.REFERRED) {
        	workPackage.setStatus(Status.REVIEWING);
        	workPackageService.save(workPackage);
        }
        if(!workPackage.isLocked()) {
        	workPackage.setLocked(true);
            workPackage.setLockedBy(SecurityUtils.getCurrentUserLogin().get());
            workPackage.setLockedSince(ZonedDateTime.now());
            workPackage = workPackageService.save(workPackage);	
        }        
//        List<WorkPackageFare> fares = workPackageFareService.findAllByWorkPackageAndFareType(workPackage.getId(), null);
//        log.debug("REST request to set WorkPackageFARES : {}", fares.size());
//        workPackage.setFares(fares);
//       
//        List<WorkPackageFare> addonFares = workPackageFareService.findAllByWorkPackageAndFareType(workPackage.getId(), "ADDON");
//        log.debug("REST request to set WorkPackageFARES Addon : {}", addonFares.size());
//        workPackage.setAddonFares(addonFares);
//        
//        List<WorkPackageFare> marketFares = workPackageFareService.findAllByWorkPackageAndFareType(workPackage.getId(), "MARKET");
//        log.debug("REST request to set WorkPackageFARES Market: {}", marketFares.size());
//        workPackage.setMarketFares(marketFares);
//        
//        List<WorkPackageFare> discountFares = workPackageFareService.findAllByWorkPackageAndFareType(workPackage.getId(), "DISCOUNT");
//        log.debug("REST request to set WorkPackageFARES Discount: {}", discountFares.size());
//        workPackage.setDiscountFares(discountFares);
        
//        if(workPackage.getVersion() == null) {
//        	workPackage.setVersion("current");
//        }
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(workPackage));
    }
    
    /**
     * GET  /work-packages/history/:id : get the "id" workPackage.
     *
     * @param id the id of the workPackage to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the workPackage, or with status 404 (Not Found)
     */
    @GetMapping("/work-packages/history/{id}")
    @Timed
    public ResponseEntity<List<WorkPackageHistory>> getWorkPackageHistory(@PathVariable String id) {
        log.debug("REST request to get WorkPackage : {}", id);
        WorkPackage workPackage = workPackageService.findOne(id);
        
        List<WorkPackageHistory> history = workPackageHistoryService.findAllByWorkPackage(new ObjectId(workPackage.getId()));
        
//        List<WorkPackageFare> fares = workPackageFareService.findAllByWorkPackage(workPackage.getId());
//        log.debug("REST request to get WorkPackageFARES : {}", fares.size());
//        workPackage.setFares(fares);
//       
//        List<WorkPackageFare> addonFares = workPackageFareService.findAllByWorkPackageAndFareType(workPackage.getId(), "ADDON");
//        log.debug("REST request to get WorkPackageFARES : {}", fares.size());
//        workPackage.setFares(fares);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(history));
    }

    /**
     * DELETE  /work-packages/:id : delete the "id" workPackage.
     *
     * @param id the id of the workPackage to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/work-packages/{id}")
    @Timed
    public ResponseEntity<Void> deleteWorkPackage(@PathVariable String id) {
        log.debug("REST request to delete WorkPackage : {}", id);
        
        List<WorkPackageFare> fares = workPackageFareService.findAllByWorkPackage(id);
        for(WorkPackageFare wp : fares){
        		workPackageFareService.delete(wp.getId());
        }
        workPackageService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
    
    /**
     * POST  /work-packages/passup : Passup
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/passup")
    @Timed
    public ResponseEntity<WorkPackage> passupWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to passup WorkPackage : {}", workPackage);
        if (workPackage.getId() == null) {
            throw new BadRequestAlertException("A workPackage should have an ID", ENTITY_NAME, "idexists");
        }
        
        workPackage = workPackageService.save(workPackage);        
            
        workPackage = validateWo(workPackage);
        if(workPackage.getValidation() != null && workPackage.getValidation().getErrorsCount() > 0) {
        	
        }
        else {
	        WorkPackage result = workPackageService.findOne(workPackage.getId());
	        String reviewLevel = result.getReviewLevel();
	        if(reviewLevel.contentEquals("LSO")) {
	    		result.setReviewLevel("HO");
	    		result.setStatus(Status.PENDING);
	    		result.setLocked(false);
	        }
	        List<WorkPackageFareSheet> fareSheet = result.getFareSheet();
	        for(WorkPackageFareSheet sheet : fareSheet) {
	        	FareVersion fareVersion = new FareVersion();
	        	fareVersion.username = SecurityUtils.getCurrentUserLogin().get();
	        	fareVersion.action = "PASSUP";
	        	fareVersion.fares = sheet.getFares();
	        	fareVersion.version = sheet.fareVersion.size() + 1;
	        	sheet.fareVersion.add(fareVersion);
	        }
	        
	        List<WorkPackageFareSheet> addOnFareSheet = result.getAddonFareSheet();
	        for(WorkPackageFareSheet sheet : addOnFareSheet) {
	        	FareVersion fareVersion = new FareVersion();
	        	fareVersion.username = SecurityUtils.getCurrentUserLogin().get();
	        	fareVersion.action = "PASSUP";
	        	fareVersion.fares = sheet.getFares();
	        	fareVersion.version = sheet.fareVersion.size() + 1;
	        	sheet.fareVersion.add(fareVersion);
	        }
	        
	        List<WorkPackageFareSheet> discountFareSheet = result.getDiscountFareSheet();
	        for(WorkPackageFareSheet sheet : discountFareSheet) {
	        	FareVersion fareVersion = new FareVersion();
	        	fareVersion.username = SecurityUtils.getCurrentUserLogin().get();
	        	fareVersion.action = "PASSUP";
	        	fareVersion.fares = sheet.getFares();
	        	fareVersion.version = sheet.fareVersion.size() + 1;
	        	sheet.fareVersion.add(fareVersion);
	        }
	        
	        List<WorkPackageFareSheet> marketFareSheet = result.getMarketFareSheet();
	        for(WorkPackageFareSheet sheet : marketFareSheet) {
	        	FareVersion fareVersion = new FareVersion();
	        	fareVersion.username = SecurityUtils.getCurrentUserLogin().get();
	        	fareVersion.action = "PASSUP";
	        	fareVersion.fares = sheet.getFares();
	        	fareVersion.version = sheet.fareVersion.size() + 1;
	        	sheet.fareVersion.add(fareVersion);
	        }
	        
	        List<WorkPackageFareSheet> waiverFareSheet = result.getWaiverFareSheet();
	        for(WorkPackageFareSheet sheet : waiverFareSheet) {
	        	FareVersion fareVersion = new FareVersion();
	        	fareVersion.username = SecurityUtils.getCurrentUserLogin().get();
	        	fareVersion.action = "PASSUP";
	        	fareVersion.fares = sheet.getFares();
	        	fareVersion.version = sheet.fareVersion.size() + 1;
	        	sheet.fareVersion.add(fareVersion);
	        }
	        result.setQueuedDate(Instant.now());
	        workPackageService.save(result);  
	        
	        saveHistoryData(workPackage);
	        
	        WorkPackageHistory history = new WorkPackageHistory();
	        history.setWorkPackage(new ObjectId(result.getId()));
	        history.setType("PASSUP");
	        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
	        workPackageHistoryService.save(history);	        
        }
        
        
        return ResponseEntity.created(new URI("/api/work-packages/" + workPackage.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, workPackage.getId().toString()))
            .body(workPackage);
    }
    
    /**
     * POST  /work-packages/unlock : unlock
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/unlock")
    @Timed
    public ResponseEntity<WorkPackage> unlockWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to withdraw WorkPackage : {}", workPackage);
        if (workPackage.getId() == null) {
            throw new BadRequestAlertException("A workPackage should have an ID", ENTITY_NAME, "idexists");
        }
        
        WorkPackage result = workPackageService.findOne(workPackage.getId());
        
        result.setLocked(false);
        workPackageService.save(result);
        
        return ResponseEntity.created(new URI("/api/work-packages/" + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
                .body(result);
    }
    
    /**
     * POST  /work-packages/withdraw : withdraw
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/withdraw")
    @Timed
    public ResponseEntity<WorkPackage> withdrawWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to withdraw WorkPackage : {}", workPackage);
        if (workPackage.getId() == null) {
            throw new BadRequestAlertException("A workPackage should have an ID", ENTITY_NAME, "idexists");
        }
        
        WorkPackage result = workPackageService.findOne(workPackage.getId());
        result.setStatus(Status.WITHDRAWN);
        result.setQueuedDate(Instant.now());
        workPackageService.save(result);
        /*
        saveHistoryData(workPackage);
        
        //updateWorkPackage(workPackage);
                        
        WorkPackage result = workPackageService.findOne(workPackage.getId());
        String reviewLevel = result.getReviewLevel();
        if(reviewLevel.contentEquals("LSO")) {
    		result.setReviewLevel("HO");
    		result.setStatus(Status.PENDING);
        }
//        else if(reviewLevel.contentEquals("LSO2")) {
//    		result.setReviewLevel("HO1");
//    		result.setStatus(Status.PENDING);
//        }
//        else if(reviewLevel.contentEquals("HO1")) {
//    		result.setReviewLevel("HO2");
//    		result.setStatus(Status.PENDING);
//        }
//        else if(reviewLevel.contentEquals("HO2")) {
//        		//cannot passup
//        }
        
        workPackageService.save(result);
        
        
        WorkPackageHistory history = new WorkPackageHistory();
        history.setWorkPackage(new ObjectId(result.getId()));
        history.setType("PASSUP");
        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
        workPackageHistoryService.save(history);
        */
        
        WorkPackageHistory history = new WorkPackageHistory();
        history.setWorkPackage(new ObjectId(result.getId()));
        history.setType("WITHDRAW");
        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
        workPackageHistoryService.save(history);
        
        return ResponseEntity.created(new URI("/api/work-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    private void saveHistoryData(WorkPackage workPackage) {
		// TODO Auto-generated method stub
//    		String woId = workPackage.getId();        
//        long count = workPackageHistoryDataRepository.countByWorkPackage(new ObjectId(woId));
//        count = count+1;
//        
//        WorkPackageHistoryData wphd = new WorkPackageHistoryData(workPackage, String.valueOf(count));        
//        wphd.setWorkPackage(new ObjectId(woId));       
//        log.debug("REST request to save WorkPackage History Data : {}", wphd);
//        workPackageHistoryDataRepository.save(wphd);

	}

	/**
     * POST  /work-packages/passdown : Passdown
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/passdown")
    @Timed
    public ResponseEntity<WorkPackage> passdownWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to passdown WorkPackage : {}", workPackage);
        if (workPackage.getId() == null) {
            throw new BadRequestAlertException("A workPackage should have an ID", ENTITY_NAME, "idexists");
        }
        
        saveHistoryData(workPackage);
        
        //updateWorkPackage(workPackage);
        
        WorkPackage result = workPackageService.findOne(workPackage.getId());
        String reviewLevel = result.getReviewLevel();
        if(reviewLevel.contentEquals("HO")) {
    		result.setReviewLevel("LSO");
    		result.setStatus(Status.PENDING);
    		result.setLocked(false);
        }
        
        List<WorkPackageFareSheet> fareSheet = result.getFareSheet();
        for(WorkPackageFareSheet sheet : fareSheet) {
        	FareVersion fareVersion = new FareVersion();
        	fareVersion.action = "PASSDOWN";
        	fareVersion.fares = sheet.getFares();
        	fareVersion.version = sheet.fareVersion.size() + 1;
        	fareVersion.username = SecurityUtils.getCurrentUserLogin().get();
        	sheet.fareVersion.add(fareVersion);
        }
        
        List<WorkPackageFareSheet> addOnFareSheet = result.getAddonFareSheet();
        for(WorkPackageFareSheet sheet : addOnFareSheet) {
        	FareVersion fareVersion = new FareVersion();
        	fareVersion.action = "PASSDOWN";
        	fareVersion.fares = sheet.getFares();
        	fareVersion.version = sheet.fareVersion.size() + 1;
        	fareVersion.username = SecurityUtils.getCurrentUserLogin().get();
        	sheet.fareVersion.add(fareVersion);
        }
               
        List<WorkPackageFareSheet> discountFareSheet = result.getDiscountFareSheet();
        for(WorkPackageFareSheet sheet : discountFareSheet) {
        	FareVersion fareVersion = new FareVersion();
        	fareVersion.username = SecurityUtils.getCurrentUserLogin().get();
        	fareVersion.action = "PASSDOWN";
        	fareVersion.fares = sheet.getFares();
        	fareVersion.version = sheet.fareVersion.size() + 1;
        	sheet.fareVersion.add(fareVersion);
        }
        
        List<WorkPackageFareSheet> marketFareSheet = result.getMarketFareSheet();
        for(WorkPackageFareSheet sheet : marketFareSheet) {
        	FareVersion fareVersion = new FareVersion();
        	fareVersion.username = SecurityUtils.getCurrentUserLogin().get();
        	fareVersion.action = "PASSDOWN";
        	fareVersion.fares = sheet.getFares();
        	fareVersion.version = sheet.fareVersion.size() + 1;
        	sheet.fareVersion.add(fareVersion);
        }
        
        List<WorkPackageFareSheet> waiverFareSheet = result.getWaiverFareSheet();
        for(WorkPackageFareSheet sheet : waiverFareSheet) {
        	FareVersion fareVersion = new FareVersion();
        	fareVersion.username = SecurityUtils.getCurrentUserLogin().get();
        	fareVersion.action = "PASSDOWN";
        	fareVersion.fares = sheet.getFares();
        	fareVersion.version = sheet.fareVersion.size() + 1;
        	sheet.fareVersion.add(fareVersion);
        }
        result.setQueuedDate(Instant.now());
        workPackageService.save(result);
        
        WorkPackageHistory history = new WorkPackageHistory();
        history.setWorkPackage(new ObjectId(result.getId()));
        history.setType("PASSDOWN");
        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
        workPackageHistoryService.save(history);
        
        
        return ResponseEntity.created(new URI("/api/work-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /work-packages/passsideway : Passsideway
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/passsideway")
    @Timed
    public ResponseEntity<WorkPackage> passsidewayWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to passsideway WorkPackage : {}", workPackage);
        if (workPackage.getId() == null) {
            throw new BadRequestAlertException("A workPackage should have an ID", ENTITY_NAME, "idexists");
        }
        
        WorkPackage result = workPackageService.findOne(workPackage.getId());
        String reviewLevel = result.getReviewLevel();
        
        if(result.getSidewayReviewLevel() == null) {
    		result.setSidewayReviewLevel(reviewLevel);
    		result.setReviewLevel("ROUTE_MANAGEMENT");
        }
        else {
     		result.setReviewLevel(result.getSidewayReviewLevel());
    		result.setSidewayReviewLevel(null);
        }
		result.setLocked(false);
        result.setStatus(Status.PENDING);
        result.setQueuedDate(Instant.now());
        workPackageService.save(result);
        
        WorkPackageHistory history = new WorkPackageHistory();
        history.setWorkPackage(new ObjectId(result.getId()));
        history.setType("PASSSIDEWAY");
        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
        workPackageHistoryService.save(history);
        
        return ResponseEntity.created(new URI("/api/work-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /work-packages/approve : Approve
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/approve")
    @Timed
    public ResponseEntity<WorkPackage> approveWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to approve WorkPackage : {}", workPackage);
        if (workPackage.getId() == null) {
            throw new BadRequestAlertException("A workPackage should have an ID", ENTITY_NAME, "idexists");
        }
        
        saveHistoryData(workPackage);
        
        //updateWorkPackage(workPackage);
        
//        WorkPackage result = workPackageService.findOne(workPackage.getId());
        String reviewLevel = workPackage.getReviewLevel();
        String distribution = workPackage.getTargetDistribution();
        String type = workPackage.getType().name();
        
        if(reviewLevel.contentEquals("HO") && distribution.contentEquals("MARKET") && type.contentEquals("REGULAR")) {
        	workPackage.setDistributionReviewLevel(reviewLevel);
        	workPackage.setReviewLevel("LSO");
        	workPackage.setLocked(false);
        	workPackage.setStatus(Status.DISTRIBUTED);
        }else if(reviewLevel.contentEquals("HO")) {
        	workPackage.setDistributionReviewLevel(reviewLevel);
        	workPackage.setReviewLevel("DISTRIBUTION");
    		workPackage.setLocked(false);
    		workPackage.setStatus(Status.PENDING);        		
	    }
//        if(reviewLevel.contentEquals("HO")) {
//    		result.setDistributionReviewLevel(reviewLevel);
//    		result.setReviewLevel("DISTRIBUTION");
//    		result.setLocked(false);
//    		result.setStatus(Status.PENDING);        		
//	    }
        workPackage.setQueuedDate(Instant.now());
        workPackageService.save(workPackage);
        
        WorkPackageHistory history = new WorkPackageHistory();
        history.setWorkPackage(new ObjectId(workPackage.getId()));
        history.setType("APPROVE");
        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
        workPackageHistoryService.save(history);
        
        ApproveConfig x = new ApproveConfig();
        
        String[] emailData = null;
        if(workPackage.getApproveConfig().getEmail() != null && workPackage.getApproveConfig().getEmail().size() > 0) {
	        emailData = new String[workPackage.getApproveConfig().getEmail().size()];
	        for (int i=0;i<workPackage.getApproveConfig().getEmail().size();i++) {
	        	emailData[i] = workPackage.getApproveConfig().getEmail().get(i);
	        }
        }
        
        x.setEmail(workPackage.getApproveConfig().getEmail()); 
        workPackage.setApproveConfig(x);
        
        String[] emailDataCc = null;
        if(workPackage.getApproveConfig().getCcEmail() != null && workPackage.getApproveConfig().getCcEmail().size() > 0) {
	        emailDataCc = new String[workPackage.getApproveConfig().getCcEmail().size()];        
	        for (int i=0;i<workPackage.getApproveConfig().getCcEmail().size();i++) {
	        	emailDataCc[i] = workPackage.getApproveConfig().getCcEmail().get(i);
	        }
        }
        
        x.setCcEmail(workPackage.getApproveConfig().getCcEmail()); 
        workPackage.setApproveConfig(x);
        
        workPackageService.save(workPackage);
        User u = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get()).get();
        
        String content = "<h2>Inter Office Comment</h2>";
        content += "<br/></br>";
        content += "<table>";
        	content += "<thead>";        
	        content += "<tr>";
	        	content += 	"<th>Message</th>";
	        	content += 	"<th>Username</th>";
	        	content += 	"<th>Date</th>";
	        content += "</tr>";
	        content += "</thead>";  
	        
	        content += "<tbody>";  
	        	if(workPackage.getInterofficeComment() != null) {
		        	for(Comment c : workPackage.getInterofficeComment()) {
				        content += "<tr>";
				        	content += 	"<td>"+c.getComment()+"</td>";
				        	content += 	"<td>"+c.getUsername()+"</td>";
				        	content += 	"<td>"+c.getCreatedTime().toString()+"</td>";
				        content += "</tr>";
		        	}
	        	}
	        	else {
	        		content += "<tr>";
		        		content += 	"<td colspan='3'>No Interoffice Comment</td>";
		        	content += "</tr>";
	        	}
        	content += "</tbody>";  
        content += "</table>";
        
        List<Attachment> sendAttachments = new ArrayList<>();
        List<Attachment> attachments = workPackage.getAttachmentData();
        for (Attachment attachment : attachments) {
        	try {
            	if(attachment.getInOnly().equals(true)) {
            		sendAttachments.add(attachment);
            	}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
        if(!sendAttachments.isEmpty()) {
        	log.debug("SEND EMAIL WITH ATTACHMENT");
        	mailService.sendEmailWithAttachment(u.getEmail(), emailData, emailDataCc, "Approve", content, true, true, sendAttachments);
        }
        else {
        	log.debug("SEND EMAIL WITHOUT ATTACHMENT");
        	mailService.sendEmailWithoutAttachment(u.getEmail(), emailData, emailDataCc, "Approve", content, true, true);
        }
//        if(workPackage.getApproveConfig().attachment) {
//        	log.debug("SEND EMAIL WITH ATTACHMENT");
//        	mailService.sendEmailWithAttachment(u.getEmail(), emailData, emailDataCc, "Approve", content, true, true, sendAttachments);
//        }
//        else {
//        	log.debug("SEND EMAIL WITHOUT ATTACHMENT");
//        	mailService.sendEmailWithoutAttachment(u.getEmail(), emailData, emailDataCc, "Approve", content, true, true);
//        }
        return ResponseEntity.created(new URI("/api/work-packages/" + workPackage.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, workPackage.getId().toString()))
            .body(workPackage);
    }
    
    /**
     * POST  /work-packages/resend-approve : Approve
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/resend-approve")
    @Timed
    public ResponseEntity<WorkPackage> resendApproveWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to resend approve WorkPackage : {}", workPackage);
        if (workPackage.getId() == null) {
            throw new BadRequestAlertException("A workPackage should have an ID", ENTITY_NAME, "idexists");
        }
        
        WorkPackage result = workPackageService.findOne(workPackage.getId());
        result.setLocked(false);
        workPackageService.save(result);
        
        ApproveConfig x = new ApproveConfig();
        
        
        String[] emailData = null;
        if(workPackage.getApproveConfig().getEmail() != null && workPackage.getApproveConfig().getEmail().size() > 0) {
	        emailData = new String[workPackage.getApproveConfig().getEmail().size()];
	        for (int i=0;i<workPackage.getApproveConfig().getEmail().size();i++) {
	        	emailData[i] = workPackage.getApproveConfig().getEmail().get(i);
	        	log.debug("cek : "+emailData[i]);
	        }
        }
        
        
        x.setEmail(workPackage.getApproveConfig().getEmail()); 
        workPackage.setApproveConfig(x);
        
        String[] emailDataCc = null;
        if(workPackage.getApproveConfig().getCcEmail() != null && workPackage.getApproveConfig().getCcEmail().size() > 0) {
	        emailDataCc = new String[workPackage.getApproveConfig().getCcEmail().size()];        
	        for (int i=0;i<workPackage.getApproveConfig().getCcEmail().size();i++) {
	        	emailDataCc[i] = workPackage.getApproveConfig().getCcEmail().get(i);
	        }
        }
        
        x.setCcEmail(workPackage.getApproveConfig().getCcEmail()); 
        workPackage.setApproveConfig(x);
        
        workPackageService.save(workPackage);
        User u = userRepository.findOneByLogin(SecurityUtils.getCurrentUserLogin().get()).get();
        
        String content = "<h2>Inter Office Comment</h2>";
        content += "<br/></br>";
        content += "<table>";
        	content += "<thead>";        
	        content += "<tr>";
	        	content += 	"<th>Message</th>";
	        	content += 	"<th>Username</th>";
	        	content += 	"<th>Date</th>";
	        content += "</tr>";
	        content += "</thead>";  
	        
	        content += "<tbody>";  
	        	if(workPackage.getInterofficeComment() != null) {
		        	for(Comment c : workPackage.getInterofficeComment()) {
				        content += "<tr>";
				        	content += 	"<td>"+c.getComment()+"</td>";
				        	content += 	"<td>"+c.getUsername()+"</td>";
				        	content += 	"<td>"+c.getCreatedTime().toString()+"</td>";
				        content += "</tr>";
		        	}
	        	}
	        	else {
	        		content += "<tr>";
		        		content += 	"<td colspan='3'>No Interoffice Comment</td>";
		        	content += "</tr>";
	        	}
        	content += "</tbody>";  
        content += "</table>";
        
        List<Attachment> sendAttachments = new ArrayList<>();
        List<Attachment> attachments = workPackage.getAttachmentData();
        for (Attachment attachment : attachments) {
        	try {
        		if(attachment.getInOnly().equals(true)) {
            		sendAttachments.add(attachment);
            	}
			} catch (Exception e) {	}        	
		}
        if(!sendAttachments.isEmpty()) {
        	log.debug("SEND EMAIL WITH ATTACHMENT");
        	mailService.sendEmailWithAttachment(u.getEmail(), emailData, emailDataCc, "Approve", content, true, true, sendAttachments);
        }
        else {
        	log.debug("SEND EMAIL WITHOUT ATTACHMENT");
        	mailService.sendEmailWithoutAttachment(u.getEmail(), emailData, emailDataCc, "Approve", content, true, true);
        }
        
//        if(workPackage.getApproveConfig().attachment) {
//        	log.debug("SEND EMAIL WITH ATTACHMENT");
//        	mailService.sendEmailWithAttachment(u.getEmail(), emailData, emailDataCc, "Approve", content, true, true, workPackage.getAttachmentData());
//        }
//        else {
//        	log.debug("SEND EMAIL WITHOUT ATTACHMENT");
//        	mailService.sendEmailWithoutAttachment(u.getEmail(), emailData, emailDataCc, "Approve", content, true, true);
//        }
        return ResponseEntity.created(new URI("/api/work-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /work-packages/referback : referback
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/referback")
    @Timed
    public ResponseEntity<WorkPackage> referbackWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to referback WorkPackage : {}", workPackage);
        if (workPackage.getId() == null) {
            throw new BadRequestAlertException("A workPackage should have an ID", ENTITY_NAME, "idexists");
        }
        
        saveHistoryData(workPackage);
        
        //updateWorkPackage(workPackage);
        
        WorkPackage result = workPackageService.findOne(workPackage.getId());

        result.setReviewLevel(result.getDistributionReviewLevel());
        result.setDistributionReviewLevel(null);
        result.setStatus(Status.REFERRED);
		result.setLocked(false);
		result.setQueuedDate(Instant.now());
        workPackageService.save(result);
        
        WorkPackageHistory history = new WorkPackageHistory();
        history.setWorkPackage(new ObjectId(result.getId()));
        history.setType("REFERBACK");
        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
        workPackageHistoryService.save(history);
        
        return ResponseEntity.created(new URI("/api/work-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /work-packages/complete : complete
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/complete")
    @Timed
    public ResponseEntity<WorkPackage> completeWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to complete WorkPackage : {}", workPackage);
        if (workPackage.getId() == null) {
            throw new BadRequestAlertException("A workPackage should have an ID", ENTITY_NAME, "idexists");
        }
        
        saveHistoryData(workPackage);
        
        //updateWorkPackage(workPackage);
        
        WorkPackage result = workPackageService.findOne(workPackage.getId());

        result.setReviewLevel(result.getReviewLevel());
        result.setDistributionReviewLevel(result.getDistributionReviewLevel());
        result.setStatus(Status.DISTRIBUTED);
		result.setLocked(false);
		result.setQueuedDate(Instant.now());
        workPackageService.save(result);
        
        WorkPackageHistory history = new WorkPackageHistory();
        history.setWorkPackage(new ObjectId(result.getId()));
        history.setType("COMPLETE");
        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
        workPackageHistoryService.save(history);
        
        return ResponseEntity.created(new URI("/api/work-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /work-packages/createbatch : createbatch
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/createbatch")
    @Timed
    public ResponseEntity<WorkPackage> createbatchWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to createbatch WorkPackage : {}", workPackage);
        /*
        if (workPackage.getId() == null) {
            throw new BadRequestAlertException("A workPackage should have an ID", ENTITY_NAME, "idexists");
        }
                
        StringBuilder batchBuilder = new StringBuilder();
        
        batchBuilder.append("W\t\t\tNN");
        batchBuilder.append("\n");        
        try {
        batchBuilder.append(workPackage.getFilingDetail().getEmail());
        }catch(Exception e) {}
        
        batchBuilder.append("\n");        
        batchBuilder.append("B  N  "+workPackage.getSpecifiedFaresName());
        batchBuilder.append("\n");        
        for(WorkPackageFare fare : workPackage.getFares()) {
	    		batchBuilder.append("F");
	    		batchBuilder.append(fare.getCarrier());
	    		batchBuilder.append(fare.getTarno());
	    		
	    		try {
		    		if(fare.getAction().contentEquals("New")) {
		    			batchBuilder.append("N");
		    		}else {
		    			batchBuilder.append(" ");
		    		}
	    		}catch(Exception e) {
	    			batchBuilder.append(" ");
	    		}
	    		batchBuilder.append(fare.getOrigin());
	    		batchBuilder.append(fare.getDestination());
	    		batchBuilder.append(fare.getFareBasis());
	    		batchBuilder.append("  ");
	    		if(fare.getTypeOfJourney().contentEquals("One Way") || fare.getTypeOfJourney().contentEquals("One Way Only") ) {
	    			batchBuilder.append("1");            			
	    		}
	    		if(fare.getTypeOfJourney().contentEquals("Return") || fare.getTypeOfJourney().contentEquals("Round Trip")) {
	    			batchBuilder.append("2");            			
	    		}
	
	    		if(fare.getRtgno() == null) {
	    			batchBuilder.append("0000");
	    		}
	    		else {
	    			batchBuilder.append(fare.getRtgno());
	    		}
	    		batchBuilder.append("  ");
	    		batchBuilder.append(fare.getCurrency());
	    		batchBuilder.append(fare.getAmount());
	    		
	    		batchBuilder.append("\n");           		
        }
        
        if(workPackage.getAddonFares() != null && workPackage.getAddonFares().size() > 0) {
        	
        }
        WorkPackage result = workPackageService.findOne(workPackage.getId());
        result.setStatus(Status.READY_TO_RELEASE);
        workPackage = workPackageService.save(result);
        
        WorkPackageHistory history = new WorkPackageHistory();
        history.setWorkPackage(new ObjectId(result.getId()));
        history.setType("CREATEBATCH");
        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
        workPackageHistoryService.save(history);
        
        result.setBatchString(batchBuilder.toString());
        
        saveHistoryData(workPackage);
        
        //updateWorkPackage(workPackage);
*/
        WorkPackage result = workPackageService.findOne(workPackage.getId());
        
        return ResponseEntity.created(new URI("/api/work-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    
    /**
     * POST  /work-packages/revisebatch : revisebatch
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/revisebatch")
    @Timed
    public ResponseEntity<WorkPackage> revisebatchWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to revisebatch WorkPackage : {}", workPackage);
        if (workPackage.getId() == null) {
            throw new BadRequestAlertException("A workPackage should have an ID", ENTITY_NAME, "idexists");
        }
        
        WorkPackage result = workPackageService.findOne(workPackage.getId());
        result.setStatus(Status.PENDING);
        workPackage = workPackageService.save(result);
        
        WorkPackageHistory history = new WorkPackageHistory();
        history.setWorkPackage(new ObjectId(result.getId()));
        history.setType("REVISEBATCH");
        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
        workPackageHistoryService.save(history);
        
        saveHistoryData(workPackage);
        
        //updateWorkPackage(workPackage);
        
        return ResponseEntity.created(new URI("/api/work-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /work-packages/completebatch : completebatch
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/completebatch")
    @Timed
    public ResponseEntity<WorkPackage> completebatchWorkPackage(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to completebatch WorkPackage : {}", workPackage);
        if (workPackage.getId() == null) {
            throw new BadRequestAlertException("A workPackage should have an ID", ENTITY_NAME, "idexists");
        }
        
        WorkPackage result = workPackageService.findOne(workPackage.getId());
        result.setStatus(Status.DISTRIBUTED);
        workPackage = workPackageService.save(result);
        
        WorkPackageHistory history = new WorkPackageHistory();
        history.setWorkPackage(new ObjectId(result.getId()));
        history.setType("COMPLETEBATCH");
        history.setUsername(SecurityUtils.getCurrentUserLogin().get());
        workPackageHistoryService.save(history);
        

        saveHistoryData(workPackage);
        
        //updateWorkPackage(workPackage);
        
        return ResponseEntity.created(new URI("/api/work-packages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }
    
    /**
     * POST  /work-packages/changeVersion : Create a new workPackage.
     *
     * @param workPackage the workPackage to change
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/changeVersion")
    @Timed
    public ResponseEntity<WorkPackage> changeVersion(@RequestBody WorkPackage workPackage) throws URISyntaxException {
        log.debug("REST request to change version of WorkPackage : {}", workPackage);
        /*
        if(workPackage.getVersion().contentEquals("current")) {
        	updateWorkPackage(workPackage);
        }
        
        if(workPackage.getChangeType() != null && !workPackage.getChangeType().contentEquals("")) {
        	String version = "current";
        	String currentVersion = workPackage.getVersion();
    		
        	if(workPackage.getChangeType().contentEquals("previous")) {
                if(currentVersion.contentEquals("current")) {
        			version = workPackageHistoryDataRepository.countByWorkPackage(new ObjectId(workPackage.getId()))+"";
                }
                else if(Long.parseLong(currentVersion) > 1){                	                	
                	version = (Long.parseLong(currentVersion) - 1)+"";
                }
        	}
        	else if(workPackage.getChangeType().contentEquals("next")) {
        	    if(!currentVersion.contentEquals("current")) {
	        	    	if(Long.parseLong(currentVersion) != workPackageHistoryDataRepository.countByWorkPackage(new ObjectId(workPackage.getId()))) {
	        	    		version = (Long.parseLong(currentVersion) + 1) + "";
	        	    	}
	        	    	else {
	        	    		version = "current";
	        	    	}
            }
        	    else {
        	    		version = "1";
        	    }
        	}
        	
        	if(version.contentEquals("current")) {        		
        		workPackage = getWorkPackage(workPackage.getId()).getBody();
        	}
        	else {
        		workPackage = workPackageHistoryDataRepository.findByWorkPackageAndVersion(new ObjectId(workPackage.getId()), version);        	
        		
        		//get fare
        		List<WorkPackageFare> fares = workPackageFareHistoryDataRepository.findAllByWorkPackageAndFareType(new ObjectId(workPackage.getId()), null);
        		workPackage.setFares(fares);
        	}
        }
        */
        return ResponseEntity.created(new URI("/api/work-packages/" + workPackage.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, workPackage.getId().toString()))
            .body(workPackage);
    }

    public static class WorkPackageRateSheet {
    	private WorkPackage wp;
    	private String ruleText;
    	private String index;
    	private String[] header;
    	
		public WorkPackage getWp() {
			return wp;
		}
		public void setWp(WorkPackage wp) {
			this.wp = wp;
		}
		public String getRuleText() {
			return ruleText;
		}
		public void setRuleText(String ruleText) {
			this.ruleText = ruleText;
		}
		public String getIndex() {
			return index;
		}
		public void setIndex(String index) {
			this.index = index;
		}
		public String[] getHeader() {
			return header;
		}
		public void setHeader(String[] header) {
			this.header = header;
		}
		
    }
    
	/**
     * POST  /work-packages/export-ratesheet : Export work package fares
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws DocumentException 
     */
    @PostMapping("/work-packages/export-ratesheet")
    @Timed
    public ResponseEntity<Attachment> exportRateSheetWorkPackage(@RequestBody WorkPackageRateSheet wprs) throws URISyntaxException, MalformedURLException, IOException, DocumentException {
    	log.debug("REST request to save exportFares : {}{}", wprs.getWp(), wprs.getRuleText());
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        WorkPackage workPackage = wprs.getWp();
        String ruleText = wprs.getRuleText();
        int idx = Integer.parseInt(wprs.getIndex());
        String[] header = wprs.getHeader();
        
        Integer count = header.length;
        PdfPTable table;
        Document document;
        if(count < 25) {
        	document = new Document(PageSize.A4,30,30,60,0);
    	}else {
    		document = new Document(PageSize.A4.rotate());
    		document.setMargins(60, 30, 30, 30);
    	}
    	
    	PdfWriter.getInstance(document, output);    	
    	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("images/logo_ga.png");
    	byte[] imageByte = IOUtils.toByteArray(inputStream);    	
    	document.open();
    	Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.BLACK);
    	Image image = Image.getInstance(imageByte); 
    	
    	image.scaleToFit(PageSize.A4.getWidth()/4, PageSize.A4.getHeight()/4);
    	image.setAlignment(Image.MIDDLE);
    	String[] content = {"WORK ID : ","TITLE FARE SHEET : ","DESCRIPTION OF GA FARES : ","NOTES RATESHEET : ", "FARES : ", "RULE TEXT : "};
    	document.add(image); 
    	document.add(new Chunk(" "));

        Paragraph p1 = new Paragraph();
        Paragraph p2 = new Paragraph();
        Paragraph p3 = new Paragraph();
        Paragraph p4 = new Paragraph();
        Paragraph p5 = new Paragraph();
        Paragraph p6 = new Paragraph();

        p1.setFont(font);               
        p1.add(content[0]+" "+ workPackage.getWpid());        
    	document.add(p1);
    	document.add(new Chunk(" "));
        p2.setFont(font);               
        p2.add(content[1]+" "+workPackage.getName());
        document.add(p2);
    	document.add(new Chunk(" "));

        p3.setFont(font);               
        p3.add(content[2]+" "+workPackage.getFareSheet().get(idx).getSpecifiedFaresName());
    	document.add(p3);
    	document.add(new Chunk(" "));

        p4.setFont(font);               
        p4.add(content[3]+" "+workPackage.getRatesheetComment());
    	document.add(p4);
    	document.add(new Chunk(" "));

        p5.setFont(font);               
        p5.add(content[4]);
    	document.add(p5);
    	document.add(new Chunk(" "));

    	table = new PdfPTable(count);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for(int l=0; l<header.length ;l++) {
			table.addCell(header[l]);
		}
		table.setHeaderRows(1);
        
		if(workPackage.getTargetDistribution().contentEquals("ATPCO")) {
			PdfPCell[] cells = table.getRow(0).getCells(); 
        	for (int j=0;j<cells.length;j++){
        			cells[j].setBackgroundColor(BaseColor.GRAY);
        	}
        	log.debug("cek : "+workPackage.getFareSheet().size());
        	for(int l=0; l<workPackage.getFareSheet().get(idx).getFares().size();l++) {
        		for (int i=0;i<header.length;i++){
            		if(header[i].contentEquals("Status")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getStatus());
            		}else if(header[i].contentEquals("Carrier")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getCarrier());
            		}else if(header[i].contentEquals("Action")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getAction());
            		}else if(header[i].contentEquals("Tar No")) {
            			try {
            				table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getTariffNumber().getTarNo());
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}            			
            		}else if(header[i].contentEquals("Tar Cd")) {
            			try {
            				table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getTariffNumber().getTarCd());
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}            			
            		}else if(header[i].contentEquals("Global")) {
            			try {
            				table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getTariffNumber().getGlobal());
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}            			
            		}else if(header[i].contentEquals("Origin")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getOrigin());
            		}else if(header[i].contentEquals("Destination")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getDestination());
            		}else if(header[i].contentEquals("Fare Class")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getFareBasis());
            		}else if(header[i].contentEquals("Booking Class")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getBookingClass());
            		}else if(header[i].contentEquals("Cabin")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getCabin());
            		}else if(header[i].contentEquals("OW/RT")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getTypeOfJourney());
            		}else if(header[i].contentEquals("Footnote")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getFootnote1());
            		}else if(header[i].contentEquals("Routing No")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getRtgno());
            		}else if(header[i].contentEquals("Rule No")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getRuleno());
            		}else if(header[i].contentEquals("Currency")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getCurrency());
            		}else if(header[i].contentEquals("Base Amt")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getAmount());
            		}else if(header[i].contentEquals("Amt Different")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
            		}else if(header[i].contentEquals("% Amt Different")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
            		}else if(header[i].contentEquals("YQYR")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getYqyr());
            		}else if(header[i].contentEquals("Cat 12")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getCat12());
            		}else if(header[i].contentEquals("TFC")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getTfc());
            		}else if(header[i].contentEquals("Target AIF")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getAif());
            		}else if(header[i].contentEquals("Itinerary")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getItinerary());
            		}else if(header[i].contentEquals("Override Indicator")) {
            			try {
                			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getOverrideIndicator().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Travel Start")) {
            			try {
                			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getTravelStart().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Travel End")) {
            			try {
                			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getTravelEnd().toString());
							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Sales Start")) {
            			try {
                			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getSaleStart().toString());
							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Sales End")) {
            			try {
                			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getSaleEnd().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("EffDt")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getEffDt());
            		}else if(header[i].contentEquals("Comment")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getComment());
            		}else if(header[i].contentEquals("Travel Complete")) {
            			try {
                			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getTravelComplete().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Travel Complete Indicator")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getTravelCompleteIndicator());
            		}else if(header[i].contentEquals("RateSheet Comment")) {
            			table.addCell(workPackage.getFareSheet().get(idx).getFares().get(l).getRatesheetComment());
            		}
            		else {
            			table.addCell("-");
            		}
            	}        		
        	}
        	
          document.add(table);
		}else if(workPackage.getTargetDistribution().contentEquals("MARKET")){
			PdfPCell[] cells = table.getRow(0).getCells(); 
        	for (int j=0;j<cells.length;j++){
        			cells[j].setBackgroundColor(BaseColor.GRAY);
        	}
        	log.debug("cek : "+workPackage.getMarketFareSheet().size());
        	for(int l=0; l<workPackage.getMarketFareSheet().get(idx).getFares().size();l++) {
        		for (int i=0;i<header.length;i++){
            		if(header[i].contentEquals("Status")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getStatus());
            		}else if(header[i].contentEquals("Carrier")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getCarrier());
            		}else if(header[i].contentEquals("Action")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getAction());
            		}else if(header[i].contentEquals("Tar No")) {
            			try {
            				table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTariffNumber().getTarNo());
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}            			
            		}else if(header[i].contentEquals("Tar Cd")) {
            			try {
            				table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTariffNumber().getTarCd());
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}            			
            		}else if(header[i].contentEquals("Global")) {
            			try {
            				table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTariffNumber().getGlobal());
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}            			
            		}else if(header[i].contentEquals("Origin")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getOrigin());
            		}else if(header[i].contentEquals("Destination")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getDestination());
            		}else if(header[i].contentEquals("Fare Class")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getFareBasis());
            		}else if(header[i].contentEquals("Booking Class")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getBookingClass());
            		}else if(header[i].contentEquals("Cabin")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getCabin());
            		}else if(header[i].contentEquals("OW/RT")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTypeOfJourney());
            		}else if(header[i].contentEquals("Footnote")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getFootnote1());
            		}else if(header[i].contentEquals("Routing No")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getRtgno());
            		}else if(header[i].contentEquals("Rule No")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getRuleno());
            		}else if(header[i].contentEquals("Currency")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getCurrency());
            		}else if(header[i].contentEquals("Base Amt")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getAmount());
            		}else if(header[i].contentEquals("Amt Different")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
            		}else if(header[i].contentEquals("% Amt Different")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
            		}else if(header[i].contentEquals("YQYR")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getYqyr());
            		}else if(header[i].contentEquals("Cat 12")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getCat12());
            		}else if(header[i].contentEquals("TFC")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTfc());
            		}else if(header[i].contentEquals("Target AIF")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getAif());
            		}else if(header[i].contentEquals("Itinerary")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getItinerary());
            		}else if(header[i].contentEquals("Override Indicator")) {
            			try {
                			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getOverrideIndicator().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Travel Start")) {
            			try {
                			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTravelStart().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Travel End")) {
            			try {
                			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTravelEnd().toString());
							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Sales Start")) {
            			try {
                			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getSaleStart().toString());
							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Sales End")) {
            			try {
                			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getSaleEnd().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("EffDt")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getEffDt());
            		}else if(header[i].contentEquals("Comment")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getComment());
            		}else if(header[i].contentEquals("Travel Complete")) {
            			try {
                			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTravelComplete().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Travel Complete Indicator")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTravelCompleteIndicator());
            		}else if(header[i].contentEquals("RateSheet Comment")) {
            			table.addCell(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getRatesheetComment());
            		}
            		else {
            			table.addCell("-");
            		}
            	}        		
        	}
        	
          document.add(table);
		}
	  
        
	
        p6.setFont(font);               
        p6.add(content[5]+" "+ruleText);
    	document.add(p6);
    	document.add(new Chunk(" "));

    	document.close();
        
        Attachment att = new Attachment();
        att.setFile(output.toByteArray());
        
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
            .body(att);
    }
    
    /**
     * POST  /work-packages/export-ratesheet-excel : Export work package fares
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws DocumentException 
     */
    @PostMapping("/work-packages/export-ratesheet-excel")
    @Timed
    public ResponseEntity<Attachment> exportRateSheetWorkPackageExcel(@RequestBody WorkPackageRateSheet wprs) throws URISyntaxException, MalformedURLException, IOException, DocumentException {
    	log.debug("REST request to save exportFaresExcel : {}{}", wprs.getWp(), wprs.getRuleText());
    	
    	WorkPackage workPackage = wprs.getWp();
        String ruleText = wprs.getRuleText();
        int idx = Integer.parseInt(wprs.getIndex());
        String[] header = wprs.getHeader();
    	
    	XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet spreadsheet = workbook.createSheet("Rate Sheet");
        
        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell;
        
        cell = row.createCell(0);
        cell.setCellValue("WORK ID :");
        cell = row.createCell(1);
        cell.setCellValue(workPackage.getWpid());
        
        XSSFRow row2 = spreadsheet.createRow(2);
        XSSFCell cell2;
        cell2 = row2.createCell(0);
        cell2.setCellValue("TITLE FARE SHEET :");
        cell2 = row2.createCell(1);
        cell2.setCellValue(workPackage.getName());
        
        if(workPackage.getTargetDistribution().contentEquals("ATPCO")) {
        	 XSSFRow row3 = spreadsheet.createRow(3);
             XSSFCell cell3;
             cell3 = row3.createCell(0);
             cell3.setCellValue("DESCRIPTION OF GA FARES :");
             cell3 = row3.createCell(1);
             cell3.setCellValue(workPackage.getFareSheet().get(idx).getSpecifiedFaresName());
        }else if(workPackage.getTargetDistribution().contentEquals("MARKET")) {
        	 XSSFRow row3 = spreadsheet.createRow(3);
             XSSFCell cell3;
             cell3 = row3.createCell(0);
             cell3.setCellValue("DESCRIPTION OF GA FARES :");
             cell3 = row3.createCell(1);
             cell3.setCellValue(workPackage.getFareSheet().get(idx).getMarketFaresName());
        }       
        
        XSSFRow row4 = spreadsheet.createRow(4);
        XSSFCell cell4;
        cell4 = row4.createCell(0);
        cell4.setCellValue("NOTES RATESHEET : ");
        cell4 = row4.createCell(1);
        cell4.setCellValue(workPackage.getRatesheetComment());
        
        XSSFRow row5 = spreadsheet.createRow(5);
        XSSFCell cell5;
        for(int l=0; l<header.length ;l++) {
        	cell5 = row5.createCell(l);
			cell5.setCellValue(header[l]);
		}
        
        if(workPackage.getTargetDistribution().contentEquals("ATPCO")) {
        	for(int l=0; l<workPackage.getFareSheet().get(idx).getFares().size();l++) {
            	XSSFRow rows = spreadsheet.createRow(l+6);
        		for (int i=0;i<header.length;i++){
        			cell = rows.createCell(i);
        			
        			if(header[i].contentEquals("Status")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getStatus());
        			} else if(header[i].contentEquals("Carrier")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getCarrier());
        			} else if(header[i].contentEquals("Action")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getAction());
        			} else if(header[i].contentEquals("Tar No")) {
        				try {
        					cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getTariffNumber().getTarNo());
    					} catch (Exception e) {
    						// TODO: handle exception
    						cell.setCellValue("-");
    					}    				
        			} else if(header[i].contentEquals("Tar Cd")) {
        				try {
            				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getTariffNumber().getTarCd());						
    					} catch (Exception e) {
    						// TODO: handle exception
    						cell.setCellValue("-");
    					}
        			} else if(header[i].contentEquals("Global")) {
        				try {
            				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getTariffNumber().getGlobal());						
    					} catch (Exception e) {
    						// TODO: handle exception
    						cell.setCellValue("-");
    					}
        			} else if(header[i].contentEquals("Origin")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getOrigin());
        			} else if(header[i].contentEquals("Destination")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getDestination());
        			} else if(header[i].contentEquals("Fare Class")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getFareBasis());
        			} else if(header[i].contentEquals("Booking Class")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getBookingClass());
        			} else if(header[i].contentEquals("Cabin")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getCabin());
        			} else if(header[i].contentEquals("OW/RT")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getTypeOfJourney());
        			} else if(header[i].contentEquals("Footnote")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getFootnote1());
        			} else if(header[i].contentEquals("Routing No")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getRtgno());
        			} else if(header[i].contentEquals("Rule No")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getRuleno());
        			} else if(header[i].contentEquals("Currency")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getCurrency());
        			} else if(header[i].contentEquals("Base Amt")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getAmount());
        			} else if(header[i].contentEquals("Amt Different")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
        			} else if(header[i].contentEquals("% Amt Different")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
        			} else if(header[i].contentEquals("YQYR")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getYqyr());
        			} else if(header[i].contentEquals("Cat 12")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getCat12());
        			} else if(header[i].contentEquals("Taxes")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getTotalTax());
        			} else if(header[i].contentEquals("TFC")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getTfc());
        			} else if(header[i].contentEquals("Target AIF")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getAif());
        			} else if(header[i].contentEquals("Itinerary")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getItinerary());
        			} else if(header[i].contentEquals("Override Indicator")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getOverrideIndicator());
        			} else if(header[i].contentEquals("Travel Start")) {
        				try {
            				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getTravelStart().toString());						
    					} catch (Exception e) {
    						// TODO: handle exception
    						cell.setCellValue("-");
    					}
        			} else if(header[i].contentEquals("Travel End")) {
        				try {
            				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getTravelEnd().toString());						
    					} catch (Exception e) {
    						// TODO: handle exception
    						cell.setCellValue("-");
    					}
        			} else if(header[i].contentEquals("Sales Start")) {
        				try {
            				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getSaleStart().toString());						
    					} catch (Exception e) {
    						// TODO: handle exception
    						cell.setCellValue("-");
    					}
        			} else if(header[i].contentEquals("Sales End")) {
        				try {
            				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getSaleEnd().toString());						
    					} catch (Exception e) {
    						// TODO: handle exception
    						cell.setCellValue("-");
    					}
        			} else if(header[i].contentEquals("EffDt")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getEffDt());
        			} else if(header[i].contentEquals("Comment")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getComment());
        			} else if(header[i].contentEquals("Travel Complete")) {
        				try {
        					cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getTravelComplete().toString());
    					} catch (Exception e) {
    						cell.setCellValue("-");
    					}
        				
        			} else if(header[i].contentEquals("Travel Complete Indicator")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getTravelCompleteIndicator());
        			} else if(header[i].contentEquals("RateSheet Comment")) {
        				cell.setCellValue(workPackage.getFareSheet().get(idx).getFares().get(l).getRatesheetComment());
        			} 
        		}
            }
            
            
            XSSFRow rowRuleText = spreadsheet.createRow(workPackage.getFareSheet().get(idx).getFares().size()+7);
            XSSFCell cellRuleText;
            cellRuleText = rowRuleText.createCell(0);
            cellRuleText.setCellValue("RULE TEXT : ");
            cellRuleText = rowRuleText.createCell(1);
            cellRuleText.setCellValue(ruleText);
        }else if(workPackage.getTargetDistribution().contentEquals("MARKET")) {
        	for(int l=0; l<workPackage.getMarketFareSheet().get(idx).getFares().size();l++) {
            	XSSFRow rows = spreadsheet.createRow(l+6);
        		for (int i=0;i<header.length;i++){
        			cell = rows.createCell(i);
        			
        			if(header[i].contentEquals("Status")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getStatus());
        			} else if(header[i].contentEquals("Carrier")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getCarrier());
        			} else if(header[i].contentEquals("Action")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getAction());
        			} else if(header[i].contentEquals("Tar No")) {
        				try {
        					cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTariffNumber().getTarNo());
    					} catch (Exception e) {
    						// TODO: handle exception
    						cell.setCellValue("-");
    					}    				
        			} else if(header[i].contentEquals("Tar Cd")) {
        				try {
            				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTariffNumber().getTarCd());						
    					} catch (Exception e) {
    						// TODO: handle exception
    						cell.setCellValue("-");
    					}
        			} else if(header[i].contentEquals("Global")) {
        				try {
            				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTariffNumber().getGlobal());						
    					} catch (Exception e) {
    						// TODO: handle exception
    						cell.setCellValue("-");
    					}
        			} else if(header[i].contentEquals("Origin")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getOrigin());
        			} else if(header[i].contentEquals("Destination")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getDestination());
        			} else if(header[i].contentEquals("Fare Class")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getFareBasis());
        			} else if(header[i].contentEquals("Booking Class")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getBookingClass());
        			} else if(header[i].contentEquals("Cabin")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getCabin());
        			} else if(header[i].contentEquals("OW/RT")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTypeOfJourney());
        			} else if(header[i].contentEquals("Footnote")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getFootnote1());
        			} else if(header[i].contentEquals("Routing No")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getRtgno());
        			} else if(header[i].contentEquals("Rule No")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getRuleno());
        			} else if(header[i].contentEquals("Currency")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getCurrency());
        			} else if(header[i].contentEquals("Base Amt")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getAmount());
        			} else if(header[i].contentEquals("Amt Different")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
        			} else if(header[i].contentEquals("% Amt Different")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
        			} else if(header[i].contentEquals("YQYR")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getYqyr());
        			} else if(header[i].contentEquals("Cat 12")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getCat12());
        			} else if(header[i].contentEquals("Taxes")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTotalTax());
        			} else if(header[i].contentEquals("TFC")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTfc());
        			} else if(header[i].contentEquals("Target AIF")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getAif());
        			} else if(header[i].contentEquals("Itinerary")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getItinerary());
        			} else if(header[i].contentEquals("Override Indicator")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getOverrideIndicator());
        			} else if(header[i].contentEquals("Travel Start")) {
        				try {
            				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTravelStart().toString());						
    					} catch (Exception e) {
    						// TODO: handle exception
    						cell.setCellValue("-");
    					}
        			} else if(header[i].contentEquals("Travel End")) {
        				try {
            				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTravelEnd().toString());						
    					} catch (Exception e) {
    						// TODO: handle exception
    						cell.setCellValue("-");
    					}
        			} else if(header[i].contentEquals("Sales Start")) {
        				try {
            				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getSaleStart().toString());						
    					} catch (Exception e) {
    						// TODO: handle exception
    						cell.setCellValue("-");
    					}
        			} else if(header[i].contentEquals("Sales End")) {
        				try {
            				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getSaleEnd().toString());						
    					} catch (Exception e) {
    						// TODO: handle exception
    						cell.setCellValue("-");
    					}
        			} else if(header[i].contentEquals("EffDt")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getEffDt());
        			} else if(header[i].contentEquals("Comment")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getComment());
        			} else if(header[i].contentEquals("Travel Complete")) {
        				try {
        					cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTravelComplete().toString());
    					} catch (Exception e) {
    						cell.setCellValue("-");
    					}
        				
        			} else if(header[i].contentEquals("Travel Complete Indicator")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTravelCompleteIndicator());
        			} else if(header[i].contentEquals("RateSheet Comment")) {
        				cell.setCellValue(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getRatesheetComment());
        			} 
        		}
            }
            
            
            XSSFRow rowRuleText = spreadsheet.createRow(workPackage.getMarketFareSheet().get(idx).getFares().size()+7);
            XSSFCell cellRuleText;
            cellRuleText = rowRuleText.createCell(0);
            cellRuleText.setCellValue("RULE TEXT : ");
            cellRuleText = rowRuleText.createCell(1);
            cellRuleText.setCellValue(ruleText);
        }
        
        
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
			workbook.write(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Attachment att = new Attachment();
        att.setFile(output.toByteArray());
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
            .body(att);
    }
        
    /**
     * POST  /work-packages/export-fares-discount : Export work package fares-discount
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws DocumentException 
     */
    @PostMapping("/work-packages/export-ratesheet-discount")
    @Timed
    public ResponseEntity<Attachment> exportRateSheetWorkPackageDiscount(@RequestBody WorkPackageRateSheet wprs) throws URISyntaxException, MalformedURLException, IOException, DocumentException {
    	log.debug("REST request to save exportFares-discount : {}{}", wprs.getWp(), wprs.getRuleText());
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        WorkPackage workPackage = wprs.getWp();
        String ruleText = wprs.getRuleText();
        int idx = Integer.parseInt(wprs.getIndex());
        String[] header = wprs.getHeader();
        
        Integer count = header.length;
        PdfPTable table;
        Document document;
        if(count < 25) {
        	document = new Document(PageSize.A4,30,30,60,0);
    	}else {
    		document = new Document(PageSize.A4.rotate());
    		document.setMargins(60, 30, 30, 30);
    	}
    	
    	PdfWriter.getInstance(document, output);
    	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("images/logo_ga.png");
    	byte[] imageByte = IOUtils.toByteArray(inputStream);    	
    	document.open();
    	Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.BLACK);
    	Image image = Image.getInstance(imageByte); 
    	image.scaleToFit(PageSize.A4.getWidth()/4, PageSize.A4.getHeight()/4);
    	image.setAlignment(Image.MIDDLE);
    	String[] content = {"WORK ID : ","TITLE FARE SHEET : ", "FARE NAME : ","NOTES RATESHEET : ", "FARES : ", "RULE TEXT : "};
    	document.add(image); 
    	document.add(new Chunk(" "));

        Paragraph p1 = new Paragraph();
        Paragraph p2 = new Paragraph();
        Paragraph p3 = new Paragraph();
        Paragraph p4 = new Paragraph();
        Paragraph p5 = new Paragraph();
        Paragraph p6 = new Paragraph();

        p1.setFont(font);               
        p1.add(content[0]+" "+ workPackage.getWpid());        
    	document.add(p1);
    	document.add(new Chunk(" "));
        p2.setFont(font);               
        p2.add(content[1]+" "+workPackage.getName());
        document.add(p2);
    	document.add(new Chunk(" "));

        p3.setFont(font);               
        p3.add(content[2]+" "+workPackage.getDiscountFareSheet().get(idx).getDiscountFaresName());
    	document.add(p3);
    	document.add(new Chunk(" "));

        p4.setFont(font);               
        p4.add(content[3]+" "+workPackage.getRatesheetComment());
    	document.add(p4);
    	document.add(new Chunk(" "));

        p5.setFont(font);               
        p5.add(content[4]);
    	document.add(p5);
    	document.add(new Chunk(" "));

    	table = new PdfPTable(count);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for(int l=0; l<header.length ;l++) {
			table.addCell(header[l]);
		}
		table.setHeaderRows(1);
        
		
		PdfPCell[] cells = table.getRow(0).getCells(); 
    	for (int j=0;j<cells.length;j++){
    			cells[j].setBackgroundColor(BaseColor.GRAY);
    	}
        	log.debug("cek discount : "+workPackage.getDiscountFareSheet().size());
        	for(int l=0; l<workPackage.getDiscountFareSheet().get(idx).getFares().size();l++) {
        		for (int i=0;i<header.length;i++){
            		if(header[i].contentEquals("Status")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getStatus());
            		}else if(header[i].contentEquals("FBR Tariff Code")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTarcd());
            		}else if(header[i].contentEquals("Loc 1 Type")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getLoc1Type());       			
            		}else if(header[i].contentEquals("Loc 1")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getLoc1());
            		}else if(header[i].contentEquals("Loc 2 Type")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getLoc2Type());
            		}else if(header[i].contentEquals("Loc 2")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getLoc2());
            		}else if(header[i].contentEquals("Base FareCls")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getBaseFareBasis());
            		}else if(header[i].contentEquals("Base Rule No")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
            		}else if(header[i].contentEquals("Base Tariff Code")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getBaseTarcd());
            		}else if(header[i].contentEquals("Calc Type")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getCalcType());
            		}else if(header[i].contentEquals("% of Base Fare")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getPercentBaseFare());
            		}else if(header[i].contentEquals("Currency")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getRuleno());
            		}else if(header[i].contentEquals("Specified Amount")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getDiscountSpecifiedAmount());
            		}else if(header[i].contentEquals("PAX Type")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getPassengerType());
            		}else if(header[i].contentEquals("Fare Type")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getFareType());
            		}else if(header[i].contentEquals("Tkt Code")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTicketCode());
            		}else if(header[i].contentEquals("Tkt Des")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTicketDesignator());
            		}else if(header[i].contentEquals("Base Fare OW/RT")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTypeOfJourney());
            		}else if(header[i].contentEquals("Global")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getGlobal());
            		}else if(header[i].contentEquals("Rtg No")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getRtgno());
            		}else if(header[i].contentEquals("Rtg No Tarno")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getRtgnoTarno());
            		}else if(header[i].contentEquals("New FareCls")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getNewFareBasis());
            		}else if(header[i].contentEquals("New OW/RT")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getNewTypeOfJourney());
            		}else if(header[i].contentEquals("New BkgCd")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getNewBookingCode());
            		}else if(header[i].contentEquals("Travel Start")) {
            			try {
                			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTravelStart().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Travel End")) {
            			try {
                			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTravelEnd().toString());
							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Sales Start")) {
            			try {
                			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getSaleStart().toString());
							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Sales End")) {
            			try {
                			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getSaleEnd().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Comment")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getComment());
            		}else if(header[i].contentEquals("Travel Complete")) {
            			try {
                			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTravelComplete().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.addCell("-");
						}
            		}else if(header[i].contentEquals("Travel Complete Indicator")) {
            			table.addCell(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTravelCompleteIndicator());
            		}
            		else {
            			table.addCell("-");
            		}
            	}        		
        	}
        	
          document.add(table);  
        
	
        p6.setFont(font);               
        p6.add(content[5]+" "+ruleText);
    	document.add(p6);
    	document.add(new Chunk(" "));

    	document.close();
        
        Attachment att = new Attachment();
        att.setFile(output.toByteArray());
        
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
            .body(att);
    }
    
    /**
     * POST  /work-packages/export-ratesheet-excel : Export work package fares
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws DocumentException 
     */
    @PostMapping("/work-packages/export-ratesheet-excel-discount")
    @Timed
    public ResponseEntity<Attachment> exportRateSheetWorkPackageExcelDiscount(@RequestBody WorkPackageRateSheet wprs) throws URISyntaxException, MalformedURLException, IOException, DocumentException {
    	log.debug("REST request to save exportFaresExcel : {}{}", wprs.getWp(), wprs.getRuleText());
    	
    	WorkPackage workPackage = wprs.getWp();
        String ruleText = wprs.getRuleText();
        int idx = Integer.parseInt(wprs.getIndex());
        String[] header = wprs.getHeader();
    	
    	XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet spreadsheet = workbook.createSheet("Rate Sheet");
        
        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell;
        
        cell = row.createCell(0);
        cell.setCellValue("WORK ID :");
        cell = row.createCell(1);
        cell.setCellValue(workPackage.getWpid());
        
        XSSFRow row2 = spreadsheet.createRow(2);
        XSSFCell cell2;
        cell2 = row2.createCell(0);
        cell2.setCellValue("TITLE FARE SHEET :");
        cell2 = row2.createCell(1);
        cell2.setCellValue(workPackage.getName());
        
		 XSSFRow row3 = spreadsheet.createRow(3);
	     XSSFCell cell3;
	     cell3 = row3.createCell(0);
	     cell3.setCellValue("FARE NAME :");
	     cell3 = row3.createCell(1);
	     cell3.setCellValue(workPackage.getDiscountFareSheet().get(idx).getDiscountFaresName());
          
        
        XSSFRow row4 = spreadsheet.createRow(4);
        XSSFCell cell4;
        cell4 = row4.createCell(0);
        cell4.setCellValue("NOTES RATESHEET : ");
        cell4 = row4.createCell(1);
        cell4.setCellValue(workPackage.getRatesheetComment());
        
        XSSFRow row5 = spreadsheet.createRow(5);
        XSSFCell cell5;
        for(int l=0; l<header.length ;l++) {
        	cell5 = row5.createCell(l);
			cell5.setCellValue(header[l]);
		}
        
    
    	for(int l=0; l<workPackage.getDiscountFareSheet().get(idx).getFares().size();l++) {
        	XSSFRow rows = spreadsheet.createRow(l+6);
    		for (int i=0;i<header.length;i++){
    			cell = rows.createCell(i);
    	
    			if(header[i].contentEquals("Status")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getStatus());
        		}else if(header[i].contentEquals("FBR Tariff Code")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTarcd());
        		}else if(header[i].contentEquals("Loc 1 Type")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getLoc1Type());       			
        		}else if(header[i].contentEquals("Loc 1")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getLoc1());
        		}else if(header[i].contentEquals("Loc 2 Type")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getLoc2Type());
        		}else if(header[i].contentEquals("Loc 2")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getLoc2());
        		}else if(header[i].contentEquals("Base FareCls")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getBaseFareBasis());
        		}else if(header[i].contentEquals("Base Rule No")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
        		}else if(header[i].contentEquals("Base Tariff Code")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getBaseTarcd());
        		}else if(header[i].contentEquals("Calc Type")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getCalcType());
        		}else if(header[i].contentEquals("% of Base Fare")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getPercentBaseFare());
        		}else if(header[i].contentEquals("Currency")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getRuleno());
        		}else if(header[i].contentEquals("Specified Amount")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getDiscountSpecifiedAmount());
        		}else if(header[i].contentEquals("PAX Type")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getPassengerType());
        		}else if(header[i].contentEquals("Fare Type")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getFareType());
        		}else if(header[i].contentEquals("Tkt Code")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTicketCode());
        		}else if(header[i].contentEquals("Tkt Des")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTicketDesignator());
        		}else if(header[i].contentEquals("Base Fare OW/RT")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTypeOfJourney());
        		}else if(header[i].contentEquals("Global")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getGlobal());
        		}else if(header[i].contentEquals("Rtg No")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getRtgno());
        		}else if(header[i].contentEquals("Rtg No Tarno")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getRtgnoTarno());
        		}else if(header[i].contentEquals("New FareCls")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getNewFareBasis());
        		}else if(header[i].contentEquals("New OW/RT")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getNewTypeOfJourney());
        		}else if(header[i].contentEquals("New BkgCd")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getNewBookingCode());
        		}else if(header[i].contentEquals("Travel Start")) {
        			try {
            			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTravelStart().toString());							
					} catch (Exception e) {
						// TODO: handle exception
						cell.setCellValue("-");
					}
        		}else if(header[i].contentEquals("Travel End")) {
        			try {
            			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTravelEnd().toString());
						
					} catch (Exception e) {
						// TODO: handle exception
						cell.setCellValue("-");
					}
        		}else if(header[i].contentEquals("Sales Start")) {
        			try {
            			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getSaleStart().toString());
						
					} catch (Exception e) {
						// TODO: handle exception
						cell.setCellValue("-");
					}
        		}else if(header[i].contentEquals("Sales End")) {
        			try {
            			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getSaleEnd().toString());							
					} catch (Exception e) {
						// TODO: handle exception
						cell.setCellValue("-");
					}
        		}else if(header[i].contentEquals("Comment")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getComment());
        		}else if(header[i].contentEquals("Travel Complete")) {
        			try {
            			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTravelComplete().toString());							
					} catch (Exception e) {
						// TODO: handle exception
						cell.setCellValue("-");
					}
        		}else if(header[i].contentEquals("Travel Complete Indicator")) {
        			cell.setCellValue(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTravelCompleteIndicator());
        		}
    			
    			
    			
    		}
        }
        
        
        XSSFRow rowRuleText = spreadsheet.createRow(workPackage.getDiscountFareSheet().get(idx).getFares().size()+7);
        XSSFCell cellRuleText;
        cellRuleText = rowRuleText.createCell(0);
        cellRuleText.setCellValue("RULE TEXT : ");
        cellRuleText = rowRuleText.createCell(1);
        cellRuleText.setCellValue(ruleText);
        
        
        
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
			workbook.write(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Attachment att = new Attachment();
        att.setFile(output.toByteArray());
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
            .body(att);
    }
        
    /**
     * POST  /work-packages/export-fares-discount : Export work package fares-discount
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws DocumentException 
     */
    @PostMapping("/work-packages/export-ratesheet-waiver")
    @Timed
    public ResponseEntity<Attachment> exportRateSheetWorkPackageWaiver(@RequestBody WorkPackageRateSheet wprs) throws URISyntaxException, MalformedURLException, IOException, DocumentException {
    	log.debug("REST request to save exportFares-discount : {}{}", wprs.getWp(), wprs.getRuleText());
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        WorkPackage workPackage = wprs.getWp();
        String ruleText = wprs.getRuleText();
        int idx = Integer.parseInt(wprs.getIndex());
        String[] header = wprs.getHeader();
        
        Integer count = header.length;
        PdfPTable table;
        Document document;
        if(count < 25) {
        	document = new Document(PageSize.A4,30,30,60,0);
    	}else {
    		document = new Document(PageSize.A4.rotate());
    		document.setMargins(60, 30, 30, 30);
    	}
             
    	PdfWriter.getInstance(document, output);    	
    	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("images/logo_ga.png");
    	byte[] imageByte = IOUtils.toByteArray(inputStream);    	
    	document.open();
    	Font font = FontFactory.getFont(FontFactory.TIMES_BOLD, 12, BaseColor.BLACK);
    	Image image = Image.getInstance(imageByte); 
    	image.scaleToFit(PageSize.A4.getWidth()/4, PageSize.A4.getHeight()/4);
    	image.setAlignment(Image.MIDDLE);
    	String[] content = {"WORK ID : ","TITLE FARE SHEET : ", "FARE NAME : ","NOTES RATESHEET : ", "FARES : ", "RULE TEXT : "};
    	document.add(image); 
    	document.add(new Chunk(" "));

        Paragraph p1 = new Paragraph();
        Paragraph p2 = new Paragraph();
        Paragraph p3 = new Paragraph();
        Paragraph p4 = new Paragraph();
        Paragraph p5 = new Paragraph();
        Paragraph p6 = new Paragraph();

        p1.setFont(font);               
        p1.add(content[0]+" "+ workPackage.getWpid());        
    	document.add(p1);
    	document.add(new Chunk(" "));
        p2.setFont(font);               
        p2.add(content[1]+" "+workPackage.getName());
        document.add(p2);
    	document.add(new Chunk(" "));

        p3.setFont(font);               
        p3.add(content[2]+" "+workPackage.getWaiverFareSheet().get(idx).getWaiverFaresName());
    	document.add(p3);
    	document.add(new Chunk(" "));

        p4.setFont(font);               
        p4.add(content[3]+" "+workPackage.getRatesheetComment());
    	document.add(p4);
    	document.add(new Chunk(" "));

        p5.setFont(font);               
        p5.add(content[4]);
    	document.add(p5);
    	document.add(new Chunk(" "));

    	table = new PdfPTable(count);
		table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
		for(int l=0; l<header.length ;l++) {
			table.addCell(header[l]);
		}
		table.setHeaderRows(1);
        
		
		PdfPCell[] cells = table.getRow(0).getCells(); 
    	for (int j=0;j<cells.length;j++){
    			cells[j].setBackgroundColor(BaseColor.GRAY);
    	}
        	log.debug("cek discount : "+workPackage.getDiscountFareSheet().size());
        	for(int l=0; l<workPackage.getWaiverFareSheet().get(idx).getFares().size();l++) {
        		for (int i=0;i<header.length;i++){
            		if(header[i].contentEquals("Type")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverType());
            		}else if(header[i].contentEquals("Full/Partial")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverFullPartial());
            		}else if(header[i].contentEquals("PNR")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverPnr());       			
            		}else if(header[i].contentEquals("Tkt From")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverTktFrom());
            		}else if(header[i].contentEquals("Tkt To")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverTktTo());
            		}else if(header[i].contentEquals("Ori")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverOri());
            		}else if(header[i].contentEquals("Dest")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverDest());
            		}else if(header[i].contentEquals("Original Itinerary")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverOriginalItinerary());
            		}else if(header[i].contentEquals("New Itinerary")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverNewItinerary());
            		}else if(header[i].contentEquals("Original Basic Fare")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverOriginalBasicFare());
            		}else if(header[i].contentEquals("New Basic Fare")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverNewBasicFare());
            		}else if(header[i].contentEquals("Approved Fares")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverApprovedFare());
            		}else if(header[i].contentEquals("Fare Lost")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverFareLost());
            		}else if(header[i].contentEquals("Calculated PN")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverCalculatedPn());
            		}else if(header[i].contentEquals("Original PN")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverOriginalPn());
            		}else if(header[i].contentEquals("Approved PN")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverApprovedPn());
            		}else if(header[i].contentEquals("Penalty Lost %")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverPenaltyLostPercent());
            		}else if(header[i].contentEquals("Penalty Lost Amount")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverPenaltyLostAmount());
            		}else if(header[i].contentEquals("Currency")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverCurrency());
            		}else if(header[i].contentEquals("Total Pax")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverTotalPax());
            		}else if(header[i].contentEquals("Total Lost")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverTotalLost());
            		}else if(header[i].contentEquals("Approver")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverApprover());
            		}else if(header[i].contentEquals("Remark")) {
            			table.addCell(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverRemark());
            		}
            		else {
            			table.addCell("-");
            		}
            	}        		
        	}
        	
          document.add(table);  
        
	
        p6.setFont(font);               
        p6.add(content[5]+" "+ruleText);
    	document.add(p6);
    	document.add(new Chunk(" "));

    	document.close();
        
        Attachment att = new Attachment();
        att.setFile(output.toByteArray());
        
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
            .body(att);
    }
    
    /**
     * POST  /work-packages/export-ratesheet-excel : Export work package fares
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws DocumentException 
     */
    @PostMapping("/work-packages/export-ratesheet-excel-waiver")
    @Timed
    public ResponseEntity<Attachment> exportRateSheetWorkPackageExcelWaiver(@RequestBody WorkPackageRateSheet wprs) throws URISyntaxException, MalformedURLException, IOException, DocumentException {
    	log.debug("REST request to save exportFaresExcel : {}{}", wprs.getWp(), wprs.getRuleText());
    	
    	WorkPackage workPackage = wprs.getWp();
        String ruleText = wprs.getRuleText();
        int idx = Integer.parseInt(wprs.getIndex());
        String[] header = wprs.getHeader();
    	
    	XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet spreadsheet = workbook.createSheet("Rate Sheet");
        
        XSSFRow row = spreadsheet.createRow(1);
        XSSFCell cell;
        
        cell = row.createCell(0);
        cell.setCellValue("WORK ID :");
        cell = row.createCell(1);
        cell.setCellValue(workPackage.getWpid());
        
        XSSFRow row2 = spreadsheet.createRow(2);
        XSSFCell cell2;
        cell2 = row2.createCell(0);
        cell2.setCellValue("TITLE FARE SHEET :");
        cell2 = row2.createCell(1);
        cell2.setCellValue(workPackage.getName());
        
		 XSSFRow row3 = spreadsheet.createRow(3);
	     XSSFCell cell3;
	     cell3 = row3.createCell(0);
	     cell3.setCellValue("FARE NAME :");
	     cell3 = row3.createCell(1);
	     cell3.setCellValue(workPackage.getWaiverFareSheet().get(idx).getDiscountFaresName());
          
        
        XSSFRow row4 = spreadsheet.createRow(4);
        XSSFCell cell4;
        cell4 = row4.createCell(0);
        cell4.setCellValue("NOTES RATESHEET : ");
        cell4 = row4.createCell(1);
        cell4.setCellValue(workPackage.getRatesheetComment());
        
        XSSFRow row5 = spreadsheet.createRow(5);
        XSSFCell cell5;
        for(int l=0; l<header.length ;l++) {
        	cell5 = row5.createCell(l);
			cell5.setCellValue(header[l]);
		}
        
    
    	for(int l=0; l<workPackage.getWaiverFareSheet().get(idx).getFares().size();l++) {
        	XSSFRow rows = spreadsheet.createRow(l+6);
    		for (int i=0;i<header.length;i++){
    			cell = rows.createCell(i);
    	
    			if(header[i].contentEquals("Type")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverType());
        		}else if(header[i].contentEquals("Full/Partial")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverFullPartial());
        		}else if(header[i].contentEquals("PNR")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverPnr());       			
        		}else if(header[i].contentEquals("Tkt From")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverTktFrom());
        		}else if(header[i].contentEquals("Tkt To")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverTktTo());
        		}else if(header[i].contentEquals("Ori")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverOri());
        		}else if(header[i].contentEquals("Dest")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverDest());
        		}else if(header[i].contentEquals("Original Itinerary")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverOriginalItinerary());
        		}else if(header[i].contentEquals("New Itinerary")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverNewItinerary());
        		}else if(header[i].contentEquals("Original Basic Fare")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverOriginalBasicFare());
        		}else if(header[i].contentEquals("New Basic Fare")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverNewBasicFare());
        		}else if(header[i].contentEquals("Approved Fares")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverApprovedFare());
        		}else if(header[i].contentEquals("Fare Lost")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverFareLost());
        		}else if(header[i].contentEquals("Calculated PN")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverCalculatedPn());
        		}else if(header[i].contentEquals("Original PN")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverOriginalPn());
        		}else if(header[i].contentEquals("Approved PN")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverApprovedPn());
        		}else if(header[i].contentEquals("Penalty Lost %")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverPenaltyLostPercent());
        		}else if(header[i].contentEquals("Penalty Lost Amount")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverPenaltyLostAmount());
        		}else if(header[i].contentEquals("Currency")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverCurrency());
        		}else if(header[i].contentEquals("Total Pax")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverTotalPax());
        		}else if(header[i].contentEquals("Total Lost")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverTotalLost());
        		}else if(header[i].contentEquals("Approver")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverApprover());
        		}else if(header[i].contentEquals("Remark")) {
        			cell.setCellValue(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverRemark());
        		}  			
    			    			
    		}
        }
        
        
        XSSFRow rowRuleText = spreadsheet.createRow(workPackage.getWaiverFareSheet().get(idx).getFares().size()+7);
        XSSFCell cellRuleText;
        cellRuleText = rowRuleText.createCell(0);
        cellRuleText.setCellValue("RULE TEXT : ");
        cellRuleText = rowRuleText.createCell(1);
        cellRuleText.setCellValue(ruleText);
        
        
        
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
			workbook.write(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Attachment att = new Attachment();
        att.setFile(output.toByteArray());
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
            .body(att);
    }
    
    /**
     * POST  /work-packages/export-ratesheet-word : Export work package fares
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws DocumentException 
     * @throws org.apache.poi.openxml4j.exceptions.InvalidFormatException 
     */
    @PostMapping("/work-packages/export-ratesheet-word")
    @Timed
    public ResponseEntity<Attachment> exportRateSheetWorkPackageWord(@RequestBody WorkPackageRateSheet wprs) throws URISyntaxException, MalformedURLException, IOException, DocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
    	log.debug("REST request to save exportFaresWord : {}{}", wprs.getWp(), wprs.getRuleText());
    	
    	WorkPackage workPackage = wprs.getWp();
        String ruleText = wprs.getRuleText();
        int idx = Integer.parseInt(wprs.getIndex());
        String[] header = wprs.getHeader();
        
        XWPFDocument document = new XWPFDocument();  
        
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("images/logo_ga.png"); 	
    	
        XWPFParagraph img_header = document.createParagraph();
        img_header.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun paragraphFiveRunOne = img_header.createRun();
        paragraphFiveRunOne.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_JPEG, "logo_ga.png", Units.toEMU(50), Units.toEMU(50));
        
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun title = paragraph.createRun();
        title.setText("Work ID : "+workPackage.getWpid());
        title.addBreak();
        
        XWPFRun name = paragraph.createRun();
        name.setText("Title Fare Sheet : "+workPackage.getName());
        name.addBreak();
        
        XWPFRun specname = paragraph.createRun();
        specname.setText("Specified Name : "+workPackage.getFareSheet().get(idx).getSpecifiedFaresName());
        specname.addBreak();
        
        XWPFRun comment = paragraph.createRun();
        comment.setText("Ratesheet Comment : "+workPackage.getRatesheetComment());
        comment.addBreak();
        
        XWPFTable table = document.createTable();
        
        for(int l=0; l<header.length ;l++) {
        	XWPFTableRow header_title = table.getRow(0);
        	if(l==0) {
        		header_title.getCell(0).setText(header[0]);
        	}else {
        		header_title.addNewTableCell().setText(header[l]);
        	}
        	table.getRow(0).getCell(l).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
		}
        
        if(workPackage.getTargetDistribution().contentEquals("ATPCO")) {
        	for(int l=0; l<workPackage.getFareSheet().get(idx).getFares().size();l++) {  
    			XWPFTableRow row = table.createRow(); 
        		for (int i=0;i<header.length;i++){
        			if(header[i].contentEquals("Status")) {
        				table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getStatus());
            		}else if(header[i].contentEquals("Carrier")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getCarrier());
            		}else if(header[i].contentEquals("Action")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getAction());
            		}else if(header[i].contentEquals("Tar No")) {
            			try {
            				table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getTariffNumber().getTarNo());
    					} catch (Exception e) {
    						// TODO: handle exception
    						table.getRow(l+1).getCell(i).setText("-");
    					}            			
            		}else if(header[i].contentEquals("Tar Cd")) {
            			try {
            				table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getTariffNumber().getTarCd());
    					} catch (Exception e) {
    						// TODO: handle exception
    						table.getRow(l+1).getCell(i).setText("-");
    					}            			
            		}else if(header[i].contentEquals("Global")) {
            			try {
            				table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getTariffNumber().getGlobal());
    					} catch (Exception e) {
    						// TODO: handle exception
    						table.getRow(l+1).getCell(i).setText("-");
    					}            			
            		}else if(header[i].contentEquals("Origin")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getOrigin());
            		}else if(header[i].contentEquals("Destination")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getDestination());
            		}else if(header[i].contentEquals("Fare Class")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getFareBasis());
            		}else if(header[i].contentEquals("Booking Class")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getBookingClass());
            		}else if(header[i].contentEquals("Cabin")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getCabin());
            		}else if(header[i].contentEquals("OW/RT")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getTypeOfJourney());
            		}else if(header[i].contentEquals("Footnote")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getFootnote1());
            		}else if(header[i].contentEquals("Routing No")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getRtgno());
            		}else if(header[i].contentEquals("Rule No")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getRuleno());
            		}else if(header[i].contentEquals("Currency")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getCurrency());
            		}else if(header[i].contentEquals("Base Amt")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getAmount());
            		}else if(header[i].contentEquals("Amt Different")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
            		}else if(header[i].contentEquals("% Amt Different")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
            		}else if(header[i].contentEquals("YQYR")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getYqyr());
            		}else if(header[i].contentEquals("Cat 12")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getCat12());
            		}else if(header[i].contentEquals("TFC")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getTfc());
            		}else if(header[i].contentEquals("Target AIF")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getAif());
            		}else if(header[i].contentEquals("Itinerary")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getItinerary());
            		}else if(header[i].contentEquals("Override Indicator")) {
            			try {
                			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getOverrideIndicator().toString());							
    					} catch (Exception e) {
    						// TODO: handle exception
    						table.getRow(l+1).getCell(i).setText("-");
    					}
            		}else if(header[i].contentEquals("Travel Start")) {
            			try {
                			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getTravelStart().toString());							
    					} catch (Exception e) {
    						// TODO: handle exception
    						table.getRow(l+1).getCell(i).setText("-");
    					}
            		}else if(header[i].contentEquals("Travel End")) {
            			try {
                			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getTravelEnd().toString());
    						
    					} catch (Exception e) {
    						// TODO: handle exception
    						table.getRow(l+1).getCell(i).setText("-");
    					}
            		}else if(header[i].contentEquals("Sales Start")) {
            			try {
                			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getSaleStart().toString());
    						
    					} catch (Exception e) {
    						// TODO: handle exception
    						table.getRow(l+1).getCell(i).setText("-");
    					}
            		}else if(header[i].contentEquals("Sales End")) {
            			try {
                			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getSaleEnd().toString());							
    					} catch (Exception e) {
    						// TODO: handle exception
    						table.getRow(l+1).getCell(i).setText("-");
    					}
            		}else if(header[i].contentEquals("EffDt")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getEffDt());
            		}else if(header[i].contentEquals("Comment")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getComment());
            		}else if(header[i].contentEquals("Travel Complete")) {
            			try {
                			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getTravelComplete().toString());							
    					} catch (Exception e) {
    						// TODO: handle exception
    						table.getRow(l+1).getCell(i).setText("-");
    					}
            		}else if(header[i].contentEquals("Travel Complete Indicator")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getTravelCompleteIndicator());
            		}else if(header[i].contentEquals("RateSheet Comment")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getFareSheet().get(idx).getFares().get(l).getRatesheetComment());
            		}
    				table.getRow(l+1).getCell(i).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
        		}
            }
        }else if(workPackage.getTargetDistribution().contentEquals("MARKET")){
        	XWPFTableRow row = table.createRow(); 
        	for(int l=0; l<workPackage.getMarketFareSheet().get(idx).getFares().size();l++) {
        		for (int i=0;i<header.length;i++){
            		if(header[i].contentEquals("Status")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getStatus());
            		}else if(header[i].contentEquals("Carrier")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getCarrier());
            		}else if(header[i].contentEquals("Action")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getAction());
            		}else if(header[i].contentEquals("Tar No")) {
            			try {
            				table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTariffNumber().getTarNo());
						} catch (Exception e) {
							// TODO: handle exception
							table.getRow(l+1).getCell(i).setText("-");
						}            			
            		}else if(header[i].contentEquals("Tar Cd")) {
            			try {
            				table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTariffNumber().getTarCd());
						} catch (Exception e) {
							// TODO: handle exception
							table.getRow(l+1).getCell(i).setText("-");
						}            			
            		}else if(header[i].contentEquals("Global")) {
            			try {
            				table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTariffNumber().getGlobal());
						} catch (Exception e) {
							// TODO: handle exception
							table.getRow(l+1).getCell(i).setText("-");
						}            			
            		}else if(header[i].contentEquals("Origin")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getOrigin());
            		}else if(header[i].contentEquals("Destination")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getDestination());
            		}else if(header[i].contentEquals("Fare Class")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getFareBasis());
            		}else if(header[i].contentEquals("Booking Class")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getBookingClass());
            		}else if(header[i].contentEquals("Cabin")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getCabin());
            		}else if(header[i].contentEquals("OW/RT")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTypeOfJourney());
            		}else if(header[i].contentEquals("Footnote")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getFootnote1());
            		}else if(header[i].contentEquals("Routing No")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getRtgno());
            		}else if(header[i].contentEquals("Rule No")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getRuleno());
            		}else if(header[i].contentEquals("Currency")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getCurrency());
            		}else if(header[i].contentEquals("Base Amt")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getAmount());
            		}else if(header[i].contentEquals("Amt Different")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
            		}else if(header[i].contentEquals("% Amt Different")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
            		}else if(header[i].contentEquals("YQYR")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getYqyr());
            		}else if(header[i].contentEquals("Cat 12")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getCat12());
            		}else if(header[i].contentEquals("TFC")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTfc());
            		}else if(header[i].contentEquals("Target AIF")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getAif());
            		}else if(header[i].contentEquals("Itinerary")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getItinerary());
            		}else if(header[i].contentEquals("Override Indicator")) {
            			try {
                			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getOverrideIndicator().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.getRow(l+1).getCell(i).setText("-");
						}
            		}else if(header[i].contentEquals("Travel Start")) {
            			try {
                			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTravelStart().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.getRow(l+1).getCell(i).setText("-");
						}
            		}else if(header[i].contentEquals("Travel End")) {
            			try {
                			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTravelEnd().toString());
							
						} catch (Exception e) {
							// TODO: handle exception
							table.getRow(l+1).getCell(i).setText("-");
						}
            		}else if(header[i].contentEquals("Sales Start")) {
            			try {
                			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getSaleStart().toString());
							
						} catch (Exception e) {
							// TODO: handle exception
							table.getRow(l+1).getCell(i).setText("-");
						}
            		}else if(header[i].contentEquals("Sales End")) {
            			try {
                			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getSaleEnd().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.getRow(l+1).getCell(i).setText("-");
						}
            		}else if(header[i].contentEquals("EffDt")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getEffDt());
            		}else if(header[i].contentEquals("Comment")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getComment());
            		}else if(header[i].contentEquals("Travel Complete")) {
            			try {
                			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTravelComplete().toString());							
						} catch (Exception e) {
							// TODO: handle exception
							table.getRow(l+1).getCell(i).setText("-");
						}
            		}else if(header[i].contentEquals("Travel Complete Indicator")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getTravelCompleteIndicator());
            		}else if(header[i].contentEquals("RateSheet Comment")) {
            			table.getRow(l+1).getCell(i).setText(workPackage.getMarketFareSheet().get(idx).getFares().get(l).getRatesheetComment());
            		}
            		else {
            			table.getRow(l+1).getCell(i).setText("-");
            		}
            	}        		
        	}
        }
        

   	 XWPFRun ruletext = paragraph.createRun();
   	 ruletext.setText("Rule Text : "+ruleText);
   	 ruletext.addBreak();
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
        	document.write(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	document.close();
    	
    	Attachment att = new Attachment();
    	att.setFile(output.toByteArray());
    	return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
                .body(att);
    }
    
    /**
     * POST  /work-packages/export-ratesheet-discount-word : Export work package fares
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws DocumentException 
     * @throws org.apache.poi.openxml4j.exceptions.InvalidFormatException 
     */
    @PostMapping("/work-packages/export-ratesheet-word-discount")
    @Timed
    public ResponseEntity<Attachment> exportRateSheetWorkPackageWordDiscount(@RequestBody WorkPackageRateSheet wprs) throws URISyntaxException, MalformedURLException, IOException, DocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
    	log.debug("REST request to save exportFaresWord : {}{}", wprs.getWp(), wprs.getRuleText());
    	
    	WorkPackage workPackage = wprs.getWp();
        String ruleText = wprs.getRuleText();
        int idx = Integer.parseInt(wprs.getIndex());
        String[] header = wprs.getHeader();
        
        XWPFDocument document = new XWPFDocument();   
        
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("images/logo_ga.png"); 	
    	
        XWPFParagraph img_header = document.createParagraph();
        img_header.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun paragraphFiveRunOne = img_header.createRun();
        paragraphFiveRunOne.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_JPEG, "logo_ga.png", Units.toEMU(50), Units.toEMU(50));    
        
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun title = paragraph.createRun();
        title.setText("Work ID : "+workPackage.getWpid());
        title.addBreak();
        
        XWPFRun name = paragraph.createRun();
        name.setText("Title Fare Sheet : "+workPackage.getName());
        name.addBreak();
        
        XWPFRun specname = paragraph.createRun();
        specname.setText("Specified Name : "+workPackage.getDiscountFareSheet().get(idx).getDiscountFaresName());
        specname.addBreak();
        
        XWPFRun comment = paragraph.createRun();
        comment.setText("Ratesheet Comment : "+workPackage.getRatesheetComment());
        comment.addBreak();
        
        XWPFTable table = document.createTable();
        
        for(int l=0; l<header.length ;l++) {
        	XWPFTableRow header_title = table.getRow(0);
        	if(l==0) {
        		header_title.getCell(0).setText(header[0]);
        	}else {
        		header_title.addNewTableCell().setText(header[l]);
        	}
        	table.getRow(0).getCell(l).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
		}
        

    	for(int l=0; l<workPackage.getDiscountFareSheet().get(idx).getFares().size();l++) {  
			XWPFTableRow row = table.createRow(); 
    		for (int i=0;i<header.length;i++){
    			if(header[i].contentEquals("Status")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getStatus());
        		}else if(header[i].contentEquals("FBR Tariff Code")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTarcd());
        		}else if(header[i].contentEquals("Loc 1 Type")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getLoc1Type());       			
        		}else if(header[i].contentEquals("Loc 1")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getLoc1());
        		}else if(header[i].contentEquals("Loc 2 Type")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getLoc2Type());
        		}else if(header[i].contentEquals("Loc 2")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getLoc2());
        		}else if(header[i].contentEquals("Base FareCls")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getBaseFareBasis());
        		}else if(header[i].contentEquals("Base Rule No")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getBaseRuleNo());
        		}else if(header[i].contentEquals("Base Tariff Code")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getBaseTarcd());
        		}else if(header[i].contentEquals("Calc Type")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getCalcType());
        		}else if(header[i].contentEquals("% of Base Fare")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getPercentBaseFare());
        		}else if(header[i].contentEquals("Currency")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getRuleno());
        		}else if(header[i].contentEquals("Specified Amount")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getDiscountSpecifiedAmount());
        		}else if(header[i].contentEquals("PAX Type")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getPassengerType());
        		}else if(header[i].contentEquals("Fare Type")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getFareType());
        		}else if(header[i].contentEquals("Tkt Code")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTicketCode());
        		}else if(header[i].contentEquals("Tkt Des")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTicketDesignator());
        		}else if(header[i].contentEquals("Base Fare OW/RT")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTypeOfJourney());
        		}else if(header[i].contentEquals("Global")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getGlobal());
        		}else if(header[i].contentEquals("Rtg No")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getRtgno());
        		}else if(header[i].contentEquals("Rtg No Tarno")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getRtgnoTarno());
        		}else if(header[i].contentEquals("New FareCls")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getNewFareBasis());
        		}else if(header[i].contentEquals("New OW/RT")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getNewTypeOfJourney());
        		}else if(header[i].contentEquals("New BkgCd")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getNewBookingCode());
        		}else if(header[i].contentEquals("Travel Start")) {
        			try {
            			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTravelStart().toString());							
					} catch (Exception e) {
						// TODO: handle exception
						table.getRow(l+1).getCell(i).setText("-");
					}
        		}else if(header[i].contentEquals("Travel End")) {
        			try {
            			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTravelEnd().toString());
						
					} catch (Exception e) {
						// TODO: handle exception
						table.getRow(l+1).getCell(i).setText("-");
					}
        		}else if(header[i].contentEquals("Sales Start")) {
        			try {
            			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getSaleStart().toString());
						
					} catch (Exception e) {
						// TODO: handle exception
						table.getRow(l+1).getCell(i).setText("-");
					}
        		}else if(header[i].contentEquals("Sales End")) {
        			try {
            			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getSaleEnd().toString());							
					} catch (Exception e) {
						// TODO: handle exception
						table.getRow(l+1).getCell(i).setText("-");
					}
        		}else if(header[i].contentEquals("Comment")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getComment());
        		}else if(header[i].contentEquals("Travel Complete")) {
        			try {
            			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTravelComplete().toString());							
					} catch (Exception e) {
						// TODO: handle exception
						table.getRow(l+1).getCell(i).setText("-");
					}
        		}else if(header[i].contentEquals("Travel Complete Indicator")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getDiscountFareSheet().get(idx).getFares().get(l).getTravelCompleteIndicator());
        		}
				table.getRow(l+1).getCell(i).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
    		}
        }
        
    	 XWPFRun ruletext = paragraph.createRun();
    	 ruletext.setText("Rule Text : "+ruleText);
    	 ruletext.addBreak();
    	 
    	 
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
        	document.write(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	document.close();
    	
    	Attachment att = new Attachment();
    	att.setFile(output.toByteArray());
    	return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
                .body(att);
    }
    
    /**
     * POST  /work-packages/export-ratesheet-waiver-word : Export work package fares
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws DocumentException 
     * @throws org.apache.poi.openxml4j.exceptions.InvalidFormatException 
     */
    @PostMapping("/work-packages/export-ratesheet-word-waiver")
    @Timed
    public ResponseEntity<Attachment> exportRateSheetWorkPackageWordWaiver(@RequestBody WorkPackageRateSheet wprs) throws URISyntaxException, MalformedURLException, IOException, DocumentException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
    	log.debug("REST request to save exportFaresWord : {}{}", wprs.getWp(), wprs.getRuleText());
    	
    	WorkPackage workPackage = wprs.getWp();
        String ruleText = wprs.getRuleText();
        int idx = Integer.parseInt(wprs.getIndex());
        String[] header = wprs.getHeader();
        
        XWPFDocument document = new XWPFDocument();       
                       
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("images/logo_ga.png"); 	
    	
        XWPFParagraph img_header = document.createParagraph();
        img_header.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun paragraphFiveRunOne = img_header.createRun();
        paragraphFiveRunOne.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_JPEG, "logo_ga.png", Units.toEMU(50), Units.toEMU(50));
        
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun title = paragraph.createRun();
        title.setText("Work ID : "+workPackage.getWpid());
        title.addBreak();
        
        XWPFRun name = paragraph.createRun();
        name.setText("Title Fare Sheet : "+workPackage.getName());
        name.addBreak();
        
        XWPFRun specname = paragraph.createRun();
        specname.setText("Specified Name : "+workPackage.getWaiverFareSheet().get(idx).getDiscountFaresName());
        specname.addBreak();
        
        XWPFRun comment = paragraph.createRun();
        comment.setText("Ratesheet Comment : "+workPackage.getRatesheetComment());
        comment.addBreak();
        
        XWPFTable table = document.createTable();
        
        for(int l=0; l<header.length ;l++) {
        	XWPFTableRow header_title = table.getRow(0);
        	if(l==0) {
        		header_title.getCell(0).setText(header[0]);
        	}else {
        		header_title.addNewTableCell().setText(header[l]);
        	}
        	table.getRow(0).getCell(l).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
		}
        

    	for(int l=0; l<workPackage.getWaiverFareSheet().get(idx).getFares().size();l++) {  
			XWPFTableRow row = table.createRow(); 
    		for (int i=0;i<header.length;i++){
    			if(header[i].contentEquals("Type")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverType());
        		}else if(header[i].contentEquals("Full/Partial")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverFullPartial());
        		}else if(header[i].contentEquals("PNR")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverPnr());       			
        		}else if(header[i].contentEquals("Tkt From")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverTktFrom());
        		}else if(header[i].contentEquals("Tkt To")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverTktTo());
        		}else if(header[i].contentEquals("Ori")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverOri());
        		}else if(header[i].contentEquals("Dest")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverDest());
        		}else if(header[i].contentEquals("Original Itinerary")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverOriginalItinerary());
        		}else if(header[i].contentEquals("New Itinerary")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverNewItinerary());
        		}else if(header[i].contentEquals("Original Basic Fare")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverOriginalBasicFare());
        		}else if(header[i].contentEquals("New Basic Fare")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverNewBasicFare());
        		}else if(header[i].contentEquals("Approved Fares")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverApprovedFare());
        		}else if(header[i].contentEquals("Fare Lost")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverFareLost());
        		}else if(header[i].contentEquals("Calculated PN")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverCalculatedPn());
        		}else if(header[i].contentEquals("Original PN")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverOriginalPn());
        		}else if(header[i].contentEquals("Approved PN")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverApprovedPn());
        		}else if(header[i].contentEquals("Penalty Lost %")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverPenaltyLostPercent());
        		}else if(header[i].contentEquals("Penalty Lost Amount")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverPenaltyLostAmount());
        		}else if(header[i].contentEquals("Currency")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverCurrency());
        		}else if(header[i].contentEquals("Total Pax")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverTotalPax());
        		}else if(header[i].contentEquals("Total Lost")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverTotalLost());
        		}else if(header[i].contentEquals("Approver")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverApprover());
        		}else if(header[i].contentEquals("Remark")) {
        			table.getRow(l+1).getCell(i).setText(workPackage.getWaiverFareSheet().get(idx).getFares().get(l).getWaiverRemark());
        		}  	
				table.getRow(l+1).getCell(i).getCTTc().addNewTcPr().addNewTcW().setW(BigInteger.valueOf(1000));
    		}
        }
        
    	 XWPFRun ruletext = paragraph.createRun();
    	 ruletext.setText("Rule Text : "+ruleText);
    	 ruletext.addBreak();
        
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
        	document.write(output);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        document.close();
        
    	Attachment att = new Attachment();
    	att.setFile(output.toByteArray());
    	return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, ""))
                .body(att);
    }
  
    /**
     * POST  /work-packages/update-latest-fare : Update Latest Fare
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/update-latest-fare")
    @Timed
    public ResponseEntity<WorkPackageFareSheet> updateLatestFare(@RequestBody WorkPackageFareSheet workPackageSheet) throws URISyntaxException {
        log.debug("REST request to update latest fare WorkPackageSheet : {}", workPackageSheet);
       
        List<WorkPackageFare> fares = workPackageSheet.getFares();
        for(WorkPackageFare fare : fares) {
	        Optional<AtpcoFare> checkAtpcoFare = atpcoFareRepository.findOneByCarrierCodeAndTariffNoAndOriginCityAndDestinationCity(fare.getCarrier(), fare.getTariffNumber() != null ? fare.getTariffNumber().getTarNo() : null, fare.getOrigin(), fare.getDestination());
			if(checkAtpcoFare.isPresent()) {
				float atpcoFareAmount = Float.parseFloat(checkAtpcoFare.get().getFareOriginAmount().bigDecimalValue().toString());
				fare.setAmount(String.valueOf(atpcoFareAmount));		
				fare.setAction("Y");      
				fare.setAmtDiff("0");
				fare.setAmtPercentDiff("0");
			}
			else {
//				fare.setAction("N");        		
			}
        }
        return ResponseEntity.ok().body(workPackageSheet);
    }
    
    
    /**
     * POST  /work-packages/update-action-codes : Update Action Codes
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/work-packages/update-action-codes")
    @Timed
    public ResponseEntity<WorkPackageFareSheet> updateActionCodes(@RequestBody WorkPackageFareSheet workPackageSheet) throws URISyntaxException {
        log.debug("REST request to update action code WorkPackageSheet : {}", workPackageSheet);
       
        List<WorkPackageFare> fares = workPackageSheet.getFares();
        for(WorkPackageFare fare : fares) {
        	Optional<AtpcoFare> checkAtpcoFare = atpcoFareRepository.findOneByCarrierCodeAndTariffNoAndOriginCityAndDestinationCity(fare.getCarrier(), fare.getTariffNumber() != null ? fare.getTariffNumber().getTarNo() : null, fare.getOrigin(), fare.getDestination());
    		if(checkAtpcoFare.isPresent()) {
    			//I, R, Y
    			if(fare.getAmount() != null) {
    				float atpcoFareAmount = Float.parseFloat(checkAtpcoFare.get().getFareOriginAmount().bigDecimalValue().toString());
    				float fareAmount = Float.parseFloat(fare.getAmount());
        			if(fareAmount < atpcoFareAmount) {
        				fare.setAction("R");
        			}
        			else if(fareAmount > atpcoFareAmount) {
        				fare.setAction("I");        				
        			}
        			else if(fareAmount== atpcoFareAmount){
        				fare.setAction("Y");        				        				
        			}
        			
        			float amtDiff = ((Float.parseFloat(fare.getAmount())) - Float.parseFloat(checkAtpcoFare.get().getFareOriginAmount().bigDecimalValue().toString()));
        			fare.setAmtDiff(String.format("%.02f",amtDiff));
        			
        			float percentDiff = (amtDiff / atpcoFareAmount) * 100;
        			fare.setAmtPercentDiff(String.format("%.02f", percentDiff));
    			}        			
    		}
    		else {
    			fare.setAction("N");        		
    		}
        }
        return ResponseEntity.ok().body(workPackageSheet);
    }
}

