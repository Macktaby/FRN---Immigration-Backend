package com.models;

public class Category {

	private int categoryID;
	private String name;
	private String description;
	private String image;

	public Category() {
		this.categoryID = 0;
		this.name = "";
		this.description = "";
		this.image = "";
	}

	public Category(int categoryID, String name, String description, String image) {
		this.categoryID = categoryID;
		this.name = name;
		this.description = description;
		this.image = image;
	}

	public int getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
