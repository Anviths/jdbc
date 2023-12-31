package edu.ty.foodapp.bean;

import java.io.Serializable;

public class Order implements Serializable {

	private int orderId;
	private String orderNumber;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	@Override
	public String toString() {
		return "orderId=" + orderId + ", orderNumber=" + orderNumber ;
	}

}
