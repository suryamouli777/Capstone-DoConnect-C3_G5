package com.wipro.gl.doconnect.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
* Author : A. Anurag
* Modified date : 28-08-2022 
* Description : View object class for message
* 
*/

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Message {

	private String fromUser;
	private String message;
	
}
