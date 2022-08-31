package com.wipro.gl.doconnect.service;

import java.util.List;

import com.wipro.gl.doconnect.dto.AdminDTO;
import com.wipro.gl.doconnect.dto.ResponseDTO;
import com.wipro.gl.doconnect.entity.Admin;
import com.wipro.gl.doconnect.entity.Answer;
import com.wipro.gl.doconnect.entity.Question;
import com.wipro.gl.doconnect.entity.User;

/*
* Author : A. Abhinav Subhedar
* Modified date : 28-08-2022 
* Description : Interface for Admin Service
* 
*/

public interface IAdminService {
	public Admin adminLogin(String email, String password);
	public String adminLogout(Long adminId);
	public Admin adminRegister(AdminDTO adminDTO);
	
	public Question approveQuestion(Long questionId);
	public Answer approveAnswer(Long answerId);
	
	public List<Question> getUnApprovedQuestions();
	public List<Answer> getUnApprovedAnswers();
	

	public List<User> getAllUser();
	public List<Question> getAllQuestion();
	public List<Answer> getAllAnswer();
	
	public ResponseDTO deleteQuestion(Long questionId);
	public ResponseDTO deleteAnswer(Long answerId);
}
