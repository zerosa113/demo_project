package com.example.demo_project.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ActiveAccountReq {
	
	@JsonProperty("verify_code")
	private int verifyCode;

	public int getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(int verifyCode) {
		this.verifyCode = verifyCode;
	}
	
	

}
