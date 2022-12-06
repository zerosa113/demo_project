package com.example.demo_project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="person")
public class Person {
	@Id
	@Column(name="id")
	private String id;
	@Column(name="name")
	private String name;
	@Column(name="age")
	private int age;
//	private String city;

	public Person() {
	}
	public Person(String id,String name,int age) {
		this.id=id;
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
//	public String getCity() {
//		return city;
//	}
//	public void setCity(String city) {
//		this.city = city;
//	}

}
