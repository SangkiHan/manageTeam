package com.manageTeam.dto;

import com.manageTeam.entity.Gym;

import lombok.Getter;
import lombok.ToString;

public class GymDto {
	
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
