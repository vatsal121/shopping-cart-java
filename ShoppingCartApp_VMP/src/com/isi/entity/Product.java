package com.isi.entity;

import java.sql.Date;

public class Product {

	private int Id, CategoryId, Quantity;
	private String ProductName, Description, ProductImagePath;
	private float Price;
	private Date DateCreated, DateModified;

	private Category CategoryObj;

	public Product(int id, int categoryId, int quantity, String productName, String description,
			String productImagePath, float price, Date dateCreated, Date dateModified, Category categoryObj) {
		Id = id;
		CategoryId = categoryId;
		Quantity = quantity;
		ProductName = productName;
		Description = description;
		ProductImagePath = productImagePath;
		Price = price;
		DateCreated = dateCreated;
		DateModified = dateModified;
		CategoryObj = categoryObj;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getCategoryId() {
		return CategoryId;
	}

	public void setCategoryId(int categoryId) {
		CategoryId = categoryId;
	}

	public int getQuantity() {
		return Quantity;
	}

	public void setQuantity(int quantity) {
		Quantity = quantity;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getProductImagePath() {
		return ProductImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		ProductImagePath = productImagePath;
	}

	public float getPrice() {
		return Price;
	}

	public void setPrice(float price) {
		Price = price;
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

	public Category getCategoryObj() {
		return CategoryObj;
	}

	public void setCategoryObj(Category categoryObj) {
		CategoryObj = categoryObj;
	}

}
