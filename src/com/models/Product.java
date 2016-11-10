package com.models;

import java.util.ArrayList;
import java.util.List;

public class Product {

	// 15 used, 1 unused

	private int productID;
	private String name;
	private String description;
	private String image;
	private int quantity;
	private double price;
	private double rating;
	private int numRatingUsers;
	private boolean isDayProd;
	private List<String> images;

	private int categoryID;
	private String categoryName;
	private int showRoomID;
	private String showRoomName;
	private int brandID;
	private String brandName;

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
		this.categoryName = "";
		this.showRoomID = 0;
		this.showRoomName = "";
		this.brandID = 0;
		this.brandName = "";
	}

	public Product(int productID, String name, String description, String image, int quantity, double price,
			double rating, int numRatingUsers, boolean isDayProd, List<String> images, int categoryID,
			String categoryName, int showRoomID, String showRoomName, int brandID, String brandName) {

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
		this.categoryName = categoryName;
		this.showRoomID = showRoomID;
		this.showRoomName = showRoomName;
		this.brandID = brandID;
		this.brandName = brandName;
	}

	public Product(int productID, String productName) {
		this();
		this.productID = productID;
		this.name = productName;
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

	public List<String> getImages() {
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getShowRoomName() {
		return showRoomName;
	}

	public void setShowRoomName(String showRoomName) {
		this.showRoomName = showRoomName;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

}
