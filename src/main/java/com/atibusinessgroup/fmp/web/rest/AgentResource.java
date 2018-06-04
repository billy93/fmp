package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.Agent;
import com.atibusinessgroup.fmp.domain.AgentHistory;
import com.atibusinessgroup.fmp.domain.WorkPackage.Attachment;
import com.atibusinessgroup.fmp.domain.WorkPackage.ImportFares;
import com.atibusinessgroup.fmp.repository.AgentHistoryRepository;
import com.atibusinessgroup.fmp.repository.AgentRepository;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Agent.
 */
@RestController
@RequestMapping("/api")
public class AgentResource {

    private final Logger log = LoggerFactory.getLogger(AgentResource.class);

    private static final String ENTITY_NAME = "agent";

    private final AgentRepository agentRepository;
    
    private final AgentHistoryRepository agentHistoryRepository;

    public AgentResource(AgentRepository agentRepository,AgentHistoryRepository agentHistoryRepository) {
        this.agentRepository = agentRepository;
        this.agentHistoryRepository = agentHistoryRepository;
    }

    /**
     * POST  /agents : Create a new agent.
     *
     * @param agent the agent to create
     * @return the ResponseEntity with status 201 (Created) and with body the new agent, or with status 400 (Bad Request) if the agent has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/agents")
    @Timed
    public ResponseEntity<Agent> createAgent(@RequestBody Agent agent) throws URISyntaxException {
        log.debug("REST request to save Agent : {}", agent);
        if (agent.getId() != null) {
            throw new BadRequestAlertException("A new agent cannot already have an ID", ENTITY_NAME, "idexists");
        }
        agent.setEffectiveDateTime(Instant.now());
        agent.setIsDeleted(false);
        Agent result = agentRepository.save(agent);
        return ResponseEntity.created(new URI("/api/agents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /agents : Updates an existing agent.
     *
     * @param agent the agent to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated agent,
     * or with status 400 (Bad Request) if the agent is not valid,
     * or with status 500 (Internal Server Error) if the agent couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/agents")
    @Timed
    public ResponseEntity<Agent> updateAgent(@RequestBody Agent agent) throws URISyntaxException {
        log.debug("REST request to update Agent : {}", agent);
        
        Optional<Agent> initAgent = agentRepository.findOneById(agent.getId());
        Agent agentHistory = initAgent.get();
        
        AgentHistory history = new AgentHistory();
        history.setIdHistory(agentHistory.getId());
        history.setAddress(agentHistory.getAddress());
        history.setAgentCategory(agentHistory.getAgentCategory());
        history.setAgentName(agentHistory.getAgentName());
        history.setContact(agentHistory.getContact());
//        history.setCrs(agentHistory.getCrs());
        history.setAgentPcc(agentHistory.getAgentPcc());
        history.setEffectiveDateTime(agentHistory.getEffectiveDateTime());
        history.setEmail(agentHistory.getEmail());
        history.setFax(agentHistory.getFax());
        history.setIataCode(agentHistory.getIataCode());
        history.setIsDeleted(agentHistory.getIsDeleted());
//        history.setPdeudoCity(agentHistory.getPdeudoCity());
        history.setPosCity(agentHistory.getPosCity());
        history.setPosCountry(agentHistory.getPosCountry());
        history.setTelephone(agentHistory.getTelephone());
        history.setDiscontinueDateTime(Instant.now().minus(1,ChronoUnit.DAYS));
        agentHistoryRepository.save(history);
     
        log.debug("AGENT HISTORY : "+ history+" id hsitory"+ history.getIdHistory());
        log.debug("AGENT LAMA : "+ agentHistory);
                
        if (agent.getId() == null) {
            return createAgent(agent);
        }
        agent.setEffectiveDateTime(Instant.now());
        agent.setDiscontinueDateTime(null);
        Agent result = agentRepository.save(agent);
        log.debug("AGENT Baru : "+ result);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, agent.getId().toString()))
            .body(result);
    }
    
    /**
     * GET  /agencies : get all the agencies.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of agencies in body
     */
    @GetMapping("/agencies/all")
    @Timed
    public List<Agent> getAllAgencies() {
        log.debug("REST request to get all Agencies");
        return agentRepository.findAll();
        }

