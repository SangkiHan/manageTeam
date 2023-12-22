package com.manageTeam.service.gym;

import com.manageTeam.domain.gym.dto.GymRequest;
import com.manageTeam.domain.gym.dto.GymResponse;
import com.manageTeam.domain.gym.repository.GymRepository;
import com.manageTeam.domain.gym.service.GymService;
import com.manageTeam.service.CreateEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

public class GymServiceTest extends CreateEntity {

    @Autowired
    private GymRepository gymRepository;
    @Autowired
    private GymService gymService;

    @AfterEach
    void tearDown() {
        gymRepository.deleteAllInBatch();
    }

    @Test
    @DisplayName("체육관을 저장한다.")
    void saveTest(){
        GymRequest.Save request = GymRequest.Save.builder()
            .gymName("보훈원")
            .address(createAddress())
            .build();

        GymResponse.Save response = gymService.save(request);

        assertThat(response).extracting("gymId", "gymName", "address.city", "address.street", "address.zipcode")
            .contains(response.getGymId(), "보훈원", "SUWON", "harylero", "12345");
    }
}
