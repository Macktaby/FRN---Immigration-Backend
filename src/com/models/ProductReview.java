package com.models;

public class ProductReview {

	private int productID;
	private int userID;
	private String review;
	private int rating;


	public ProductReview() {
		this.productID = 0;
		this.userID = 0;
		this.review = "";
		this.rating = 0;
	}
	
	public ProductReview(int productID, int userID, String review, int rating) {
		this.productID = productID;
		this.userID = userID;
		this.review = review;
		this.rating = rating;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

}
