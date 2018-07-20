package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.RbdColorMapping;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the RbdColorMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RbdColorMappingRepository extends MongoRepository<RbdColorMapping, String> {

}
