package com.atibusinessgroup.fmp.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.atibusinessgroup.fmp.domain.Form;

/**
 * Spring Data MongoDB repository for the Form entity.
 */
public interface FormRepository extends MongoRepository<Form,String> {
	Optional<Form> findOneById(String formId);
	Optional<Form> findOneByTag(String tag);
}
