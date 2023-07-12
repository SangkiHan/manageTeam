package com.manageTeam.domain.gym.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manageTeam.domain.gym.entity.Gym;

public interface GymRepository extends JpaRepository<Gym, Long>, GymRepositoryCustom{

}