    /**
     * GET  /agents : get all the agents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of agents in body
     * @throws IllegalAccessException 
     */
    @GetMapping("/agents")
    @Timed
    public ResponseEntity<List<Agent>> getAllAgents(Agent filter, Pageable pageable) throws IllegalAccessException {
        log.debug("REST request to get a page of Agents {}", filter);
      
        if(checkNull(filter)) {
//        	Page<Agent> page = agentRepository.findAll(pageable);
        	Page<Agent> page = agentRepository.findCustomTrue(filter, pageable);
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/agents");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
        }else {
        	Page<Agent> page = agentRepository.findCustom(filter, pageable);   
            HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/agents");
            return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);     	
        }
    }

    /**
     * GET  /agents/:id : get the "id" agent.
     *
     * @param id the id of the agent to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the agent, or with status 404 (Not Found)
     */
    @GetMapping("/agents/{id}")
    @Timed
    public ResponseEntity<Agent> getAgent(@PathVariable String id) {
        log.debug("REST request to get Agent : {}", id);
        Agent agent = agentRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(agent));
    }

    /**
     * DELETE  /agents/:id : delete the "id" agent.
     *
     * @param id the id of the agent to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/agents/{id}")
    @Timed
    public ResponseEntity<Void> deleteAgent(@PathVariable String id) {
        log.debug("REST request to delete Agent : {}", id);
//        agentRepository.delete(id);
        Agent agent = agentRepository.findOne(id);
        agent.setIsDeleted(true);
        agent.setDiscontinueDateTime(Instant.now().minus(1,ChronoUnit.DAYS));
        agentRepository.save(agent);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
    
    /**
     * POST  /agents/export-agents : Export work agents fares
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     * @throws IllegalAccessException 
     */
    @PostMapping("/agencies/export-agencies")
    @Timed
    public ResponseEntity<Attachment> exportAgencies(@RequestBody Agent filter) throws URISyntaxException, IllegalAccessException {
    	log.debug("REST request to save exportAgencies : {}", filter);

        XSSFWorkbook workbook = new XSSFWorkbook(); 
    	if(checkNull(filter)) {
        	List<Agent> page = agentRepository.findAll();    
        	workbook = exportAgent(page);
        }else {
        	List<Agent> page = agentRepository.findCustom(filter);   
        	workbook = exportAgent(page);            
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
     * POST  /agents/export-all-agents : Export work agents fares
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/agencies/export-all-agencies")
    @Timed
    public ResponseEntity<Attachment> exportAllAgencies() throws URISyntaxException {
    	log.debug("REST request to export All Agencies");

        List<Agent> agent = getAllAgencies();
        XSSFWorkbook workbook = exportAgent(agent);
        

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
    
    private XSSFWorkbook exportAgent(List<Agent> agent) {

        XSSFWorkbook workbook = new XSSFWorkbook(); 
        XSSFSheet spreadsheet = workbook.createSheet("Agencies");
        
        XSSFRow row = spreadsheet.createRow(0);
        XSSFCell cell;

        cell = row.createCell(0);
        cell.setCellValue("AGENT NAME");
        cell = row.createCell(1);
        cell.setCellValue("IATA CODE");
        cell = row.createCell(2);
        cell.setCellValue("AGENT TYPE");
        cell = row.createCell(3);
        cell.setCellValue("AGENT CATEGORY");
        cell = row.createCell(4);
        cell.setCellValue("PSEUDO CITY");
        cell = row.createCell(5);
        cell.setCellValue("CRS");
        cell = row.createCell(6);
        cell.setCellValue("POS CITY");
        cell = row.createCell(7);
        cell.setCellValue("POS COUNTRY");
        cell = row.createCell(8);
        cell.setCellValue("ADDRESS");
        cell = row.createCell(9);
        cell.setCellValue("TELEPHONE");
        cell = row.createCell(10);
        cell.setCellValue("FAX");
        cell = row.createCell(11);
        cell.setCellValue("EMAIL");
        cell = row.createCell(12);
        cell.setCellValue("CONTACT");
        cell = row.createCell(13);
        cell.setCellValue("DELETED");
        
         
        
        int index=0;
        for(int i=0; i<agent.size(); i++) {
        	if(agent.get(i).getAgentPcc() != null && agent.get(i).getAgentPcc().size() > 0) {
        		for(int j=0;j<agent.get(i).getAgentPcc().size();j++) {

            		XSSFRow rows = spreadsheet.createRow(index+1);
            		
                    cell = rows.createCell(0);
                    if (agent.get(i).getAgentName()!=null){
                        cell.setCellValue(agent.get(i).getAgentName());
                    }
                    
                    cell = rows.createCell(1);
                    if (agent.get(i).getIataCode()!=null) {
        				cell.setCellValue(agent.get(i).getIataCode());
        			}
                    
        			cell = rows.createCell(2);
                    if (agent.get(i).getAgentType()!=null) {
        				cell.setCellValue(agent.get(i).getAgentType());
        			}
                    
        			cell = rows.createCell(3);
                    if (agent.get(i).getAgentCategory()!=null) {
        				cell.setCellValue(agent.get(i).getAgentCategory());
        			}
                    
        			cell = rows.createCell(4);
                    if (agent.get(i).getAgentPcc().get(j).getPdeudoCity()!=null) {
        				cell.setCellValue(agent.get(i).getAgentPcc().get(j).getPdeudoCity());
        			}
                    
        			cell = rows.createCell(5);
                    if (agent.get(i).getAgentPcc().get(j).getCrs()!=null) {
        				cell.setCellValue(agent.get(i).getAgentPcc().get(j).getCrs());
        			}
                    
        			cell = rows.createCell(6);
                    if (agent.get(i).getPosCity()!=null) {
        				cell.setCellValue(agent.get(i).getPosCity());
        			}
                    
        			cell = rows.createCell(7);
                    if (agent.get(i).getPosCountry()!=null) {
        				cell.setCellValue(agent.get(i).getPosCountry());
        			}
                    
        			cell = rows.createCell(8);
                    if (agent.get(i).getAddress()!=null) {
        				cell.setCellValue(agent.get(i).getAddress());
        			}
                    
        			cell = rows.createCell(9);
                    if (agent.get(i).getTelephone()!=null) {
        				cell.setCellValue(agent.get(i).getTelephone());
        			}
                    
        			cell = rows.createCell(10);
                    if (agent.get(i).getFax()!=null) {
        				cell.setCellValue(agent.get(i).getFax());
        			}
                    
        			cell = rows.createCell(11);
                    if (agent.get(i).getEmail()!=null) {
        				cell.setCellValue(agent.get(i).getEmail());
        			}
                    
        			cell = rows.createCell(12);
                    if (agent.get(i).getContact()!=null) {
        				cell.setCellValue(agent.get(i).getContact());
        			}
                    
        			cell = rows.createCell(13);
                    if (agent.get(i).getIsDeleted()!=null) {
        				cell.setCellValue(agent.get(i).getIsDeleted());
        			}
                    
                    index++;
            	}
        	}
        	else {
        		XSSFRow rows = spreadsheet.createRow(index+1);
        		
                cell = rows.createCell(0);
                if (agent.get(i).getAgentName()!=null){
                    cell.setCellValue(agent.get(i).getAgentName());
                }
                
                cell = rows.createCell(1);
                if (agent.get(i).getIataCode()!=null) {
    				cell.setCellValue(agent.get(i).getIataCode());
    			}
                
    			cell = rows.createCell(2);
                if (agent.get(i).getAgentType()!=null) {
    				cell.setCellValue(agent.get(i).getAgentType());
    			}
                
    			cell = rows.createCell(3);
                if (agent.get(i).getAgentCategory()!=null) {
    				cell.setCellValue(agent.get(i).getAgentCategory());
    			}
                
    			cell = rows.createCell(4);
    			cell = rows.createCell(5);
    		
    			cell = rows.createCell(6);
                if (agent.get(i).getPosCity()!=null) {
    				cell.setCellValue(agent.get(i).getPosCity());
    			}
                
    			cell = rows.createCell(7);
                if (agent.get(i).getPosCountry()!=null) {
    				cell.setCellValue(agent.get(i).getPosCountry());
    			}
                
    			cell = rows.createCell(8);
                if (agent.get(i).getAddress()!=null) {
    				cell.setCellValue(agent.get(i).getAddress());
    			}
                
    			cell = rows.createCell(9);
                if (agent.get(i).getTelephone()!=null) {
    				cell.setCellValue(agent.get(i).getTelephone());
    			}
                
    			cell = rows.createCell(10);
                if (agent.get(i).getFax()!=null) {
    				cell.setCellValue(agent.get(i).getFax());
    			}
                
    			cell = rows.createCell(11);
                if (agent.get(i).getEmail()!=null) {
    				cell.setCellValue(agent.get(i).getEmail());
    			}
                
    			cell = rows.createCell(12);
                if (agent.get(i).getContact()!=null) {
    				cell.setCellValue(agent.get(i).getContact());
    			}
                
    			cell = rows.createCell(13);
                if (agent.get(i).getIsDeleted()!=null) {
    				cell.setCellValue(agent.get(i).getIsDeleted());
    			}
                
            	index++;
        	}        	
        }
        return workbook;
	}

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
    
    /**
     * POST  /agencies/import-agencies : Import a new fares workPackage.
     *
     * @param workPackage the workPackage to create
     * @return the ResponseEntity with status 201 (Created) and with body the new workPackage, or with status 400 (Bad Request) if the workPackage has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/agencies/import-agencies")
    @Timed
    public Agent importAgencies(@RequestBody ImportFares agenciesData) throws URISyntaxException {
        log.debug("REST request to save importAgencies : {}", agenciesData);
        Agent agenciesResult = null;
        List<Agent> agencies = new ArrayList<Agent>();
        
        try {
            ImportFares importData = agenciesData;
			InputStream input = new ByteArrayInputStream(importData.getFile());
			Workbook workbook = new XSSFWorkbook(input);
			Sheet datatypeSheet = workbook.getSheetAt(0);	
			
			String status = datatypeSheet.getRow(1).getCell(0).getStringCellValue();
			
			Iterator<Row> iterator = datatypeSheet.iterator();
			iterator.next();
			
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();

                Agent agent = new Agent();
                for(int cell=0;cell<13;cell++) {
                    Cell currentCell = currentRow.getCell(cell);

                    String value = "";
                    try {
                    		value = getCellValueAsString(currentCell);
                    }catch(Exception e) {                    	
                    }
                    
                    System.out.println("CELL "+cell+" : "+value);
                    
                    try {
						if (cell == 0) {
							agent.setAgentName(value);
						} else if (cell == 1) {
							agent.setIataCode(value);
						} else if (cell == 2) {
							agent.setAgentType(value);
						} else if (cell == 3) {
							agent.setAgentCategory(value);
						} else if (cell == 4) {
//							agent.setPdeudoCity(value);
						} else if (cell == 5) {
//							agent.setCrs(value);
						} else if (cell == 6) {
							agent.setPosCity(value);
						} else if (cell == 7) {
							agent.setPosCountry(value);
						} else if (cell == 8) {
							agent.setAddress(value);
						} else if (cell == 9) {
							agent.setTelephone(value);
						} else if (cell == 10) {
							agent.setFax(value);
						} else if (cell == 11) {
							agent.setEmail(value);
						} else if (cell == 12) {
							agent.setContact(value);
						} else if (cell == 13) {
							agent.setIsDeleted(Boolean.valueOf(value));
						} 
					} catch (Exception e) {
						e.printStackTrace();
					}
                    
                }
                //checking exist or not 
                Optional<Agent> initAgent = agentRepository.findOneByIataCode(agent.getIataCode());
                if(!initAgent.isPresent()) {
                	 agencies.add(agent);
                }
               
            }
            
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        for (Agent data : agencies) {
        	agenciesResult = agentRepository.save(data);
		}
        
        return agenciesResult;
    }
    
    public boolean checkNull(Object object) throws IllegalAccessException {
        for (Field f : object.getClass().getDeclaredFields()) {
        	f.setAccessible(true);
            if (f.get(object) != null) {
            	log.debug("FIELD NAME : {}",f.getName());
                return false;
            }
        }
        return true;            
    }
}
