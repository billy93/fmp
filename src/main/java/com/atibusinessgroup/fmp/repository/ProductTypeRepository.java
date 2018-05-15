package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.ProductType;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the ProductType entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductTypeRepository extends MongoRepository<ProductType, String> {

}
