package com.manageTeam.domain.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manageTeam.global.entity.Team;
import com.manageTeam.global.entity.User;

public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom{
	public Optional<User> findByTeam(Team team);
}
