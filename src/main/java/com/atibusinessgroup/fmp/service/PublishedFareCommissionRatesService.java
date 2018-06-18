package com.atibusinessgroup.fmp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.DataFeedPublishedFareRates;
import com.atibusinessgroup.fmp.repository.PublishedFareCommissionRatesRepository;

@Service
public class PublishedFareCommissionRatesService {

	private final Logger log = LoggerFactory.getLogger(PublishedFareCommissionRatesService.class);

	private final PublishedFareCommissionRatesRepository publishedFareCommissionRatesRepository;

	public PublishedFareCommissionRatesService(
			PublishedFareCommissionRatesRepository publishedFareCommissionRatesRepository) {
		this.publishedFareCommissionRatesRepository = publishedFareCommissionRatesRepository;
	}

	/**
	 * Save a dataFeedPublishedFareRates.
	 *
	 * @param dataFeedPublishedFareRates
	 *            the entity to save
	 * @return the persisted entity
	 */
	public DataFeedPublishedFareRates save(DataFeedPublishedFareRates dataFeedPublishedFareRates) {
		log.debug("Request to save DataFeedPublishedFareRates : {}", dataFeedPublishedFareRates);
		return publishedFareCommissionRatesRepository.save(dataFeedPublishedFareRates);
	}

	/**
	 * Get all the dataFeedPublishedFareRates.
	 *
	 * @return the list of entities
	 */
	public List<DataFeedPublishedFareRates> findAll() {
		log.debug("Request to get all DataFeedPublishedFareRates");
		return publishedFareCommissionRatesRepository.findAll();
	}

	/**
	 * Get one dataFeedPublishedFareRates by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	public DataFeedPublishedFareRates findOne(String id) {
		log.debug("Request to get DataFeedPublishedFareRates : {}", id);
		return publishedFareCommissionRatesRepository.findOne(id);
	}

	/**
	 * Delete the dataFeedPublishedFareRates by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	public void delete(String id) {
		log.debug("Request to delete DataFeedPublishedFareRates : {}", id);
		publishedFareCommissionRatesRepository.delete(id);
	}
}
