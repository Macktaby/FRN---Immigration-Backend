package com.models;

public class Brand {

	private int brandID;
	private String name;
	private String description;
	private String image;

	
	public Brand() {
		this.brandID = 0;
		this.name = "";
		this.description = "";
		this.image = "";
	}
	
	public Brand(int brandID, String name, String description, String image) {
		this.brandID = brandID;
		this.name = name;
		this.description = description;
		this.image = image;
	}

	public int getBrandID() {
		return brandID;
	}

	public void setBrandID(int brandID) {
		this.brandID = brandID;
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
