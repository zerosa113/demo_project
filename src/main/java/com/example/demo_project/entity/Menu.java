package com.example.demo_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity			//宣告實體類
@Table(name="menu")		
public class Menu {
	@Id
	@Column(name="name")
	private String name;
	
	@Column(name="price")
	private int price;
	
	public Menu() {
	}
	public Menu(String name,int price) {
		this.name = name;
		this.price = price;
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

}
