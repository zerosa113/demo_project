package com.example.demo_project.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo_project.entity.Orders;
import com.example.demo_project.vo.OrdersInfo;

@Repository
public interface OrdersDao extends JpaRepository<Orders, Integer>{
	
	@Transactional
	@Modifying
	@Query("select new com.example.demo_project.vo.OrdersInfo (c.name,o.productName,o.quantity,o.customerId) "
			+ "from Customers c join Orders o "
			+ "on c.id = o.customerId")
	public List<OrdersInfo> findAllOrdersInfo();
	
//	@Transactional
//	@Modifying
//	@Query("select c.name,o.product_name,o.quantity,o.customer_id from customers c join orders o "
//			+ "on c.id = o.customer_id")
//	public List<Object> findAllOrdersInfo();
	
	
//	public List<OrdersInfo> findOrdersInfoByCustomersIdIn();
}
