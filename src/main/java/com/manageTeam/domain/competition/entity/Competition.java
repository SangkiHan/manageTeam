package com.manageTeam.domain.competition.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.manageTeam.domain.competition.dto.CompetitionDto;
import com.manageTeam.domain.competitionTeam.entity.CompetitionTeam;
import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.global.entity.ActivateStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @description 대회테이블 Entity
 * @author skhan
 */
@Entity
@Getter
@NoArgsConstructor
public class Competition {
	/**
	 * 대회ID
	 */
	@Id @GeneratedValue
	@Column(name = "competition_id")
	private Long competitionId;
	/**
	 * 대회에 참여 가능한 팀수
	 */
	@Column(name = "team_count")
	private int teamCnt;
	/**
	 * 체육관ID
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "gym_id")
	private Gym gym;
	/**
	 * 활성화 상태
	 */
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;
	/**
	 * 시작 날짜
	 */
	private Date startDate;
	/**
	 * 종료 날짜
	 */
	private Date endDate;
	/**
	 * 대회 참가팀 List
	 */
	@OneToMany(mappedBy = "competition")
	private List<CompetitionTeam> competitionTeams = new ArrayList<>();
	
	public Competition(CompetitionDto.Save competition) {
		this.competitionId = competition.getCompetitionId();
		this.teamCnt = competition.getTeamCnt();
		this.activateStatus = (competition.getActivateStatus()==null)?ActivateStatus.YES:competition.getActivateStatus();
		this.startDate = competition.getStartDate();
		this.endDate = competition.getEndDate();
	}
	/**
	 * 대회 개최 체육관 세팅
	 */
	public void createCompetition(Gym gym) {
		this.gym = gym;
		gym.getCompetition().add(this);
	}
	
	public void cancel() {
		this.activateStatus = ActivateStatus.NO;
		competitionTeams.forEach(competitionTeam -> competitionTeam.cancel());
	}
}
