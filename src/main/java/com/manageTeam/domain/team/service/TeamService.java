package com.manageTeam.domain.team.service;

import com.manageTeam.domain.team.dto.TeamConditionDto;
import com.manageTeam.domain.team.dto.TeamRequest;
import com.manageTeam.domain.team.dto.TeamResponse;
import com.manageTeam.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class TeamService {
	
	private final TeamRepository teamRepository;
	private final TeamReadService teamReadService;

	/**
	 * @api /api/team/v1/save
	 * @description 팀 저장 및 수정한다.
	 * @author skhan
	 */
	public TeamResponse.Save save(TeamRequest.Save request) {
		//이미 등록된 팀인지 체크한다.
		teamReadService.existsByTeamNameAndCity(request.getTeamName(), request.getCity());
		return TeamResponse.Save.of(teamRepository.save(request.toEntity()));
	}
	
	/**
	 * @api /api/team/v1/status
	 * @description 팀의 활성화 상태를 번경한다.
	 * @author skhan
	 */
	public void status(TeamRequest.Status request) {
		teamReadService.findById(request.getTeamId()).setStatus(request.getActivateStatus());
	}
	
	/**
	 * @api /api/team/v1/findById
	 * @description 팀상세를 조회한다.
	 * @author skhan
	 */
	public TeamResponse.DetailInfo findOne(Long teamId) {
		return teamRepository.findOne(teamId);
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
