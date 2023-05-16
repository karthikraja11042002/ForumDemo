package com.exterro.ForumDemo.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exterro.ForumDemo.entity.Question;
import com.exterro.ForumDemo.entity.request.QuestionRequest;
import com.exterro.ForumDemo.services.QuestionServices;

@Controller
public class QuestionController {
	// Creating a logger object
	private static Logger logger = LoggerFactory.getLogger("QuestionController");

	@Autowired
	QuestionServices questionservices;

	@RequestMapping("question")
	public String getIndex() {
		return "question.html";
	}

	@RequestMapping("admin/addQuestion")
	public String postQuestion(QuestionRequest questionReq) {
		logger.info("Received request to add a question: " + questionReq);
		try {
			Question question1 = questionservices.postQuestion(questionReq);
		} catch (Exception e) {
			logger.error("Error occurred while posting a question: " + e.getMessage());

		}
		return "questionaddsuccessful.html";
	}

	@RequestMapping("admin/viewQuestion")
	@ResponseBody
	public Question viewQuestion(int questionId) {
		return questionservices.getQuestion(questionId);
	}

	@RequestMapping("admin/viewallQuestion")
	@ResponseBody
	public List<Question> viewAllQuestion() {
		return questionservices.getAllQuestion();
	}

	@RequestMapping("admin/updateQuestion")
	public String updateQuestion(int questionId, String questionValue, String userEmail) {
		logger.info("Received request to update a question. Question ID: " + questionId);
		Question question1 = null;
		try {
			question1 = questionservices.updateQuestion(questionId, questionValue, userEmail);
		} catch (Exception e) {
			logger.error("Error occurred while updating a question: " + e.getMessage());

		}
		return (question1 != null) ? "questionupdatesuccessful.html" : "questionupdatefailure.html";
	}

	@RequestMapping("admin/deleteQuestion")
	@ResponseBody
	public String deleteQuestion(int questionId) {
		return questionservices.deleteQuestion(questionId);
	}

	@RequestMapping("user/addQuestion")
	public String userpostQuestion(QuestionRequest questionReq) {
		logger.info("Received request to add a question from a user: " + questionReq);
		try {	
			Question question1 = questionservices.postQuestion(questionReq);
		} catch (Exception e) {
			logger.error("Error occurred while posting a question from a user: " + e.getMessage());

		}
		return "questionaddsuccessful.html";
	}
}
