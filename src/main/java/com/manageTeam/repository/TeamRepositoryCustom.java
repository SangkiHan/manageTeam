package com.manageTeam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.manageTeam.dto.MemberConditionDto;
import com.manageTeam.dto.TeamDto;
import com.manageTeam.entity.Position;

public interface TeamRepositoryCustom {
	public Page<TeamDto.Info> findAllByCondition(MemberConditionDto conditionDto, Pageable pageable);
}
