package com.manageTeam.domain.user.repository;

import java.util.Optional;

import com.manageTeam.domain.user.dto.UserDto;
import com.manageTeam.global.security.CustumUserDetails;

public interface UserRepositoryCustom {
	public CustumUserDetails findUser(String userId);
	public Optional<UserDto.Info> findUserInfo(String userId);
	public boolean existsByRsdntRgnmb(String rsdntRgnmb);
}
