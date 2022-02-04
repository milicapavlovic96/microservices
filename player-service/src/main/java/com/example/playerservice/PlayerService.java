package com.example.playerservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

	@Autowired
	PlayerRepository playerRepository;

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