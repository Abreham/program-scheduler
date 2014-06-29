package com.thought.exercise.service;

import java.util.List;

import com.thought.exercise.domain.Talk;
import com.thought.exercise.exception.InvalidScheduleException;

public class TrackServiceFactory implements IServiceFactory{
	
	public TrackService instance(String fileName) {
		TalkService talkService = new TalkService(fileName);
		TrackService trackService = new TrackService();
		SessionService sessionService = new SessionService(talkService);
		try {
			talkService.loadTalks(fileName);
		} catch (InvalidScheduleException e) {
			e.printStackTrace();
		}
		List<Talk> talks = talkService.get_talks();
		
		trackService.setTalks(talks);
		trackService.setTalkService(talkService);
		trackService.setSessionService(sessionService);

		return trackService;

	}
}
