package com.wipro.gl.doconnect.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Author : A. Abhinav Subhedar
 * Modified date : 24-08-2022 
 * Description : entity class for answer
 * 
 */


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	private User user;
	
	@OneToOne
	private Question question;
	
	private String answer;
	private Boolean isApproved = false;
}
