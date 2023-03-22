package com.example.demo_project;


import java.util.Arrays;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo_project.entity.Users;
import com.example.demo_project.repository.UsersDao;

@SpringBootTest(classes = DemoProjectApplication.class)
public class UsersTest {
	
	@Autowired
	private UsersDao usersDao;
	
	@Test
	public void addUserInfo() {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Users u1 = new Users("AAA",encoder.encode("AA123"),"admin",true);
		Users u2 = new Users("BBB",encoder.encode("BB123"),"user",true);
		Users u3 = new Users("CCC",encoder.encode("CC123"),"user",true);
		Users u4 = new Users("DDD",encoder.encode("DD123"),"user",false);
		
		List<Users>list = Arrays.asList(u1,u2,u3,u4);
		usersDao.saveAll(list);
	}
}
