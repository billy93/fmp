package com.atibusinessgroup.fmp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
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

import com.atibusinessgroup.fmp.domain.Clipboard;
import com.atibusinessgroup.fmp.domain.WorkPackageFare;
import com.atibusinessgroup.fmp.security.SecurityUtils;
import com.atibusinessgroup.fmp.service.ClipboardService;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;
import com.mongodb.DBObject;
import com.mongodb.util.JSON;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing Clipboard.
 */
@RestController
@RequestMapping("/api")
public class ClipboardResource {

    private final Logger log = LoggerFactory.getLogger(ClipboardResource.class);

    private static final String ENTITY_NAME = "clipboard";

    private final ClipboardService clipboardService;

    private final MongoTemplate mongoTemplate;
    
    public ClipboardResource(ClipboardService clipboardService, MongoTemplate mongoTemplate) {
        this.clipboardService = clipboardService;
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * POST  /clipboards : Create a new clipboard.
     *
     * @param clipboard the clipboard to create
     * @return the ResponseEntity with status 201 (Created) and with body the new clipboard, or with status 400 (Bad Request) if the clipboard has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/clipboards")
    @Timed
    public ResponseEntity<Clipboard> createClipboard(@RequestBody Clipboard clipboard) throws URISyntaxException {
        log.debug("REST request to save Clipboard : {}", clipboard);
        if (clipboard.getId() != null) {
            throw new BadRequestAlertException("A new clipboard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Clipboard result = clipboardService.save(clipboard);
        return ResponseEntity.created(new URI("/api/clipboards/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /clipboards : Updates an existing clipboard.
     *
     * @param clipboard the clipboard to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clipboard,
     * or with status 400 (Bad Request) if the clipboard is not valid,
     * or with status 500 (Internal Server Error) if the clipboard couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/clipboards")
    @Timed
    public ResponseEntity<Clipboard> updateClipboard(@RequestBody Clipboard clipboard) throws URISyntaxException {
        log.debug("REST request to update Clipboard : {}", clipboard);
        if (clipboard.getId() == null) {
            return createClipboard(clipboard);
        }
        Clipboard result = clipboardService.save(clipboard);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, clipboard.getId().toString()))
            .body(result);
    }
    
    /**
     * PUT  /clipboards/copy : Copy objects.
     *
     * @param clipboard the clipboard to copy
     * @return the ResponseEntity with status 200 (OK) and with body the updated clipboard,
     * or with status 400 (Bad Request) if the clipboard is not valid,
     * or with status 500 (Internal Server Error) if the clipboard couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/clipboards/copy")
    @Timed
    public ResponseEntity<Clipboard> copyObject(@RequestBody Clipboard clipboard) throws URISyntaxException {
        log.debug("REST request to copy objects to Clipboard : {}", clipboard);
        
        Clipboard dbClipboard = clipboardService.findOneByUsername(SecurityUtils.getCurrentUserLogin().get());
        
        if (dbClipboard == null) {
        	dbClipboard = new Clipboard();
        	dbClipboard.setUsername(SecurityUtils.getCurrentUserLogin().get());
        }
        
        dbClipboard.setPage(clipboard.getPage());
        dbClipboard.setCopyDateTime(Instant.now());
        
//        Map<String, List<Object>> filtered = new HashMap<>();
//        
//        for (Object entry : clipboard.getContent()) {
//        	if (entry.getKey() != null) {
//        		filtered.put(entry.getKey(), entry.getValue());
//        	}
//        }
//        for(Object o : clipboard.getContent()) {
//        	WorkPackageFare fare = (WorkPackageFare) o;
//        	log.debug("TRAVEL START : {}", fare.getTravelStart());
//        }
        dbClipboard.setContent(clipboard.getContent());
//        dbClipboard.setFares(clipboard.getFares());
        dbClipboard = clipboardService.save(dbClipboard);
        
        return ResponseEntity.ok().body(dbClipboard);
    }

    /**
     * GET  /clipboards : get all the clipboards.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of clipboards in body
     */
    @GetMapping("/clipboards")
    @Timed
    public ResponseEntity<List<Clipboard>> getAllClipboards(Pageable pageable) {
        log.debug("REST request to get a page of Clipboards");
        Page<Clipboard> page = clipboardService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/clipboards");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /clipboards/:id : get the "id" clipboard.
     *
     * @param id the id of the clipboard to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the clipboard, or with status 404 (Not Found)
     */
    @GetMapping("/clipboards/{id}")
    @Timed
    public ResponseEntity<Clipboard> getClipboard(@PathVariable String id) {
        log.debug("REST request to get Clipboard : {}", id);
        Clipboard clipboard = clipboardService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(clipboard));
    }

    /**
     * GET  /clipboard-sheets/findByCurrentUsername : get the "id" clipboard sheets.
     *
     * @param id the id of the clipboard to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the clipboard, or with status 404 (Not Found)
     */
    @GetMapping("/clipboards/findByCurrentUsername")
    @Timed
    public ResponseEntity<Clipboard> findClipboardSheetByCurrentUsername() {
        log.debug("REST request to get ClipboardSheet by current username: {}", SecurityUtils.getCurrentUserLogin().get());
        Clipboard clipboard = clipboardService.findOneByUsername(SecurityUtils.getCurrentUserLogin().get());
        
//        for(int a=0;a<clipboard.getContent().size();a++) {
//           String jsonString = new JSONObject(clipboard.getContent().get(a)).toString();
//      	   DBObject dbObject = (DBObject) JSON.parse(jsonString);
//      	   WorkPackageFare result = mongoTemplate.getConverter().read(WorkPackageFare.class, dbObject);
//      	   clipboard.getContent().set(a, result);
//        }
//       clipboard.getContent().forEach((content) -> {
//    	   String jsonString = new JSONObject(content).toString();
//    	   DBObject dbObject = (DBObject) JSON.parse(jsonString);
//    	   WorkPackageFare result = mongoTemplate.getConverter().read(WorkPackageFare.class, dbObject);
//    	   log.debug("TEST : {}",result.toString());
////    	   WorkPackageFare wpf = (WorkPackageFare) content;
//       });
        
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(clipboard));
    }
    
    /**
     * DELETE  /clipboards/:id : delete the "id" clipboard.
     *
     * @param id the id of the clipboard to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/clipboards/{id}")
    @Timed
    public ResponseEntity<Void> deleteClipboard(@PathVariable String id) {
        log.debug("REST request to delete Clipboard : {}", id);
        clipboardService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
