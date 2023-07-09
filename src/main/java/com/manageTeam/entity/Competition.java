package com.manageTeam.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;

/**
 * @description 대회테이블 Entity
 * @author skhan
 */
@Entity
@Getter
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
	 * 대회 개최지
	 */
	@Embedded
	@Column()
	private Address address;
	/**
	 * 활성화 상태
	 */
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;
	/**
	 * 대회 참가팀 List
	 */
	@OneToMany(mappedBy = "competition")
	private List<CompetitionTeam> CompetitionTeams = new ArrayList<>();

}
