package com.example.teamservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeamService {

	@Autowired
	TeamRepository teamRepository;

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
