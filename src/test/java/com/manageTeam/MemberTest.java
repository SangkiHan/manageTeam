package com.manageTeam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.manageTeam.dto.AddressDto;
import com.manageTeam.dto.MemberDto;
import com.manageTeam.service.MemberService;

@SpringBootTest
public class MemberTest {
	
	@Autowired MemberService memberService;
	
	@Test
	@Rollback(value = true)
	void save() {
		AddressDto address = new AddressDto("수원시","영통로","16225");
		MemberDto.Save member = new MemberDto.Save(1L, "한상기", 28, "961027", address, "C");
		memberService.save(member);
	}
	
	@Test
	void findById() {
		MemberDto.Info member = memberService.findById(4L);
		System.out.println(member);
	}

}
