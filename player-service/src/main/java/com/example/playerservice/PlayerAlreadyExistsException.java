package com.example.playerservice;

public class PlayerAlreadyExistsException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	PlayerAlreadyExistsException(String message) {
		super(message);
	}
}
