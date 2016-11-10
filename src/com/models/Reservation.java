package com.models;

import java.sql.Timestamp;
import java.util.Date;

public class Reservation {

	private int reservationID;
	private int userID;
	private int productID;
	private int quantity;
	private Timestamp time;
	private String userName;
	private String productName;

	public Reservation() {
		this.reservationID = 0;
		this.userID = 0;
		this.productID = 0;
		this.quantity = 0;
		this.userName = "";
		this.productName = "";
		this.time = new Timestamp(0);
	}

	public Reservation(int reservationID, int userID, int productID, int quantity, String userName,
			String productName) {
		this.reservationID = reservationID;
		this.userID = userID;
		this.productID = productID;
		this.quantity = quantity;
		this.userName = userName;
		this.productName = productName;
		Date date = new Date();
		this.time = new Timestamp(date.getTime());
	}

	public Reservation(int reservationID, int userID, int productID, int quantity, Timestamp time) {
		this.reservationID = reservationID;
		this.userID = userID;
		this.productID = productID;
		this.quantity = quantity;
		this.time = time;
	}

	public int getReservationID() {
		return reservationID;
	}

	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
