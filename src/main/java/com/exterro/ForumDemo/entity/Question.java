package com.exterro.ForumDemo.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Question {
	
	@Id
	private int questionId;
	private String questionValue;
	
	@ManyToOne
	@JoinColumn(name = "userEmail")
	private User userEmail;
	
	// Creating a logger object
	private static Logger logger = LoggerFactory.getLogger("Question");
	
	public Question() {
		super();
	}
	public Question(int questionId, String questionValue, User userEmail) {
		super();
		this.questionId = questionId;
		this.questionValue = questionValue;
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
	public User getUserEmail() {
		return userEmail;
	}
	public void setUser(User userEmail) {
		this.userEmail = userEmail;
	}
	@Override
	public String toString() {
		return "Question [questionId=" + questionId + ", questionValue=" + questionValue + ", user=" + userEmail + "]";
	}
	
}

