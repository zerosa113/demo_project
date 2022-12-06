package com.example.demo_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity			//宣告實體類
@Table(name="bank")		//用於連接資料庫的表
public class Bank {
	@Id
	@Column(name="account")
	public String account;
	
	@Column(name="amount")
	public int amount;
	
	@Column(name="name")
	public String name;
	
	public Bank() {
		
	}
	public Bank( String account,int amount) {
		this.account = account;
		this.amount = amount;
	}
	
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
