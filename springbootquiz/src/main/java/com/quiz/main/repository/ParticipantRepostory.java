package com.quiz.main.repository;


import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.quiz.main.model.Admin;
import com.quiz.main.model.Participant;


@Repository
public interface ParticipantRepostory extends CrudRepository<Participant, Integer> {

	public Optional<Participant> findParticipantByName(String name);
}
