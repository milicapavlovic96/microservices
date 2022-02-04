package com.example.playerservice;

public class PlayerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	PlayerNotFoundException(String message) {
		super(message);
	}
}