package com.atibusinessgroup.fmp.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.WorkPackageHistory;
import com.atibusinessgroup.fmp.repository.WorkPackageHistoryRepository;


/**
 * Service Implementation for managing WorkPackage.
 */
@Service
public class WorkPackageHistoryService {

    private final Logger log = LoggerFactory.getLogger(WorkPackageHistoryService.class);

    private final WorkPackageHistoryRepository workPackageHistoryRepository;

    public WorkPackageHistoryService(WorkPackageHistoryRepository workPackageHistoryRepository) {
        this.workPackageHistoryRepository = workPackageHistoryRepository;
    }

    /**
     * Save a workPackage.
     *
     * @param workPackage the entity to save
     * @return the persisted entity
     */
    public WorkPackageHistory save(WorkPackageHistory workPackageHistory) {
        log.debug("Request to save WorkPackageHistory : {}", workPackageHistory);
        return workPackageHistoryRepository.save(workPackageHistory);
    }

    /**
     * Get all the workPackages.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<WorkPackageHistory> findAll(Pageable pageable) {
        log.debug("Request to get all WorkPackageHistoryRepository");
        return workPackageHistoryRepository.findAll(pageable);
    }
    
	public Long getCount() {
		return workPackageHistoryRepository.count();
	}
    /**
     * Get one workPackage by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public WorkPackageHistory findOne(String id) {
        log.debug("Request to get WorkPackageHistory : {}", id);
        return workPackageHistoryRepository.findOne(id);
    }

    /**
     * Delete the workPackage by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete WorkPackage : {}", id);
        workPackageHistoryRepository.delete(id);
    }

	public List<WorkPackageHistory> findAllByWorkPackage(ObjectId objectId) {
		// TODO Auto-generated method stub
		return workPackageHistoryRepository.findAllByWorkPackage(objectId);
	}
}
