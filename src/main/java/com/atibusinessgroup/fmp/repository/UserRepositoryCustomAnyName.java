package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atibusinessgroup.fmp.service.dto.UserDTO;
import com.atibusinessgroup.fmp.web.rest.UserResource.UserFilter;

public interface UserRepositoryCustomAnyName {
	
	Page<UserDTO> findCustom(UserFilter filter, Pageable pageable);
	List<UserDTO> findCustom(UserFilter filter);
}
