package com.example.demo_project.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo_project.entity.Bank;
import com.example.demo_project.entity.Menu;
import com.example.demo_project.entity.Person;
import com.example.demo_project.repository.BankDao;
import com.example.demo_project.repository.OrderDao;
import com.example.demo_project.service.ifs.OrderService;
@Service
public class OrderServiceImpl implements OrderService{

//	private Map<Menu,Integer> menuMap = new HasMap<>();
/*	@Override
	public Menu getMenuPrice(String name, int price) {
		// TODO Auto-generated method stub
		return null;
	}
//	@Override
//	public Menu getOrder(Map<Menu,Integer> itemMap) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	@Override
	public void gettotal() {
		int totalPrice = 0;
	}*/
/*		
			if(flag>= 500) {
				System.out.println("滿500 九折優惠");
				double sumprice=(b1*priceb1+p1*pricep1+c1*pricec1)*0.9;
				System.out.println("應收:"+sumprice);
				int deserveprice=sc.nextInt();
				System.out.println("收款:"+deserveprice);
				System.out.println("找零:"+(deserveprice-sumprice));
				break;
			}else {
				int sumprice=b1*priceb1+p1*pricep1+c1*pricec1;
				System.out.println("應收:"+sumprice);
				int deserveprice=sc.nextInt();
				System.out.println("收款:"+deserveprice);
				System.out.println("找零:"+(deserveprice-sumprice));
				break;
			}		
			*/	

/*	@Override
	public Menu getMenuInfo(String name,int price) {
		Menu beef = new Menu();
		beef.setName("beef");
		beef.setPrice(100);
		return beef;
	}
	public Menu getMenuInfo2(String name,int price) {
		Menu pork = new Menu();
		pork.setName("pork");
		pork.setPrice(90);
		return pork;
	}
	public Menu getMenuInfo3(String name,int price) {
		Menu chicken = new Menu();
		chicken.setName("chicken");
		chicken.setPrice(80);
		return chicken;
	}
//	public String getMenuName(Menu beef) {
//		return beef.getName();
//	}
	public void printMenuArributes(Menu beef,Menu pork,Menu chicken) {
		System.out.println(beef.getName());
		System.out.println(beef.getPrice());
		System.out.println(pork.getName());
		System.out.println(pork.getPrice());
		System.out.println(chicken.getName());
		System.out.println(chicken.getPrice());
	}
*/	
//===============1019hw==============
	
	@Autowired
	private OrderDao orderDao;
//	@Override
//	public Menu getMenu(String beef,String pork,String chicken) {
		
@SuppressWarnings("unused")
	//		beefmenu.setName("beef");
//		beefmenu.setPrice(100);
//		porkmenu.setName(pork);
//		porkmenu.setPrice(90);
//		chickenmenu.setName(chicken);
//		chickenmenu.setPrice(0);
//		return beefmenu;	
//	}
	@Override
	public Menu addMenus(String name,int price) {
		Menu menu = new Menu();
		menu.setName(name);
		menu.setPrice(price);
		if(!StringUtils.hasText(name)) {
			System.out.println("餐點品項錯誤");
			return new Menu();
		}else {
		return orderDao.save(menu);
		}
	}
	@Override
	public List<Menu> getMenu() {
		List<Menu> menuList = orderDao.findAll();
		return menuList;	
	}
	@Override
	public Menu findMenu(String name) {
		Optional<Menu> menuOp= orderDao.findById(name);
		return menuOp.orElse(null);
	}
/*	@Override
	public int getTotal(Map<String,Integer>menuList) {
		int totalPrice=0;
		Map<String,Integer>num=new HashMap<>();
		num.put("beefMenu", 100);
		num.put("porkMenu", 90);
		num.put("chickenMenu", 80);		
		for(Entry<String,Integer>item:num.entrySet()) {		//for對比輸入的key使否相同
			for(Entry<String,Integer>pay:menuList.entrySet()) {		//對比Map中的Key使否相同
				if(item.getKey().equalsIgnoreCase(pay.getKey())) {	//equals找輸入的是否字一樣
					totalPrice += item.getValue()*pay.getValue();	//Map中的Value相乘取總價
				}
			}
		}
		if(totalPrice >= 500) {
			totalPrice= (int) (totalPrice*0.9);
		}
		return totalPrice;
	}*/
	@Override
	public int getTotal(String name,int price) {
		List<Menu>menuList=new ArrayList();
//		menuList.add(null);
		
		return 0;
	}//
	
}
