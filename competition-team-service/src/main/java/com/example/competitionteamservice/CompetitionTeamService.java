package com.example.competitionteamservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ComponentScan({"com.delivery.request"})

public class CompetitionTeamService {

	@Autowired
	TeamCompetitionRepository teamCompetitionRepository;

	@Autowired
	private RestTemplate restTemplate = this.restTemplate();
	
	@Autowired
	TeamProxy teamProxy;
	
	@Autowired
	CompetitionProxy competitionProxy;

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public List<CompetitionTeam> getAllTeamsCompetitions() {
		return teamCompetitionRepository.findAll();
	}
	
	public List<CompetitionTeam> getCompetitionsByTeam(long teamId) {
		List<CompetitionTeam> teamCompetitions = teamCompetitionRepository.findByTeamId(teamId);
		if(teamCompetitions.isEmpty()) {
			throw new CompetitionTeamNotFoundException("Team with id " + teamId + " is not signed up for any competitions.");
		}
		return teamCompetitions;
	}

	public List<CompetitionTeam> getTeamsByCompetition(long competitionId) {
		List<CompetitionTeam> competitionTeams = teamCompetitionRepository.findByCompetitionId(competitionId);
		if(competitionTeams.isEmpty()) {
			throw new CompetitionTeamNotFoundException("Competition with id " + competitionId + " does not have any teams signed up for it.");
		}
		return competitionTeams;
	}

	public CompetitionTeam addTeamCompetition(long teamId, long competitionId) {

		TeamDTO teamDTO = teamProxy.getTeam(teamId);
		if (teamDTO == null) {
			throw new EntityNotFoundException("Team with the given id does not exist.");
		}

		CompetitionDTO competitionDTO = competitionProxy.getCompetition(competitionId);
		if (competitionDTO == null) {
			throw new EntityNotFoundException("Competition with the given id does not exist.");
		}

		Optional<CompetitionTeam> entity = teamCompetitionRepository.findByTeamIdAndCompetitionId(teamId, competitionId);

		if (entity.isPresent()) {
			throw new CompetitionTeamAlreadyExistsException("Team-competition entity already exists.");
		}

		return teamCompetitionRepository.save(new CompetitionTeam(teamId, competitionId));
	}

	public void deleteTeamCompetition(long teamId, long competitionId) {
		Optional<CompetitionTeam> entity = teamCompetitionRepository.findByTeamIdAndCompetitionId(teamId, competitionId);
		if (entity.isEmpty()) {
            throw new CompetitionTeamNotFoundException("Team-competition with team id " + teamId + " and competition id " + competitionId + " does not exist.");
        } else {
        	teamCompetitionRepository.delete(entity.get());
        }
	}
}