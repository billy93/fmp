package com.atibusinessgroup.fmp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.atibusinessgroup.fmp.domain.ReviewLevel;
import com.atibusinessgroup.fmp.service.ReviewLevelService;
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
 * REST controller for managing ReviewLevel.
 */
@RestController
@RequestMapping("/api")
public class ReviewLevelResource {

    private final Logger log = LoggerFactory.getLogger(ReviewLevelResource.class);

    private static final String ENTITY_NAME = "reviewLevel";

    private final ReviewLevelService reviewLevelService;

    public ReviewLevelResource(ReviewLevelService reviewLevelService) {
        this.reviewLevelService = reviewLevelService;
    }

    /**
     * POST  /review-levels : Create a new reviewLevel.
     *
     * @param reviewLevel the reviewLevel to create
     * @return the ResponseEntity with status 201 (Created) and with body the new reviewLevel, or with status 400 (Bad Request) if the reviewLevel has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/review-levels")
    @Timed
    public ResponseEntity<ReviewLevel> createReviewLevel(@RequestBody ReviewLevel reviewLevel) throws URISyntaxException {
        log.debug("REST request to save ReviewLevel : {}", reviewLevel);
        if (reviewLevel.getId() != null) {
            throw new BadRequestAlertException("A new reviewLevel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ReviewLevel result = reviewLevelService.save(reviewLevel);
        return ResponseEntity.created(new URI("/api/review-levels/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /review-levels : Updates an existing reviewLevel.
     *
     * @param reviewLevel the reviewLevel to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated reviewLevel,
     * or with status 400 (Bad Request) if the reviewLevel is not valid,
     * or with status 500 (Internal Server Error) if the reviewLevel couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/review-levels")
    @Timed
    public ResponseEntity<ReviewLevel> updateReviewLevel(@RequestBody ReviewLevel reviewLevel) throws URISyntaxException {
        log.debug("REST request to update ReviewLevel : {}", reviewLevel);
        if (reviewLevel.getId() == null) {
            return createReviewLevel(reviewLevel);
        }
        ReviewLevel result = reviewLevelService.save(reviewLevel);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, reviewLevel.getId().toString()))
            .body(result);
    }

    /**
     * GET  /review-levels : get all the reviewLevels.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of reviewLevels in body
     */
    @GetMapping("/review-levels")
    @Timed
    public ResponseEntity<List<ReviewLevel>> getAllReviewLevels(Pageable pageable) {
        log.debug("REST request to get a page of ReviewLevels");
        Page<ReviewLevel> page = reviewLevelService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/review-levels");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }
    
    /**
     * GET  /review-levels/all : get all the reviewLevels.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of reviewLevels in body
     */
    @GetMapping("/review-levels/all")
    @Timed
    public ResponseEntity<List<ReviewLevel>> getAllReviewLevels() {
        log.debug("REST request to get all of ReviewLevels");
        List<ReviewLevel> result = reviewLevelService.findAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * GET  /review-levels/:id : get the "id" reviewLevel.
     *
     * @param id the id of the reviewLevel to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the reviewLevel, or with status 404 (Not Found)
     */
    @GetMapping("/review-levels/{id}")
    @Timed
    public ResponseEntity<ReviewLevel> getReviewLevel(@PathVariable String id) {
        log.debug("REST request to get ReviewLevel : {}", id);
        ReviewLevel reviewLevel = reviewLevelService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(reviewLevel));
    }

    /**
     * DELETE  /review-levels/:id : delete the "id" reviewLevel.
     *
     * @param id the id of the reviewLevel to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/review-levels/{id}")
    @Timed
    public ResponseEntity<Void> deleteReviewLevel(@PathVariable String id) {
        log.debug("REST request to delete ReviewLevel : {}", id);
        reviewLevelService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
    }
}
