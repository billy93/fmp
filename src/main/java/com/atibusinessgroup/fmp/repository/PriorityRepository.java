package com.atibusinessgroup.fmp.repository;

import com.atibusinessgroup.fmp.domain.Priority;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Spring Data MongoDB repository for the Priority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PriorityRepository extends MongoRepository<Priority, String> {

	List<Priority> findAllOrderByValue();

}
