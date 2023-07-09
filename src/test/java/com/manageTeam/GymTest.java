package com.manageTeam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.manageTeam.domain.gym.dto.GymDto;
import com.manageTeam.domain.gym.service.GymService;
import com.manageTeam.dto.AddressDto;

@SpringBootTest
public class GymTest {
	
	@Autowired private GymService gymService;
	
	@Test
	void save() {
		AddressDto addressDto = new AddressDto("용인시","테스트로","12345");
		GymDto.Save gym = new GymDto.Save("비트바스켓볼",addressDto);
		gymService.save(gym);
	}
	
	@Test
	void findById() {
		GymDto.Info result = gymService.findById(1L);
		System.out.println(result);
	}
}
