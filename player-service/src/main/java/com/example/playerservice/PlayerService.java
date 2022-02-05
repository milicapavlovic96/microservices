package com.example.playerservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	private RestTemplate restTemplate = this.restTemplate();

	@Autowired
	TeamProxy teamProxy;
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public List<Player> getAllPlayers() {
		return playerRepository.findAll();
	}

	public Player getPlayer(long id) {
		Optional<Player> player = playerRepository.findById(id);
		if (player.isEmpty()) {
			throw new PlayerNotFoundException("Player with the id " + id + " does not exsist.");
		}
		return player.get();
	}

	public Player createPlayer(Player player) {
		
		TeamDTO teamDTO = teamProxy.getTeam(player.getTeamId());
		
		if (teamDTO == null) {
			throw new EntityNotFoundException("Department with the given id does not exist.");
		}

		
		Optional<Player> existingPlayer = playerRepository.findById(player.getId());
		if (existingPlayer.isPresent()) {
			throw new PlayerAlreadyExistsException("Player with the id " + player.getId() + " already exists.");
		}
		return playerRepository.save(player);
	}

	public void deletePlayer(long id) {
		playerRepository.deleteById(id);
	}
}