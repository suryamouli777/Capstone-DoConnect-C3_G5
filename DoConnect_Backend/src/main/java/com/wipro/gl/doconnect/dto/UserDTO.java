package com.wipro.gl.doconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Author : B. Surya Mouli
 * Modified date : 24-08-2022 
 * Description : DTO class for user
 * 
 */

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDTO {

	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	private String password;
	private Boolean isActive = true;
}
