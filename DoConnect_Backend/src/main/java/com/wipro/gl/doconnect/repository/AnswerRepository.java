package com.wipro.gl.doconnect.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wipro.gl.doconnect.entity.Answer;

/*
* Author : A. Abhinav Subhedar
* Modified date : 24-08-2022 
* Description : repository class for answer
* 
*/

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{

	@Query("from Answer where question.id = ?1 and isApproved = true")
	public List<Answer> findByQuestionId(Long questionId);

	@Query("from Answer where isApproved = false")
	public List<Answer> findByIsApproved();
}
