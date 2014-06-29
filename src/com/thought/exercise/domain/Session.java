package com.thought.exercise.domain;

import java.util.Date;
import java.util.List;

import com.thought.exercise.utils.SessionType;

public class Session {
	private Date sessionStartTime;
	private Date sessionEndTime;
	private SessionType sessionType;

	private int duration;
    
	
	public Session(SessionType sessionType, List<Talk> sessionTalks) {
		this.sessionType = sessionType;
		this.sessionTalks = sessionTalks;
	}

	public List<Talk> sessionTalks;

	public Date getSessionStartTime() {
		return sessionStartTime;
	}

	public void setSessionStartTime(Date sessionStartTime) {
		this.sessionStartTime = sessionStartTime;
	}

	public Date getSessionEndTime() {
		return sessionEndTime;
	}

	public void setSessionEndTime(Date sessionEndTime) {
		this.sessionEndTime = sessionEndTime;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<Talk> getSessionTalks() {
		return sessionTalks;
	}

	public void setSessionTalks(List<Talk> sessionTalks) {
		this.sessionTalks = sessionTalks;
	}

	public SessionType getSessionType() {
		return sessionType;
	}

	public void setSessionType(SessionType sessionType) {
		this.sessionType = sessionType;
	}
}
