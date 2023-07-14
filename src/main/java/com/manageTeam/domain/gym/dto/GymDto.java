package com.manageTeam.domain.gym.dto;

import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.global.dto.AddressDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class GymDto {
	
	@ApiModel(value = "체육관 등록,수정 Dto")
	@Getter
	@NoArgsConstructor
	public static class Save{
		
		@ApiParam(value = "체육관ID")
		private Long gymId;
		
		@ApiParam(value = "체육관 명")
		private String gymName;
		
		@ApiParam(value = "체육관 주소")
		private AddressDto address;
		
		/**
		 * Entity to Dto Constructor
		 */
		public Save(String gymName, AddressDto address) {
			this.gymName = gymName;
			this.address = address;
		}
	}
	
	@ApiModel(value = "체육관 정보 Dto")
	@Getter
	@NoArgsConstructor
	public static class Info{
		
		@ApiParam(value = "체육관ID")
		private Long gymId;
		
		@ApiParam(value = "체육관 명")
		private String gymName;
		
		@ApiParam(value = "체육관 주소")
		private AddressDto address;
		
		/**
		 * Entity to Dto Constructor
		 */
		public Info(Gym gym) {
			this.gymId = gym.getGymId();
			this.gymName = gym.getGymName();
			this.address = new AddressDto(gym.getAddress());
		}
	}

}
