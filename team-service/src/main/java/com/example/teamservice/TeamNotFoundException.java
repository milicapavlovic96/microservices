package com.example.teamservice;

public class TeamNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	TeamNotFoundException(String message) {
		super(message);
	}
}