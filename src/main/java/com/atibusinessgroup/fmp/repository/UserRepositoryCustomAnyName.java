package com.atibusinessgroup.fmp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.atibusinessgroup.fmp.service.dto.UserDTO;

public interface UserRepositoryCustomAnyName {
	
	Page<UserDTO> findCustom(UserDTO filter, Pageable pageable);
	List<UserDTO> findCustom(UserDTO filter);
}
