package com.example.leagueservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class LeagueServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeagueServiceApplication.class, args);
	}

}
