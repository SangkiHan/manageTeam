package com.manageTeam.dto;

import com.manageTeam.entity.Address;

import lombok.Getter;

public class GymDto {
	
	@Getter
	public static class Save{
		private Long gymId;
		private String gymName;
		private Address address;
		
		public Save() {}

		public Save(String gymName, AddressDto address) {
			this.gymName = gymName;
			this.address = new Address(address);
		}
	}

}
