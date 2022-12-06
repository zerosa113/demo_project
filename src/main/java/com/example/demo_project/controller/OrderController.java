package com.example.demo_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_project.entity.Bank;
import com.example.demo_project.entity.Menu;
import com.example.demo_project.service.ifs.OrderService;
import com.example.demo_project.vo.BankReq;
import com.example.demo_project.vo.BankRes;
import com.example.demo_project.vo.OrderReq;
import com.example.demo_project.vo.OrderRes;

@RestController
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@PostMapping(value="/api/addMenus")
	public Menu addMenus(@RequestBody OrderReq req) {
		Menu addMenu = new Menu();
		addMenu = orderService.addMenus(req.getName(),req.getPrice());
		if(req.getName() == null) {
			System.out.println("�\�I�~�����~");
			return new Menu();
		}else {
		return addMenu;
		}
	}
	@PostMapping(value="/api/getMenu")
	public OrderRes getMenus() {
		OrderRes res = new OrderRes();	//�]�m�Ū����m�ݭn�^�Ǫ��ƭ�
		List<Menu> menuList = orderService.getMenu();
		res.setMenuList(menuList);
//		Menu menu = new Menu(req.getName(),req.getPrice());
//		res.setName(menu.getName());
//		res.setPrice(menu.getPrice());
//		res.setMessage("sucessful");
//		res.setMenuList(orderService.getMenus());	//�qservice���oList
		return res;		//�^�Ǩ��o�ƭȪ�����res
	}
//	@PostMapping(value="/api/getMenu1")
//	public Menu getMenu(@RequestBody OrderReq req) {
////		Menu menu = orderService.getMenu(name);
////		return menu;
//		return orderService.getMenu(req.getName());	//34.35�X��
//		
//	}
	@PostMapping(value="/api/findMenu")
	public Menu findMenu(@RequestBody OrderReq req){
//		OrderRes res= new OrderRes();
		Menu findMenu = new Menu();
		findMenu=orderService.findMenu(req.getName());
		if(findMenu==null) {
			System.out.println("�~�����s�b");
			return new Menu();
		}
		return findMenu;
	}
	
	@PostMapping(value="/api/getTotal")
	public OrderReq getTotal(@RequestBody OrderReq req) {
		return null;
	}
	
}
