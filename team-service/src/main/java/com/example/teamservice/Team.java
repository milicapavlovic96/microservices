package com.example.teamservice;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String teamName;

	private Long leagueId;

	public Team() {
	}
	
	public Team(String teamName, Long leagueId) {
		super();
		this.teamName = teamName;
		this.leagueId = leagueId;  

	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Long getId() {
		return id;
	}
	
	public Long getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(Long leagueId) {
		this.leagueId = leagueId;
	}
}