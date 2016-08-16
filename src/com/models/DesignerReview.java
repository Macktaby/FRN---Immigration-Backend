package com.models;

public class DesignerReview {

	private int designerID;
	private int userID;
	private String review;
	private int rating;


	public DesignerReview() {
		this.designerID = 0;
		this.userID = 0;
		this.review = "";
		this.rating = 0;
	}
	
	public DesignerReview(int designerID, int userID, String review, int rating) {
		this.designerID = designerID;
		this.userID = userID;
		this.review = review;
		this.rating = rating;
	}

	public int getDesignerID() {
		return designerID;
	}

	public void setDesignerID(int designerID) {
		this.designerID = designerID;
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
