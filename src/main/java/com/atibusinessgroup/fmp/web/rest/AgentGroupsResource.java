package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.Agent;
import com.atibusinessgroup.fmp.domain.AgentGroups;

import com.atibusinessgroup.fmp.repository.AgentGroupsRepository;
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
 * REST controller for managing AgentGroups.
 */
@RestController
@RequestMapping("/api")
public class AgentGroupsResource {

    private final Logger log = LoggerFactory.getLogger(AgentGroupsResource.class);

    private static final String ENTITY_NAME = "agentGroups";

    private final AgentGroupsRepository agentGroupsRepository;

    public AgentGroupsResource(AgentGroupsRepository agentGroupsRepository) {
        this.agentGroupsRepository = agentGroupsRepository;
    }

    /**
     * POST  /agent-groups : Create a new agentGroups.
     *
     * @param agentGroups the agentGroups to create
     * @return the ResponseEntity with status 201 (Created) and with body the new agentGroups, or with status 400 (Bad Request) if the agentGroups has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/agent-groups")
    @Timed
    public ResponseEntity<AgentGroups> createAgentGroups(@RequestBody AgentGroups agentGroups) throws URISyntaxException {
        log.debug("REST request to save AgentGroups : {}", agentGroups);
        if (agentGroups.getId() != null) {
            throw new BadRequestAlertException("A new agentGroups cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AgentGroups result = agentGroupsRepository.save(agentGroups);
        return ResponseEntity.created(new URI("/api/agent-groups/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /agent-groups : Updates an existing agentGroups.
     *
     * @param agentGroups the agentGroups to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated agentGroups,
     * or with status 400 (Bad Request) if the agentGroups is not valid,
     * or with status 500 (Internal Server Error) if the agentGroups couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/agent-groups")
    @Timed
    public ResponseEntity<AgentGroups> updateAgentGroups(@RequestBody AgentGroups agentGroups) throws URISyntaxException {
        log.debug("REST request to update AgentGroups : {}", agentGroups);
        if (agentGroups.getId() == null) {
            return createAgentGroups(agentGroups);
        }
        AgentGroups result = agentGroupsRepository.save(agentGroups);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, agentGroups.getId().toString()))
            .body(result);
    }

    /**
     * GET  /agent-groups : get all the agentGroups.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of agentGroups in body
     */
    @GetMapping("/agent-groups")
    @Timed
    public ResponseEntity<List<AgentGroups>> getAllAgentGroups(Pageable pageable) {
        log.debug("REST request to get a page of AgentGroups");
        Page<AgentGroups> page = agentGroupsRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/agent-groups");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /agent-groups/:id : get the "id" agentGroups.
     *
     * @param id the id of the agentGroups to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the agentGroups, or with status 404 (Not Found)
     */
    @GetMapping("/agent-groups/{id}")
    @Timed
    public ResponseEntity<AgentGroups> getAgentGroups(@PathVariable String id) {
        log.debug("REST request to get AgentGroups : {}", id);
        AgentGroups agentGroups = agentGroupsRepository.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(agentGroups));
    }

   
    /**
     * DELETE  /agent-groups/:id : delete the "id" agentGroups.
     *
     * @param id the id of the agentGroups to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/agent-groups/{id}")
    @Timed
    public ResponseEntity<Void> deleteAgentGroups(@PathVariable String id) {
        log.debug("REST request to delete AgentGroups : {}", id);
        agentGroupsRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
