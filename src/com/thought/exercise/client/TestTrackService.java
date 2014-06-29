package com.thought.exercise.client;

import java.text.ParseException;

import com.thought.exercise.exception.InvalidScheduleException;
import com.thought.exercise.service.IServiceFactory;
import com.thought.exercise.service.TrackService;
import com.thought.exercise.service.TrackServiceFactory;

/*
 * Test Track and schedule creation 
 * throws exception in case of parse error.
 */
public class TestTrackService {
	public static void main(String[] args) {
		String fileName = "/home/dagem/workspace/thought-work/src/sample.txt";
		IServiceFactory serviceFactory = new TrackServiceFactory();
		TrackService trackService = serviceFactory.instance(fileName);
		try {
			trackService.createSchedule();
		} catch (InvalidScheduleException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}		
	}
}
