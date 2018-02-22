package com.securde.papema.entity;


public class Product {
	
	private String name;
	private Brand brand;
	//private ArrayList<Tag> tags;
	private String status;
	private float price;
	
	public Product() {
		name ="";
		brand = new Brand();
		//tags = new ArrayList();
		status = "Out Of Stock";
		price = 0;
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
