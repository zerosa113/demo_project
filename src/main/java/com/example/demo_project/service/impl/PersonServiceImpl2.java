package com.example.demo_project.service.impl;

import java.util.List;


import com.example.demo_project.entity.Person;
//@Service("A2")//別名暱稱,用於讓@Qualifier辨認
public class PersonServiceImpl2 {

	
	public Person getPersonInfo(String id) {
		Person person = new Person();
		person.setId(id);		//String id會有所變動
		person.setName("AAA");
		person.setAge(20);
//		person.setCity("Tainan");
		return person;		//回傳宣告的變數
	}
	public String getPersonId(Person person) {
		return person.getId();
	}
	public void printPersonAttributes(Person person) {
		System.out.println(person.getId());
		System.out.println(person.getName());
		System.out.println(person.getAge());
//		System.out.println(person.getCity());
	}
//	@Override
	public List<Person> getPersonInfo() {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
	public Person getPersonInfoByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
	public List<Person> getPersonInfoByAgeLargerThan(int age) {
		// TODO Auto-generated method stub
		return null;
	}

}
