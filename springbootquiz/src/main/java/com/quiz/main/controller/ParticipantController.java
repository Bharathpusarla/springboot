package com.quiz.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.quiz.main.model.Participant;
import com.quiz.main.repository.ParticipantRepostory;

@RestController
@RequestMapping("/participants")
public class ParticipantController {

	@Autowired
	private ParticipantRepostory repo;
	
	
	@GetMapping("/{id}")
	public Participant getParticipant(@PathVariable("id") Integer id) {
		Optional<Participant> optProduct = repo.findById(id);
		if (optProduct.isEmpty()) {
//			;
		}
		System.out.println(optProduct.get());
		return optProduct.get();
	}
	
	@PostMapping
	public Participant create(@RequestBody Participant participant) {
		return repo.save(participant);
	}
	 
	@PutMapping
	public Participant update(@RequestBody Participant participant) {
		return repo.save(participant);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") Integer id) {
		repo.deleteById(id);
	}
	
}