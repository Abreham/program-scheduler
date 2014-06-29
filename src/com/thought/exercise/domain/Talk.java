package com.thought.exercise.domain;

import java.util.Date;

public class Talk {

	String title;
	int duration;
	private Date startTime;
	private boolean scheduled;

	public Talk(String title, int time) {
		this.title = title;
		this.duration = time;
	}
	
	public boolean isScheduled(){
		return scheduled;
	}
	
	public void setScheduled(boolean scheduled){
		this.scheduled = scheduled;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

}
