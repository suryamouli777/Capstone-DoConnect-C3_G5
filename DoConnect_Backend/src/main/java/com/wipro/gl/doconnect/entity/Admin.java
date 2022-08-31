package com.wipro.gl.doconnect.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Author : A. Abhinav Subhedar
 * Modified date : 24-08-2022 
 * Description : entity class for admin
 * 
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String phoneNumber;
	private Boolean isActive=true;
}