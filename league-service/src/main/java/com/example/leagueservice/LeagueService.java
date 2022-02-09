package com.example.leagueservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeagueService {

	@Autowired
	LeagueRepository leagueRepository;

	public List<League> getAllLeagues() {
		return leagueRepository.findAll();
	}

	public League getLeague(long id) {
		Optional<League> league = leagueRepository.findById(id);
		if (league.isEmpty()) {
			throw new RuntimeException("League with the id " + id + " does not exsist.");
		}
		return league.get();
	}

	public League createLeague(League league) {
		Optional<League> existingLeague = leagueRepository.findById(league.getId());
		if(existingLeague.isPresent()) {
			throw new RuntimeException("League with the id " + league.getId() + " already exists.");
		}
		return leagueRepository.save(league);
	}

	public void removeLeague(long id) {
		Optional<League> entity = leagueRepository.findById(id);
		if (entity.isEmpty()) {
            throw new RuntimeException("League not found");
        } else {
        	leagueRepository.delete(entity.get());
        }
	}

}