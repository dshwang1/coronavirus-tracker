package com.cognixia.jump.model;

public class Location {
	private String state;
	private int latestTotalCases;
	private String region;
	private int numIncreased;
	
	
	
	public int getNumIncreased() {
		return numIncreased;
	}
	public void setNumIncreased(int numIncreased) {
		this.numIncreased = numIncreased;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getLatestTotalCases() {
		return latestTotalCases;
	}
	public void setLatestTotalCases(int latestTotalCases) {
		this.latestTotalCases = latestTotalCases;
	}
	
	
	
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	@Override
	public String toString() {
		return "Location [state=" + state + ", region=" + region + ", latestTotalCases=" + latestTotalCases  + "]";
	}

}
