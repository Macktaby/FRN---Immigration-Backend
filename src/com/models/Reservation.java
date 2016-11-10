package com.models;

import java.sql.Timestamp;
import java.util.Date;

public class Reservation {

	private int reservationID;
	private int userID;
	private String userName;
	private Product product;
	private int quantity;
	private Timestamp time;

	public Reservation() {
		this.reservationID = 0;
		this.userID = 0;
		this.userName = "";
		this.product = new Product();
		this.quantity = 0;
		this.time = new Timestamp(0);
	}

	public Reservation(int reservationID, int userID, String userName, Product product, int quantity) {
		this.reservationID = reservationID;
		this.userID = userID;
		this.userName = userName;
		this.product = product;
		this.quantity = quantity;
		Date date = new Date();
		this.time = new Timestamp(date.getTime());
	}

	public Reservation(int reservationID, int userID, String userName, int productID, String productName,
			int quantity) {
		this.reservationID = reservationID;
		this.userID = userID;
		this.userName = userName;
		this.product = new Product(productID, productName);
		this.quantity = quantity;
		Date date = new Date();
		this.time = new Timestamp(date.getTime());
	}

	public int getReservationID() {
		return reservationID;
	}

	public void setReservationID(int reservationID) {
		this.reservationID = reservationID;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
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

}
