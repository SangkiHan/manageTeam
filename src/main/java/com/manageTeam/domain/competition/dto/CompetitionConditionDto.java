package com.manageTeam.domain.competition.dto;

import java.sql.Date;

import com.manageTeam.global.entity.ActivateStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompetitionConditionDto {
	
	@ApiParam(value = "시작 날짜")
	private Date startDate;
	
	@ApiParam(value = "종료 날짜")
	private Date endDate;
	
	@ApiParam(value = "활성화 상태")
	private ActivateStatus activateStatus;
	
	@ApiParam(value = "개최지")
	private String city;
	
	@ApiModel(value = "등록되어있는 대회 날짜 체크 DTO")
	@Getter
	public static class DateCheck{
		
		@ApiParam(value = "체육관ID")
		private Long gymId;
		
		@ApiParam(value = "시작 날짜")
		private Date startDate;
		
		@ApiParam(value = "종료 날짜")
		private Date endDate;
		
		public DateCheck(CompetitionDto.Save competition) {
			this.gymId = competition.getGymId();
			this.startDate = competition.getStartDate();
			this.endDate = competition.getEndDate();
		}
	}
}
