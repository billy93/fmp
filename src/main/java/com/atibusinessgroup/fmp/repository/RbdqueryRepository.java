package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.RbdQuery;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Rbdquery entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RbdqueryRepository extends MongoRepository<RbdQuery, String> {

}
