package com.example.demo_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_project.entity.Person;

@Repository
public interface PersonDao extends JpaRepository<Person, String>{
	
	public List<Person> findByAgeGreaterThan(int age);	//�ӳW��R�W�n�T��p�m�p
									//GreaterThan�j��Equal����
	public List<Person> findByNameAndAge(String name,int age);

	public List<Person> findByAgeGreaterThanAndName(int age,String name);
}
