package com.manageTeam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.manageTeam.dto.ReservationTeamDto;
import com.manageTeam.service.ReservationTeamService;

@SpringBootTest
public class ReservationTeamTest {
	
	@Autowired private ReservationTeamService reservationTeamServices;
	
	@Test
	void save() {
		ReservationTeamDto.Save save = new ReservationTeamDto.Save(3L, 2L);
		reservationTeamServices.save(save);
	}

}
