package com.thought.exercise.utils;

public class ValidationContext {

	private IValidator _validator;

	public ValidationContext(IValidator _validator) {
		this._validator = _validator;
	}
	
	public boolean isValid(String data){
		return _validator.isValid(data);
	}
}
