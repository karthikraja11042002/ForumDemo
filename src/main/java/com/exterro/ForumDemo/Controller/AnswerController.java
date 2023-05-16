package com.exterro.ForumDemo.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exterro.ForumDemo.entity.Answer;
import com.exterro.ForumDemo.entity.request.AnswerRequest;
import com.exterro.ForumDemo.services.AnswerServices;

@Controller
public class AnswerController {
	// Creating a logger object
	private static Logger logger = LoggerFactory.getLogger("AnswerController");

	@Autowired
	AnswerServices answerservices;

	// Handles the request for the index page.

	@RequestMapping("answer")
	public String getIndex() {
		return "answer.html";
	}

	// Handles the request to add an answer.

	@RequestMapping("admin/addAnswer")
	public String postAnswer(AnswerRequest answerReq) {
		try {
			logger.info("Received request to add an answer: " + answerReq);
			Answer answer = answerservices.postAnswer(answerReq);
			logger.info("Answer added successfully");
			return "Answeraddsuccessful.html";
		} catch (Exception e) {
			logger.error("Error occurred while adding an answer: " + e.getMessage());
			return "error.html";
		}
	}

	// Handles the request to view a specific answer.

	@RequestMapping("admin/viewAnswer")
	@ResponseBody
	public Answer viewAnswer(int answerId) {
		return answerservices.getAnswer(answerId);
	}

	// Handles the request to view all answers.

	@RequestMapping("admin/viewallAnswer")
	@ResponseBody
	public List<Answer> viewAllAnswer() {
		return answerservices.getAllAnswer();
	}

	// Handles the request to update an answer.

	@RequestMapping("admin/updateAnswer")
	public String updateAnswer(int answerId, int questionId, String userEmail, String answerValue) {
		try {
			Answer updatedAnswer = answerservices.updateAnswer(answerId, questionId, userEmail, answerValue);
			if (updatedAnswer != null) {

				logger.info("Answer updated successfully");
				return "answerupdatesuccessful.html";
			} else {
				logger.error("Failed to update the answer");
				return "answerupdatefailure.html";
			}
		} catch (Exception e) {
			logger.error("Error occurred while updating the answer: " + e.getMessage());
			return "error.html";
		}
	}

	// Handles the request to delete an answer.

	@RequestMapping("admin/deleteAnswer")
	@ResponseBody
	public String deleteAnswer(int answerId) {
		return answerservices.deleteAnswer(answerId);
	}

	// Handles the request from the user to add an answer.

	@RequestMapping("user/addAnswer")
	public String userpostAnswer(AnswerRequest answerReq) {
		try {
			logger.info("Received request from the user to add an answer: " + answerReq);
			Answer answer = answerservices.postAnswer(answerReq);
			logger.info("User's answer added successfully");
			return "Answeraddsuccessful.html";
		} catch (Exception e) {
			logger.error("Error occurred while adding the user's answer: " + e.getMessage());
			return "error.html";
		}
	}
}
