package com.example.demo_project.vo;

import com.example.demo_project.entity.Bank;
import com.fasterxml.jackson.annotation.JsonInclude;

public class BankRes {
	private String account;
	private int amount;
	private String message;
//	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Bank bank;
	public BankRes() {
		
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
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	
}
