package com.example.competitionservice;

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

@RestController
@CircuitBreaker(name = "default", fallbackMethod = "fallback")
public class CompetitionController {

	@Autowired
	CompetitionService competitionService;

	@GetMapping("competitions")
	public ResponseEntity getAllCompetitions() {
		return new ResponseEntity<>(competitionService.getAllCompetitions(), HttpStatus.OK);
	}

	@GetMapping("competitions/{id}")
	public ResponseEntity getCompetition(@PathVariable long id) {
		try {
			Competition competition = competitionService.getCompetition(id);
		return new ResponseEntity<Competition>(competition, HttpStatus.OK);
		}
		catch (CompetitionNotFoundException cnfe) {
			String msg = cnfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("competitions")
	public ResponseEntity<Competition> createCompetition(@RequestBody Competition competition) {
		Competition newCompetition = competitionService.createCompetition(competition);
		return new ResponseEntity<>(newCompetition, HttpStatus.CREATED);
	}

	@DeleteMapping("competitions/{id}")
	public ResponseEntity removeCompetition(@PathVariable long id) {
		try {
			competitionService.deleteCompetition(id);
		return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Competition with the id " + id + " does not exsist.", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity fallback(RuntimeException e) {
	    return new ResponseEntity<String>("Competition service is taking too long to respond. Please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
	}
}
