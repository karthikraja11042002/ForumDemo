package com.exterro.ForumDemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.exterro.model.Car;
import com.exterro.model.User;
import com.exterro.repository.CarRepository;
import com.exterro.repository.UserRepository;

@SpringBootApplication
public class ForumDemoApplication {

	// Creating a logger object	
	private static Logger logger = LoggerFactory.getLogger("ForumDemoApplication");

	public static void main(String[] args) {
		try {
			logger.info("Starting ForumDemoApplication");

			// Starting the Spring Boot application
			SpringApplication.run(ForumDemoApplication.class, args);

			logger.info("ForumDemoApplication started successfully");
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
	
	@Bean
	CommandLineRunner commandLineRunner(CarRepository carRepo, UserRepository users, PasswordEncoder encoder) {
		return args -> {
			users.save(new User("user", encoder.encode("password"), "ROLE_USER"));
			users.save(new User("admin", encoder.encode("password"), "ROLE_USER,ROLE_ADMIN"));
			carRepo.save(new Car("TN66C7689", "TIAGO", "RAGHU", 100000.0));
			carRepo.save(new Car("TN38G8888", "ALTRAZ", "SHYAM", 150000.0));
		};
	}

}
