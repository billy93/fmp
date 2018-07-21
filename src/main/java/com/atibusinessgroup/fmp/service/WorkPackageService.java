package com.atibusinessgroup.fmp.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.Counter;
import com.atibusinessgroup.fmp.domain.Priority;
import com.atibusinessgroup.fmp.domain.WorkPackage;
import com.atibusinessgroup.fmp.domain.WorkPackage.Attachment;
import com.atibusinessgroup.fmp.domain.WorkPackage.Comment;
import com.atibusinessgroup.fmp.domain.WorkPackage.FilingInstruction;
import com.atibusinessgroup.fmp.domain.WorkPackage.MarketRules;
import com.atibusinessgroup.fmp.domain.WorkPackage.MarketRules.RulesData;
import com.atibusinessgroup.fmp.domain.WorkPackage.MarketRules.RulesData.Field;
import com.atibusinessgroup.fmp.domain.WorkPackage.WorkPackageFareSheet;
import com.atibusinessgroup.fmp.domain.WorkPackageFare;
import com.atibusinessgroup.fmp.domain.WorkPackageFilter;
import com.atibusinessgroup.fmp.domain.atpco.AtpcoFare;
import com.atibusinessgroup.fmp.domain.enumeration.Status;
import com.atibusinessgroup.fmp.repository.AtpcoFareRepository;
import com.atibusinessgroup.fmp.repository.CounterRepository;
import com.atibusinessgroup.fmp.repository.PriorityRepository;
import com.atibusinessgroup.fmp.repository.WorkPackageRepository;
import com.atibusinessgroup.fmp.security.SecurityUtils;
import com.atibusinessgroup.fmp.web.rest.WorkPackageResource.WorkPackageQuery;


/**
 * Service Implementation for managing WorkPackage.
 */
@Service
public class WorkPackageService {

    private final Logger log = LoggerFactory.getLogger(WorkPackageService.class);

    private final WorkPackageRepository workPackageRepository;
    private final CounterRepository counterRepository;
    private final PriorityRepository priorityRepository;
    private final AtpcoFareRepository atpcoFareRepository;
    
    public WorkPackageService(WorkPackageRepository workPackageRepository, CounterRepository counterRepository, PriorityRepository priorityRepository, AtpcoFareRepository atpcoFareRepository) {
        this.workPackageRepository = workPackageRepository;
        this.counterRepository = counterRepository;
        this.priorityRepository = priorityRepository;
        this.atpcoFareRepository = atpcoFareRepository;
    }

    /**
     * Save a workPackage.
     *
     * @param workPackage the entity to save
     * @return the persisted entity
     */
    public WorkPackage save(WorkPackage workPackage) {
        log.debug("Request to save WorkPackage : {}", workPackage.toString());
        
        if(workPackage.getFareSheet() != null) {
	        for(WorkPackageFareSheet sheet : workPackage.getFareSheet()) {
	        	List<WorkPackageFare> fares = sheet.getFares();
	        	for(WorkPackageFare fare : fares) {
	//        		if(fare.getTravelStart() != null)
	//        			fare.setTravelStart(ZonedDateTime.ofInstant(fare.getTravelStart().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getTravelEnd() != null)
	//        			fare.setTravelEnd(ZonedDateTime.ofInstant(fare.getTravelEnd().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getSaleStart() != null)	            		
	//        			fare.setSaleStart(ZonedDateTime.ofInstant(fare.getSaleStart().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getSaleEnd() != null)
	//            		fare.setSaleEnd(ZonedDateTime.ofInstant(fare.getSaleEnd().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getTravelComplete() != null)
	//	        		fare.setTravelComplete(ZonedDateTime.ofInstant(fare.getTravelComplete().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	        		
	        		Optional<AtpcoFare> checkAtpcoFare = atpcoFareRepository.findOneByCarrierCodeAndTariffNoAndOriginCityAndDestinationCityAndFareOriginCurrencyCodeAndFareClassCodeAndOwrtAndFootnoteAndRoutingNoAndRuleNo(
	        				fare.getCarrier(), 
	        				fare.getTariffNumber() != null ? fare.getTariffNumber().getTarNo() : null, 
	        				fare.getOrigin(), 
	        				fare.getDestination(), 
	        				fare.getCurrency(), 
	        				fare.getFareBasis(), 
	        				fare.getTypeOfJourney(), 
	        				fare.getFootnote1(), 
	        				fare.getRtgno(), 
	        				fare.getRuleno());
	
	        		if(checkAtpcoFare.isPresent()) {
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
		        			fare.setAmtDiff(String.valueOf(amtDiff));
		        			
		        			float percentDiff = (amtDiff / atpcoFareAmount) * 100;
		        			fare.setAmtPercentDiff(String.valueOf(percentDiff));
	        			}        			
	        		}
	        		else {
	        			fare.setAction("N");        		
	        		}
	        		
	        		if(fare.getId() == null) {
	        			fare.setId(new ObjectId().toString());
	        		}
	        	}
	        }
        }
        
