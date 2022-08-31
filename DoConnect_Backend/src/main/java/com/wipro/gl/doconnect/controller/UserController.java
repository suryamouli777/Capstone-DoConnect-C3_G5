package com.wipro.gl.doconnect.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.gl.doconnect.dto.AnswerDTO;
import com.wipro.gl.doconnect.dto.QuestionDTO;
import com.wipro.gl.doconnect.dto.UserDTO;
import com.wipro.gl.doconnect.entity.Answer;
import com.wipro.gl.doconnect.entity.Image;
import com.wipro.gl.doconnect.entity.Question;
import com.wipro.gl.doconnect.entity.User;
import com.wipro.gl.doconnect.service.IUserService;
import com.wipro.gl.doconnect.vo.Message;

/*
* Author : B. Surya Mouli
* Modified date : 27-08-2022 
* Description : REST controller for admin
* 
*/


@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
	
	@Autowired
	IUserService iUserService;
	
	@GetMapping("/login/{email}/{password}")
	public User userLogin(@PathVariable String email, @PathVariable String password) {
		return iUserService.userLogin(email, password);
	}

	@GetMapping("/logout/{userId}")
	public String userLogout(@PathVariable Long userId) {
		return iUserService.userLogout(userId);
	}

	@PostMapping("/register")
	public User userRegister(@RequestBody UserDTO userDTO) {
		return iUserService.userRegister(userDTO);
	}

	@PostMapping("/postQuestion")
	public Question Question(@RequestBody QuestionDTO questionDTO) {
		return iUserService.postQuestion(questionDTO);
	}

	@PostMapping("/postAnswer")
	public Answer giveAnswer(@RequestBody AnswerDTO answerDTO) {
		return iUserService.postAnswer(answerDTO);
	}

	@GetMapping("/searchQuestion/{question}")
	public List<Question> searchQuestion(@PathVariable String question) {
		return iUserService.searchQuestion(question);
	}

	@GetMapping("/getAnswers/{questionId}")
	public List<Answer> getAnswers(@PathVariable Long questionId) {
		return iUserService.getAnswers(questionId);
	}

	@GetMapping("/getQuestions/{topic}")
	public List<Question> getQuestions(@PathVariable String topic) {
		return iUserService.getQuestions(topic);
	}

	@PostMapping("/upload")
	public BodyBuilder uplaodImage(@RequestParam("imageFile") MultipartFile file) throws IOException {
		return iUserService.uploadImage(file);
	}

	@GetMapping(path = { "/get/{imageName}" })
	public Image getImage(@PathVariable("imageName") String imageName) throws IOException {
		return iUserService.getImage(imageName);
	}
	
	@GetMapping(path = { "/getid/{imageName}" })
	public Long getId(@PathVariable("imageName") String imageName) throws IOException {
		return iUserService.getId(imageName);
	}
	

	@PostMapping("/sendMessage")
	public Message sendMessage(@RequestBody Message messageDTO) {
		return iUserService.sendMessage(messageDTO);
	}

}
