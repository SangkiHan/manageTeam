package com.manageTeam.service.member;

import com.manageTeam.domain.member.dto.MemberResponse;
import com.manageTeam.domain.member.entity.Member;
import com.manageTeam.domain.member.repository.MemberRepository;
import com.manageTeam.domain.member.service.MemberReadService;
import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.global.entity.ActivateStatus;
import com.manageTeam.global.entity.Position;
import com.manageTeam.global.exception.GlobalException;
import com.manageTeam.service.CreateEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MemberReadServiceTest extends CreateEntity {
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberReadService memberReadService;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("이미 등록된 팀원이 있는지 체크한다.")
    void existsByRsdntRgnmbTest(){
        Team savedTeam = teamRepository.save(createTeam());
        memberRepository.save(createMember(savedTeam));

        assertThatThrownBy(() -> memberReadService.existsByRsdntRgnmb("123412341234"))
            .isInstanceOf(GlobalException.class);
    }

    @Test
    @DisplayName("등록된 팀원을 조회한다.")
    @Transactional
    void findByIdTest(){
        Team savedTeam = teamRepository.save(createTeam());
        Member savedMember = memberRepository.save(createMember(savedTeam));

        Member findMember = memberReadService.findById(savedMember.getMemberId());

        assertThat(findMember).extracting("memberId", "team.teamId", "membername", "age", "rsdntRgnmb", "phone", "address.city", "address.street", "address.zipcode", "position", "activateStatus")
            .contains(findMember.getMemberId(), savedTeam.getTeamId(), "상기", 29, "123412341234", "01012341234", "SUWON", "harylero", "12345", Position.C, ActivateStatus.YES);
    }

    @Test
    @DisplayName("등록된 팀원을 조회시 존재하지않는 회원이면 예외가 발생한다.")
    @Transactional
    void findByIdTestWithoutMember(){
        assertThatThrownBy(() -> memberReadService.findById(1L))
            .isInstanceOf(GlobalException.class);
    }

    @Test
    @DisplayName("등록된 팀원을 조회한다.")
    @Transactional
    void findOneTest(){
        Team savedTeam = teamRepository.save(createTeam());
        Member savedMember = memberRepository.save(createMember(savedTeam));

        MemberResponse.Info response = memberReadService.findOne(savedMember.getMemberId());

        assertThat(response).extracting("memberId", "memberName", "age", "rsdntRgnmb", "phone", "address.city", "address.street", "address.zipcode", "teamName", "position", "activateStatus")
            .contains(savedMember.getMemberId(), "상기", 29, "123412341234", "01012341234", "SUWON", "harylero", "12345", "창공", Position.C, ActivateStatus.YES);
    }
}
