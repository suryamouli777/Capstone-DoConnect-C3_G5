package com.wipro.gl.doconnect.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.wipro.gl.doconnect.dto.AnswerDTO;
import com.wipro.gl.doconnect.dto.QuestionDTO;
import com.wipro.gl.doconnect.dto.UserDTO;
import com.wipro.gl.doconnect.entity.Answer;
import com.wipro.gl.doconnect.entity.Image;
import com.wipro.gl.doconnect.entity.Question;
import com.wipro.gl.doconnect.entity.User;
import com.wipro.gl.doconnect.exceptions.AlreadyExists;
import com.wipro.gl.doconnect.exceptions.NotFound;
import com.wipro.gl.doconnect.repository.AnswerRepository;
import com.wipro.gl.doconnect.repository.ImageRepository;
import com.wipro.gl.doconnect.repository.QuestionRepository;
import com.wipro.gl.doconnect.repository.UserRepository;
import com.wipro.gl.doconnect.vo.Message;

/*
* Author : A. Anurag, B. Surya Mouli, B. Krishna Chaitanya
* Modified date : 24-08-2022 
* Description : repository class for user
* 
*/

@Service
public class UserServiceImp implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private ImageRepository imageRepository;
	@Autowired
	private RestTemplate restTemplate;
//	@Autowired
//	private AdminRepository adminRepository;
	@Autowired
	private IEmailService iEmailService;

	/*
	* Author : B. Surya Mouli
	* Modified date : 26-08-2022 
	* Description : user login method 
	* parameters : email and password object
	* return type : user entity
	*/

	@Override
	public User userLogin(String email, String password) {
		User user = userRepository.findByEmail(email);
		if (Objects.isNull(user))
			throw new NotFound();

		if (user.getPassword().equals(password)) {
			user.setIsActive(true);
			userRepository.save(user);
		} else
			throw new NotFound();
		return user;
	}

	/*
	* Author : B. Surya Mouli
	* Modified date : 26-08-2022 
	* Description : user logout method 
	* parameters : userId
	* return type : string
	*/
	@Override
	public String userLogout(Long userId) {
		User user = userRepository.findById(userId).orElse(new User());
		user.setIsActive(false);
		userRepository.save(user);
		return "Logged Out";
	}

	/*
	* Author : B. Surya Mouli
	* Modified date : 26-08-2022 
	* Description : user register method 
	* parameters : userDTO object 
	* return type : user entity
	*/

	@Override
	public User userRegister(UserDTO userDTO) {
		
		User user1 = new User();
		user1.setFirstName(userDTO.getFirstName());
		user1.setLastName(userDTO.getLastName());
		user1.setEmail(userDTO.getEmail());
		user1.setPassword(userDTO.getPassword());
		user1.setPhoneNumber(userDTO.getPhoneNumber());
		
		User user = userRepository.findByEmail(userDTO.getEmail());
		
		if (Objects.isNull(user))
			return userRepository.save(user1);
		else {
			throw new AlreadyExists();
		}
	}

	/*
	* Author :  A. Anurag
	* Modified date : 27-08-2022 
	* Description : postQuestion  method 
	* parameters : QuestionDTO object
	* return type : Question entity
	*/
	@Override
	public Question postQuestion(QuestionDTO questionDTO) {
		Question question = new Question();
		User user = userRepository.findById(questionDTO.getUserId()).orElse(new User());
		question.setQuestion(questionDTO.getQuestion());
		question.setTopic(questionDTO.getTopic());
		question.setUser(user);
		questionRepository.save(question);
		iEmailService.send("Approve Question","New question is added");
		return question;
	}

	/*
	* Author :  A. Anurag
	* Modified date : 27-08-2022 
	* Description : postAnswer  method 
	* parameters : AnswerDTO object
	* return type : Answer entity
	*/
	@Override
	public Answer postAnswer(AnswerDTO answerDTO) {
		Answer answer = new Answer();
		User user = userRepository.findById(answerDTO.getUserId()).orElse(new User());

		Question question = questionRepository.findById(answerDTO.getQuestionId()).orElse(new Question());
		answer.setQuestion(question);
		answer.setAnswer(answerDTO.getAnswer());
		answer.setUser(user);
		answerRepository.save(answer);
		iEmailService.send("Approve answer","New answer is added");
		return answer;
	}

	/*
	* Author :  A. Anurag
	* Modified date : 27-08-2022 
	* Description : getAnswer method 
	* parameters : questionId 
	* return type : ListAnswer entity
	*/

	@Override
	public List<Answer> getAnswers(Long questionId) {
		return answerRepository.findByQuestionId(questionId);
	}

	/*
	* Author :  A. Anurag
	* Modified date : 27-08-2022 
	* Description : getQuestion  method 
	* parameters : topic
	* return type : List of Question 
	*/
	@Override
	public List<Question> getQuestions(String topic) {
		if (topic.equalsIgnoreCase("All")) {
			return questionRepository.findByIsApprovedTrue();
		}
		return questionRepository.findByTopicAndApproved(topic);
	}

	/*
	* Author : B.krishna chaitanya
	* Modified date : 28-08-2022 
	* Description : serachQuestion  method 
	* parameters : Question
	* return type : List of Question 
	*/
	@Override
	public List<Question> searchQuestion(String question) {
		String sqlQuery = "from Question where (question like :question) and isApproved = 1";
		return entityManager.createQuery(sqlQuery, Question.class).setParameter("question", "%" + question + "%")
				.getResultList();
	}

	@Override
	public BodyBuilder uploadImage(MultipartFile file) throws IOException {
		System.out.println("Original Image Byte Size - " + file.getBytes().length);
		Image img = new Image(file.getOriginalFilename(), file.getContentType(),
				compressBytes(file.getBytes()));
		imageRepository.save(img);
		return ResponseEntity.status(HttpStatus.OK);
	}

	@Override
	public Image getImage(String imageName) {
		final Optional<Image> retrievedImage = imageRepository.findByName(imageName);
		if(retrievedImage.isPresent())
		{
			Image img = new Image(retrievedImage.get().getName(), retrievedImage.get().getType(), decompressBytes(retrievedImage.get().getImgByte()));
			return img;
		}
		return null;
	}
	
	@Override
	public Long getId(String imageName) {
		Image img= imageRepository.findByName(imageName).get();
		
		return img.getId();
	}

	@Override
	public Message sendMessage(Message messageDTO) {
		String url = "http://localhost:9090/chat/sendMessage";
		ResponseEntity<Message> responseEntity = restTemplate.postForEntity(url,messageDTO, Message.class);
		Message response = responseEntity.getBody();
		return response;
	}

	public static byte[] compressBytes(byte[] data) {
		Deflater deflater = new Deflater();
		deflater.setLevel(Deflater.BEST_COMPRESSION);
		deflater.setInput(data);
		deflater.finish();

		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[4*1024];
		while (!deflater.finished()) {
			int count = deflater.deflate(buffer);
			outputStream.write(buffer, 0, count);
		}
		try {
			outputStream.close();
		} catch (IOException e) {
		}
		System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);

		return outputStream.toByteArray();
	}

	public static byte[] decompressBytes(byte[] data) {
		Inflater inflater = new Inflater();
		inflater.setInput(data);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
		byte[] buffer = new byte[4*1024];
		try {
			while (!inflater.finished()) {
				int count = inflater.inflate(buffer);
				outputStream.write(buffer, 0, count);
			}
			outputStream.close();
		} catch (IOException ioe) {
		} catch (DataFormatException e) {
		}
		return outputStream.toByteArray();
	}
}