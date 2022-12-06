package com.example.demo_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_project.entity.Person;
import com.example.demo_project.service.ifs.PersonService;
import com.example.demo_project.vo.PersonReq;
import com.example.demo_project.vo.PersonRes;
@RestController
public class PersonController {
	@Autowired
	private PersonService personService;
	
	@PostMapping(value = "/api/person")
	public PersonRes getPersonInfo() {
		PersonRes res = new PersonRes();
		List<Person>perList = personService.getPersonInfo();
		res.setPersonList(perList);
		return res;
	}
	
	@PostMapping(value = "/api/personById")
	public Person getPersonInfoById(@RequestBody PersonReq req) {
//		Person per = personService
		return personService.getPersonInfoById(req.getId());
	}
	
	@PostMapping(value="/api/PersonInfoByAge")
	public List<Person> getPersonInfoByAgeLargerThan(@RequestBody PersonReq req) {
//		List<Person> per =personService.getPersonInfoByAgeLargerThan(req.getAge());
//		List<Person> prList= new ArrayList<>();
//		prList.addAll(per);
//		List<Person> prList1=personService.getPersonInfoByAgeLargerThan(req.getAge());
//		return per;
		return personService.getPersonInfoByAgeLargerThan(req.getAge());
	}
	@PostMapping(value="/api/PersonInfoByNameAge")
	public List<Person> findByNameAndAge(@RequestBody PersonReq req){
		return personService.findByNameAndAge(req.getName(),req.getAge());
		
	}
	@PostMapping(value="/api/findByAgeGreaterThanAndName")
	public List<Person> findByAgeGreaterThanAndName(@RequestBody PersonReq req){
		return personService.findByAgeGreaterThanAndName(req.getAge(),req.getName());
		
	}
	
}
