package com.exterro.ForumDemo.entity.request;

import java.util.logging.Logger;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;


public class QuestionRequest {
	

	private int questionId;
	private String questionValue;
	private String userEmail;
	
	// Creating a logger object
		private static Logger logger = Logger.getLogger("QuestionRequest");
	public QuestionRequest() {
		super();
	}
	public QuestionRequest(String userEmail, int questionId, String questionValue) {
		super();
		this.userEmail = userEmail;
		this.questionId = questionId;
		this.questionValue = questionValue;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public String getQuestionValue() {
		return questionValue;
	}
	public void setQuestionValue(String questionValue) {
		this.questionValue = questionValue;
	}
	@Override
	public String toString() {
		return "Question [userEmail=" + userEmail + ", questionId=" + questionId + ", questionValue=" + questionValue + "]";
	}

}
