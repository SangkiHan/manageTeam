package com.manageTeam.dto;

import com.manageTeam.entity.Gym;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.ToString;

public class GymDto {
	
	@ApiModel(value = "체육관 등록,수정 Dto")
	@Getter
	public static class Save{
		private Long gymId;
		private String gymName;
		private AddressDto address;

		public Save(String gymName, AddressDto address) {
			this.gymName = gymName;
			this.address = address;
		}
	}
	
	@ApiModel(value = "체육관 정보 Dto")
	@Getter
	@ToString
	public static class Info{
		private Long gymId;
		private String gymName;
		private AddressDto address;
		
		public Info(Gym gym) {
			this.gymId = gym.getGymId();
			this.gymName = gym.getGymName();
			this.address = new AddressDto(gym.getAddress());
		}
	}

}
