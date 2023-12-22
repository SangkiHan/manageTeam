package com.manageTeam.service.member;

import com.manageTeam.domain.member.dto.MemberRequest;
import com.manageTeam.domain.member.dto.MemberResponse;
import com.manageTeam.domain.member.entity.Member;
import com.manageTeam.domain.member.repository.MemberRepository;
import com.manageTeam.domain.member.service.MemberService;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Position;
import com.manageTeam.service.CreateEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class MemberServiceTest extends CreateEntity {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberService memberService;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("팀원을 저장한다.")
    void saveTest(){
        Team savedTeam = teamRepository.save(createTeam());

        MemberRequest.Save request = MemberRequest.Save.builder()
            .teamId(savedTeam.getTeamId())
            .memberName("상기")
            .age(29)
            .rsdntRgnmb("123412341234")
            .phone("01012341234")
            .address(createAddress())
            .position(Position.C)
            .build();

        MemberResponse.Save response = memberService.save(request);

        assertThat(response).extracting("memberId", "teamId", "memberName", "age", "rsdntRgnmb", "phone", "address.city", "address.street", "address.zipcode", "position")
            .contains(response.getMemberId(), savedTeam.getTeamId(), "상기", 29, "123412341234", "01012341234", "SUWON", "harylero", "12345", Position.C);
    }

    @Test
    @DisplayName("팀원의 상태를 변경한다.")
    void statusTest(){
        Team savedTeam = teamRepository.save(createTeam());
        Member savedMember = memberRepository.save(createMember(savedTeam));

        savedMember.changeStatus(ActivateStatus.NO);

        assertThat(savedMember.getActivateStatus()).isEqualTo(ActivateStatus.NO);
    }


}
