package com.example.demo_project.vo;

import javax.persistence.Column;

public class RegisterReq {
	
	private String account;

	private String pwd;
	
	private String name;
	
	private int age;
	
	private String city = "";	//�bcontroller�Onull�A���Q�X�{null�ҥH�]�ߪŦr��
	
	public RegisterReq() {
		
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
}
