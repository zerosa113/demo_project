package com.example.demo_project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo_project.entity.Person;
import com.example.demo_project.repository.PersonDao;
import com.example.demo_project.service.ifs.PersonService;
@Service
public class PersonServiceImpl implements PersonService{
	
	@Override
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
//	public void printPersonAttributes(Person person) {
//		System.out.println(person.getId());
//		System.out.println(person.getName());
//		System.out.println(person.getAge());
////		System.out.println(person.getCity());
//	}
	
//==================1020===================
	@Autowired
	private PersonDao personDao;
//	@Autowired
//	private PersonService personService;
//	Person p1 = new Person("001","John",26);
//	Person p2 = new Person("002","Emma",19);
//	Person p3 = new Person("003","Tom",32);
	@Override
	public List<Person> getPersonInfo() {
//		List<Person> perList = new ArrayList<>();
//		perList.add(p1);
//		perList.add(p2);
//		perList.add(p3);
		List<Person> perList = personDao.findAll();
		return perList;
	}
	@Override
	public Person getPersonInfoById(String id) {
//========													//Optional是值的容器，只有有值或是沒值
		Optional<Person>personOp=personDao.findById(id);	//findById找的是主鍵
//		if(personOp.isPresent()) {	//Optional				//Person裡的主鍵是@Id所以找的是id
//			Person per=personOp.get();
//			System.out.println(per.getId());
//			System.out.println(per.getAge());
//			return personOp.get();
//		}else {
//			return new Person();
//		}											//orElse否則
		return personOp.orElse(new Person());		//57~64行可寫做65
//		return personDao.findById(id).orElse(new Person());		//56+65行
//========
//		List<Person> List = new ArrayList<>();
//=============1021hw
//		Person person = new Person();
//		person = personDao.findById(id).get();	
//		return person;
//=============	
//		Person p1 = new Person("001","John",26);
//		Person p2 = new Person("002","Emma",19);
//		Person p3 = new Person("003","Tom",32);
//		if(id.equalsIgnoreCase(p1.getId())) {
//			return p1;
//		}else if(id.equalsIgnoreCase(p2.getId())) {
//			return p2;
//		}else if(id.equalsIgnoreCase(p3.getId())) {
//			return p3;
//		}else {
//		return new Person();
//		}
	}
	@Override
	public List<Person> getPersonInfoByAgeLargerThan(int age) {
		List<Person>  findage = personDao.findByAgeGreaterThan(age);
		return findage;
//=============1021hw
//		List<Person> ageList = new ArrayList<>();
//		List<Person> List = personDao.findAll();
//		for(Person item:List) {
//			if(age<item.getAge()) {
//				ageList.add(item);
//			}
//		}
//		return ageList;
//=============
	}
	@Override
	public List<Person> findByNameAndAge(String name, int age) {
		List<Person> findNameAge =personDao.findByNameAndAge(name,age);
		return findNameAge;
	}
	@Override
	public List<Person> findByAgeGreaterThanAndName(int age,String name ) {
		List<Person> find =personDao.findByAgeGreaterThanAndName(age,name);
		return find;
	}
}
