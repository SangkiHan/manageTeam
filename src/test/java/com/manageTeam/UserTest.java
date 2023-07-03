package com.manageTeam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.manageTeam.dto.AddressDto;
import com.manageTeam.dto.UserDto;
import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.entity.Auth;
import com.manageTeam.service.UserService;

@SpringBootTest
public class UserTest {
	
	@Autowired private UserService userService;
	
	@Test
	void save() throws Exception {
		AddressDto addressDto = new AddressDto("수원시","영통구","16225");
		UserDto.Save user = new UserDto.Save(
				"tkdrl8908",3L,"navskd1048","한상기",
				"961027-1164524","01064091048",addressDto,
				Auth.TEAM, ActivateStatus.YES
				);
		userService.save(user);
	}
	
	@Test
	void findUser() {
		UserDto.Info info = userService.findUserInfo("tkdrl8908");
		System.out.println(info.toString());
	}

}
