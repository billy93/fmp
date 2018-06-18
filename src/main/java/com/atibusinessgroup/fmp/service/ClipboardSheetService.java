package com.atibusinessgroup.fmp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.atibusinessgroup.fmp.domain.ClipboardSheet;
import com.atibusinessgroup.fmp.repository.ClipboardSheetRepository;


/**
 * Service Implementation for managing Clipboard.
 */
@Service
public class ClipboardSheetService {

    private final Logger log = LoggerFactory.getLogger(ClipboardSheetService.class);

    private final ClipboardSheetRepository clipboardSheetRepository;

    public ClipboardSheetService(ClipboardSheetRepository clipboardSheetRepository) {
        this.clipboardSheetRepository = clipboardSheetRepository;
    }

    /**
     * Save a clipboard.
     *
     * @param clipboard the entity to save
     * @return the persisted entity
     */
    public ClipboardSheet save(ClipboardSheet clipboard) {
        log.debug("Request to save Clipboard : {}", clipboard);
        return clipboardSheetRepository.save(clipboard);
    }

    /**
     * Get all the clipboards.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    public Page<ClipboardSheet> findAll(Pageable pageable) {
        log.debug("Request to get all Clipboards");
        return clipboardSheetRepository.findAll(pageable);
    }

    /**
     * Get one clipboard by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public ClipboardSheet findOne(String id) {
        log.debug("Request to get Clipboard : {}", id);
        return clipboardSheetRepository.findOne(id);
    }

    /**
     * Delete the clipboard by id.
     *
     * @param id the id of the entity
     */
    public void delete(String id) {
        log.debug("Request to delete Clipboard : {}", id);
        clipboardSheetRepository.delete(id);
    }

	public ClipboardSheet findOneByUsername(String username) {
		log.debug("Request to get Clipboard by username");
        return clipboardSheetRepository.findOneByUsername(username);
	}
}
