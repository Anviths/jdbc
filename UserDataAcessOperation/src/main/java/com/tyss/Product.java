package com.tyss;

public class Product {

	private int id;
	private String name;
	private String type;
	private String descripetion;
	private String availability;
	private double price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescripetion() {
		return descripetion;
	}

	public void setDescripetion(String descripetion) {
		this.descripetion = descripetion;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void display() {
		System.out.println("id :" + id);
		System.out.println("name : " + name);
		System.out.println("type : " + type);
		System.out.println("decription : " + descripetion);
		System.out.println("availability : " + availability);
		System.out.println("price: " + price);

	}
}
