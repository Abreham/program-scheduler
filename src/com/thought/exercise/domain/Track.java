package com.thought.exercise.domain;

import java.util.Date;

public class Track {

	private String title;
	private String LunchTitle;
	private Session morningSession;
	private Session afternoonSession;
	private Date networkingEventStart;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLunchTitle() {
		return LunchTitle;
	}

	public void setLunchTitle(String lunchTitle) {
		LunchTitle = lunchTitle;
	}

	public Session getMorningSession() {
		return morningSession;
	}

	public void setMorningSession(Session morningSession) {
		this.morningSession = morningSession;
	}

	public Session getAfternoonSession() {
		return afternoonSession;
	}

	public void setAfternoonSession(Session afternoonSession) {
		this.afternoonSession = afternoonSession;
	}

	public Date getNetworkingEventStart() {
		return networkingEventStart;
	}

	public void setNetworkingEventStart(Date networkingEventStart) {
		this.networkingEventStart = networkingEventStart;
	}

}
