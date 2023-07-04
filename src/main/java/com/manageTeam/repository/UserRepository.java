package com.manageTeam.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manageTeam.entity.Team;
import com.manageTeam.entity.User;

public interface UserRepository extends JpaRepository<User, String>, UserRepositoryCustom{
	public Optional<User> findByTeam(Team team);
}
