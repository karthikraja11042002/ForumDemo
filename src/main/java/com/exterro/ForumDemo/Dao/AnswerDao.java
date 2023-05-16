package com.exterro.ForumDemo.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exterro.ForumDemo.entity.Answer;

@Repository

//create interface named AnswerDao that extends JpaRepository to perform CRUD operations
public interface AnswerDao extends JpaRepository<Answer, Integer> {

	// create a custom query
	@Query(nativeQuery = true, value = "select * from answer where question_id = ?1")
	List<Answer> getAnswerByQuestionId(int quesionId);

}
