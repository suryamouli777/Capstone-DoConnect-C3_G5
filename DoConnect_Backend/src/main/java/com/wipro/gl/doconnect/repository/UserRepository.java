package com.wipro.gl.doconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.gl.doconnect.entity.User;

/*
* Author : B. Surya Mouli 
* Modified date : 24-08-2022 
* Description : repository class for user
* 
*/

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
}
