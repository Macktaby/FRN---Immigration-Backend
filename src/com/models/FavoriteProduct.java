package com.models;

import java.sql.Timestamp;
import java.util.Date;

public class FavoriteProduct {

	private int wishlistID;
	private int userID;
	private int productID;
	private Timestamp time;

	public FavoriteProduct() {
		this.wishlistID = 0;
		this.userID = 0;
		this.productID = 0;
		this.time = new Timestamp(0);
	}

	public FavoriteProduct(int wishlistID, int userID, int productID) {
		this.wishlistID = wishlistID;
		this.userID = userID;
		this.productID = productID;
		Date date = new Date();
		this.time = new Timestamp(date.getTime());
	}

	public FavoriteProduct(int wishlistID, int userID, int productID, Timestamp time) {
		this.wishlistID = wishlistID;
		this.userID = userID;
		this.productID = productID;
		this.time = time;
	}

	public int getWishlistID() {
		return wishlistID;
	}

	public void setWishlistID(int wishlistID) {
		this.wishlistID = wishlistID;
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

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

}
