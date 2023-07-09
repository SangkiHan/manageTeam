package com.manageTeam.global.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;

/*
 * 대회참가팀 테이블
 * */
@Entity
@Getter
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
}
