package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atibusinessgroup.fmp.domain.CityGroup;
import com.atibusinessgroup.fmp.web.rest.CityGroupResource.CityGroupFilter;

public interface CityGroupRepositoryCustomAnyName {

	List<CityGroup> findCustom(CityGroupFilter filter);
	Page<CityGroup> findCustom(CityGroupFilter filter, Pageable pageable);
}
