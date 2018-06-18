package com.atibusinessgroup.fmp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.DataFeedAirportMapping;
import com.atibusinessgroup.fmp.repository.CityCodestoAirportCodesRepository;

@Service
public class CityCodestoAirportCodesService {

	private final Logger log = LoggerFactory.getLogger(CityCodestoAirportCodesService.class);

	private final CityCodestoAirportCodesRepository cityCodestoAirportCodesRepository;

	public CityCodestoAirportCodesService(CityCodestoAirportCodesRepository cityCodestoAirportCodesRepository) {
		this.cityCodestoAirportCodesRepository = cityCodestoAirportCodesRepository;
	}

	/**
	 * Save a dataFeedAirportMapping.
	 *
	 * @param dataFeedAirportMapping
	 *            the entity to save
	 * @return the persisted entity
	 */
	public DataFeedAirportMapping save(DataFeedAirportMapping dataFeedAirportMapping) {
		log.debug("Request to save DataFeedAirportMapping : {}", dataFeedAirportMapping);
		return cityCodestoAirportCodesRepository.save(dataFeedAirportMapping);
	}

	/**
	 * Get all the dataFeedAirportMapping.
	 *
	 * @return the list of entities
	 */
	public List<DataFeedAirportMapping> findAll() {
		log.debug("Request to get all DataFeedAirportMapping");
		return cityCodestoAirportCodesRepository.findAll();
	}

	/**
	 * Get one dataFeedAirportMapping by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	public DataFeedAirportMapping findOne(String id) {
		log.debug("Request to get DataFeedAirportMapping : {}", id);
		return cityCodestoAirportCodesRepository.findOne(id);
	}

	/**
	 * Delete the dataFeedAirportMapping by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	public void delete(String id) {
		log.debug("Request to delete DataFeedAirportMapping : {}", id);
		cityCodestoAirportCodesRepository.delete(id);
	}
}
