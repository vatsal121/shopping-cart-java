package com.isi.entity;

import java.sql.Date;

public class Cart {

	private int Id, UserId, ProductId, QtyOrdered;
	private float DiscountPercentage, FinalProductPrice;
	private boolean IsCustomerCheckedOut;
	private Date DateCreated, DateModified;

	private Product _Product;

	public Cart(int id, int userId, int productId, int qtyOrdered, float discountPercentage, float finalProductPrice,
			boolean isCustomerCheckedOut, Date dateCreated, Date dateModified, Product _Product) {
		Id = id;
		UserId = userId;
		ProductId = productId;
		QtyOrdered = qtyOrdered;
		DiscountPercentage = discountPercentage;
		FinalProductPrice = finalProductPrice;
		IsCustomerCheckedOut = isCustomerCheckedOut;
		DateCreated = dateCreated;
		DateModified = dateModified;
		this._Product = _Product;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public int getProductId() {
		return ProductId;
	}

	public void setProductId(int productId) {
		ProductId = productId;
	}

	public int getQtyOrdered() {
		return QtyOrdered;
	}

	public void setQtyOrdered(int qtyOrdered) {
		QtyOrdered = qtyOrdered;
	}

	public float getDiscountPercentage() {
		return DiscountPercentage;
	}

	public void setDiscountPercentage(float discountPercentage) {
		DiscountPercentage = discountPercentage;
	}

	public float getFinalProductPrice() {
		return FinalProductPrice;
	}

	public void setFinalProductPrice(float finalProductPrice) {
		FinalProductPrice = finalProductPrice;
	}

	public boolean isIsCustomerCheckedOut() {
		return IsCustomerCheckedOut;
	}

	public void setIsCustomerCheckedOut(boolean isCustomerCheckedOut) {
		IsCustomerCheckedOut = isCustomerCheckedOut;
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

	public Product get_Product() {
		return _Product;
	}

	public void set_Product(Product _Product) {
		this._Product = _Product;
	}

}
