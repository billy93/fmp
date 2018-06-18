package com.atibusinessgroup.fmp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

import com.atibusinessgroup.fmp.domain.ClipboardSheet;
import com.atibusinessgroup.fmp.security.SecurityUtils;
import com.atibusinessgroup.fmp.service.ClipboardSheetService;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing ClipboardSheet.
 */
@RestController
@RequestMapping("/api")
public class ClipboardSheetResource {

    private final Logger log = LoggerFactory.getLogger(ClipboardSheetResource.class);

    private static final String ENTITY_NAME = "clipboard_sheet";

    private final ClipboardSheetService clipboardSheetService;

    public ClipboardSheetResource(ClipboardSheetService clipboardSheetService) {
        this.clipboardSheetService = clipboardSheetService;
    }

    /**
     * POST  /clipboard-sheets : Create a new clipboard sheet.
     *
     * @param clipboard the clipboard to create
     * @return the ResponseEntity with status 201 (Created) and with body the new clipboard, or with status 400 (Bad Request) if the clipboard has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/clipboard-sheets")
    @Timed
    public ResponseEntity<ClipboardSheet> createClipboardSheet(@RequestBody ClipboardSheet clipboardSheet) throws URISyntaxException {
        log.debug("REST request to save ClipboardSheet : {}", clipboardSheet);
        if (clipboardSheet.getId() != null) {
            throw new BadRequestAlertException("A new clipboard cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ClipboardSheet result = clipboardSheetService.save(clipboardSheet);
        return ResponseEntity.created(new URI("/api/clipboard-sheets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /clipboard-sheets : Updates an existing clipboard sheet.
     *
     * @param clipboard the clipboard to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated clipboard,
     * or with status 400 (Bad Request) if the clipboard is not valid,
     * or with status 500 (Internal Server Error) if the clipboard couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/clipboard-sheets")
    @Timed
    public ResponseEntity<ClipboardSheet> updateClipboardSheet(@RequestBody ClipboardSheet clipboard) throws URISyntaxException {
        log.debug("REST request to update ClipboardSheet : {}", clipboard);
        if (clipboard.getId() == null) {
            return createClipboardSheet(clipboard);
        }
        ClipboardSheet result = clipboardSheetService.save(clipboard);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, clipboard.getId().toString()))
            .body(result);
    }
    
    /**
     * PUT  /clipboard-sheets/copy : Copy objects.
     *
     * @param clipboard the clipboard to copy
     * @return the ResponseEntity with status 200 (OK) and with body the updated clipboard,
     * or with status 400 (Bad Request) if the clipboard is not valid,
     * or with status 500 (Internal Server Error) if the clipboard couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/clipboard-sheets/copy")
    @Timed
    public ResponseEntity<ClipboardSheet> copyObject(@RequestBody ClipboardSheet clipboard) throws URISyntaxException {
        log.debug("REST request to copy objects to ClipboardSheet : {}", clipboard);
        
        ClipboardSheet dbClipboardSheet = clipboardSheetService.findOneByUsername(SecurityUtils.getCurrentUserLogin().get());
        
        if (dbClipboardSheet == null) {
        	dbClipboardSheet = new ClipboardSheet();
        	dbClipboardSheet.setUsername(SecurityUtils.getCurrentUserLogin().get());
        }
        
        dbClipboardSheet.setSheet(clipboard.getSheet());
        dbClipboardSheet.setType(clipboard.getType());
        dbClipboardSheet.setCopyDateTime(Instant.now());
        dbClipboardSheet = clipboardSheetService.save(dbClipboardSheet);
        
        return ResponseEntity.ok().body(dbClipboardSheet);
    }

    /**
     * GET  /clipboard-sheets : get all the clipboard sheets.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of clipboards in body
     */
    @GetMapping("/clipboard-sheets")
    @Timed
    public ResponseEntity<List<ClipboardSheet>> getAllClipboardSheets(Pageable pageable) {
        log.debug("REST request to get a page of ClipboardSheets");
        Page<ClipboardSheet> page = clipboardSheetService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/clipboards");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /clipboard-sheets/:id : get the "id" clipboard sheets.
     *
     * @param id the id of the clipboard to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the clipboard, or with status 404 (Not Found)
     */
    @GetMapping("/clipboard-sheets/{id}")
    @Timed
    public ResponseEntity<ClipboardSheet> getClipboardSheet(@PathVariable String id) {
        log.debug("REST request to get ClipboardSheet : {}", id);
        ClipboardSheet clipboard = clipboardSheetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(clipboard));
    }

    /**
     * DELETE  /clipboards/:id : delete the "id" clipboard.
     *
     * @param id the id of the clipboard to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/clipboard-sheets/{id}")
    @Timed
    public ResponseEntity<Void> deleteClipboardSheet(@PathVariable String id) {
        log.debug("REST request to delete ClipboardSheet : {}", id);
        clipboardSheetService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
