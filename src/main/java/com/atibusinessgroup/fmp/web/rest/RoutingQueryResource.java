package com.atibusinessgroup.fmp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

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
        RoutingQuery routingquery = routingQueryService.findOne(id);
        routingquery = routingQueryService.getFullRouteDetails(routingquery);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(routingquery));
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
}