        if(workPackage.getAddonFareSheet() != null) {        
	        for(WorkPackageFareSheet sheet : workPackage.getAddonFareSheet()) {
	        	List<WorkPackageFare> fares = sheet.getFares();
	        	for(WorkPackageFare fare : fares) {
	//        		if(fare.getTravelStart() != null)
	//        			fare.setTravelStart(ZonedDateTime.ofInstant(fare.getTravelStart().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getTravelEnd() != null)
	//        			fare.setTravelEnd(ZonedDateTime.ofInstant(fare.getTravelEnd().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getSaleStart() != null)	            		
	//        			fare.setSaleStart(ZonedDateTime.ofInstant(fare.getSaleStart().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getSaleEnd() != null)
	//            		fare.setSaleEnd(ZonedDateTime.ofInstant(fare.getSaleEnd().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getTravelComplete() != null)
	//	        		fare.setTravelComplete(ZonedDateTime.ofInstant(fare.getTravelComplete().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));        		
	        		
	        		fare.setAction("N");
	        		if(fare.getId() == null) {
	        			fare.setId(new ObjectId().toString());
	        		}
	        	}
	        }
        }
        
        if(workPackage.getMarketFareSheet() != null) {
	        for(WorkPackageFareSheet sheet : workPackage.getMarketFareSheet()) {
	        	List<WorkPackageFare> fares = sheet.getFares();
	        	for(WorkPackageFare fare : fares) {
	//        		if(fare.getTravelStart() != null)
	//        			fare.setTravelStart(ZonedDateTime.ofInstant(fare.getTravelStart().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getTravelEnd() != null)
	//        			fare.setTravelEnd(ZonedDateTime.ofInstant(fare.getTravelEnd().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getSaleStart() != null)	            		
	//        			fare.setSaleStart(ZonedDateTime.ofInstant(fare.getSaleStart().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getSaleEnd() != null)
	//            		fare.setSaleEnd(ZonedDateTime.ofInstant(fare.getSaleEnd().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getTravelComplete() != null)
	//	        		fare.setTravelComplete(ZonedDateTime.ofInstant(fare.getTravelComplete().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	        		
	        		fare.setAction("N");   
	        		if(fare.getId() == null) {
	        			fare.setId(new ObjectId().toString());
	        		}
	        	}
	        }
        }
        
        if(workPackage.getDiscountFareSheet() != null) {
	        for(WorkPackageFareSheet sheet : workPackage.getDiscountFareSheet()) {
	        	List<WorkPackageFare> fares = sheet.getFares();
	        	for(WorkPackageFare fare : fares) {
	//        		if(fare.getTravelStart() != null)
	//        			fare.setTravelStart(ZonedDateTime.ofInstant(fare.getTravelStart().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getTravelEnd() != null)
	//        			fare.setTravelEnd(ZonedDateTime.ofInstant(fare.getTravelEnd().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getSaleStart() != null)	            		
	//        			fare.setSaleStart(ZonedDateTime.ofInstant(fare.getSaleStart().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getSaleEnd() != null)
	//            		fare.setSaleEnd(ZonedDateTime.ofInstant(fare.getSaleEnd().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getTravelComplete() != null)
	//	        		fare.setTravelComplete(ZonedDateTime.ofInstant(fare.getTravelComplete().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	        		
	        		if(fare.getId() == null) {
	        			fare.setId(new ObjectId().toString());
	        		}
	        	}
	        }
        }
        
