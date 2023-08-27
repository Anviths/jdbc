package edu.ty.foodapp.bean;

import java.io.Serializable;

public class Product implements Serializable {

	private String productName;
	private String productDescription;
	private double productPrice;
	private int orderId;
	private   int productId;
	
	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId=productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	@Override
	public String toString() {
		return "Product [productName=" + productName + ", productDescription=" + productDescription + ", productPrice="
				+ productPrice + ", orderId=" + orderId + ", productId=" + productId + "]";
	}

}
