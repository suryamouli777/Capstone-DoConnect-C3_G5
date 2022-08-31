package com.wipro.gl.doconnect.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Author : A. Anurag
 * Modified date : 24-08-2022 
 * Description : DTO class for question
 * 
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO
{

	private String question;
	private String topic;
	private Long userId;
	
	private String type;
}
