package com.example.demo_project.service.ifs;

import java.util.List;

import com.example.demo_project.entity.Person;

public interface PersonService {	//介面主要用來定義方法
		//回傳型態		//方法		//字串型態
	public Person getPersonInfo(String id);
	
//===============1020==============
	public List<Person> getPersonInfo();
	public Person getPersonInfoById(String id);
	public List<Person> getPersonInfoByAgeLargerThan(int age);
	public List<Person> findByNameAndAge(String name,int age);
	public List<Person> findByAgeGreaterThanAndName(int age,String name);
}
