package com.wipro.gl.doconnect.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wipro.gl.doconnect.entity.Admin;

/*
* Author : A. Abhinav Subhedar
* Modified date : 24-08-2022 
* Description : repository class for admin
* 
*/

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {

	public Admin findByEmail(String email);
}
