package com.models;

public class ShowRoom {

	private int showRoomID;
	private String name;
	private String description;
	private String address;
	private String location;
	private String phone;
	private String image;

	public ShowRoom() {
		this.showRoomID = 0;
		this.name = "";
		this.description = "";
		this.address = "";
		this.location = "";
		this.phone = "";
		this.image = "";
	}
	
	public ShowRoom(int showRoomID, String name, String description, String address, String location, String phone,
			String image) {
		this.showRoomID = showRoomID;
		this.name = name;
		this.description = description;
		this.address = address;
		this.location = location;
		this.phone = phone;
		this.image = image;
	}

	public int getShowRoomID() {
		return showRoomID;
	}

	public void setShowRoomID(int showRoomID) {
		this.showRoomID = showRoomID;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
