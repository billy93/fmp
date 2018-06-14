package com.atibusinessgroup.fmp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.DataFeedOriginDestination;
import com.atibusinessgroup.fmp.repository.OriginDestinationRepository;

@Service
public class OriginDestinationService {

	private final Logger log = LoggerFactory.getLogger(OriginDestinationService.class);

	private final OriginDestinationRepository originDestinationRepository;

	public OriginDestinationService(OriginDestinationRepository originDestinationRepository) {
		this.originDestinationRepository = originDestinationRepository;
	}

	/**
	 * Save a dataFeedOriginDestination.
	 *
	 * @param dataFeedOriginDestination
	 *            the entity to save
	 * @return the persisted entity
	 */
	public DataFeedOriginDestination save(DataFeedOriginDestination dataFeedOriginDestination) {
		log.debug("Request to save DataFeedOriginDestination : {}", dataFeedOriginDestination);
		return originDestinationRepository.save(dataFeedOriginDestination);
	}

	/**
	 * Get all the dataFeedOriginDestination.
	 *
	 * @return the list of entities
	 */
	public List<DataFeedOriginDestination> findAll() {
		log.debug("Request to get all DataFeedOriginDestination");
		return originDestinationRepository.findAll();
	}

	/**
	 * Get one dataFeedOriginDestination by id.
	 *
	 * @param id
	 *            the id of the entity
	 * @return the entity
	 */
	public DataFeedOriginDestination findOne(String id) {
		log.debug("Request to get DataFeedOriginDestination : {}", id);
		return originDestinationRepository.findOne(id);
	}

	/**
	 * Delete the dataFeedOriginDestination by id.
	 *
	 * @param id
	 *            the id of the entity
	 */
	public void delete(String id) {
		log.debug("Request to delete DataFeedOriginDestination : {}", id);
		originDestinationRepository.delete(id);
	}
}
