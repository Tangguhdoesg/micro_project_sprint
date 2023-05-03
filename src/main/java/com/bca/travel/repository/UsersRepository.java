package com.bca.travel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bca.travel.model.User;

public interface UsersRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);

}
