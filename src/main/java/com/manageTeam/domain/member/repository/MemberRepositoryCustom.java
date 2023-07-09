package com.manageTeam.domain.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.manageTeam.domain.member.dto.MemberConditionDto;
import com.manageTeam.domain.member.dto.MemberDto;

public interface MemberRepositoryCustom {
	public Page<MemberDto.Info> findAllByCondition(MemberConditionDto memberConditionDto, Pageable pageable);
	public boolean existsByRsdntRgnmb(String rsdntRgnmb);
}
