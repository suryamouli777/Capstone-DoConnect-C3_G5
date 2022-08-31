package com.wipro.gl.doconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Author : A. Abhinav Subhedar
 * Modified date : 24-08-2022 
 * Description : DTO class for admin
 * 
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AdminDTO {
	
	private long id;
	private String firstName;
	private String lastName;
	private String password;
	private String email;
	private String phoneNumber;
}
