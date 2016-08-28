package com.models;

public class Catalog {

	private int catalogID;
	private String name;
	private String description;
	private String month;
	private int year;
	private String pdfLink;

	public Catalog() {
		this.catalogID = 0;
		this.name = "";
		this.description = "";
		this.month = "";
		this.year = 0;
		this.pdfLink = "";
	}

	public Catalog(int catalogID, String name, String description, String month, int year, String pdfLink) {
		this.catalogID = catalogID;
		this.name = name;
		this.description = description;
		this.month = month;
		this.year = year;
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

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

}
