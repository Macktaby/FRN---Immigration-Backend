package com.models;

import java.sql.Timestamp;
import java.util.Date;

public class Report {

	private int reportID;
	private String type;
	private String details;
	private Timestamp time;
	private int userID;
	private String userName;
	private int productID;
	private String productName;
	private int designerID;
	private String designerName;

	public Report() {
		this.reportID = 0;
		this.type = "";
		this.details = "";
		this.time = new Timestamp(0);
		this.userID = 0;
		this.userName = "";
		this.productID = 0;
		this.productName = "";
		this.designerID = 0;
		this.designerName = "";
	}

	public Report(int reportID, String type, int userID, String userName, int productID, String productName,
			int designerID, String designerName) {
		this.reportID = reportID;
		this.type = type;
		Date date = new Date();
		this.time = new Timestamp(date.getTime());
		this.userID = userID;
		this.userName = userName;
		this.productID = productID;
		this.productName = productName;
		this.designerID = designerID;
		this.designerName = designerName;
	}

	public Report(int reportID, String type, String details, Timestamp time, int userID, String userName, int productID,
			String productName, int designerID, String designerName) {
		this.reportID = reportID;
		this.type = type;
		this.details = details;
		this.time = time;
		this.userID = userID;
		this.userName = userName;
		this.productID = productID;
		this.productName = productName;
		this.designerID = designerID;
		this.designerName = designerName;
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

		if (type.equals("reservre"))
			details = "User " + userName + " reserved product " + productName;
		else if (type.equals("cancel"))
			details = "User " + userName + " canceled reservation for product " + productName;
		else if (type.equals("contact"))
			details = "User " + userName + " contacted designer " + designerName;

		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getDesignerID() {
		return designerID;
	}

	public void setDesignerID(int designerID) {
		this.designerID = designerID;
	}

	public String getDesignerName() {
		return designerName;
	}

	public void setDesignerName(String designerName) {
		this.designerName = designerName;
	}

}
