package com.quiz.main.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quiz.main.model.Admin;


@Repository
public interface AdminRepository extends CrudRepository<Admin, Integer> {

	public Optional<Admin> findAdminByName(String name);
}
