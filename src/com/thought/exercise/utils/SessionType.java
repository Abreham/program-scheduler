package com.thought.exercise.utils;

public enum SessionType {
	MORNING_SESSION(180,180), AFTERNOON_SESSION(180,240);
	
    int minSessionTimeLimit;
    int maxSessionTimeLimit;
    
    private SessionType(int min,int max){
    	this.minSessionTimeLimit = min;
    	this.maxSessionTimeLimit = max;
    }
    
    
	public int getMinSessionTimeLimit() {
		return minSessionTimeLimit;
	}



	public void setMinSessionTimeLimit(int minSessionTimeLimit) {
		this.minSessionTimeLimit = minSessionTimeLimit;
	}



	public int getMaxSessionTimeLimit() {
		return maxSessionTimeLimit;
	}



	public void setMaxSessionTimeLimit(int maxSessionTimeLimit) {
		this.maxSessionTimeLimit = maxSessionTimeLimit;
	}



	@Override
	public String toString() {		
		return new String("[" + minSessionTimeLimit + "," + maxSessionTimeLimit + "]");				
	}
}
