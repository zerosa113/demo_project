package com.example.demo_project.service.impl;

import java.util.List;


import com.example.demo_project.entity.Person;
//@Service("A2")//�O�W�ʺ�,�Ω���@Qualifier��{
public class PersonServiceImpl2 {

	
	public Person getPersonInfo(String id) {
		Person person = new Person();
		person.setId(id);		//String id�|�����ܰ�
		person.setName("AAA");
		person.setAge(20);
//		person.setCity("Tainan");
		return person;		//�^�ǫŧi���ܼ�
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
