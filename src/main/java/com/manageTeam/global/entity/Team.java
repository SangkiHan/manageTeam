package com.manageTeam.global.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.manageTeam.domain.team.dto.TeamDto;
import com.manageTeam.global.exception.GlobalException;

import lombok.Getter;

/**
 * @description 팀 테이블 Entity
 * @author skhan
 */
@Entity
@Getter
public class Team extends BaseEntity{
	/**
	 * 팀 ID
	 */
	@Id @GeneratedValue
	@Column(name = "team_id")
	private Long teamId;
	/**
	 * 팀명
	 */
	private String teamName;
	/**
	 * 도시
	 */
	private String city;
	/**
	 * 속해있는 팀원 수
	 */
	private int memberCount;
	/**
	 * 활성화 상태
	 */
	@Enumerated(EnumType.STRING)
	private ActivateStatus activateStatus;
	/**
	 * 속해있는 팀원 List
	 */
	@OneToMany(mappedBy = "team")
	private List<Member> members = new ArrayList<>();
	/**
	 * 속해있는 관리자 List
	 */
	@OneToMany(mappedBy = "team")
	private List<User> users = new ArrayList<>();
	/**
	 * 참가되어있는 대회
	 */
	@OneToMany(mappedBy = "team")
	private List<CompetitionTeam> competitionTeams = new ArrayList<>();
	@OneToMany(mappedBy = "team")
	private List<ReservationTeam> reservationTeams = new ArrayList<>();
	
	public Team() {}
	
	/**
	 * Dto to Entity Constructor
	 */
	public Team(TeamDto.Save team) {
		this.teamId = team.getTeamId();
		this.teamName = team.getTeamName();
		this.city = team.getCity();
		this.memberCount = 0;
	}
	/**
	 * @description 팀의 활성화 상태를 바꿔준다.
	 * @author skhan
	 */
	public void setStatus(ActivateStatus activateStatus) {
		this.activateStatus = activateStatus;
	}
	/**
	 * @description 팀의 총원수를 +1 해준다.
	 * @author skhan
	 */
	public void addMember() {
		this.memberCount++;
	}
	/**
	 * @description 팀의 총원수를 -1 해준다.
	 * @author skhan
	 */
	public void minusMember() {
		this.memberCount--;
	}
	/**
	 * @description 팀의 인원수를 체크한다. 농구는 5명 미만으로 경기를 할 수 없다.
	 * @author skhan
	 */
	public void checkMemberCnt() {
		int count = this.memberCount;
		if(count<5) {
			throw new GlobalException("팀 인원이 5명 미만입니다.");
		}
	}
}
