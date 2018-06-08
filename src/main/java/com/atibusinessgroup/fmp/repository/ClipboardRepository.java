package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.Clipboard;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Clipboard entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ClipboardRepository extends MongoRepository<Clipboard, String> {

	Clipboard findOneByUsername(String username);

}
