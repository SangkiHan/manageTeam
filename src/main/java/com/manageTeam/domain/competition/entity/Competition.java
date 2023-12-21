package com.manageTeam.domain.competition.entity;

import com.manageTeam.domain.competition.dto.CompetitionDto;
import com.manageTeam.domain.competitionTeam.entity.CompetitionTeam;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.exception.ErrorCode;
import com.manageTeam.global.exception.GlobalException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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
	private Long competitionId;
	/**
	 * 대회 이름
	 */
	private String competitionName;
	/**
	 * 대회에 참여 가능한 팀수
	 */
	private int teamCnt;
	/**
	 * 대회에 참여한 팀수
	 */
	private int regTeamCnt;
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
	@OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CompetitionTeam> competitionTeams = new ArrayList<>();
	
	public Competition(CompetitionDto.Save competition) {
		this.competitionId = competition.getCompetitionId();
		this.competitionName = competition.getCompetitionName();
		this.teamCnt = competition.getTeamCnt();
		this.activateStatus = (competition.getActivateStatus()==null)?ActivateStatus.YES:competition.getActivateStatus();
		this.startDate = competition.getStartDate();
		this.endDate = competition.getEndDate();
	}

	public void checkTeamCnt(){
		if(teamCnt==regTeamCnt) {
			throw new GlobalException(ErrorCode.COMPETITION_DEADLINE);
		}
	}
	
	public void cancel() {
		this.activateStatus = ActivateStatus.NO;
		competitionTeams.forEach(CompetitionTeam::cancel);
	}

	public void addRegTeamCnt(){
		this.regTeamCnt++;
	}
}
