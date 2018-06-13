package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.RoutingQuery;
import com.atibusinessgroup.fmp.domain.dto.RouteMapView;
import com.atibusinessgroup.fmp.domain.dto.RoutingQueryParam;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing RoutingQuery.
 */
public interface RoutingQueryService {

    /**
     * Save a routingQuery.
     *
     * @param routingQuery the entity to save
     * @return the persisted entity
     */
    RoutingQuery save(RoutingQuery routingQuery);

    /**
     * Get all the routingQuerys.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<RoutingQuery> findAll(Pageable pageable);

    /**
     * Get the "id" routingQuery.
     *
     * @param id the id of the entity
     * @return the entity
     */
    RoutingQuery findOne(String id);

    /**
     * Delete the "id" routingQuery.
     *
     * @param id the id of the entity
     */
    void delete(String id);
    
    Page<RoutingQuery> findCustom(RoutingQueryParam routingQueryParam, Pageable pageable);
    Page<RoutingQuery> findCustomJoin(RoutingQueryParam routingQueryParam, Pageable pageable);
    String[][] getRouteDetails(RoutingQuery routingquery);
    RoutingQuery getFullRouteDetails(RoutingQuery routingquery);
}
