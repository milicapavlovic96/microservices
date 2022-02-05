package com.example.competitionteamservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@CircuitBreaker(name = "default", fallbackMethod = "fallback")
@RateLimiter(name="default")
public class CompetitionTeamController {

	@Autowired
    CompetitionTeamService competitionTeamService;
    
	@GetMapping("/competition-team")
    public ResponseEntity getAllCompetitionsTeams() { 
        return new ResponseEntity<>(competitionTeamService.getAllTeamsCompetitions(), HttpStatus.OK);
    }
	
    @GetMapping("/competition-by-team/{id}")
    public ResponseEntity getCompetitionsByTeam(@PathVariable long id) { 
    	try {
			List<CompetitionTeam> competitionTeams = competitionTeamService.getCompetitionsByTeam(id);
			return new ResponseEntity<>(competitionTeams, HttpStatus.OK);
		} catch (CompetitionTeamNotFoundException scnfe) {
			String msg = scnfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}     
    }
    
    @GetMapping("/team-by-competition/{id}")
    public ResponseEntity getTeamsByCompetition(@PathVariable long id) {
        try {
			List<CompetitionTeam> competitionTeams = competitionTeamService.getTeamsByCompetition(id);
			return new ResponseEntity<>(competitionTeams, HttpStatus.OK);
		} catch (CompetitionTeamNotFoundException scnfe) {
			String msg = scnfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
    }
    
    @PostMapping("/team/{teamId}/competition/{competitionId}")
    public ResponseEntity addNewTeamCompetition(@PathVariable long teamId, @PathVariable long competitionId) {
    	try {
    		CompetitionTeam newTeamCompetition = competitionTeamService.addTeamCompetition(teamId, competitionId);
			return new ResponseEntity<>(newTeamCompetition, HttpStatus.CREATED);
		} catch (RuntimeException re) {
			String msg = re.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}
    }
    
    @DeleteMapping("/team/{teamId}/competition/{competitionId}")
    public ResponseEntity deleteTeamCompetition(@PathVariable long teamId, @PathVariable long competitionId) {
        try {
        	competitionTeamService.deleteTeamCompetition(teamId, competitionId);
            return new ResponseEntity<>(HttpStatus.OK);
		} catch (CompetitionTeamNotFoundException scnfe) {
			String msg = scnfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
    }
    
    public ResponseEntity fallback(RuntimeException e) {
	    return new ResponseEntity<String>("Competition-Team service is taking too long to respond. Please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
	}
}
