package com.quiz.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.main.model.Result;

@Repository
public interface ResultRepo extends JpaRepository<Result, Integer> {
	
}
