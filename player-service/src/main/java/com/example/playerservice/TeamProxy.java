package com.example.playerservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// Feign will automatically talk to Eureka service registry and load balance between multiple instances of microservices
@FeignClient(name="team-service")
public interface TeamProxy {
	
	@GetMapping("teams/{id}")
	public TeamDTO getTeam(@PathVariable long id);
}
