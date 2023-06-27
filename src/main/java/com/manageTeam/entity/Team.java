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

import lombok.Getter;

/*
 * 팀관리 테이블
 * */
@Entity
@Getter
public class Team extends BaseEntity{
	
	@Id @GeneratedValue
	@Column(name = "team_id")
	private Long id;
	@Column(name = "team_name")
	private String teamName;
	private String city;
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();
	@OneToMany(mappedBy = "team")
	private List<Reservation> reservations = new ArrayList<>();
	@OneToMany(mappedBy = "team")
	private List<CompetitionTeam> CompetitionTeams = new ArrayList<>();
	
	public Team() {}
	
	public Team(TeamDto.Save team) {
		this.id = team.getId();
		this.teamName = team.getTeamName();
		this.city = team.getCity();
		this.activateStatus = ActivateStatus.YES;
	}
}
