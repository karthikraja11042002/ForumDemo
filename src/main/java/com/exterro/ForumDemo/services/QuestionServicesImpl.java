package com.exterro.ForumDemo.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exterro.ForumDemo.Dao.QuestionDao;
import com.exterro.ForumDemo.Dao.UserDao;
import com.exterro.ForumDemo.entity.Question;
import com.exterro.ForumDemo.entity.User;
import com.exterro.ForumDemo.entity.request.QuestionRequest;

@Service
public class QuestionServicesImpl implements QuestionServices {

	@Autowired
	private QuestionDao questionDao;

	@Autowired
	private UserDao userDao;

	// Creating a logger object
	private static Logger logger = LoggerFactory.getLogger("QuestionServicesImpl");
	@Override
	public Question postQuestion(QuestionRequest questionReq) throws Exception {
		Question question = new Question();

		BeanUtils.copyProperties(questionReq, question);
		User user = userDao.findById(questionReq.getUserEmail()).orElse(null);
		if (user != null) {
			question.setUser(user);
			return questionDao.save(question);
		} else {
			logger.error("User not found while posting question.");
			throw new Exception();
		}
	}

	@Override
	public Question getQuestion(int questionId) {
		return questionDao.findById(questionId).orElseThrow();
	}

	@Override
	public List<Question> getAllQuestion() {
		return questionDao.findAll();
	}

	@Override
	public Question updateQuestion(int questionId, String questionValue, String userEmail) throws Exception {
		User user = userDao.findById(userEmail).orElse(null);
		Question question = new Question(questionId, questionValue, user);
		if (user != null) {
			Question old = questionDao.findById(questionId).orElse(null);
			if (old != null) {
				questionDao.delete(old);
			}
			return questionDao.save(question);
		} else {
			logger.error("User not found while updating question.");
			throw new Exception();
		}
	}

	@Override
	public String deleteQuestion(int questionId) {
		Question question = questionDao.findById(questionId).orElse(null);

		if (question != null) {
			questionDao.delete(question);
			return "Question deleted successfully";
		} else {
			logger.error("Question not found while deleting.");
			return "Question unavailable";
		}
	}

}
