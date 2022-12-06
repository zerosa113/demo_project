package com.example.demo_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo_project.entity.Bank;
@Transactional
@Repository		//宣告成資料管理類
public interface BankDao extends JpaRepository<Bank, String>{
										//繼承介面Jpa
	
	public void deleteByName(String name);
}
