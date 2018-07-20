package com.atibusinessgroup.fmp.repository.custom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class AtpcoDerivedFareCustomRepository {

	@Autowired
    MongoTemplate mongoTemplate;
}
