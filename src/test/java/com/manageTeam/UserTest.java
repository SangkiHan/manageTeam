package com.manageTeam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.manageTeam.domain.user.dto.UserDto;
import com.manageTeam.domain.user.service.UserService;
import com.manageTeam.dto.AddressDto;
import com.manageTeam.entity.ActivateStatus;
import com.manageTeam.entity.Auth;

@SpringBootTest
public class UserTest {
	
	@Autowired private UserService userService;
	
	@Test
	void save() throws Exception {
		AddressDto addressDto = new AddressDto("수원시","영통구","16225");
		UserDto.Save user = new UserDto.Save(
				"tkdrl",21L,"navskd1048","케이저 테스트",
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
