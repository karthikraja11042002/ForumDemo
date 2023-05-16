package com.exterro.ForumDemo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.exterro.ForumDemo.entity.User;
@Service
public interface UserService {
	public User signUp(User user);
	public User getUser(String userEmail);
	public List<User> getAllUser();
	public User updateUser(User user);
	public String deleteUser(String userEmail);
	public String validateUser(String userEmail,String userPassword);
    public boolean userLogin(User user);
}

