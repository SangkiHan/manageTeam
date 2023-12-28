package com.manageTeam.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manageTeam.domain.team.entity.Team;
import com.manageTeam.domain.user.entity.User;

public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom{
	Optional<User> findByTeam(Team team);
	Optional<User> findByUserId(String userId);
}
