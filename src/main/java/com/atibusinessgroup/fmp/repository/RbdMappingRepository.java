package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.RbdMapping;

/**
 * Spring Data MongoDB repository for the RbdMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RbdMappingRepository extends MongoRepository<RbdMapping, String> {

	List<RbdMapping> findAllByOalCxrIn(List<String> carriers);

}
