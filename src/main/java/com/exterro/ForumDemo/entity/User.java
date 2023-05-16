package com.exterro.ForumDemo.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue
	private long id;
	private String userName;
	private String userPassword;
	private String roles;

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public User(String userName, String userPassword, String roles) {
		super();
		this.userName = userName;
		this.userPassword = userPassword;
		
		this.roles = roles;
	}

	// Creating a logger object
	private static Logger logger = LoggerFactory.getLogger("User");

	public User() {
		super();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	

	@Override
	public String toString() {
		return "User [userName=" + userName + ", userPassword=" + userPassword + ",  roles="
				+ roles + "]";
	}

}
