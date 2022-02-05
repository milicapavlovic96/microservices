package com.example.teamservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ComponentScan({"com.delivery.request"})

public class TeamService {

	@Autowired
	TeamRepository teamRepository;

	@Autowired
	LeagueProxy leagueProxy;
	
	@Autowired
	private RestTemplate restTemplate = this.restTemplate();

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public List<Team> getAllTeams() {
		return teamRepository.findAll();
	}

	public Team getTeam(long id) {
		Optional<Team> team = teamRepository.findById(id);
		if (team.isEmpty()) {
			throw new TeamNotFoundException("Team with the id " + id + " does not exsist.");
		}
		return team.get();
	}

	public Team createTeam(Team team) {
		LeagueDTO leaugueDTO = leagueProxy.getLeague(team.getLeagueId());
		
		if (leaugueDTO == null) {
			throw new EntityNotFoundException("League with the given id does not exist.");
		}

		Optional<Team> existingTeam = teamRepository.findById(team.getId());
		if (existingTeam.isPresent()) {
			throw new TeamAlreadyExistsException("Team with the id " + team.getId() + " already exists.");
		}
		return teamRepository.save(team);
	}

	public void deleteTeam(long id) {
		teamRepository.deleteById(id);
	}
}
