package com.example.teamservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@CircuitBreaker(name = "default", fallbackMethod = "fallback")
@RateLimiter(name="default")
public class TeamController {

	@Autowired
	TeamService teamService;

	@GetMapping("teams")
	public ResponseEntity getAllTeams() {
		return new ResponseEntity<>(teamService.getAllTeams(), HttpStatus.OK);
	}

	@GetMapping("teams/{id}")
	public ResponseEntity getTeam(@PathVariable long id) {
		try {
		Team team = teamService.getTeam(id);
		return new ResponseEntity<Team>(team, HttpStatus.OK);
		}
		catch (TeamNotFoundException cnfe) {
			String msg = cnfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("teams")
	public ResponseEntity<Team> createTeam(@RequestBody Team team) {
		Team newTeam = teamService.createTeam(team);
		return new ResponseEntity<>(newTeam, HttpStatus.CREATED);
	}

	@DeleteMapping("teams/{id}")
	public ResponseEntity removePlayer(@PathVariable long id) {
		try {
			teamService.deleteTeam(id);
		return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Team with the id " + id + " does not exsist.", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity fallback(RuntimeException e) {
	    return new ResponseEntity<String>("Team service is taking too long to respond. Please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
	}
}
