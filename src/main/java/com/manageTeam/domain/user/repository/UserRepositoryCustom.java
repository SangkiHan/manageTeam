package com.manageTeam.domain.user.repository;

import com.manageTeam.global.security.CustumUserDetails;

import java.util.Optional;

public interface UserRepositoryCustom {
	public boolean existsByRsdntRgnmb(String rsdntRgnmb);
	public CustumUserDetails findUser(String userId);
}
