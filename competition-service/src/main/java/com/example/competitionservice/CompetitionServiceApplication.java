package com.example.competitionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CompetitionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompetitionServiceApplication.class, args);
	}

}
