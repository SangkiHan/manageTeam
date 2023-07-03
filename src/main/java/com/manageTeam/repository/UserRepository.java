package com.manageTeam.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.manageTeam.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
