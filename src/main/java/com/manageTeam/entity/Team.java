package com.manageTeam.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.manageTeam.dto.TeamDto;
import com.manageTeam.exception.GlobalException;

import lombok.Getter;

/*
 * 팀관리 테이블
 * */
@Entity
@Getter
public class Team extends BaseEntity{
	
	@Id @GeneratedValue
	@Column(name = "team_id")
	private Long teamId;
	private String teamName;
	private String city;
	private int memberCount;
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();
	@OneToMany(mappedBy = "team")
	private List<User> users = new ArrayList<>();
	@OneToMany(mappedBy = "team")
	private List<CompetitionTeam> competitionTeams = new ArrayList<>();
	@OneToMany(mappedBy = "team")
	private List<ReservationTeam> reservationTeams = new ArrayList<>();
	
	public Team() {}
	
	public Team(TeamDto.Save team) {
		this.teamId = team.getTeamId();
		this.teamName = team.getTeamName();
		this.city = team.getCity();
		this.memberCount = 0;
		this.activateStatus = team.getActivateStatus();
	}
	
	public void activate() {
		this.activateStatus = ActivateStatus.YES;
	}
	
	public void addMember() {
		this.memberCount++;
	}
	
	public void minusMember() {
		this.memberCount--;
	}
	
	public void checkMemberCnt() {
		int count = this.memberCount;
		if(count<5) {
			throw new GlobalException("팀 인원이 5명 미만입니다.");
		}
	}
}
