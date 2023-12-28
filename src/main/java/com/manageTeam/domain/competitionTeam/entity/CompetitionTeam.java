package com.manageTeam.domain.competitionTeam.entity;

import com.manageTeam.domain.competition.entity.Competition;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.global.entity.ActivateStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 대회참가팀 테이블
 * */
@Entity
@Getter
@NoArgsConstructor
public class CompetitionTeam {
	
	@Id @GeneratedValue
	private Long competitionTeamId;
	@ManyToOne(fetch = FetchType.LAZY)
	private Team team;
	@ManyToOne(fetch = FetchType.LAZY)
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
}
