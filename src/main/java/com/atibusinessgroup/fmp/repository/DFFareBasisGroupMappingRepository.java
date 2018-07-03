package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.DFFareBasisGroupMapping;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the DFFareBasisGroupMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DFFareBasisGroupMappingRepository extends MongoRepository<DFFareBasisGroupMapping, String> {

}
