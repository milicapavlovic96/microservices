package com.example.teamservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//Feign will automatically talk to Eureka service registry and load balance between multiple instances of microservices
@FeignClient(name="league-service")
public interface LeagueProxy {
	
	@GetMapping("leagues/{id}")
	public LeagueDTO getLeague(@PathVariable long id);
}
