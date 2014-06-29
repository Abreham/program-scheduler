package com.thought.exercise.exception;

public class InvalidScheduleException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidScheduleException(String message) {
		super(message);
	}
}
