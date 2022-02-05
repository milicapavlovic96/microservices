package com.example.competitionteamservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="team-competition")
public interface TeamProxy {
	
	@GetMapping("teams/{id}")
	public TeamDTO getTeam(@PathVariable long id);
}