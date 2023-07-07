package com.manageTeam.repository;

import java.util.Optional;

import com.manageTeam.config.security.CustumUserDetails;
import com.manageTeam.dto.UserDto;

public interface UserRepositoryCustom {
	public CustumUserDetails findUser(String userId);
	public Optional<UserDto.Info> findUserInfo(String userId);
	public boolean existsByRsdntRgnmb(String rsdntRgnmb);
}
