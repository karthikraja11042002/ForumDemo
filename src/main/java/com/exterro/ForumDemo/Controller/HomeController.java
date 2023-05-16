package com.exterro.ForumDemo.Controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exterro.ForumDemo.entity.Answer;
import com.exterro.ForumDemo.entity.Question;
import com.exterro.ForumDemo.services.AnswerServices;
import com.exterro.ForumDemo.services.QuestionServices;

@Controller
public class HomeController {

	// Creating a logger object
	private static Logger logger = LoggerFactory.getLogger("HomeController");
	@Autowired
	QuestionServices questionServices;

	@Autowired
	AnswerServices answerServices;

	@RequestMapping("login")
	public String login() {
		return "login.html";
	}

	@RequestMapping("home/{userEmail}")
	@ResponseBody
	public String getHome(@PathVariable String userEmail) {

		try {
			List<Question> questionList = questionServices.getAllQuestion();

			// Generate HTML content for displaying questions and answers
			String result = "<html><head><style>" + "table {border-collapse: collapse; width: 100%;}"
					+ "th, td {text-align: left; padding: 8px;}" + "th {background-color: #4CAF50; color: white;}"
					+ "tr:nth-child(even){background-color: #f2f2f2}"
					+ "input[type='submit'] {background-color: #4CAF50; color: white; padding: 6px 12px; border: none; border-radius: 4px;}"
					+ "input[type='submit']:hover {background-color: #45a049; cursor: pointer;}"
					+ "</style></head><body>" + "<div style='text-align: right; padding: 10px;'>"
					+ "<a href='/addQuestion.html'>Post Question</a> | " + "<a href='/addanswer.html'>Post Answer</a>"
					+ "</div>" + "<h1> Welcome to the view page</h1>";

			for (int i = 0; i < questionList.size(); i++) {
				Question question = questionList.get(i);
				result += "<table><tr><th>" + question.getQuestionValue();
				result += "<br>Question ID: " + question.getQuestionId();

				if (userEmail.equals(question.getUserEmail().getUserEmail())) {
					result += "<form action='deleteQuestion'>" + "<input type='hidden' name='questionId' value='"
							+ question.getQuestionId() + "'>" + "<input type='submit' value='Delete Question'>"
							+ "</form>" + "<form action='updateQuestion'>"
							+ "<input type='hidden' name='userEmail' value='" + question.getUserEmail().getUserEmail()
							+ "'>" + "<input type='hidden' name='questionId' value='" + question.getQuestionId() + "'>"
							+ "Edit Your Question:<input type='text' name='questionValue'>"
							+ "<input type='submit' value='EDIT QUESTION'>" + "</form>";
				}

				result += "</th></tr>";

				List<Answer> answerList = answerServices.findAnswerByQuestionId(question.getQuestionId());

				for (int j = 0; j < answerList.size(); j++) {
					Answer answer = answerList.get(j);
					result += "<tr><td>" + answer.getAnswerValue();
					result += "<br>Answer ID: " + answer.getAnswerId();

					if (userEmail.equals(answer.getUserEmail().getUserEmail())) {

						result += "<form action='deleteAnswer'>" + "<input type='hidden' name='answerId' value='"
								+ answer.getAnswerId() + "'>" + "<input type='submit' value='Delete Answer'>"
								+ "</form>" + "<form action='updateAnswer'>"
								+ "<input type='hidden' name='userEmail' value='" + answer.getUserEmail().getUserEmail()
								+ "'>" + "<input type='hidden' name='questionId' value='"
								+ answer.getQuestionId().getQuestionId() + "'>"
								+ "<input type='hidden' name='answerId' value='" + answer.getAnswerId() + "'>"
								+ "Edit Your Answer:<input type='text' name='answerValue'>"
								+ "<input type='submit' value='EDIT ANSWER'>" + "</form>" + "</td></tr>";
					} else {
						result += "</td></tr>";
					}
				}
				result += "</table>";
			}
			result += "</body></html>";
			return result;
		} catch (Exception e) {
			logger.error("Error occurred while fetching home data: " + e.getMessage());
			return "error.html";
		}
	}
}
