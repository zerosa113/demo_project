package com.example.demo_project.service.impl;

import org.springframework.stereotype.Service;

import com.example.demo_project.entity.Person;
import com.example.demo_project.service.ifs.PersonService;
@Service
public class PersonServiceImpl implements PersonService{

	@Override
	public Person getPersonInfo(String id) {
		Person person = new Person();
		person.setId(id);		//String id會有所變動
		person.setName("AAA");
		person.setAge(20);
		person.setCity("Tainan");
		return person;		//回傳宣告的變數
	}
	public String getPersonId(Person person) {
		return person.getId();
	}
	public void printPersonAttributes(Person person) {
		System.out.println(person.getId());
		System.out.println(person.getName());
		System.out.println(person.getAge());
		System.out.println(person.getCity());
	}

}
