package com.manageTeam.domain.competitionTeam.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.global.entity.ActivateStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/*
 * 대회참가팀 테이블
 * */
@Entity
@Getter
@NoArgsConstructor
public class CompetitionTeam {
	
	@Id @GeneratedValue
	@Column(name = "competition_team_id")
	private Long competitionTeamId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private Team team;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "competition_id")
	private Competition competition;
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;

	@Builder
	private CompetitionTeam(Team team, Competition competition, ActivateStatus activateStatus) {
		this.team = team;
		this.competition = competition;
		this.activateStatus = activateStatus;
	}

	/**
	 * 대회 참가 취소 
	 */
	public void cancel() {
		this.activateStatus = ActivateStatus.NO;
	}

	public void updateTeam(Team team){
		this.team = team;
	}
	
	public void createCompetition(Competition competition) {
		this.competition = competition;
		competition.getCompetitionTeams().add(this);
	}
}
