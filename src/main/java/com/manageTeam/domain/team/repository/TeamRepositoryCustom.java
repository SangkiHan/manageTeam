package com.manageTeam.domain.team.repository;

import com.manageTeam.domain.team.dto.TeamConditionDto;
import com.manageTeam.domain.team.dto.TeamResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeamRepositoryCustom {
	public Page<TeamResponse.Info> findAllByCondition(TeamConditionDto conditionDto, Pageable pageable);

}
