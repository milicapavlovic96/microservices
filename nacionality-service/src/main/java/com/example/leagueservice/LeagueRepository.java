package com.example.leagueservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Long> {
	public League findByName(String name);
}