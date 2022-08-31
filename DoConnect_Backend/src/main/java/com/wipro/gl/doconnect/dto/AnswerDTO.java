package com.wipro.gl.doconnect.dto;

/*
 * Author : A. Abhinav Subhedar
 * Modified date : 24-08-2022 
 * Description : DTO class for admin
 * 
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

	private Long userId;
	private Long questionId;
	private String answer;
}
