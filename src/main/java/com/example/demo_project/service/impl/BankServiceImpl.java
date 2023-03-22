package com.example.demo_project.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo_project.entity.Bank;
import com.example.demo_project.entity.Person;
import com.example.demo_project.repository.BankDao;
import com.example.demo_project.service.ifs.BankService;
import com.example.demo_project.vo.BankRes;

@Service
public class BankServiceImpl implements BankService {

	/*
	 * @Override public void getAmount(Bank bank) { System.out.println("帳號: " +
	 * bank.getAccount()); System.out.println("餘額: " + bank.getAmount()); }
	 * 
	 * @Override public void withdraw(Bank bank, int withdrawAmount) {
	 * System.out.println("提款"); if (bank.getAmount() < withdrawAmount) {
	 * System.out.println("金額不足，無法取款，等待取款"); }else { bank.setAmount(bank.getAmount()
	 * - withdrawAmount); System.out.println("取款完成"); System.out.println("銀行帳號: " +
	 * bank.getAccount()); System.out.println("存款餘額: " + bank.getAmount()); } }
	 * 
	 * @Override public void deposit(Bank bank, int depositAmount) {
	 * System.out.println("存款"); bank.setAmount(bank.getAmount() + depositAmount);
	 * System.out.println("存款完成"); System.out.println("銀行帳號: " + bank.getAccount());
	 * System.out.println("存款後餘額: " + bank.getAmount()); }
	 */
//===========1018hw+1024===========
	/*
	 * @Override public Bank getAmount(String account) { Bank bank= new Bank();
	 * bank.setAccount(account); bank.setAmount(500); return bank; }
	 * 
	 * @Override public Bank deposit(String account, int depositAmount) { int
	 * intAmount = 500; Bank bank= new Bank(); bank.setAccount(account);
	 * bank.setAmount(intAmount+depositAmount); return bank; }
	 * 
	 * @Override public BankRes withdraw(String account, int withdrawAmount) { int
	 * intAmount = 500; //初始餘額 // Bank bank= new Bank(); BankRes res = new
	 * BankRes(); if(withdrawAmount<0) {
	 * res.setMessage("withdrawAmount is negative"); return res; }
	 * if(intAmount<withdrawAmount) { res.setMessage("withdrawAmount is negative");
	 * return res; } res.setAccount(account);
	 * res.setAmount(intAmount-withdrawAmount); res.setMessage("Successful"); return
	 * res; }
	 */
//======================	
	@Autowired
	private BankDao bankDao;

	@Override
	public Bank createAccount(String account) {
		Bank bankAccount = new Bank();
		bankAccount.setAccount(account);
		return bankDao.save(bankAccount);
	}

	@Override
	public Bank getAmount1(String account) {
		Optional<Bank> bankOp = bankDao.findById(account);
		return bankOp.orElse(new Bank());
	}

	@Override
	public Bank deposit(String account, int depositAmount) {
		int intAmount = 500;
		Bank bank = new Bank();
		bank.setAccount(account);
		bank.setAmount(intAmount + depositAmount);
		return bank;
	}

	@Override
	public Bank withdraw1(String account, int withdrawAmount) {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	@Override
	public void deleAccount(String account) throws RuntimeException {
		bankDao.deleteById(account);
		System.out.println("Delete account");
		throw new RuntimeException("DELETE ACCOUNT ERROR!");

	}

//	@Transactional	//寫在Interface中
	@Override
	public void deleteByName(String name) {
		bankDao.deleteByName(name);
		System.out.println("Delete name");
	}

}
