package com.manageTeam.service.gym;

import com.manageTeam.domain.gym.dto.GymResponse;
import com.manageTeam.domain.gym.entity.Gym;
import com.manageTeam.domain.gym.repository.GymRepository;
import com.manageTeam.domain.gym.service.GymReadService;
import com.manageTeam.domain.member.repository.MemberRepository;
import com.manageTeam.domain.team.repository.TeamRepository;
import com.manageTeam.global.exception.GlobalException;
import com.manageTeam.service.CreateEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GymReadServiceTest extends CreateEntity {

    @Autowired
    private GymRepository gymRepository;
    @Autowired
    private GymReadService gymReadService;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;

    @AfterEach
    void tearDown() {
        memberRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
        gymRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("체육관을 조회한다.")
    void findByIdTest(){
        Gym savedGym = gymRepository.save(createGym());

        Gym findGym = gymReadService.findById(savedGym.getGymId());

        assertThat(findGym).extracting("gymId", "gymName", "address.city", "address.street", "address.zipcode")
            .contains(savedGym.getGymId(), "보훈원", "SUWON", "harylero", "12345");
    }

    @Test
    @DisplayName("이미 존재하는 체육관인지 체크한다.")
    void checkGymExistTest(){
        gymRepository.save(createGym());

        assertThatThrownBy(() -> gymReadService.checkGymExist("12345", "보훈원"))
            .isInstanceOf(GlobalException.class);
    }

    @Test
    @DisplayName("체육관 정보를 조회한다.")
    void findOneTest(){
        Gym savedGym = gymRepository.save(createGym());

        GymResponse.Info response = gymReadService.findOne(savedGym.getGymId());

        assertThat(response).extracting("gymId", "gymName", "address.city", "address.street", "address.zipcode")
            .contains(savedGym.getGymId(), "보훈원", "SUWON", "harylero", "12345");
    }

}
