package com.models;

public class Sponsor {

	private int sponsorID;
	private String name;
	private String image;

	public Sponsor() {
		sponsorID = 0;
		name = "";
		image = "";
	}

	public Sponsor(int sponsorID, String name, String image) {
		this.sponsorID = sponsorID;
		this.name = name;
		this.image = image;
	}

	public int getSponserID() {
		return sponsorID;
	}

	public void setSponserID(int sponserID) {
		this.sponsorID = sponserID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
