package com.exterro.ForumDemo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exterro.ForumDemo.entity.Answer;
import com.exterro.ForumDemo.entity.request.AnswerRequest;

@Service
public interface AnswerServices {

	public Answer postAnswer(AnswerRequest answerReq) throws Exception;

	public Answer getAnswer(int answerId);

	public List<Answer> getAllAnswer();

	public Answer updateAnswer(int answerId, int questionId, String userEmail, String answerValue) throws Exception;

	public String deleteAnswer(int answerId);
	 
	public List<Answer> findAnswerByQuestionId(int questionId);



	

}
