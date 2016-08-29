package com.models;

import java.util.ArrayList;
import java.util.List;

public class Designer {

	// 9 used , 1 unused

	private int designerID;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String website;
	private double rating;
	private int nRatingUsers;

	private String profileImage;
	private List<String> designs;

	public Designer() {
		this.designerID = 0;
		this.name = "";
		this.email = "";
		this.phone = "";
		this.address = "";
		this.website = "";
		this.rating = 0;
		this.nRatingUsers = 0;
		this.profileImage = "";
		this.designs = new ArrayList<String>();
	}

	public Designer(int designerID, String name, String email, String phone, String address, String website,
			double rating, int nRatingUsers, String profileImage, List<String> designs) {

		this.designerID = designerID;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.website = website;
		this.rating = rating;
		this.nRatingUsers = nRatingUsers;
		this.profileImage = profileImage;
		this.designs = designs;
	}

	public int getDesignerID() {
		return designerID;
	}

	public void setDesignerID(int designerID) {
		this.designerID = designerID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getnRatingUsers() {
		return nRatingUsers;
	}

	public void setnRatingUsers(int nRatingUsers) {
		this.nRatingUsers = nRatingUsers;
	}

	public String getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public List<String> getDesigns() {
		return designs;
	}

	public void setDesigns(List<String> designs) {
		this.designs = designs;
	}

}
