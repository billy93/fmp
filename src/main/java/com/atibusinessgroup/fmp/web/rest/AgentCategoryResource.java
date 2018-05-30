package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.AgentCategory;
import com.atibusinessgroup.fmp.domain.AgentGroups;
import com.atibusinessgroup.fmp.repository.AgentCategoryRepository;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.atibusinessgroup.fmp.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AgentCategory.
 */
@RestController
@RequestMapping("/api")
public class AgentCategoryResource {

    private final Logger log = LoggerFactory.getLogger(AgentCategoryResource.class);

    private static final String ENTITY_NAME = "agentCategory";

    private final AgentCategoryRepository agentCategoryRepository;

    public AgentCategoryResource(AgentCategoryRepository agentCategoryRepository) {
        this.agentCategoryRepository = agentCategoryRepository;
    }

    /**
     * POST  /agent-categories : Create a new agentCategory.
     *
     * @param agentCategory the agentCategory to create
     * @return the ResponseEntity with status 201 (Created) and with body the new agentCategory, or with status 400 (Bad Request) if the agentCategory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/agent-categories")
    @Timed
    public ResponseEntity<AgentCategory> createAgentCategory(@RequestBody AgentCategory agentCategory) throws URISyntaxException {
        log.debug("REST request to save AgentCategory : {}", agentCategory);
        if (agentCategory.getId() != null) {
            throw new BadRequestAlertException("A new agentCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgentCategory result = agentCategoryRepository.save(agentCategory);
        return ResponseEntity.created(new URI("/api/agent-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /agent-categories : Updates an existing agentCategory.
     *
     * @param agentCategory the agentCategory to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated agentCategory,
     * or with status 400 (Bad Request) if the agentCategory is not valid,
     * or with status 500 (Internal Server Error) if the agentCategory couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/agent-categories")
    @Timed
    public ResponseEntity<AgentCategory> updateAgentCategory(@RequestBody AgentCategory agentCategory) throws URISyntaxException {
        log.debug("REST request to update AgentCategory : {}", agentCategory);
        if (agentCategory.getId() == null) {
            return createAgentCategory(agentCategory);
        }
        AgentCategory result = agentCategoryRepository.save(agentCategory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, agentCategory.getId().toString()))
            .body(result);
    }

    /**
     * GET  /agent-categories : get all the agentCategories.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of agentCategories in body
     */
    @GetMapping("/agent-categories")
    @Timed
    public ResponseEntity<List<AgentCategory>> getAllAgentCategories(Pageable pageable) {
        log.debug("REST request to get a page of AgentCategories");
        Page<AgentCategory> page = agentCategoryRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/agent-categories");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /agent-categories/:id : get the "id" agentCategory.
     *
     * @param id the id of the agentCategory to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the agentCategory, or with status 404 (Not Found)
     */
    @GetMapping("/agent-categories/{id}")
    @Timed
    public ResponseEntity<AgentCategory> getAgentCategory(@PathVariable String id) {
        log.debug("REST request to get AgentCategory : {}", id);
        AgentCategory agentCategory = agentCategoryRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(agentCategory));
    }
    
    /**
     * GET  /agent-categories : get all the agent-categories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of agencies in body
     */
    @GetMapping("/agent-categories/all")
    @Timed
    public List<AgentCategory> getAllAgentCategories() {
        log.debug("REST request to get all Agent Categories");
        return agentCategoryRepository.findAll();
        }
    

    /**
     * DELETE  /agent-categories/:id : delete the "id" agentCategory.
     *
     * @param id the id of the agentCategory to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/agent-categories/{id}")
    @Timed
    public ResponseEntity<Void> deleteAgentCategory(@PathVariable String id) {
        log.debug("REST request to delete AgentCategory : {}", id);
        agentCategoryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
