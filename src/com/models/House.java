package com.models;

public class House {

	private int houseID;
	private String name;
	private String description;
	private String image;

	public House() {
		this.houseID = 0;
		this.name = "";
		this.description = "";
		this.image = "";
	}
	
	public House(int houseID, String name, String description, String image) {
		this.houseID = houseID;
		this.name = name;
		this.description = description;
		this.image = image;
	}

	public int getHouseID() {
		return houseID;
	}

	public void setHouseID(int houseID) {
		this.houseID = houseID;
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
