package com.thought.exercise.service;

import java.util.ArrayList;
import java.util.List;

import com.thought.exercise.domain.Session;
import com.thought.exercise.domain.Talk;
import com.thought.exercise.utils.SessionType;

public class SessionService {
	   private List<Session> _sessions;
	   private final TalkService talkService;
	   
	   
	
	public SessionService(TalkService talkService) {
		this.talkService = talkService;
	}

	public List<Session> createSessions(final List<Talk> talks,final int totalPossibleDays, final SessionType type)
	{
		_sessions = new ArrayList<Session>();
		List<List<Talk>> afternoonSessions = findPossibleCombSession(talkService.get_talks(), 2, SessionType.AFTERNOON_SESSION);
		for (List<Talk> session : afternoonSessions) {
			_sessions.add(new Session(type, session));
		}
		return _sessions;
	}
	
	public List<Session> GetSessions()
	{
		return _sessions;
	}
	
	public void printSessionCombination(final List<List<Talk>> talks){
		for (List<Talk> list : talks) {
			System.out.println("#######");
			for (Talk talk : list) {
				 System.out.println(talk.getTitle() + "/" + talk.getDuration());
			}
		}
	}
	
	
	
	 private List<List<Talk>> findPossibleCombSession(final List<Talk> talksListForOperation, final int totalPossibleDays, final SessionType type)
	    {

	        
	        int talkListSize = talksListForOperation.size();
	        
	        List<List<Talk>> possibleCombinationsOfTalks = new ArrayList<List<Talk>>();
	        int possibleCombinationCount = 0;
	        
	        // Loop to get combination for total possible days.
	        // Check one by one from each talk to get possible combination.
	        for(int count = 0; count < talkListSize; count++) {
	            int startPoint = count;
	            int totalTime = 0;
	            
	            List<Talk> possibleCombinationList = new ArrayList<Talk>();
	            
	            // Loop to get possible combination.
	            while(startPoint != talkListSize) {
	                int currentCount = startPoint;
	                startPoint++;
	                Talk currentTalk = talksListForOperation.get(currentCount);
	                if(currentTalk.isScheduled())
	                    continue;
	                int talkTime = currentTalk.getDuration();
	                // If the current talk time is greater than maxSessionTimeLimit or 
	                // sum of the current time and total of talk time added in list  is greater than maxSessionTimeLimit.
	                // then continue.
	                if(talkTime > type.getMaxSessionTimeLimit() || talkTime + totalTime > type.getMaxSessionTimeLimit()) {
	                    continue;
	                }
	                
	                possibleCombinationList.add(currentTalk);
	                totalTime += talkTime;
	                
	                // If total time is completed for this session then break this loop.
	                if(type.equals(SessionType.MORNING_SESSION)) {
	                    if(totalTime == type.getMaxSessionTimeLimit())
	                        break;
	                }else if(totalTime >= type.getMinSessionTimeLimit())
	                    break;
	            }
	            
	            // If session is valid than add this session in the possible combination list and set all added talk as scheduled.
	            if(isValidSession(totalTime, type)) {
	                possibleCombinationsOfTalks.add(possibleCombinationList);
	                for(Talk talk : possibleCombinationList){
	                    talk.setScheduled(true);
	                }
	                possibleCombinationCount++;
	                if(possibleCombinationCount == totalPossibleDays)
	                    break;
	            }
	        }
	        
	        return possibleCombinationsOfTalks;
	    }
	 

	   
     /* morning session is equal to maxSessionTimeLimit.
      * SessionType.AFTERNOON_SESSION min <= afternoon session <= SessionType.AFTERNOON_SESSION maximum  
      */
	 public boolean isValidSession(final int sessionTimeMin,SessionType type){
		  boolean validSession = false;
		  if(type.equals(SessionType.MORNING_SESSION))
              validSession = (sessionTimeMin == type.getMaxSessionTimeLimit());
          else
              validSession = (sessionTimeMin >= type.getMinSessionTimeLimit() && sessionTimeMin <= type.getMaxSessionTimeLimit());
		  return validSession;
	 }	
}
