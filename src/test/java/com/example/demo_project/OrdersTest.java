package com.example.demo_project;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.demo_project.repository.OrdersDao;
import com.example.demo_project.vo.OrdersInfo;


@SpringBootTest(classes = DemoProjectApplication.class)	//±M®×ªºApplication.class
public class OrdersTest {
	@Autowired
	private OrdersDao ordersDao;
	
	@Test
	public void findAllOrdersInfo(){
		List<OrdersInfo> result = ordersDao.findAllOrdersInfo();
		for(OrdersInfo item:result) {
			System.out.println(item.getName());
		}
	}
	
//	@Test
//	public void findOrdersInfoByCustomersIdIn(){
//		
//	}
	
}
