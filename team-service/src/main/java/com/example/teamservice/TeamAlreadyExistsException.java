package com.example.teamservice;

public class TeamAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	TeamAlreadyExistsException(String message) {
		super(message);
	}
}
