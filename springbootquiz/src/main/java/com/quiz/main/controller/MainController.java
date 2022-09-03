package com.quiz.main.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.quiz.main.model.QuestionForm;
import com.quiz.main.model.Result;
import com.quiz.main.service.QuizService;

@RestController
public class MainController {
	
	@Autowired
	Result result;
	@Autowired
	QuizService qService;
	
	Boolean submitted = false;
	
	@ModelAttribute("result")
	public Result getResult() {
		return result;
	}
	
	@GetMapping("/")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("home.html");
		return modelAndView;
	}
	@GetMapping("/admin.html")
	public  ModelAndView admin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("admin.html");
		return mv;
	}
	@GetMapping("/index.html")
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("index.html");
		return mv;
	}
	@GetMapping("/participant.html")
	public ModelAndView participant() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("participant.html");
		return mv;
	}
	
	@PostMapping("/quiz")
	public ModelAndView quiz(@RequestParam String username, Model m, RedirectAttributes ra) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("quiz.html");
		if(username.equals("")) {
			ra.addFlashAttribute("warning", "You must enter your name");
			return mv;
		}
		
		submitted = false;
		result.setUsername(username);
		
		QuestionForm qForm = qService.getQuestions();
		m.addAttribute("qForm", qForm);
		
		return mv;
	}
	
	@PostMapping("/submit")
	public ModelAndView submit(@ModelAttribute QuestionForm qForm, Model m) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("result.html");
		if(!submitted) {
			result.setTotalCorrect(qService.getResult(qForm));
			qService.saveScore(result);
			submitted = true;
		}
		
		return mv;
	}
	
	@GetMapping("/score")
	public ModelAndView score(Model m) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("scoreboard.html");
		List<Result> sList = qService.getTopScore();
		m.addAttribute("sList", sList);
		
		return mv;
	}

}
