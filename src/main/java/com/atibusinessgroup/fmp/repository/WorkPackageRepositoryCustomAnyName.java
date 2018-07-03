package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atibusinessgroup.fmp.domain.WorkPackage;
import com.atibusinessgroup.fmp.domain.WorkPackageFilter;
import com.atibusinessgroup.fmp.domain.dto.AfdQueryParam;
import com.atibusinessgroup.fmp.domain.dto.WorkPackageMarketFare;

public interface WorkPackageRepositoryCustomAnyName {
	
	Page<WorkPackage> findCustom(WorkPackageFilter wpFilter, Pageable pageable);

	List<WorkPackage> findCustom(WorkPackageFilter workPackageFilter);

	Page<WorkPackageMarketFare> findAllMarketFare(AfdQueryParam param, Pageable pageable);
}
