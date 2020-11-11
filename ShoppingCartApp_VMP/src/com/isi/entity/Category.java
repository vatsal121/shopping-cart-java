package com.isi.entity;

import java.sql.Date;

public class Category {

	private int Id;
	String CategoryName;
	Date DateCreated, DateModified;

	public Category(int id, String categoryName, Date dateCreated, Date dateModified) {
		Id = id;
		CategoryName = categoryName;
		DateCreated = dateCreated;
		DateModified = dateModified;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getCategoryName() {
		return CategoryName;
	}

	public void setCategoryName(String categoryName) {
		CategoryName = categoryName;
	}

	public Date getDateCreated() {
		return DateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		DateCreated = dateCreated;
	}

	public Date getDateModified() {
		return DateModified;
	}

	public void setDateModified(Date dateModified) {
		DateModified = dateModified;
	}

}
