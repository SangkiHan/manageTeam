package com.manageTeam.domain.competitionTeam.service;

import javax.transaction.Transactional;

import com.manageTeam.domain.competition.service.CompetitionReadService;
import com.manageTeam.domain.competitionTeam.dto.CompetitionTeamRequest;
import com.manageTeam.domain.competitionTeam.dto.CompetitionTeamResponse;
import com.manageTeam.domain.competitionTeam.entity.CompetitionTeam;
import com.manageTeam.domain.team.service.TeamReadService;
import org.springframework.stereotype.Service;

import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.competitionTeam.dto.CompetitionTeamContidtionDto;
import com.manageTeam.domain.competitionTeam.repository.CompetitionTeamRepository;
import com.manageTeam.domain.team.entity.Team;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CompetitionTeamService {
	
	private final CompetitionTeamRepository competitionTeamRepository;
	private final CompetitionTeamReadService competitionTeamReadService;
	private final TeamReadService teamReadService;
	private final CompetitionReadService competitionReadService;
	
	/**
	 * @api /api/competitionTeam/v1/save
	 * @description 대회 참가 신청을 한다.
	 * @author skhan
	 */
	public CompetitionTeamResponse.Save save(CompetitionTeamRequest.Save request) {
		//대회를 조회한다.
		Competition competition = competitionReadService.findById(request.getCompetitionId());
		//대회 참가 신청을 한 팀의 수를 조회한다. 
		competition.checkTeamCnt();

		//해당 날짜에 이미 등록되어있는 대회여부를 조회한다.
		competitionTeamReadService.checkCompetitionTeamDate(CompetitionTeamContidtionDto.checkDate.builder()
			.teamId(request.getTeamId())
			.startDate(competition.getStartDate())
			.endDate(competition.getEndDate())
			.build());

		Team team = teamReadService.findById(request.getTeamId());
		competition.addRegTeamCnt();
		return CompetitionTeamResponse.Save.of(competitionTeamRepository.save(request.toEntity(competition, team)));
	}
	
	/**
	 * @api /api/competitionTeam/v1/cancel
	 * @description 대회 참가를 취소한다.
	 * @author skhan
	 */
	public void cancel(CompetitionTeamRequest.CompetitionTeamId request) {
		competitionReadService.findById(request.getCompetitionTeamId()).cancel();
	}
}
