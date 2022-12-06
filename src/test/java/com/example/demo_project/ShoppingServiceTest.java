package com.example.demo_project;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo_project.entity.Product;
import com.example.demo_project.service.ifs.ShoppingService;

@SpringBootTest
public class ShoppingServiceTest {
	@Autowired
	private ShoppingService shoppingservice;
	@Test
	public void queryProduct() {
//		Product p = new Product();
//		p.setName("°Ó«~");
//		p.setPrice(100);
//		p.setQuantity(2);
//		p.setStorage(10);
//		shoppingservice.getProduct(p);
		Product p1 = new Product("A1",100,2,10);
		Product p2 = new Product("B1",50,3,5);
		Product p3 = new Product("C1",30,6,8);
		List<Product> productList = new ArrayList<Product>();
		productList.add(p1);
		productList.add(p2);
		productList.add(p3);
		List<String> nameList = new ArrayList<String>();
		nameList.add("A2");
		nameList.add("B1");
		nameList.add("CCC");
		shoppingservice.queryProduct(nameList, productList);
		List<Product> checkoutList = new ArrayList<Product>();
		checkoutList.add(p1);
		checkoutList.add(p3);
		shoppingservice.checkout(checkoutList);
	}

}
