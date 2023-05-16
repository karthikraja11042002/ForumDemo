package com.exterro.ForumDemo.entity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Answer {
	@Id
	private int answerId;
	@ManyToOne
	@JoinColumn(name = "questionId")
	private Question questionId;
	@ManyToOne
	@JoinColumn(name = "userEmail")
	private User userEmail;
	private String answerValue;
	
	// Creating a logger object
	private static Logger logger = LoggerFactory.getLogger("Answer");
	public Answer() {
		super();
	}
	
	public Answer(int answerId, Question questionId, User userEmail, String answerValue) {
		super();
		this.answerId = answerId;
		this.questionId = questionId;
		this.userEmail = userEmail;
		this.answerValue = answerValue;
	}
	public User getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(User userEmail) {
		this.userEmail = userEmail;
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	public Question getQuestionId() {
		return questionId;
	}
	public void setQuestionId(Question questionId) {
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
		return "Answer [answerId=" + answerId + ", question=" + questionId + ", user=" + userEmail + ", answerValue="
				+ answerValue + "]";
	}
}
