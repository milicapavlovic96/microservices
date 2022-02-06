package com.example.playerservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
@CircuitBreaker(name = "default", fallbackMethod = "fallback")
@RateLimiter(name="default")
public class PlayerController {

	@Autowired
	PlayerService playerService;

	@GetMapping("players")
	public ResponseEntity getAllPlayers() {
		return new ResponseEntity<>(playerService.getAllPlayers(), HttpStatus.OK);
	}

	@GetMapping("players/{id}")
	public ResponseEntity getPlayer(@PathVariable long id) {
		try {
			Player player = playerService.getPlayer(id);
		return new ResponseEntity<Player>(player, HttpStatus.OK);
		}
		catch (PlayerNotFoundException cnfe) {
			String msg = cnfe.getMessage();
			return new ResponseEntity<String>(msg, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("players")
	public ResponseEntity<Player> createPlayer(@RequestBody Player player) {
		Player newPlayer = playerService.createPlayer(player);
		return new ResponseEntity<>(newPlayer, HttpStatus.CREATED);
	}

	@DeleteMapping("players/{id}")
	public ResponseEntity removePlayer(@PathVariable long id) {
		try {
			playerService.deletePlayer(id);
		return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Player with the id " + id + " does not exsist.", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity fallback(RuntimeException e) {
	    return new ResponseEntity<String>("Player service is taking too long to respond. Please try again later.", HttpStatus.SERVICE_UNAVAILABLE);
	}
}