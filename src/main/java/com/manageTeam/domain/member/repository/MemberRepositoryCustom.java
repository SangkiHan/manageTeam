package com.manageTeam.domain.member.repository;

import com.manageTeam.domain.member.dto.MemberResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.manageTeam.domain.member.dto.MemberConditionDto;

public interface MemberRepositoryCustom {
	public Page<MemberResponse.Info> findAllByCondition(MemberConditionDto memberConditionDto, Pageable pageable);
	boolean existsByRsdntRgnmb(String rsdntRgnmb);
}
