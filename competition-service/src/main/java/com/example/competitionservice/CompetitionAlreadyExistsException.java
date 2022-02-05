package com.example.competitionservice;

public class CompetitionAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	CompetitionAlreadyExistsException(String message) {
		super(message);
	}
}