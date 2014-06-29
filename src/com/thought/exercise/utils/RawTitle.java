package com.thought.exercise.utils;

import java.util.regex.Pattern;

public class RawTitle implements IValidator{

	 final Pattern timePattern = Pattern.compile("enum pattern for time");
     final String minSuffix = "min";
     final String lightningSuffix = "lightning"; 

	@Override
	public boolean isValid(String input) {
		String candidateTrim = input.trim();
		boolean isValidWithLightning = isValidWithLightning(candidateTrim);


		if(isValidWithLightning) 
			return isValidWithLightning;


		return isValidPosition(candidateTrim);			
	}

	
	private boolean isValidWithLightning(String data)
	{
		if(data.toUpperCase().endsWith(lightningSuffix))
		{
			return !data.matches(".*\\d.*");
		}

		return false;
	}
	
	private boolean isValidPosition(String candidate)
	{
		return candidate.matches("regualr expression here");
	}
	
}
