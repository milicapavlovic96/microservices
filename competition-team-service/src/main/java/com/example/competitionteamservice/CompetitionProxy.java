package com.example.competitionteamservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "competition-service")
public interface CompetitionProxy {
	
	@GetMapping("competitions/{id}")
	public CompetitionDTO getCompetition(@PathVariable long id);
}
