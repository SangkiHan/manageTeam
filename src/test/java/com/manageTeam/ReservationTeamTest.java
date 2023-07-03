package com.manageTeam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.manageTeam.dto.ReservationTeamDto;
import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.service.ReservationTeamService;

@SpringBootTest
public class ReservationTeamTest {
	
	@Autowired private ReservationTeamService reservationTeamServices;
	
	@Test
	void save() {
		ReservationTeamDto.Save save = new ReservationTeamDto.Save(3L, 19L);
		reservationTeamServices.save(save);
	}

	
	@Test
	void findAllByTeam() {
		Pageable pageable = PageRequest.of(0, 2);
		
		Page<ReservationTeamDto.Info> results = reservationTeamServices.findAllByTeam(3L,ActivateStatus.YES,pageable);
		
		System.out.println(results.getContent());
	}

}
