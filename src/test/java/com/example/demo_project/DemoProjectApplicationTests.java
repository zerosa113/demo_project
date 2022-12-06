package com.example.demo_project;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo_project.controller.BankController;
import com.example.demo_project.entity.Bank;
import com.example.demo_project.entity.Bird;
import com.example.demo_project.entity.Menu;
import com.example.demo_project.entity.Person;
import com.example.demo_project.service.ifs.Active;
import com.example.demo_project.service.ifs.BankService;
import com.example.demo_project.service.ifs.OrderService;
import com.example.demo_project.service.ifs.PersonService;
import com.example.demo_project.vo.BankReq;
import com.example.demo_project.vo.BankRes;

/*	@Autowired
	private BankService bankservice;
	
	@Test
	public void bankservice() {
		Bank b = new Bank();
		b.setAccount("John");
		b.setAmount(1000);
		bankservice.getAmount(b);
		bankservice.deposit(b, 500);
		bankservice.withdraw(b, 200);
		bankservice.withdraw(b, 2000);
	}
*/
//	@Qualifier("A2")//hard code->變數寫死(固定的值)在程式碼裡
//	@Autowired		//@Qualifier會與@Autowired
//	private PersonService personService;
//	@Test
//	void contextLoads() {
//		Person per = personService.getPersonInfo("TTT");
//		System.out.println(per.getId());
//	}
//	@Test
//	public void activeTest() {
//	}
//	@Autowired
//	private Active active;
//	@Test
//	void contextLoads1() {
////		active.getBirdInfo("SSS", 2);
//		active.printBirdAttributes(active.getBirdInfo("SSS", 2));
////		Bird bir = active.getBirdInfo("SSS",2);
////		System.out.println(bir);
////		active.printBirdAttributes(bir);
//	}
//	private List<Menu>menuList = new ArrayList();
//	@Test
//	public void addMenuTest() {
//		Menu beefMenu = new Menu("beef",100);
//	}
//========1018_Bank========
@SpringBootTest
class DemoProjectApplicationTests {
	@Autowired
	private BankController bankController;
	
/*	@Test
	public void bankControllerTest() {
		BankReq req = new BankReq();
		req.setAccount("A01");
		BankRes res = bankController.getAmount(req);
		System.out.println(res.getBank().getAccount());
		System.out.println(res.getBank().getAmount());
		System.out.println(res.getMessage());
	}
*/	
}
