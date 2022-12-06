package com.example.demo_project.service.ifs;

import java.util.List;
import java.util.Map;

import com.example.demo_project.entity.Bank;
import com.example.demo_project.entity.Menu;

public interface OrderService {
	
//	public Menu getMenuPrice(String name, int price);
//	public Menu getOrder(List<Menu,Integer> menuList);//<mapList>
//	public Menu getMenuInfo3(String name, int price);
//	public void gettotal();

//===============1031hw==============
	public Menu addMenus(String name,int price);
	public List<Menu> getMenu();
	public Menu findMenu(String name) ;
	//List<Menu>
	public int getTotal(String name,int price);

}
