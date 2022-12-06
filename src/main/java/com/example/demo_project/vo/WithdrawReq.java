package com.example.demo_project.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WithdrawReq {
	@JsonProperty("account")
	private String account;
	@JsonProperty("withdraw_amount")
	private int withdrawAmount;
	public void withdrawReq() {
		
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public int getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(int withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	
}
