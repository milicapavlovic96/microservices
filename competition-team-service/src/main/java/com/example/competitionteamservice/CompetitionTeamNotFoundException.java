package com.example.competitionteamservice;

public class CompetitionTeamNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public CompetitionTeamNotFoundException(String message) {
		super(message);
	}
}