package com.atibusinessgroup.fmp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.SystemParameter;
import com.atibusinessgroup.fmp.repository.SystemParameterRepository;
import com.atibusinessgroup.fmp.web.rest.errors.SystemParameterException;


/**
 * Service Implementation for managing SystemParameter.
 */
@Service
public class SystemParameterService {

    private final Logger log = LoggerFactory.getLogger(SystemParameterService.class);

    private final SystemParameterRepository systemParameterRepository;

    public SystemParameterService(SystemParameterRepository systemParameterRepository) {
        this.systemParameterRepository = systemParameterRepository;
    }

    /**
     * Save a systemParameter.
     *
     * @param systemParameter the entity to save
     * @return the persisted entity
     */
    public SystemParameter save(SystemParameter systemParameter) {
        log.debug("Request to save SystemParameter : {}", systemParameter);
        return systemParameterRepository.save(systemParameter);
    }

    /**
     * Get all the systemParameters.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<SystemParameter> findAll(Pageable pageable) {
        log.debug("Request to get all SystemParameters");
        return systemParameterRepository.findAll(pageable);
    }

    /**
     * Get one systemParameter by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public SystemParameter findOne(String id) {
        log.debug("Request to get SystemParameter : {}", id);
        return systemParameterRepository.findOne(id);
    }

    /**
     * Delete the systemParameter by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete SystemParameter : {}", id);
        systemParameterRepository.delete(id);
    }

	public SystemParameter findOneByName(String name) {
		log.debug("Request to find SystemParameter by name: {}", name);
		return systemParameterRepository.findOneByName(name);
	}
	
	public int getParameterNameValue(String parameterName) {
		int value = 0;
		
		try {
			SystemParameter sysParameter = systemParameterRepository.findOneByName(parameterName);
			value = Integer.parseInt(sysParameter.getValue());
		} catch (Exception e) {
			throw new SystemParameterException("Invalid user system parameter name/value (" + parameterName + ")");
		}
		
		return value;
	}
}
