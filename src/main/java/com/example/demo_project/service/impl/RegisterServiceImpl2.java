package com.example.demo_project.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo_project.annotation.ConditionalOnB;
import com.example.demo_project.constants.RegisterRtnCode;
import com.example.demo_project.entity.Register;
import com.example.demo_project.repository.RegisterDao;
import com.example.demo_project.service.ifs.RegisterService;
import com.example.demo_project.vo.RegisterRes;

@ConditionalOnB
@EnableScheduling
@Service
public class RegisterServiceImpl2 implements RegisterService{
	
	@Autowired
	private RegisterDao registerDao;
	
	@Override
	public Register register(String account, String pwd, String name, int age, String city) {
		Register reg = new Register(account, pwd, name, age, city); 
		//不能註冊已存在帳號
		if(registerDao.existsById(account)) {
			return null;
		}
		reg.setRegTime(new Date());		//new Date()系統當前時間	*要import util
		//reg.setActive(false);		//Active的date_type是布林值，false default
		reg.setRole("General");
		return registerDao.save(reg);
	}

	@Override
	public RegisterRes activeAccount(String account) {
		Optional<Register> regOp = registerDao.findById(account);	//從資料庫找資料出來
		if(regOp.isPresent()) {
			Register reg = regOp.get();
			reg.setActive(true);
			registerDao.save(reg);		//回傳enum訊息
			return new RegisterRes(null, RegisterRtnCode.SUCCESSFUL.getMessage());
		}
		return new RegisterRes(null, RegisterRtnCode.FAILURE.getMessage());
	}

	@Override
	public RegisterRes addRole(String account, List<String> roleList) {
		Optional<Register> regOp = registerDao.findById(account);
		if(regOp.isPresent()) {
			Set<String>roleSet = new HashSet<>(); //Set不允許重複的值出現
			//去除參數重複，定義在DB的屬性的值不需要重複
			for(String str:roleList) {
				roleSet.add(str);
			}
			//去除DB裡已存在的值和參數得值、兩者的重複部分
			Register reg = regOp.get();		//取出值
			String role = reg.getRole();	
			//陣列的固定格式是[值, 值, 值, ]
			//可能會有多個值，要用逗號(,)做區隔切割
			String[] roleArray = role.split(",");	//本身的儲存就會加上空格
			for(String item : roleArray) {
				String str = item.trim();		//去除空白	 trim():去除字串的前後空白
				roleSet.add(str);
			}	
//			System.out.println(roleSet.toString());		//測試陣列結果
			//陣列前後含有[]，使用substring去除[]				//toString把陣列轉成字串
			System.out.println(roleSet.toString().substring(1,roleSet.toString().length()-1));		
			//index從0開始算,substring包含開頭不包含結尾	//ex.substring(4,8)抓位置4~7
			//roleSet.toString().length()-1 --> 陣列字串長度-1
			String newStr = roleSet.toString().substring(1,roleSet.toString().length()-1);
//			StringBuffer newStrBuf = new StringBuffer();
//			for(String item:roleSet) {
//				newStrBuf.append(item.trim());
//			}
			reg.setRole(newStr);	
			registerDao.save(reg);
			return new RegisterRes(reg, RegisterRtnCode.SUCCESSFUL.getMessage());
		}
		
		return new RegisterRes(null, RegisterRtnCode.ADD_ROLE_FAILURE.getMessage());
	}

	@Override
	public RegisterRes addRoleSet(String account, Set<String> roleSet) {
		Optional<Register> regOp = registerDao.findById(account);
		if(regOp.isPresent()) {
			//去除DB裡已存在的值和參數得值、兩者的重複部分
			Register reg = regOp.get();		//取出值
			String role = reg.getRole();	
			//陣列的固定格式是[值, 值, 值, ]
			//可能會有多個值，要用逗號(,)做區隔切割
			String[] roleArray = role.split(",");	//本身的儲存就會加上空格
			for(String item : roleArray) {
				String str = item.trim();		//去除空白	 trim():去除字串的前後空白
				roleSet.add(str);
			}	
			//陣列前後含有[]，使用substring去除[]				//toString把陣列轉成字串
			System.out.println(roleSet.toString().substring(1,roleSet.toString().length()-1));		
			//index從0開始算,substring包含開頭不包含結尾	//ex.substring(4,8)抓位置4~7
			//roleSet.toString().length()-1 --> 陣列字串長度-1
			String newStr = roleSet.toString().substring(1,roleSet.toString().length()-1);
			reg.setRole(newStr);	
			registerDao.save(reg);
			return new RegisterRes(reg, RegisterRtnCode.SUCCESSFUL.getMessage());
		}
		return new RegisterRes(null, RegisterRtnCode.ADD_ROLE_FAILURE.getMessage());
	}
	

//	@Scheduled(fixedRateString = "${heartbeat.ms}")
//	public void scheduledPrintDate() {
//		System.out.println("=======================");
//		System.out.println(new Date());
//	}

//	@Scheduled(cron = "0/5 * * * * *")
//	public void scheduledPrintDate1() {
//		System.out.println(new Date());
//	}
	
	@Override
	public Register findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Register> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
