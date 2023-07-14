package com.manageTeam.domain.team.service;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.manageTeam.domain.team.dto.TeamConditionDto;
import com.manageTeam.domain.team.dto.TeamDto;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {
	
	private final TeamRepository teamRepository;
	
	/**
	 * @api /api/team/v1/save
	 * @description 팀 저장 및 수정한다.
	 * @author skhan
	 */
	public void save(TeamDto.Save request) {
		//이미 등록된 팀인지 체크한다.
		if(!teamRepository.checkTeamExist(request)) {
			throw new GlobalException(ErrorCode.TEAM_EXIST);
		}
		Team team = new Team(request);
		team.setStatus(request.getActivateStatus());
		teamRepository.save(team);
	}
	
	/**
	 * @api /api/team/v1/status
	 * @description 팀의 활성화 상태를 번경한다.
	 * @throws GlobalException
	 * @author skhan
	 */
	public void status(TeamDto.Status request) {
		Team team = teamRepository.findById(request.getTeamId())
				.orElseThrow(() -> new GlobalException(ErrorCode.TEAM_UNKNOWN));
		team.setStatus(request.getActivateStatus());
	}
	
	/**
	 * @api /api/team/v1/findById
	 * @description 팀상세를 조회한다.
	 * @author skhan
	 */
	public TeamDto.DetailInfo findOne(TeamDto.TeamId request) {
		return teamRepository.findOne(request.getTeamId());
	}
	
	/**
	 * @api /api/team/v1/findAll
	 * @description 팀목록을 조회한다.
	 * @author skhan
	 */
	public Page<TeamDto.Info> findAllByCondition (TeamConditionDto conditionDto, Pageable pageable){
		return teamRepository.findAllByCondition(conditionDto, pageable);
	}
}
