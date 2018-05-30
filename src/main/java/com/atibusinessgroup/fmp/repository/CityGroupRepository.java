package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.CityGroup;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the CityGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CityGroupRepository extends MongoRepository<CityGroup, String> {

}
