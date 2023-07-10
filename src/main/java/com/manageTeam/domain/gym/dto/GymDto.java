package com.manageTeam.domain.gym.dto;

import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.global.dto.AddressDto;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.ToString;

public class GymDto {
	
	@ApiModel(value = "체육관 등록,수정 Dto")
	@Getter
	public static class Save{
		/**
		 * 체육관 ID
		 */
		private Long gymId;
		/**
		 * 체육관 명
		 */
		private String gymName;
		/**
		 * 체육관 주소
		 */
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
	@ToString
	public static class Info{
		/**
		 * 체육관 ID
		 */
		private Long gymId;
		/**
		 * 체육관 명
		 */
		private String gymName;
		/**
		 * 체육관 주소
		 */
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
