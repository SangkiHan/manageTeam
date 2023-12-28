package com.manageTeam.domain.user.repository;

import com.manageTeam.global.security.CustumUserDetails;

import java.util.Optional;

public interface UserRepositoryCustom {
	boolean existsByRsdntRgnmb(String rsdntRgnmb);
	CustumUserDetails findUser(String userId);
}
