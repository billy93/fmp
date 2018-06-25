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

import com.atibusinessgroup.fmp.domain.DataFeedOriginDestination;
import com.atibusinessgroup.fmp.service.OriginDestinationService;
import com.atibusinessgroup.fmp.web.rest.errors.BadRequestAlertException;
import com.atibusinessgroup.fmp.web.rest.util.HeaderUtil;
import com.codahale.metrics.annotation.Timed;

import io.github.jhipster.web.util.ResponseUtil;

@RestController
@RequestMapping("/api")
public class OriginDestinationResource {

	private final Logger log = LoggerFactory.getLogger(OriginDestinationResource.class);

	private static final String ENTITY_NAME = "dataFeedOriginDestination";

	private final OriginDestinationService originDestinationService;

	public OriginDestinationResource(OriginDestinationService originDestinationService) {
		this.originDestinationService = originDestinationService;
	}

	/**
	 * POST /origin-destination : Create a new dataFeedOriginDestination.
	 *
	 * @param dataFeedOriginDestination
	 *            the dataFeedOriginDestination to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 *         dataFeedOriginDestination, or with status 400 (Bad Request) if the
	 *         dataFeedOriginDestination has already an ID
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PostMapping("/origin-destination")
	@Timed
	public ResponseEntity<DataFeedOriginDestination> createDataFeedOriginDestination(
			@RequestBody DataFeedOriginDestination dataFeedOriginDestination) throws URISyntaxException {
		log.debug("REST request to save DataFeedOriginDestination : {}", dataFeedOriginDestination);
		if (dataFeedOriginDestination.getId() != null) {
			throw new BadRequestAlertException("A new dataFeedOriginDestination cannot already have an ID", ENTITY_NAME,
					"idexists");
		}
		DataFeedOriginDestination result = originDestinationService.save(dataFeedOriginDestination);
		return ResponseEntity.created(new URI("/api/city-codes-to-airport-codes/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);
	}

	/**
	 * PUT /origin-destination : Updates an existing dataFeedOriginDestination.
	 *
	 * @param dataFeedOriginDestination
	 *            the dataFeedOriginDestination to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 *         dataFeedOriginDestination, or with status 400 (Bad Request) if the
	 *         dataFeedOriginDestination is not valid, or with status 500 (Internal
	 *         Server Error) if the dataFeedOriginDestination couldn't be updated
	 * @throws URISyntaxException
	 *             if the Location URI syntax is incorrect
	 */
	@PutMapping("/origin-destination")
	@Timed
	public ResponseEntity<DataFeedOriginDestination> updateDataFeedOriginDestination(
			@RequestBody DataFeedOriginDestination dataFeedOriginDestination) throws URISyntaxException {
		log.debug("REST request to update DataFeedOriginDestination : {}", dataFeedOriginDestination);
		if (dataFeedOriginDestination.getId() == null) {
			return createDataFeedOriginDestination(dataFeedOriginDestination);
		}
		DataFeedOriginDestination result = originDestinationService.save(dataFeedOriginDestination);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, dataFeedOriginDestination.getId().toString()))
				.body(result);
	}

	/**
	 * GET /origin-destination : get all the dataFeedOriginDestination.
	 *
	 * @return the ResponseEntity with status 200 (OK) and the list of
	 *         dataFeedOriginDestination in body
	 */
	@GetMapping("/origin-destination")
	@Timed
	public List<DataFeedOriginDestination> getAllDataFeedOriginDestination() {
		log.debug("REST request to get all DataFeedOriginDestination");
		return originDestinationService.findAll();
	}

	/**
	 * GET /origin-destination/:id : get the "id" dataFeedOriginDestination.
	 *
	 * @param id
	 *            the id of the dataFeedOriginDestination to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the
	 *         dataFeedOriginDestination, or with status 404 (Not Found)
	 */
	@GetMapping("/origin-destination/{id}")
	@Timed
	public ResponseEntity<DataFeedOriginDestination> getDataFeedOriginDestination(@PathVariable String id) {
		log.debug("REST request to get DataFeedOriginDestination : {}", id);
		DataFeedOriginDestination dataFeedOriginDestination = originDestinationService.findOne(id);
		return ResponseUtil.wrapOrNotFound(Optional.ofNullable(dataFeedOriginDestination));
	}

	/**
	 * DELETE /origin-destination/:id : delete the "id" dataFeedOriginDestination.
	 *
	 * @param id
	 *            the id of the dataFeedOriginDestination to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/origin-destination/{id}")
	@Timed
	public ResponseEntity<Void> deleteDataFeedOriginDestination(@PathVariable String id) {
		log.debug("REST request to delete DataFeedOriginDestination : {}", id);
		originDestinationService.delete(id);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id)).build();
	}
}
