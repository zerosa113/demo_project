package com.example.demo_project.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderReq {
	@JsonProperty("name")
	private String name;
	@JsonProperty("price")
	private int price;
	
	private int intPrice;
	
	
	public void orderReq() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getIntpPrice() {
		return intPrice;
	}
	public void setIntPrice(int intPrice) {
		this.intPrice = intPrice;
	}
	
}
