package com.models;

public class Report {

	private int reportID;
	private String type;
	private String details;
	private int userID;
	private int destID;

	public Report() {
		this.reportID = 0;
		this.type = "";
		this.details = "";
		this.userID = 0;
		this.destID = 0;
	}

	public Report(int reportID, String type, String details, int userID, int destID) {
		this.reportID = reportID;
		this.type = type;
		this.details = details;
		this.userID = userID;
		this.destID = destID;
	}

	public int getReportID() {
		return reportID;
	}

	public void setReportID(int reportID) {
		this.reportID = reportID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getDestID() {
		return destID;
	}

	public void setDestID(int destID) {
		this.destID = destID;
	}

}
