package com.manageTeam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.manageTeam.dto.MemberConditionDto;
import com.manageTeam.dto.MemberDto;

public interface MemberRepositoryCustom {
	public Page<MemberDto.Info> findAllByCondition(MemberConditionDto memberConditionDto, Pageable pageable);
}
