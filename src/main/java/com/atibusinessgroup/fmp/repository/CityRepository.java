package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.City;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the City entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CityRepository extends MongoRepository<City, String> {

}
