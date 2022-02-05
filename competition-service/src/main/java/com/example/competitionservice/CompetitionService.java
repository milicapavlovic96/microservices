package com.example.competitionservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompetitionService {

	@Autowired
	CompetitionRepository competitionRepository;

	public List<Competition> getAllCompetitions() {
		return competitionRepository.findAll();
	}

	public Competition getCompetition(long id) {
		Optional<Competition> competition = competitionRepository.findById(id);
		if (competition.isEmpty()) {
			throw new CompetitionNotFoundException("Competition with the id " + id + " does not exsist.");
		}
		return competition.get();
	}

	public Competition createCompetition(Competition competition) {
		Optional<Competition> existingCompetition = competitionRepository.findById(competition.getId());
		if (existingCompetition.isPresent()) {
			throw new CompetitionAlreadyExistsException("Competition with the id " + competition.getId() + " already exists.");
		}
		return competitionRepository.save(competition);
	}

	public void deleteCompetition(long id) {
		competitionRepository.deleteById(id);
	}
}