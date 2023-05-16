package com.exterro.ForumDemo.entity.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class AnswerRequest {

	private int answerId;
	private int questionId;
	private String userEmail;
	private String answerValue;

	// Creating a logger object
	private static Logger logger = LoggerFactory.getLogger("AnswerRequest");
	public AnswerRequest() {
		super();

	}

	public AnswerRequest(String userEmail, int answerId, int questionId, String answerValue) {
		super();
		this.userEmail = userEmail;
		this.answerId = answerId;
		this.questionId = questionId;
		this.answerValue = answerValue;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getAnswerId() {
		return answerId;
	}

	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getAnswerValue() {
		return answerValue;
	}

	public void setAnswerValue(String answerValue) {
		this.answerValue = answerValue;
	}

	@Override
	public String toString() {
		return "Answer [userEmail=" + userEmail + ", answerId=" + answerId + ", questionId=" + questionId
				+ ", answerValue=" + answerValue + "]";
	}

}
