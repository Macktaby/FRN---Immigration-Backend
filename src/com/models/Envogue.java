package com.models;

public class Envogue {

	private int envogueID;
	private String name;
	private String description;
	private String image;

	public Envogue() {
		this.envogueID = 0;
		this.name = "";
		this.description = "";
		this.image = "";
	}

	public Envogue(int envogueID, String name, String description, String image) {
		this.envogueID = envogueID;
		this.name = name;
		this.description = description;
		this.image = image;
	}

	public int getEnvogueID() {
		return envogueID;
	}

	public void setEnvogueID(int envogueID) {
		this.envogueID = envogueID;
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
