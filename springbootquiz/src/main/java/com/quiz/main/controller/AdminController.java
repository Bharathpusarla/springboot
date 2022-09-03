package com.quiz.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.main.model.Admin;
import com.quiz.main.model.Participant;
import com.quiz.main.model.Question;
import com.quiz.main.repository.AdminRepository;
import com.quiz.main.repository.QuestionRepo;
import com.quiz.main.service.AdminService;



@Controller
@RequestMapping("/admin")
public class AdminController {

	Logger logger = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	AdminService authService;
	@Autowired
	QuestionRepo repo;
	AdminRepository repo1;
	@PostMapping
	public Admin create(@RequestBody Admin admin) {
		return repo1.save(admin);
	}
	
	@GetMapping("/addquestion.html")
	public String admin() {
		return "addquestion.html";
		
	}
	@RequestMapping(value="/admin/add", method = RequestMethod.POST)
	public String createProduct(@ModelAttribute("question") Question question, ModelMap model) {
		Question savedProduct = repo.save(question);
		model.addAttribute("", repo.findAll());
		return "added";
	}

	@PostMapping("/Auth")
	public String authenticateUser(@RequestParam("username") String name, @RequestParam("password") String pswd) {
	Admin admin = authService.GetAdminByName(name);
	if(authService.isValidPassword(pswd, admin.getPassword())) {
		return "success";
	}
	else {
		return "failure";
	}
		
	}
}