package com.example.teamservice;


public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	EntityNotFoundException(String message) {
		super(message);
	}
}