package com.example.demo_project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.example.demo_project.entity.Bird;
import com.example.demo_project.entity.Person;
import com.example.demo_project.service.ifs.Active;
import com.example.demo_project.service.ifs.PersonService;

@SpringBootTest
class DemoProjectApplicationTests {
	
	@Autowired
	private PersonService personService;
	
	@Test
	void contextLoads() {
		Person per = personService.getPersonInfo("TTT");
		System.out.println(per.getId());
	}
	@Test
	public void activeTest() {
		
	}
	@Autowired
	private Active active;
	@Test
	void contextLoads1() {
		Bird bir = active.getBirdInfo("SSS",2);
		System.out.println(bir);
//		active.printBirdAttributes(bir);
	}

}
