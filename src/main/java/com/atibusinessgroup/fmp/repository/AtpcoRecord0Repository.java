package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.atpco.AtpcoRecord0;

@Repository
public interface AtpcoRecord0Repository extends MongoRepository<AtpcoRecord0, String> {

	List<AtpcoRecord0> findAllByRecordId(String string);

}
