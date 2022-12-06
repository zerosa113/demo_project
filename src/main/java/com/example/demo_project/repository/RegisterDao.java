package com.example.demo_project.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo_project.entity.Register;

@Repository
public interface RegisterDao extends JpaRepository<Register, String> {
	
	//@Transactional㎝@Modifyingノupdatedelete
	@Transactional
	@Modifying
	@Query(" update Register set name = :newName, age = :newAge, city = :newCity ,regTime = :newRegTime "
			 + " where account = :account ")
	public int updateReisterInfo (
			//"把计籔SQL粂猭:嘿",()把计璹
			@Param("newName")String newName,
			@Param("newAge")int newAge,
			@Param("newCity")String newCity,
			@Param("newRegTime")Date newDate,
			@Param("account")String account);
	
	public List<Register> doQueryByExiredRegTime(Date date);
	
	public List<Register> doQueryByExiredRegTime(Date date,int pageSize);
	
	public List<Register> doQueryByExiredRegTime(Date date,int pageSize,int startPosition);
	
	public List<Register> doNativeQueryByExiredRegTime(Date date,int pageSize,int startPosition);
	
	@Transactional
	public int doUpdateAgeByName(String name,int age);
	
	@Transactional
	public int doUpdateAgeByAccount(String account,int age);
	
	@Transactional
	public int nativeUpdateAgeByName(String name,int age);
	
	@Transactional
	public int nativeUpdateAgeByAccount(String account,int age);
	
	//笆篈把计_dynamic params
	public List<Register>doQueryRoleContains(List<String>roleList);
}
