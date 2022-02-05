package com.example.competitionteamservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CompetitionTeamServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompetitionTeamServiceApplication.class, args);
	}

}
