package com.manageTeam.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;

/*
 * 대회 테이블
 * */
@Entity
@Getter
public class Competition {
	
	@Id @GeneratedValue
	@Column(name = "competition_id")
	private Long competitionId;
	@Column(name = "team_count")
	private int teamCnt;
	@Embedded
	private Address address;
	private ActivateStatus activateStatus;
	@OneToMany(mappedBy = "competition")
	private List<CompetitionTeam> CompetitionTeams = new ArrayList<>();

}
