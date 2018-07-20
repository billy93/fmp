package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.RbdMapping;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the RbdMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RbdMappingRepository extends MongoRepository<RbdMapping, String> {

}
