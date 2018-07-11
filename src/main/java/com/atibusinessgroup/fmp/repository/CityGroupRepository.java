package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.atibusinessgroup.fmp.domain.CityGroup;
import com.atibusinessgroup.fmp.web.rest.CityGroupResource.CityGroupFilter;

/**
 * Spring Data MongoDB repository for the CityGroup entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CityGroupRepository extends MongoRepository<CityGroup, String>, CityGroupRepositoryCustomAnyName {

	CityGroup findOneByCode(String code);
	
	List<CityGroup> findCustom(CityGroupFilter filter);

}
