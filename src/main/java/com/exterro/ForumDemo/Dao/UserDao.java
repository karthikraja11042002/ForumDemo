package com.exterro.ForumDemo.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.exterro.ForumDemo.entity.User;
@Repository

//create interface named UserDao that extends JpaRepository to perform CRUD operations
public interface UserDao extends JpaRepository<User, String> {
	
	 // create a custom query
	@Query(nativeQuery=true,value="select * from users where user_email=?1 and user_password=?2")
    List<User> findByEmailAndPassword(String userEmail,String userPassword);
	Optional<User> findByUserEmail(String userEmail);
}
