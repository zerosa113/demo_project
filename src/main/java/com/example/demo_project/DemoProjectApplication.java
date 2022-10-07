package com.example.demo_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo_project.entity.Person;
import com.example.demo_project.service.ifs.PersonService;
import com.example.demo_project.service.impl.PersonServiceImpl;

@SpringBootApplication
public class DemoProjectApplication {
	
//	@Autowired
//	private PersonService personService;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoProjectApplication.class, args);
//		PersonServiceImpl personservice = new PersonServiceImpl();
//		Person per = personservice.getPersonInfo("YYY");
//		//這裡設定id至personinfo
//		personservice.printPersonAttributes(per);
		
	}

}
