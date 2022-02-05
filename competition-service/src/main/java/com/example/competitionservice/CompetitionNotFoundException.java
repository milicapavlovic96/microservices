package com.example.competitionservice;

public class CompetitionNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	CompetitionNotFoundException(String message) {
		super(message);
	}
}