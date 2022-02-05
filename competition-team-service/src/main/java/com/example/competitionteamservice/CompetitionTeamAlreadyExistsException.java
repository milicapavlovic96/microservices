package com.example.competitionteamservice;


public class CompetitionTeamAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CompetitionTeamAlreadyExistsException(String message) {
		super(message);
	}
}
