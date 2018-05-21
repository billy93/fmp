package com.atibusinessgroup.fmp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atibusinessgroup.fmp.domain.WorkPackage;
import com.atibusinessgroup.fmp.web.rest.WorkPackageResource.WorkPackageFilter;

public interface WorkPackageRepositoryCustomAnyName {
	
	Page<WorkPackage> findCustom(WorkPackageFilter wpFilter, Pageable pageable);
}