        if(workPackage.getWaiverFareSheet() != null) {
	        for(WorkPackageFareSheet sheet : workPackage.getWaiverFareSheet()) {
	        	List<WorkPackageFare> fares = sheet.getFares();
	        	for(WorkPackageFare fare : fares) {
	//        		if(fare.getTravelStart() != null)
	//        			fare.setTravelStart(ZonedDateTime.ofInstant(fare.getTravelStart().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getTravelEnd() != null)
	//        			fare.setTravelEnd(ZonedDateTime.ofInstant(fare.getTravelEnd().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getSaleStart() != null)	            		
	//        			fare.setSaleStart(ZonedDateTime.ofInstant(fare.getSaleStart().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getSaleEnd() != null)
	//            		fare.setSaleEnd(ZonedDateTime.ofInstant(fare.getSaleEnd().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	//        		if(fare.getTravelComplete() != null)
	//	        		fare.setTravelComplete(ZonedDateTime.ofInstant(fare.getTravelComplete().toInstant().truncatedTo(ChronoUnit.DAYS), ZoneId.systemDefault()));
	        		if(fare.getId() == null) {
	        			fare.setId(new ObjectId().toString());
	        		}
	        	}
	        }
        }
        
        if(workPackage.getWpid() == null) {
    		DateFormat df = new SimpleDateFormat("yy"); // Just the year, with 2 digits
    		DateFormat dfFull = new SimpleDateFormat("yyyy"); // Just the year, with 4 digits
    		
    		Counter c = counterRepository.findOneByIdAndYear("workpackageId", dfFull.format(Calendar.getInstance().getTime()));
    		if(c == null) {
    			c = new Counter();
    			c.setId("workpackageId");
    			c.setSequenceValue(0);
    			c.setYear(dfFull.format(Calendar.getInstance().getTime()));
    			c = counterRepository.save(c);
    		}
    		NumberFormat nf = new DecimalFormat("00000");
        	c.setSequenceValue(c.getSequenceValue()+1);
        	c = counterRepository.save(c);
        	
    		String year = df.format(Calendar.getInstance().getTime());
    		workPackage.setWpid(year+nf.format(c.getSequenceValue()));
        }
    	
    	if(!workPackage.isSpecifiedFares()) {
    		if(workPackage.getFareSheet() != null) {
    			workPackage.getFareSheet().clear();
    		}
    	}
    	if(!workPackage.isWaiverFares()) {
    		if(workPackage.getWaiverFareSheet() != null) {
    			workPackage.getWaiverFareSheet().clear();
    		}
    	}
    	if(!workPackage.isMarketFares()) {
    		if(workPackage.getMarketFareSheet() != null) {
    			workPackage.getMarketFareSheet().clear();
    		}
    	}
    	if(!workPackage.isDiscount()) {
    		if(workPackage.getDiscountFareSheet() != null) {
    			workPackage.getDiscountFareSheet().clear();
    		}
    	}
    	if(!workPackage.isAddon()) {
    		if(workPackage.getAddonFareSheet() != null) {
    			workPackage.getAddonFareSheet().clear();
    		}
    	}
    	
    	if(workPackage.getComment() != null) {
	    	for(Comment comments : workPackage.getComment()) {
	    		if(comments.getUsername() == null && comments.getCreatedTime() == null) {
	    			comments.setUsername(SecurityUtils.getCurrentUserLogin().get());
	    			comments.setCreatedTime(ZonedDateTime.now());
	    		}
	    	}
    	}
    	
    	if(workPackage.getInterofficeComment() != null) {
	    	for(Comment comments : workPackage.getInterofficeComment()) {
	    		if(comments.getUsername() == null && comments.getCreatedTime() == null) {
	    			comments.setUsername(SecurityUtils.getCurrentUserLogin().get());
	    			comments.setCreatedTime(ZonedDateTime.now());
	    		}
	    	}
    	}
    	
    	if(workPackage.getAttachmentData() != null) {
    		for(Attachment attachment : workPackage.getAttachmentData()) {
    			if(attachment.getUsername() == null && attachment.getCreatedTime() == null) {
    				attachment.setUsername(SecurityUtils.getCurrentUserLogin().get());
    				attachment.setCreatedTime(ZonedDateTime.now());
    			}
    		}
    	}
    	
    	if(workPackage.getFilingInstructionData() != null) {
    		for(FilingInstruction filingInstruction : workPackage.getFilingInstructionData()) {    			
    			if(filingInstruction.getUsername() == null && filingInstruction.getCreatedTime() == null) {
    				filingInstruction.setUsername(SecurityUtils.getCurrentUserLogin().get());
    				filingInstruction.setCreatedTime(ZonedDateTime.now());
    			}
    		}
    	}
    	
