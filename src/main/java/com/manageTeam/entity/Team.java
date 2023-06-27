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

import lombok.Getter;

@Entity
@Getter
public class Team extends BaseEntity{
	
	@Id @GeneratedValue
	@Column(name = "team_id")
	private Long id;
	@Column(name = "team_name")
	private String teamName;
	private String activate;
	private String city;
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();
	@OneToMany(mappedBy = "team")
	private List<Reservation> reservations = new ArrayList<>();
	@OneToMany(mappedBy = "team")
	private List<CompetitionTeam> CompetitionTeams = new ArrayList<>();

}
