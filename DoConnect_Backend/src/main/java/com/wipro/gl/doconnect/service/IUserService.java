package com.wipro.gl.doconnect.service;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.gl.doconnect.dto.AnswerDTO;
import com.wipro.gl.doconnect.dto.QuestionDTO;
import com.wipro.gl.doconnect.dto.UserDTO;
import com.wipro.gl.doconnect.entity.Answer;
import com.wipro.gl.doconnect.entity.Image;
import com.wipro.gl.doconnect.entity.Question;
import com.wipro.gl.doconnect.entity.User;
import com.wipro.gl.doconnect.vo.Message;

/*
* Author : B. Surya Mouli
* Modified date : 28-08-2022 
* Description : Interface for User service
* 
*/

public interface IUserService {
	
	public User userLogin(String email, String password);
	public String userLogout(Long userId);
	public User userRegister(UserDTO userDTO);
	
	public Question postQuestion(QuestionDTO questionDTO);
	public Answer postAnswer(AnswerDTO answerDTO);
	
	
	public List<Question> searchQuestion(String question);
	
	public List<Answer> getAnswers(Long questionId);
	public List<Question> getQuestions(String topic);
	
	public BodyBuilder uploadImage(MultipartFile file) throws IOException;
	public Image getImage(String imageName);
	
	public Message sendMessage(Message message);
	
	public Long getId(String imageName);
}
