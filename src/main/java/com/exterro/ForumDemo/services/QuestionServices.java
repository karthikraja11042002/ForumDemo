package com.exterro.ForumDemo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exterro.ForumDemo.entity.Question;
import com.exterro.ForumDemo.entity.request.QuestionRequest;

@Service
public interface QuestionServices {
	public Question postQuestion(QuestionRequest question) throws Exception;

	public Question getQuestion(int questionId);

	public List<Question> getAllQuestion();

	public Question updateQuestion(int questionId, String questionValue, String userEmail) throws Exception;

	public String deleteQuestion(int questionId);

	

}
