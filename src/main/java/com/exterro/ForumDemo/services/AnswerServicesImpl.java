package com.exterro.ForumDemo.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exterro.ForumDemo.Dao.AnswerDao;
import com.exterro.ForumDemo.Dao.QuestionDao;
import com.exterro.ForumDemo.Dao.UserDao;
import com.exterro.ForumDemo.entity.Answer;
import com.exterro.ForumDemo.entity.Question;
import com.exterro.ForumDemo.entity.User;
import com.exterro.ForumDemo.entity.request.AnswerRequest;

@Service
public class AnswerServicesImpl implements AnswerServices {

	@Autowired
	AnswerDao answerDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private QuestionDao questionDao;

	// Creating a logger object
	private static Logger logger = LoggerFactory.getLogger("AnswerServicesImpl");
	@Override
	public Answer postAnswer(AnswerRequest answerReq) throws Exception {
		Answer answer = new Answer();
		BeanUtils.copyProperties(answerReq, answer);
		User user = userDao.findById(answerReq.getUserEmail()).orElse(null);
		Question question = questionDao.findById(answerReq.getQuestionId()).orElse(null);
		if (user != null && question != null) {
			answer.setUserEmail(user);
			answer.setQuestionId(question);
			return answerDao.save(answer);
		} else {
			logger.error("User or question not found while posting answer.");
			throw new Exception();
		}
	}

	@Override
	public Answer getAnswer(int answerId) {
		return answerDao.findById(answerId).orElse(null);
	}

	@Override
	public List<Answer> getAllAnswer() {
		return answerDao.findAll();
	}

	@Override
	public Answer updateAnswer(int answerId, int questionId, String userEmail, String answerValue) throws Exception {
		User user = userDao.findById(userEmail).orElse(null);
		Question question = questionDao.findById(questionId).orElse(null);
		if (user != null && question != null) {
			Answer answer = new Answer(answerId, question, user, answerValue);

			Answer old = answerDao.findById(answerId).orElse(null);
			if (old != null) {
				answerDao.delete(old);
			}
			return answerDao.save(answer);
		} else {
			logger.error("User or question not found while updating answer.");
			throw new Exception();
		}
	}

	@Override
	public String deleteAnswer(int answerId) {
		Answer answer1 = answerDao.findById(answerId).orElse(null);
		if (answer1 != null) {
			answerDao.delete(answer1);
			return "Answer deleted successfully";
		} else {
			logger.error("Answer not found while deleting.");
			return "Answer unavailable";
		}
	}

	@Override
	public List<Answer> findAnswerByQuestionId(int questionId) {
		return answerDao.getAnswerByQuestionId(questionId);
	}
}
