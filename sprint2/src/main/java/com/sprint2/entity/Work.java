package com.sprint2.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Work {
	private String companyName;
	private String workLocation;
	private String position;
	private String startDate;
	private String endDate;
	private boolean currentlyWorking;
	
	
	public Work(String companyName, String workLocation, String position, String startDate, String endDate,
			boolean currentlyWorking) {
		super();
		this.companyName = companyName;
		this.workLocation = workLocation;
		this.position = position;
		this.startDate = startDate;
		this.endDate = endDate;
		this.currentlyWorking = currentlyWorking;
	}

	
	public Work() {
		super();
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getWorkLocation() {
		return workLocation;
	}


	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	public String getStartDate() {
		return startDate;
	}


	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	public String getEndDate() {
		return endDate;
	}


	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}


	public boolean isCurrentlyWorking() {
		return currentlyWorking;
	}


	public void setCurrentlyWorking(boolean currentlyWorking) {
		this.currentlyWorking = currentlyWorking;
	}


	@Override
	public String toString() {
		return "Work [companyName=" + companyName + ", workLocation=" + workLocation + ", position=" + position
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", currentlyWorking=" + currentlyWorking + "]";
	}
	
}
