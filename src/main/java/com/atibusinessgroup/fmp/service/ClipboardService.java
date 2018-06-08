package com.atibusinessgroup.fmp.service;

import com.atibusinessgroup.fmp.domain.Clipboard;
import com.atibusinessgroup.fmp.repository.ClipboardRepository;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


/**
 * Service Implementation for managing Clipboard.
 */
@Service
public class ClipboardService {

    private final Logger log = LoggerFactory.getLogger(ClipboardService.class);

    private final ClipboardRepository clipboardRepository;

    public ClipboardService(ClipboardRepository clipboardRepository) {
        this.clipboardRepository = clipboardRepository;
    }

    /**
     * Save a clipboard.
     *
     * @param clipboard the entity to save
     * @return the persisted entity
     */
    public Clipboard save(Clipboard clipboard) {
        log.debug("Request to save Clipboard : {}", clipboard);
        return clipboardRepository.save(clipboard);
    }

    /**
     * Get all the clipboards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<Clipboard> findAll(Pageable pageable) {
        log.debug("Request to get all Clipboards");
        return clipboardRepository.findAll(pageable);
    }

    /**
     * Get one clipboard by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public Clipboard findOne(String id) {
        log.debug("Request to get Clipboard : {}", id);
        return clipboardRepository.findOne(id);
    }

    /**
     * Delete the clipboard by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Clipboard : {}", id);
        clipboardRepository.delete(id);
    }

	public Clipboard findOneByUsername(String username) {
		log.debug("Request to get Clipboard by username");
        return clipboardRepository.findOneByUsername(username);
	}
}
