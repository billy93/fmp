package com.atibusinessgroup.fmp.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.WorkPackage;
import com.atibusinessgroup.fmp.domain.enumeration.Status;
import com.atibusinessgroup.fmp.repository.WorkPackageRepository;


/**
 * Service Implementation for managing WorkPackage.
 */
@Service
public class WorkPackageService {

    private final Logger log = LoggerFactory.getLogger(WorkPackageService.class);

    private final WorkPackageRepository workPackageRepository;

    public WorkPackageService(WorkPackageRepository workPackageRepository) {
        this.workPackageRepository = workPackageRepository;
    }

    /**
     * Save a workPackage.
     *
     * @param workPackage the entity to save
     * @return the persisted entity
     */
    public WorkPackage save(WorkPackage workPackage) {
        log.debug("Request to save WorkPackage : {}", workPackage);
        return workPackageRepository.save(workPackage);
    }

    /**
     * Get all the workPackages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<WorkPackage> findAll(Pageable pageable) {
        log.debug("Request to get all WorkPackages");
        return workPackageRepository.findAll(pageable);
    }
    
	public Long getCount() {
		return workPackageRepository.count();
	}
    /**
     * Get one workPackage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public WorkPackage findOne(String id) {
        log.debug("Request to get WorkPackage : {}", id);
        return workPackageRepository.findOne(id);
    }

    /**
     * Delete the workPackage by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete WorkPackage : {}", id);
        workPackageRepository.delete(id);
    }

	public List<WorkPackage> findAllByStatus(Status status) {
		log.debug("Request to get all WorkPackages by status");
        return workPackageRepository.findAllByStatus(status.toString());
	}

	public Page<WorkPackage> findAllByOrderByLastModifiedDate(Pageable pageable) {
		log.debug("Request to get all WorkPackages");
        return workPackageRepository.findAllByOrderByLastModifiedDateDesc(pageable);
	}
}
