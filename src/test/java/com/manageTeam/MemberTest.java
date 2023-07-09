package com.manageTeam;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.manageTeam.domain.member.dto.MemberDto;
import com.manageTeam.domain.member.service.MemberService;
import com.manageTeam.dto.AddressDto;

@SpringBootTest
public class MemberTest {
	
	@Autowired MemberService memberService;
	
	/*
	 * 저장 테스트
	 * */
	@Test
	@Rollback(value = true)
	void save() {
		for(int i=0; i<5; i++) {
			AddressDto address = new AddressDto("수원시","화서로"+i,"16225");
			MemberDto.Save member = new MemberDto.Save(21L, "케이저"+i, 28+i, "970212","01076409148", address, "SG");
			memberService.save(member);
		}
	}
	
	/*
	 * 단건 조회 테스트
	 * */
	@Test
	void findById() {
		MemberDto.Info member = memberService.findById(4L);
		System.out.println(member);
	}

}
