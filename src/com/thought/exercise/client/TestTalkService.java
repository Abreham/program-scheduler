package com.thought.exercise.client;

import com.thought.exercise.exception.InvalidScheduleException;
import com.thought.exercise.service.TalkService;

public class TestTalkService {
	public static void main(String[] args) {
		String fileName = "/home/dagem/workspace/thought-work/src/sample.txt";
		TalkService talkService = new TalkService(fileName);
		
			try {
				talkService.loadTalks(fileName);
				//sessionService.printSessionCombination(sessionService.findPossibleCombSession(talkService.get_talks(), 2, SessionType.AFTERNOON_SESSION));
				
			} catch (InvalidScheduleException e) {
				e.printStackTrace();
			}
	}
}