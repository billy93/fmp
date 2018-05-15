package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.ProductSubtype;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the ProductSubtype entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductSubtypeRepository extends MongoRepository<ProductSubtype, String> {

}
