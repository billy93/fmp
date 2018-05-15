package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.ProductTypeSubType;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the ProductTypeSubType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductTypeSubTypeRepository extends MongoRepository<ProductTypeSubType, String> {

}
