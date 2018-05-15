package com.atibusinessgroup.fmp.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.WorkPackageFare;
import com.atibusinessgroup.fmp.repository.WorkPackageFareRepository;

/**
 * Service Implementation for managing Fare.
 */
@Service
public class WorkPackageFareService {

    private final Logger log = LoggerFactory.getLogger(WorkPackageFareService.class);

    private final WorkPackageFareRepository workPackageFareRepository;

    public WorkPackageFareService(WorkPackageFareRepository workPackageFareRepository) {
        this.workPackageFareRepository = workPackageFareRepository;
    }

    /**
     * Save a WorkPackageFare.
     *
     * @param WorkPackageFare the entity to save
     * @return the persisted entity
     */
    public WorkPackageFare save(WorkPackageFare fare) {
        log.debug("Request to save WorkPackageFare : {}", fare);
        return workPackageFareRepository.save(fare);
    }

    /**
     * Get all the WorkPackageFare.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<WorkPackageFare> findAll(Pageable pageable) {
        log.debug("Request to get all WorkPackageFares");
        return workPackageFareRepository.findAll(pageable);
    }

    /**
     * Get one WorkPackageFare by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public WorkPackageFare findOne(String id) {
        log.debug("Request to get WorkPackageFare : {}", id);
        return workPackageFareRepository.findOne(id);
    }

    /**
     * Delete the WorkPackageFare by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete WorkPackageFare : {}", id);
        workPackageFareRepository.delete(id);
    }

	public List<WorkPackageFare> findAllByWorkPackage(String id) {
		// TODO Auto-generated method stub
		return workPackageFareRepository.findAllByWorkPackage(new ObjectId(id));
	}

	public List<WorkPackageFare> findAllByWorkPackageAndFareType(String id, String string) {
		// TODO Auto-generated method stub
		return workPackageFareRepository.findAllByWorkPackageAndFareType(new ObjectId(id), string);
	}

	public List<WorkPackageFare> findAllByOriginAndDestinationAndFareBasis(String origin, String destination,
			String fareBasis) {
		// TODO Auto-generated method stub
		return workPackageFareRepository.findAllByOriginAndDestinationAndFareBasis(origin, destination, fareBasis);
	}
}
