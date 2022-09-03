package com.quiz.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quiz.main.execptions.AdminNotFoundExecption;
import com.quiz.main.model.Admin;
import com.quiz.main.repository.AdminRepository;


@Service
public class AdminService {

	@Autowired
	AdminRepository authRepo;
	
	public Admin GetAdminByName(String name) {
		Optional<Admin> found = authRepo.findAdminByName(name);
		if(found.isPresent()) return found.get();
		else throw new AdminNotFoundExecption();
	}
	
	
	public Boolean isValidPassword(String cmp, String actual) {
		return ((cmp.equals(actual)) ?  true :  false);	
	}
	
}
 
