package com.manageTeam.domain.team.service;

import com.manageTeam.domain.team.dto.TeamConditionDto;
import com.manageTeam.domain.team.dto.TeamResponse;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TeamReadService {

    private final TeamRepository teamRepository;

    public void existsByTeamNameAndCity(String teamName, String city){
        if(teamRepository.existsByTeamNameAndCity(teamName, city)) {
            throw new GlobalException(ErrorCode.TEAM_EXIST);
        }
    }

    public Team findById(Long teamId){
        return teamRepository.findById(teamId)
            .orElseThrow(() -> new GlobalException(ErrorCode.TEAM_UNKNOWN));
    }

    /**
     * @api /api/team/v1/findById
     * @description 팀상세를 조회한다.
     * @author skhan
     */
    public TeamResponse.DetailInfo findOne(Long teamId) {
        return TeamResponse.DetailInfo.of(findById(teamId)) ;
    }

    /**
     * @api /api/team/v1/findAll
     * @description 팀목록을 조회한다.
     * @author skhan
     */
    public Page<TeamResponse.Info> findAllByCondition (TeamConditionDto conditionDto, Pageable pageable){
        return teamRepository.findAllByCondition(conditionDto, pageable);
    }

}
