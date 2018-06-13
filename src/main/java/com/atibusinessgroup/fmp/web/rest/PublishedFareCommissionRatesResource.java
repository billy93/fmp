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

import com.atibusinessgroup.fmp.domain.DataFeedPublishedFareRates;
import com.atibusinessgroup.fmp.service.PublishedFareCommissionRatesService;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class PublishedFareCommissionRatesResource {
	private final Logger log = LoggerFactory.getLogger(PublishedFareCommissionRatesResource.class);

	private static final String ENTITY_NAME = "dataFeedPublishedFareRates";

	private final PublishedFareCommissionRatesService publishedFareCommissionRatesService;

	public PublishedFareCommissionRatesResource(
			PublishedFareCommissionRatesService publishedFareCommissionRatesService) {
		this.publishedFareCommissionRatesService = publishedFareCommissionRatesService;
	}

	/**
	 * POST /published-fare-commission-rates : Create a new
	 * dataFeedPublishedFareRates.
	 *
	 * @param dataFeedPublishedFareRates
	 *            the dataFeedPublishedFareRates to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         dataFeedPublishedFareRates, or with status 400 (Bad Request) if the
	 *         dataFeedPublishedFareRates has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/published-fare-commission-rates")
	@Timed
	public ResponseEntity<DataFeedPublishedFareRates> createDataFeedPublishedFareRates(
			@RequestBody DataFeedPublishedFareRates dataFeedPublishedFareRates) throws URISyntaxException {
		log.debug("REST request to save DataFeedPublishedFareRates : {}", dataFeedPublishedFareRates);
		if (dataFeedPublishedFareRates.getId() != null) {
			throw new BadRequestAlertException("A new dataFeedPublishedFareRates cannot already have an ID",
					ENTITY_NAME, "idexists");
		}
		DataFeedPublishedFareRates result = publishedFareCommissionRatesService.save(dataFeedPublishedFareRates);
		return ResponseEntity.created(new URI("/api/city-codes-to-airport-codes/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /published-fare-commission-rates : Updates an existing
	 * dataFeedPublishedFareRates.
	 *
	 * @param dataFeedPublishedFareRates
	 *            the dataFeedPublishedFareRates to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         dataFeedPublishedFareRates, or with status 400 (Bad Request) if the
	 *         dataFeedPublishedFareRates is not valid, or with status 500 (Internal
	 *         Server Error) if the dataFeedAirportMapping couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/published-fare-commission-rates")
	@Timed
	public ResponseEntity<DataFeedPublishedFareRates> updateDataFeedPublishedFareRates(
			@RequestBody DataFeedPublishedFareRates dataFeedPublishedFareRates) throws URISyntaxException {
		log.debug("REST request to update DataFeedPublishedFareRates : {}", dataFeedPublishedFareRates);
		if (dataFeedPublishedFareRates.getId() == null) {
			return createDataFeedPublishedFareRates(dataFeedPublishedFareRates);
		}
		DataFeedPublishedFareRates result = publishedFareCommissionRatesService.save(dataFeedPublishedFareRates);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dataFeedPublishedFareRates.getId().toString()))
				.body(result);
	}

	/**
	 * GET /published-fare-commission-rates : get all the
	 * dataFeedPublishedFareRates.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         dataFeedPublishedFareRates in body
	 */
	@GetMapping("/published-fare-commission-rates")
	@Timed
	public List<DataFeedPublishedFareRates> getAllDataFeedPublishedFareRates() {
		log.debug("REST request to get all DataFeedPublishedFareRates");
		return publishedFareCommissionRatesService.findAll();
	}

	/**
	 * GET /published-fare-commission-rates/:id : get the "id"
	 * dataFeedPublishedFareRates.
	 *
	 * @param id
	 *            the id of the dataFeedPublishedFareRates to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         dataFeedPublishedFareRates, or with status 404 (Not Found)
	 */
	@GetMapping("/published-fare-commission-rates/{id}")
	@Timed
	public ResponseEntity<DataFeedPublishedFareRates> getDataFeedPublishedFareRates(@PathVariable String id) {
		log.debug("REST request to get DataFeedPublishedFareRates : {}", id);
		DataFeedPublishedFareRates dataFeedPublishedFareRates = publishedFareCommissionRatesService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dataFeedPublishedFareRates));
	}

	/**
	 * DELETE /published-fare-commission-rates/:id : delete the "id"
	 * dataFeedPublishedFareRates.
	 *
	 * @param id
	 *            the id of the dataFeedPublishedFareRates to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/published-fare-commission-rates/{id}")
	@Timed
	public ResponseEntity<Void> deleteDataFeedPublishedFareRates(@PathVariable String id) {
		log.debug("REST request to delete DataFeedPublishedFareRates : {}", id);
		publishedFareCommissionRatesService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
	}
}
