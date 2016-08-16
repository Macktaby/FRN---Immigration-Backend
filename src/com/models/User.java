package com.models;

import java.sql.Timestamp;

public class User {

	private int userID;
	private String userName;
	private String password;
	private String nickName;
	private String email;
	private String website;
	private String phone;
	private Timestamp registerTime;
	private String activationKey;
	private String userStatus;
	private boolean isAdmin;

	public User() {
		userID = 0;
		userName = "";
		password = "";
		nickName = "";
		email = "";
		website = "";
		phone = "";
		registerTime = new Timestamp(0);
		activationKey = "";
		userStatus = "";
		isAdmin = false;

	}

	public User(int userID, String userName, String password, String nickName, String email, String website,
			String phone, Timestamp registerTime, String activationKey, String userStatus, boolean isAdmin) {
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
		this.email = email;
		this.website = website;
		this.phone = phone;
		this.registerTime = registerTime;
		this.activationKey = activationKey;
		this.userStatus = userStatus;
		this.isAdmin = isAdmin;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Timestamp getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Timestamp registerTime) {
		this.registerTime = registerTime;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	public String getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
