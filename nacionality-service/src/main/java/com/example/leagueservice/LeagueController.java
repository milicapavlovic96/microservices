package com.example.leagueservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeagueController {
	@Autowired
	LeagueService leagueService;

	@GetMapping("leagues")
	public ResponseEntity getAllLeague() {
		return new ResponseEntity<>(leagueService.getAllLeagues(), HttpStatus.OK);
	}

	@GetMapping("leagues/{id}")
	public ResponseEntity getLeague(@PathVariable long id) {
		try {
			League leagues = leagueService.getLeague(id);
		return new ResponseEntity<League>(leagues, HttpStatus.OK);
		}
		catch (RuntimeException snfe) {
			String msg = snfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("leagues")
	public ResponseEntity<League> createLeague(@RequestBody League leagues) {
		League newLeague = leagueService.createLeague(leagues);
		return new ResponseEntity<>(newLeague, HttpStatus.CREATED);
	}

	@DeleteMapping("leagues/{id}")
	public ResponseEntity removeLeague(@PathVariable long id) {
		try {
			leagueService.removeLeague(id);
		return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("League with the id " + id + " does not exsist.", HttpStatus.NOT_FOUND);
		}
	}
}