package com.models;

import java.util.ArrayList;

public class Product {

	private int productID;
	private String name;
	private String description;
	private String image;
	private int quantity;
	private double price;
	private double rating;
	private int numRatingUsers;
	private boolean isDayProd;
	private ArrayList<String> images;

	private int categoryID;
	private int showRoomID;
	private int brandID;

	public Product() {
		this.productID = 0;
		this.name = "";
		this.description = "";
		this.image = "";
		this.quantity = 0;
		this.price = 0;
		this.rating = 0;
		this.numRatingUsers = 0;
		this.isDayProd = false;
		this.images = new ArrayList<String>();
		this.categoryID = 0;
		this.showRoomID = 0;
		this.brandID = 0;
	}

	public Product(int productID, String name, String description, String image, int quantity, double price,
			double rating, int numRatingUsers, boolean isDayProd, ArrayList<String> images, int categoryID,
			int showRoomID, int brandID) {
		super();
		this.productID = productID;
		this.name = name;
		this.description = description;
		this.image = image;
		this.quantity = quantity;
		this.price = price;
		this.rating = rating;
		this.numRatingUsers = numRatingUsers;
		this.isDayProd = isDayProd;
		this.images = images;
		this.categoryID = categoryID;
		this.showRoomID = showRoomID;
		this.brandID = brandID;
	}

	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getNumRatingUsers() {
		return numRatingUsers;
	}

	public void setNumRatingUsers(int numRatingUsers) {
		this.numRatingUsers = numRatingUsers;
	}

	public ArrayList<String> getImages() {
		return images;
	}

	public void setImages(ArrayList<String> images) {
		this.images = images;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}

	public int getShowRoomID() {
		return showRoomID;
	}

	public void setShowRoomID(int showRoomID) {
		this.showRoomID = showRoomID;
	}

	public int getBrandID() {
		return brandID;
	}

	public void setBrandID(int brandID) {
		this.brandID = brandID;
	}

	public boolean isDayProd() {
		return isDayProd;
	}

	public void setDayProd(boolean isDayProd) {
		this.isDayProd = isDayProd;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
