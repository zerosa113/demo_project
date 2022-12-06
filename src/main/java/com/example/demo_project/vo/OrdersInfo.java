package com.example.demo_project.vo;

public class OrdersInfo {

	private String name;

	private String productName;

	private Integer quantity;

	private Integer customerId;

	public OrdersInfo() {

	}
	
	public OrdersInfo(String name,String productName,Integer quantity,Integer customerId) {
		this.name = name;
		this.productName = productName;
		this.quantity = quantity;
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	
	
}
