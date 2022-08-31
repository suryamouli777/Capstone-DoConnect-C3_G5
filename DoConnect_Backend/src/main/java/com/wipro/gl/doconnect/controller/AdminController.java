package com.wipro.gl.doconnect.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.gl.doconnect.dto.AdminDTO;
import com.wipro.gl.doconnect.dto.ResponseDTO;
import com.wipro.gl.doconnect.entity.Admin;
import com.wipro.gl.doconnect.entity.Answer;
import com.wipro.gl.doconnect.entity.Question;
import com.wipro.gl.doconnect.entity.User;
import com.wipro.gl.doconnect.service.IAdminService;

/*
* Author : A. Abhinav Subhedar
* Modified date : 27-08-2022 
* Description : REST controller for admin
* 
*/


@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

	@Autowired
	IAdminService iAdminService;
	
	
	@GetMapping("/login/{email}/{password}")
	public Admin adminLogin(@PathVariable String email, @PathVariable String password) {
		return iAdminService.adminLogin(email, password);
	}

	@GetMapping("/logout/{adminId}")
	public String adminLogout(@PathVariable Long adminId) {
		return iAdminService.adminLogout(adminId);
	}

	@PostMapping("/register")
	public Admin adminRegister(@RequestBody AdminDTO adminDTO) {
		return iAdminService.adminRegister(adminDTO);
	}
	
	@GetMapping("/getUnApprovedQuestions")
	public List<Question> getUnApprovedQuestions() {
		return iAdminService.getUnApprovedQuestions();
	}

	@GetMapping("/getUnApprovedAnswers")
	public List<Answer> getUnApprovedAnswers() {
		return iAdminService.getUnApprovedAnswers();
	}

	@PutMapping("/approveQuestion/{questionId}")
	public Question approveQuestion(@PathVariable Long questionId) {
		return iAdminService.approveQuestion(questionId);
	}

	@PutMapping("/approveAnswer/{answerId}")
	public Answer approveAnswer(@PathVariable Long answerId) {
		return iAdminService.approveAnswer(answerId);
	}

	@DeleteMapping("/deleteQuestion/{questionId}")
	public ResponseDTO deleteQuestion(@PathVariable Long questionId) {
		return iAdminService.deleteQuestion(questionId);
	}

	@DeleteMapping("/deleteAnswer/{answerId}")
	public ResponseDTO deleteAnswer(@PathVariable Long answerId) {
		return iAdminService.deleteAnswer(answerId);
	}

	@GetMapping("/getAllUsers")
	public List<User> getAllUser() {
		return iAdminService.getAllUser();
	}
	
	@GetMapping("/getAllQuestions")
	public List<Question> getAllQuestion() {
		return iAdminService.getAllQuestion();
	}
	
	@GetMapping("/getAllAnswers")
	public List<Answer> getAllAnswer() {
		return iAdminService.getAllAnswer();
	}

}
