package com.manageTeam.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.manageTeam.dto.TeamConditionDto;
import com.manageTeam.dto.TeamDto;

public interface TeamRepositoryCustom {
	public Page<TeamDto.Info> findAllByCondition(TeamConditionDto conditionDto, Pageable pageable);
}
