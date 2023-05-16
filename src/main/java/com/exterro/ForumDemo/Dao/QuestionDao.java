package com.exterro.ForumDemo.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.exterro.ForumDemo.entity.Question;

@Repository

//create interface named QuestionDao that extends JpaRepository to perform CRUD operations
public interface QuestionDao extends JpaRepository<Question, Integer> 
{

}
