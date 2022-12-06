package com.example.demo_project.service.ifs;

import java.util.List;

import com.example.demo_project.entity.Bank;
import com.example.demo_project.entity.Company;
import com.example.demo_project.entity.Person;
import com.example.demo_project.vo.BankRes;

public interface BankService {
//	public void getAmount(Bank bank);
//	public void deposit(Bank bank,int depositAmount);
//	public void withdraw(Bank bank,int withdrawAmount);
	//======1018========
//	public Bank getAmount(String account);	
	public Bank deposit(String account,int depositAmount);
//	public BankRes withdraw(String account, int withdrawAmount);
	//======1024hw========
	public Bank getAmount1(String account);
//	public Bank getAmountById(String account);
	public Bank createAccount(String account);	
	public Bank withdraw1(String account, int withdrawAmount);
//========================1025
	public void deleAccount(String account)throws RuntimeException;
	public void deleteByName(String name);
}
