package com.thought.exercise.service;

public interface IServiceFactory {
	public TrackService instance(String fileName);
}
