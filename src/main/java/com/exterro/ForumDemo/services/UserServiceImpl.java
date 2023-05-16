package com.exterro.ForumDemo.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.exterro.ForumDemo.Dao.UserDao;
import com.exterro.ForumDemo.entity.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder paswordEncoder;

	// Creating a logger object
	private static Logger logger = LoggerFactory.getLogger("UserServiceImpl");

	@Override
	public User signUp(User user) {
		String pwd = paswordEncoder.encode(user.getUserPassword());
		user.setUserPassword(pwd);
		return userDao.save(user);
	}

	@Override
	public User getUser(String userEmail) {
		return userDao.findById(userEmail).orElse(new User());
	}

	@Override
	public List<User> getAllUser() {
		return userDao.findAll();
	}

	@Override
	public User updateUser(User user) {
		User user1 = userDao.findById(user.getUserEmail()).orElse(null);
		if (user1 != null) {
			userDao.delete(user1);
		}
		return userDao.save(user);
	}

	@Override
	public String deleteUser(String userEmail) {
		User user1 = userDao.findById(userEmail).orElse(null);
		if (user1 != null) {
			userDao.delete(user1);
			return "User Deleted Successfully";
		}
		return "User Unavailable";
	}

	@Override
	public boolean userLogin(User user) {
		return userDao.equals(user);
	}

	@Override
	public String validateUser(String userEmail, String userPassword) {
		try {
			List<User> userList = userDao.findByEmailAndPassword(userEmail, userPassword);
			for (User user : userList) {
				if (user.getUserEmail().equals(userEmail)) {
					return "Login";
				}
			}
			return "Invalid Login credentials";
		} catch (Exception e) {
			logger.error("An error occurred while validating user credentials: " + e.getMessage());
			return "Error occurred during login";
		}
	}
}
