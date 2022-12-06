package com.example.demo_project;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DemoProjectApplication.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class restaurantTest {

	
	@Test
	public void searchTopCommodtity() {
	//	findTopByOrderBySalesVolume
	}
	
}
