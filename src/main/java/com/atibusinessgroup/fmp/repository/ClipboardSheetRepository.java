package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.ClipboardSheet;

/**
 * Spring Data MongoDB repository for the Clipboard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClipboardSheetRepository extends MongoRepository<ClipboardSheet, String> {

	ClipboardSheet findOneByUsername(String username);

}
