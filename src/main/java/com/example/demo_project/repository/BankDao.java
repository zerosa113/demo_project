package com.example.demo_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo_project.entity.Bank;
@Transactional
@Repository		//�ŧi����ƺ޲z��
public interface BankDao extends JpaRepository<Bank, String>{
										//�~�Ӥ���Jpa
	
	public void deleteByName(String name);
}
