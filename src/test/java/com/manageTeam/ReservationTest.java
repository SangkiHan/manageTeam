package com.manageTeam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.manageTeam.dto.GymDto;
import com.manageTeam.dto.ReservationDto;
import com.manageTeam.service.GymService;
import com.manageTeam.service.ReservationService;

@SpringBootTest
public class ReservationTest {
	@Autowired ReservationService reservationService;
	@Autowired GymService gymService;
	
	
	@Test
	void save() {
		GymDto.Info gym = gymService.findById(1L);
//		ReservationDto.Save reservation = new ReservationDto.Save(2,gym,"2023-06-28-18:00","2023-06-28-20:00");
//		reservationService.save(reservation);
	}
}
