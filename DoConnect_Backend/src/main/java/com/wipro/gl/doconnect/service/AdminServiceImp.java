package com.wipro.gl.doconnect.service;


import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.gl.doconnect.dto.AdminDTO;
import com.wipro.gl.doconnect.dto.ResponseDTO;
import com.wipro.gl.doconnect.entity.Admin;
import com.wipro.gl.doconnect.entity.Answer;
import com.wipro.gl.doconnect.entity.Question;
import com.wipro.gl.doconnect.entity.User;
import com.wipro.gl.doconnect.exceptions.AlreadyExists;
import com.wipro.gl.doconnect.exceptions.NotFound;
import com.wipro.gl.doconnect.repository.AdminRepository;
import com.wipro.gl.doconnect.repository.AnswerRepository;
import com.wipro.gl.doconnect.repository.QuestionRepository;
import com.wipro.gl.doconnect.repository.UserRepository;

/*
* Author : A. Abhinav Subhedar, D. Surya Vamshi
* Modified date : 24-08-2022 
* Description : Service implementation class for admin
* 
*/

@Service
public class AdminServiceImp implements IAdminService {

	@Autowired
	AdminRepository adminRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	
	/*
	* Author : Abhinav subhedar
	* Modified date : 26-08-2022 
	* Description : adminLogin  method 
	* parameters : email and password
	* return type : admin entity
	*/
	@Override
	public Admin adminLogin(String email, String password)
	{
		Admin admin = adminRepository.findByEmail(email);
		if (Objects.isNull(admin))
			throw new NotFound();

		if (admin.getPassword().equals(password))
		{
			admin.setIsActive(true);
			adminRepository.save(admin);
		}else {
			throw new NotFound();
		}
		return admin;
	}


	/*
	* Author : Abhinav subhedar
	* Modified date : 26-08-2022 
	* Description : adminLogout  method 
	* parameters : adminId
	* return type : string
	*/
	@Override
	public String adminLogout(Long adminId)
	{
		Admin admin = adminRepository.findById(adminId).orElse(new Admin());
		admin.setIsActive(false);
		adminRepository.save(admin);
		return "Logged Out";
	}

	/*
	* Author : Abhinav subhedar
	* Modified date : 26-08-2022 
	* Description : adminRegister  method 
	* parameters : adminDTO object
	* return type : Admin entity
	*/
	@Override
	public Admin adminRegister(AdminDTO adminDTO)
	{
		Admin admin1 = new Admin();
		admin1.setFirstName(adminDTO.getFirstName());
		admin1.setLastName(adminDTO.getLastName());
		admin1.setEmail(adminDTO.getEmail());
		admin1.setPassword(adminDTO.getPassword());
		admin1.setPhoneNumber(adminDTO.getPhoneNumber());
		
		Admin admin = adminRepository.findByEmail(adminDTO.getEmail());
		if (Objects.isNull(admin)) {
			return adminRepository.save(admin1);
		}else {
			throw new AlreadyExists();
		}
		
	}

	/*
	* Author : surya vamshi
	* Modified date : 27-08-2022 
	* Description : getAllUser  method 
	* parameters : 
	* return type : User List 
	*/

	@Override
	public List<User> getAllUser()
	{
		return userRepository.findAll();
	}

	/*
	* Author : surya vamshi
	* Modified date : 27-08-2022 
	* Description : getUnApprovedQuestion  method 
	* parameters : 
	* return type : Question List 
	*/
	@Override
	public List<Question> getUnApprovedQuestions()
	{
		return questionRepository.findByIsApproved();
	}

	/*
	* Author :surya vamshi
	* Modified date : 27-08-2022 
	* Description : getUnApprovedAnswers  method 
	* parameters : 
	* return type : Answer List 
	*/

	@Override
	public List<Answer> getUnApprovedAnswers()
	{
		return answerRepository.findByIsApproved();
	}

	/*
	* Author : surya vamshi
	* Modified date : 27-08-2022 
	* Description : approveQuestion method 
	* parameters : questionId 
	* return type : question entity
	*/

	@Override
	public Question approveQuestion(Long questionId)
	{
		Question question = questionRepository.findById(questionId).orElse(new Question());

		question.setIsApproved(true);
		question = questionRepository.save(question);
		return question;
	}

	/*
	* Author : surya vamshi
	* Modified date : 27-08-2022 
	* Description : approvedAnswers  method 
	* parameters : answerId
	* return type : Answer entity
	*/
	@Override
	public Answer approveAnswer(Long answerId)
	{
		Answer answer = answerRepository.findById(answerId).orElse(new Answer());

		answer.setIsApproved(true);
		answer = answerRepository.save(answer);
		return answer;
	}

	/*
	* Author : Abhinav subhedar
	* Modified date : 27-08-2022 
	* Description : deleteQuestion  method 
	* parameters : questionId
	* return type :ResponseDTO
	*/
	@Override
	public ResponseDTO deleteQuestion(Long questionId)
	{
		ResponseDTO responseDTO = new ResponseDTO();
		Question question = questionRepository.findById(questionId).orElse(new Question());

		questionRepository.delete(question);
		responseDTO.setMsg("Question removed");
		return responseDTO;
	}

	/*
	* Author : Abhinav subhedar
	* Modified date : 27-08-2022 
	* Description : deleteAnswers  method 
	* parameters : answerId
	* return type : ResponseDTO
	*/

	@Override
	public ResponseDTO deleteAnswer(Long answerId)
	{
		ResponseDTO responseDTO = new ResponseDTO();

		Answer answer = answerRepository.findById(answerId).orElse(new Answer());

		answerRepository.delete(answer);
		responseDTO.setMsg("Answer Removed");
		return responseDTO;
	}


	/*
	* Author : B. Surya Mouli
	* Modified date : 29-08-2022 
	* Description : getAllQuestions  method 
	* parameters : 
	* return type : list of questions
	*/

	@Override
	public List<Question> getAllQuestion() {
		return questionRepository.findAll();
	}


	/*
	* Author : B. Surya Mouli
	* Modified date : 29-08-2022 
	* Description : getAllAnswers  method 
	* parameters : 
	* return type : list of answers
	*/
	@Override
	public List<Answer> getAllAnswer() {
		return answerRepository.findAll();
	}
}
