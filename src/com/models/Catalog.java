package com.models;

import java.sql.Timestamp;

public class Catalog {

	private int catalogID;
	private String name;
	private String description;
	private Timestamp date;
	private String pdfLink;
	
	public Catalog() {
		this.catalogID = 0;
		this.name = "";
		this.description = "";
		this.date = new Timestamp(0);
		this.pdfLink = "";
	}

	public Catalog(int catalogID, String name, String description, Timestamp date, String pdfLink) {
		this.catalogID = catalogID;
		this.name = name;
		this.description = description;
		this.date = date;
		this.pdfLink = pdfLink;
	}

	public int getCatalogID() {
		return catalogID;
	}

	public void setCatalogID(int catalogID) {
		this.catalogID = catalogID;
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

	public String getPdfLink() {
		return pdfLink;
	}

	public void setPdfLink(String pdfLink) {
		this.pdfLink = pdfLink;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

}
