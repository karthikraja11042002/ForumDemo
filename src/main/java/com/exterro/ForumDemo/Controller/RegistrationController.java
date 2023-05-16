package com.exterro.ForumDemo.Controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exterro.ForumDemo.entity.User;
import com.exterro.ForumDemo.services.UserService;

@Controller
public class RegistrationController {
	
	// Creating a logger object
	private static Logger logger = LoggerFactory.getLogger("RegistrationController");
	@Autowired
	private UserService userSevices;

	@RequestMapping("/index")
	public String getIndex() {
		return "index.html";
	}
	
	@RequestMapping("/admin")
	public String getAdmin() {
		return "admin.html";
	}
	
	@RequestMapping("/register")
	public String signUp() {
		return "userregistraton.html";
	}

	@RequestMapping("admin/add")
	@ResponseBody
	public String signUp(User user) {
		logger.info("Received request to sign up a user: " + user);
		User user1 = null;
		try {
			user1 = userSevices.signUp(user);
		} catch (Exception e) {
			logger.error("Error occurred while signing up a user: " + e.getMessage());
			
		}
		
		// Return the HTML response
		return "<style>\r\n" + "		body {\r\n" + "			font-family: Arial, Helvetica, sans-serif;\r\n"
				+ "			background-color: #f1f1f1;\r\n" + "		}\r\n" + "		form {\r\n"
				+ "			background-color: #fff;\r\n" + "			border: 3px solid #f1f1f1;\r\n"
				+ "			margin: 50px auto;\r\n" + "			padding: 20px;\r\n" + "			max-width: 400px;\r\n"
				+ "			text-align: center;\r\n" + "		}\r\n"
				+ "		input[type=text], input[type=password] {\r\n" + "			width: 100%;\r\n"
				+ "			padding: 12px 20px;\r\n" + "			margin: 8px 0;\r\n"
				+ "			display: inline-block;\r\n" + "			border: 1px solid #ccc;\r\n"
				+ "			box-sizing: border-box;\r\n" + "		}\r\n" + "		button {\r\n"
				+ "			background-color: #4CAF50;\r\n" + "			color: white;\r\n"
				+ "			padding: 14px 20px;\r\n" + "			margin: 8px 0;\r\n" + "			border: none;\r\n"
				+ "			cursor: pointer;\r\n" + "			width: 100%;\r\n" + "		}\r\n"
				+ "		button:hover {\r\n" + "			opacity: 0.8;\r\n" + "		}\r\n" + "		.cancelbtn {\r\n"
				+ "			background-color: #f44336;\r\n" + "		}\r\n" + "		.imgcontainer {\r\n"
				+ "			text-align: center;\r\n" + "			margin: 24px 0 12px 0;\r\n" + "		}\r\n"
				+ "		img.avatar {\r\n" + "			width: 40%;\r\n" + "			border-radius: 50%;\r\n"
				+ "		}\r\n" + "		.container {\r\n" + "			padding: 16px;\r\n" + "		}\r\n"
				+ "		span.psw {\r\n" + "			float: right;\r\n" + "			padding-top: 16px;\r\n"
				+ "		}\r\n" + "	</style>Registration Completed Succesfully!!!\r\n"
				+ "	<form action='question'>\r\n" + "	<br>\r\n" + "		<input type='hidden' value='"
				+ user.getUserEmail() + "'>\r\n" + "          <input type='submit' value='QUESTION'>\r\n"
				+ "	</form>\r\n" + "	\r\n" + "	<form action='answer'>\r\n" + "	<br>\r\n"
				+ "	<input type='hidden' value='\"+reg.getUserEmail()+\"'>\r\n"
				+ "          <input type='submit' value='ANSWER'>\r\n" + "	</form>";

	}

	@RequestMapping("admin/view")
	@ResponseBody
	public User viewUser(String userEmail) {
		logger.info("Received request to view a user with email: " + userEmail);
		User user = null;
		try {
			user = userSevices.getUser(userEmail);
		} catch (Exception e) {
			logger.error("Error occurred while retrieving user with email " + userEmail + ": " + e.getMessage());
		
		}
		return user;
	}

	@RequestMapping("admin/viewall")
	@ResponseBody
	public List<User> viewAllUser() {
		logger.info("Received request to view all users");
		List<User> users = null;
		try {
			users = userSevices.getAllUser();
		} catch (Exception e) {
			logger.error("Error occurred while retrieving all users: " + e.getMessage());
			
		}
		return users;
	}

	@RequestMapping("admin/update")
	public String updateUser(User user) {
		logger.info("Received request to update a user: " + user);
		User updatedUser = null;
		try {
			updatedUser = userSevices.updateUser(user);
		} catch (Exception e) {
			logger.error("Error occurred while updating a user: " + e.getMessage());
			
		}
		return (updatedUser != null) ? "questionupdatesuccessful.html" : "questionupdatefailure.html";
	}

	@RequestMapping("admin/delete")
	@ResponseBody
	public String deleteUser(String userEmail) {
		logger.info("Received request to delete a user with email: " + userEmail);
		String result = null;
		try {
			result = userSevices.deleteUser(userEmail);
		} catch (Exception e) {
			logger.error("Error occurred while deleting user with email " + userEmail + ": " + e.getMessage());
		
		}
		return result;
	}

	@RequestMapping("user/add")
	public String userSignUp(User user) {
		logger.info("Received request to sign up a user: " + user);
		User registeredUser = null;
		try {
			registeredUser = userSevices.signUp(user);
		} catch (Exception e) {
			logger.error("Error occurred while signing up a user: " + e.getMessage());
			
		}
		return "registrationsuccessfull.html";
	}

	@RequestMapping("user/validateUser")
	@ResponseBody
	public String userLogin(String userEmail, String userPassword) {

		logger.info("Received request to validate user login: " + userEmail);
		boolean user = false;
		String result = "Hi User";
		try {
			if (userSevices.validateUser(userEmail, userPassword).equalsIgnoreCase("Login")) {
				User user1 = new User();
				user = userSevices.userLogin(user1);
				result += ("<h1>Login successful!!!</h1>" + "<form action='home\\" + userEmail + "'>"
						+ "    	<input type=\"submit\" value=\"Go Home\">\r\n" + "    </form>");
			}
		} catch (Exception e) {
			logger.error("Error occurred while validating user login for email " + userEmail + ": " + e.getMessage());
			
		}
		return result;
	}
	}


