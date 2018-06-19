package com.atibusinessgroup.fmp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoCcfParcity;

/**
 * Spring Data MongoDB repository for the AtpcoCcfParcity entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AtpcoCcfParcityRepository extends MongoRepository<AtpcoCcfParcity, String> {

}
