package com.atibusinessgroup.fmp.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atibusinessgroup.fmp.domain.DataFeedAirportMapping;
import com.atibusinessgroup.fmp.service.CityCodestoAirportCodesService;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class CityCodestoAirportCodesResource {
	private final Logger log = LoggerFactory.getLogger(CityCodestoAirportCodesResource.class);

	private static final String ENTITY_NAME = "airportMapingDataFeeds";

	private final CityCodestoAirportCodesService cityCodestoAirportCodesService;

	public CityCodestoAirportCodesResource(CityCodestoAirportCodesService cityCodestoAirportCodesService) {
		this.cityCodestoAirportCodesService = cityCodestoAirportCodesService;
	}

	/**
	 * POST /city-codes-to-airport-codes : Create a new dataFeedAirportMapping.
	 *
	 * @param dataFeedAirportMapping
	 *            the dataFeedAirportMapping to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         dataFeedAirportMapping, or with status 400 (Bad Request) if the
	 *         dataFeedAirportMapping has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/city-codes-to-airport-codes")
	@Timed
	public ResponseEntity<DataFeedAirportMapping> createDataFeedAirportMapping(
			@RequestBody DataFeedAirportMapping dataFeedAirportMapping) throws URISyntaxException {
		log.debug("REST request to save DataFeedAirportMapping : {}", dataFeedAirportMapping);
		if (dataFeedAirportMapping.getId() != null) {
			throw new BadRequestAlertException("A new dataFeedAirportMapping cannot already have an ID", ENTITY_NAME,
					"idexists");
		}
		DataFeedAirportMapping result = cityCodestoAirportCodesService.save(dataFeedAirportMapping);
		return ResponseEntity.created(new URI("/api/city-codes-to-airport-codes/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /city-codes-to-airport-codes : Updates an existing
	 * dataFeedAirportMapping.
	 *
	 * @param dataFeedAirportMapping
	 *            the dataFeedAirportMapping to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         dataFeedAirportMapping, or with status 400 (Bad Request) if the
	 *         dataFeedAirportMapping is not valid, or with status 500 (Internal
	 *         Server Error) if the dataFeedAirportMapping couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/city-codes-to-airport-codes")
	@Timed
	public ResponseEntity<DataFeedAirportMapping> updateDataFeedAirportMapping(
			@RequestBody DataFeedAirportMapping dataFeedAirportMapping) throws URISyntaxException {
		log.debug("REST request to update DataFeedAirportMapping : {}", dataFeedAirportMapping);
		if (dataFeedAirportMapping.getId() == null) {
			return createDataFeedAirportMapping(dataFeedAirportMapping);
		}
		DataFeedAirportMapping result = cityCodestoAirportCodesService.save(dataFeedAirportMapping);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dataFeedAirportMapping.getId().toString()))
				.body(result);
	}

	/**
	 * GET /city-codes-to-airport-codes : get all the dataFeedAirportMapping.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         dataFeedAirportMapping in body
	 */
	@GetMapping("/city-codes-to-airport-codes")
	@Timed
	public List<DataFeedAirportMapping> getAllDataFeedAirportMapping() {
		log.debug("REST request to get all DataFeedAirportMapping");
		return cityCodestoAirportCodesService.findAll();
	}

	/**
	 * GET /city-codes-to-airport-codes/:id : get the "id" dataFeedAirportMapping.
	 *
	 * @param id
	 *            the id of the dataFeedAirportMapping to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         dataFeedAirportMapping, or with status 404 (Not Found)
	 */
	@GetMapping("/city-codes-to-airport-codes/{id}")
	@Timed
	public ResponseEntity<DataFeedAirportMapping> getDataFeedAirportMapping(@PathVariable String id) {
		log.debug("REST request to get DataFeedAirportMapping : {}", id);
		DataFeedAirportMapping dataFeedAirportMapping = cityCodestoAirportCodesService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dataFeedAirportMapping));
	}

	/**
	 * DELETE /city-codes-to-airport-codes/:id : delete the "id"
	 * dataFeedAirportMapping.
	 *
	 * @param id
	 *            the id of the dataFeedAirportMapping to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/city-codes-to-airport-codes/{id}")
	@Timed
	public ResponseEntity<Void> deleteDataFeedAirportMapping(@PathVariable String id) {
		log.debug("REST request to delete DataFeedFareBasisGroupMapping : {}", id);
		cityCodestoAirportCodesService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
	}
}
