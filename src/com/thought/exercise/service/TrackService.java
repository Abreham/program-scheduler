package com.thought.exercise.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.thought.exercise.domain.Session;
import com.thought.exercise.domain.Talk;
import com.thought.exercise.domain.Track;
import com.thought.exercise.exception.InvalidScheduleException;
import com.thought.exercise.utils.SessionType;

public class TrackService {
	
    private SessionService sessionService;
    private TalkService talkService;
    private List<Talk> talks;
    private List<Session> morningSessions;
    private List<Session> afternoonSessions;
    
    private static final int MINIMUM_TIME_PER_DAY = 360;
    private String networkingStartTime;
    


	
	/* create a Schedule and prints the tracks inside the schedule
	 */
	public void createSchedule() throws InvalidScheduleException,ParseException{
	
		int totalTalktime = talkService.getTotalTalkTimeminutes(talks);
		int conferenceDays = totalTalktime/MINIMUM_TIME_PER_DAY;
		
		
		morningSessions = sessionService.createSessions(talks, conferenceDays, SessionType.MORNING_SESSION);
		removeScheduleTalks(morningSessions);
		afternoonSessions = sessionService.createSessions(talks, conferenceDays, SessionType.AFTERNOON_SESSION);
		removeScheduleTalks(afternoonSessions);
		
		if(!isEveryTalkScheduled()){
			throw new InvalidScheduleException("data size is invalid!");
		}	
        List<Track> tracks = createTracks(morningSessions, afternoonSessions);
        
        for (int index = 0; index < tracks.size(); index++) {
        	int trackNumber = index + 1;
			System.out.println("Day " + trackNumber);
			showTrackInformation(tracks.get(index),conferenceDays);
		}
	}
	
	/*
	 * 
	 */
		private List<Track> createTracks(final List<Session> moriningSessions,final List<Session> afternoonSessions) {
			List<Track> tracks = new ArrayList<Track>();
            for (int index = 0; index < morningSessions.size(); index++) {
            	Track track = new Track();
            	
	            	track.setMorningSession(moriningSessions.get(index));
					track.setAfternoonSession(afternoonSessions.get(index));
					tracks.add(track);
					for (Talk talk : track.getAfternoonSession().getSessionTalks()) {
						System.out.println(talk.getTitle());
					}
			}
			return tracks;
		}
		

	/*
	 * takes a track object and display Information
	 */
	public void showTrackInformation(Track track,int conferenceDays) throws ParseException{
        printTrack(track, SessionType.MORNING_SESSION);	
        printTrack(track, SessionType.AFTERNOON_SESSION);
	}

	
	private void removeScheduleTalks(final List<Session> sessions){
        for (Session session : sessions) {
			talks.removeAll(session.getSessionTalks());
		}
	}
	
	public boolean isEveryTalkScheduled(){
		boolean scheduled = false;
		if (!talks.isEmpty()) {
		    List<Talk> scheduledTalkList = new ArrayList<Talk>();
		    for (Session session : afternoonSessions) {
				int afternoonSessionTalktime = talkService.getTotalTalkTimeminutes(session.getSessionTalks());
				for(Talk talk : talks) {
					 int talkTime = talk.getDuration();
					 if(talkTime  + afternoonSessionTalktime <= SessionType.AFTERNOON_SESSION.getMaxSessionTimeLimit()){
						 session.getSessionTalks().add(talk);
						 talk.setScheduled(true);
						 scheduledTalkList.add(talk);
					 }			
				}
			    
				removeScheduleTalks(afternoonSessions);
			}	    
		    scheduled = true;
		}
		return scheduled;
	}
	
	
	public void printTrack(final Track track,final SessionType type) throws ParseException{
		
		DateFormat df = new SimpleDateFormat("h:mm a");
		Calendar cal = Calendar.getInstance();
		String scheduledTime;
		
		if(type.equals(SessionType.MORNING_SESSION)){
			System.out.println("********* start track info **************");
			Date date = df.parse("9:00 AM");
			cal.setTime(date);
			scheduledTime = df.format(date);
			printSession(SessionType.MORNING_SESSION, track.getMorningSession().getSessionTalks(), scheduledTime);
			
		}else if(type.equals(SessionType.AFTERNOON_SESSION)){
			
            // Scheduled Lunch Time for 60 mins.
            Date date = df.parse("12:00 PM");
			scheduledTime = df.format(date);
            System.out.println(scheduledTime + " -- Lunch " + "==" + 60);
			
	        Date afternoonStartTime = df.parse("1:00 PM");
	        cal.setTime(afternoonStartTime);		
			String noonScheduleStartTime = df.format(afternoonStartTime);
			printSession(SessionType.AFTERNOON_SESSION, track.getAfternoonSession().getSessionTalks(), noonScheduleStartTime);
			
            System.out.println(networkingStartTime + " --Networking-- ");
			System.out.println("********* end track info **************");
			
		}
	}
	
	private void printSession(final SessionType type, final List<Talk> talks ,String talkStartTime) throws ParseException{	
		for (Talk talk : talks) {
			System.out.println(talkStartTime + " -- " + talk.getTitle() + "==" + talk.getDuration());
			 talkStartTime = talkStarTime(talkStartTime,talk.getDuration());
		}
	}
	
	
    private String talkStarTime(final String date, final int timeDuration) throws ParseException
    {
    	DateFormat dateFormat = new SimpleDateFormat("h:mm a");
    	Calendar c = Calendar.getInstance();
    	c.setTime(dateFormat.parse(date));
    	c.add(Calendar.MINUTE, timeDuration);
    	networkingStartTime = dateFormat.format(c.getTime());
        return dateFormat.format(c.getTime());
    }

	public SessionService getSessionService() {
		return sessionService;
	}

	public void setSessionService(SessionService sessionService) {
		this.sessionService = sessionService;
	}

	public TalkService getTalkService() {
		return talkService;
	}

	public void setTalkService(TalkService talkService) {
		this.talkService = talkService;
	}

	public List<Talk> getTalks() {
		return talks;
	}

	public void setTalks(List<Talk> talks) {
		this.talks = talks;
	}

	public String getNetworkingStartTime() {
		return networkingStartTime;
	}
}
