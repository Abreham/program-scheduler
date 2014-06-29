package com.thought.exercise.utils;

public class RawTime implements IValidator {

	@Override
	public boolean isValid(String input) {
		return (input.trim().matches("Default TalkTimePattern"))
				|| (input.trim().toUpperCase()
						.endsWith("Default TalkTimePattern"));
	}
}
