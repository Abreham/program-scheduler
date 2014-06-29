package com.thought.exercise.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thought.exercise.domain.Function;
import com.thought.exercise.domain.Pair;
import com.thought.exercise.domain.Talk;
import com.thought.exercise.exception.InvalidScheduleException;
import com.thought.exercise.utils.ReadFileUtil;

public class TalkService {
	
	private String fileName;
	
	public TalkService(String fileName) {
		this.fileName = fileName;
	}

	private List<Talk> _talks = new ArrayList<Talk>();
	private ReadFileUtil util = ReadFileUtil.Instance;
	private static final StringListtoTalkFunction STRING_LIST_TO_TALK_LIST = new StringListtoTalkFunction();

	public List<Talk> get_talks() {
		return _talks;
	}

	public void reset() {
		_talks = new ArrayList<Talk>();
	}

	public void loadTalks(String fileName) throws InvalidScheduleException {
		try {
			_talks = STRING_LIST_TO_TALK_LIST.apply(util.readLines(fileName));
	
		} catch (IOException e) {
			throw new InvalidScheduleException(
					"Input program data is corrupted or not properly formatted");
		}
	}
	
	   
	private static class StringListtoTalkFunction implements Function<List<Pair<String,String>>, List<Talk>>{

		@Override
		public List<Talk> apply(List<Pair<String, String>> input) {
			List<Talk> talks = new ArrayList<Talk>();
			for (Pair<String, String> pair : input)
			{
			    System.out.println(pair.getFirst() + "/" + pair.getSecond());
			    talks.add(new Talk(pair.getFirst(), Integer.parseInt(pair.getSecond())));
			}
			
			return talks;
		}
		
	}
	
	public int getTotalTalkTimeminutes(List<Talk> talksList){
		 if(talksList == null || talksList.isEmpty())
	            return 0;
		  int totalTime = 0;
		  
	        for(Talk talk : talksList) {
	            totalTime += talk.getDuration();
	        }
	        return totalTime;
	}

	public String getFileName() {
		return fileName;
	}
}