    	if(workPackage.getMarketRulesData() != null) {
    		for(MarketRules marketRules : workPackage.getMarketRulesData()) {
    			if(marketRules.getUsername() == null && marketRules.getCreatedTime() == null) {
    				marketRules.setUsername(SecurityUtils.getCurrentUserLogin().get());
    				marketRules.setCreatedTime(ZonedDateTime.now());
    			}
    			try {
					extractRules(marketRules);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	
    	List<WorkPackageFare> allFares = new ArrayList<>();
    	if(workPackage.getFareSheet() != null && workPackage.getFareSheet().size() > 0) {
    		for(WorkPackageFareSheet sheet : workPackage.getFareSheet()) {
    			if(sheet.getFares().size() > 0) {
    				for(WorkPackageFare fares : sheet.getFares()) {
    					if(fares.getSaleStart() != null) {
    						allFares.add(fares);
    						continue;
    					}
    				}
    			}
    		}
    	}
    	if(workPackage.getAddonFareSheet() != null && workPackage.getAddonFareSheet().size() > 0) {
    		for(WorkPackageFareSheet sheet : workPackage.getAddonFareSheet()) {
    			if(sheet.getFares().size() > 0) {
    				for(WorkPackageFare fares : sheet.getFares()) {
    					if(fares.getSaleStart() != null) {
    						allFares.add(fares);
    						continue;
    					}
    				}
    			}
    		}
    	}
    	if(workPackage.getDiscountFareSheet() != null && workPackage.getDiscountFareSheet().size() > 0) {
    		for(WorkPackageFareSheet sheet : workPackage.getDiscountFareSheet()) {
    			if(sheet.getFares().size() > 0) {
    				for(WorkPackageFare fares : sheet.getFares()) {
    					if(fares.getSaleStart() != null) {
    						allFares.add(fares);
    						continue;
    					}
    				}
    			}
    		}
    	}
    	if(workPackage.getMarketFareSheet() != null && workPackage.getMarketFareSheet().size() > 0) {
    		for(WorkPackageFareSheet sheet : workPackage.getMarketFareSheet()) {
    			if(sheet.getFares().size() > 0) {
    				for(WorkPackageFare fares : sheet.getFares()) {
    					if(fares.getSaleStart() != null) {
    						allFares.add(fares);
    						continue;
    					}
    				}
    			}
    		}
    	}
    	if(workPackage.getWaiverFareSheet() != null && workPackage.getWaiverFareSheet().size() > 0) {
    		for(WorkPackageFareSheet sheet : workPackage.getWaiverFareSheet()) {
    			if(sheet.getFares().size() > 0) {
    				for(WorkPackageFare fares : sheet.getFares()) {
    					if(fares.getSaleStart() != null) {
    						allFares.add(fares);
    						continue;
    					}
    				}
    			}
    		}
    	}
    	
    	if(allFares.size() > 0) {
	    	Collections.sort(allFares, new WorkPackageFare.WorkPackageFareComparator());  
	    	workPackage.setSaleDate(allFares.get(0).getSaleStart());
    	}
    	
    	if(workPackage.getSaleDate() != null && (workPackage.getStatus() != Status.DISTRIBUTED) && workPackage.getTargetDistribution().contentEquals("ATPCO")) {
	    	Sort sort = new Sort(Direction.ASC, "priority");
	    	List<Priority> priorities = priorityRepository.findAll(sort);
	    	
	    	boolean found = false;
	    	
	    	for(Priority p : priorities) {
	    		if(p.getType().contentEquals("DAYS")) {
	    			long val = getDateDiff(new Date(), workPackage.getSaleDate(), TimeUnit.DAYS);
	    			long value = p.getValue();

	    			if(val <= value) {    				
	    				workPackage.setPriority(p.getName());
	    				found = true;
	    				break;
	    			}
	    		}
	    	}
	    	
	    	if(!found) {
		    	Sort sortDesc = new Sort(Direction.DESC, "priority");
		    	List<Priority> prioritiesDesc = priorityRepository.findAll(sortDesc);
		    	workPackage.setPriority(prioritiesDesc.get(0).getName());
	    	}
    	}
    	
    	workPackage.setValidation(null);
        return workPackageRepository.save(workPackage);
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
    
    private void extractRules(MarketRules marketRules) throws IOException {
		// TODO Auto-generated method stub
		byte[] attachment = marketRules.getFile();		
		if(attachment != null) {
			if(marketRules.getRulesData() != null) {
				marketRules.getRulesData().clear();
			}
			else {
				marketRules.setRulesData(new ArrayList<>());
			}
			
			List<RulesData> rulesData = marketRules.getRulesData();
			
			//
			InputStream input = new ByteArrayInputStream(attachment);

			Workbook workbook = new XSSFWorkbook(input);
			Sheet datatypeSheet = workbook.getSheetAt(0);
			
//			RulesData rd = new RulesData();
//			rd.setCategory("1");			
//				List<Field> fields = new ArrayList<>();
//				
//				Field f = new Field();
//				f.setKey("psg_type");
//				f.setValue("ADT");
//				fields.add(f);
//				rd.setFields(fields);
//			rulesData.add(rd);
			
			RulesData rd = null;
			for (Row myrow : datatypeSheet) {		
				boolean checkHeader = false;
				boolean checkValue = false;
				
			    for (Cell mycell : myrow) {
			    	if(mycell.getColumnIndex() == 0 && mycell.getStringCellValue().contentEquals("Category")) {
			    		checkHeader = true;
			    		if(rd != null) {
			    			rulesData.add(rd);
			    		}
			    		rd = new RulesData();
			    		rd.setFields(new ArrayList<>());
			    		rd.setCategory(datatypeSheet.getRow(myrow.getRowNum()+1).getCell(0).getStringCellValue());
			    	}
	
			    	if(checkHeader) {
			    		if(mycell.getColumnIndex() != 0) {
				    		if(getCellValueAsString(mycell) != null && !getCellValueAsString(mycell).contentEquals("")) {
				    			Field f = new Field();
				    			f.setKey(mycell.getStringCellValue());
				    			
				    			String value = getCellValueAsString(datatypeSheet.getRow(myrow.getRowNum()+1).getCell(mycell.getColumnIndex()));				    			
				    			f.setValue(value);
				    			rd.getFields().add(f);
				    		}
			    		}
			    	}
			    }
			}
			rulesData.add(rd);
		}
	}

	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
        long diffInMillies = removeTimeFromDate(date2).getTime() - removeTimeFromDate(date1).getTime();
        return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
    }
    
    public static Date removeTimeFromDate(Date date) {
    	 
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

	static long zonedDateTimeDifference(ZonedDateTime d1, ZonedDateTime d2, ChronoUnit unit){
        return unit.between(d1, d2);
    }

    /**
     * Get all the workPackages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<WorkPackage> findAll(Pageable pageable) {
        log.debug("Request to get all WorkPackages");
        return workPackageRepository.findAll(pageable);
    }
    
	public Long getCount() {
		return workPackageRepository.count();
	}
    /**
     * Get one workPackage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public WorkPackage findOne(String id) {
        log.debug("Request to get WorkPackage : {}", id);
        return workPackageRepository.findOne(id);
    }

    /**
     * Delete the workPackage by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete WorkPackage : {}", id);
        workPackageRepository.delete(id);
    }

	public List<WorkPackage> findAllByStatus(Status status) {
		log.debug("Request to get all WorkPackages by status");
        return workPackageRepository.findAllByStatus(status.toString());
	}

	public Page<WorkPackage> findAllByOrderByLastModifiedDate(Pageable pageable) {
		log.debug("Request to get all WorkPackages");
        return workPackageRepository.findAllByOrderByLastModifiedDateDesc(pageable);
	}
	
	public Page<WorkPackage> findCustom(WorkPackageFilter wpFilter, Pageable pageable){
		return workPackageRepository.findCustom(wpFilter, pageable);
	}

	public List<WorkPackage> findCustom(WorkPackageFilter workPackageFilter) {
		log.debug("Export find work package : {}", workPackageFilter);
		return workPackageRepository.findCustom(workPackageFilter);
	}
	
	public Page<WorkPackage> findCustomQuery(WorkPackageQuery wpFilter, Pageable pageable){
		return workPackageRepository.findCustomQuery(wpFilter, pageable);
	}
	public List<WorkPackage> findCustomQuery(WorkPackageQuery wpFilter){
		return workPackageRepository.findCustomQuery(wpFilter);
	}
}
