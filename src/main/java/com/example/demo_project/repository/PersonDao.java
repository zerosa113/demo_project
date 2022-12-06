package com.example.demo_project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_project.entity.Person;

@Repository
public interface PersonDao extends JpaRepository<Person, String>{
	
	public List<Person> findByAgeGreaterThan(int age);	//照規格命名要確實小駝峰
									//GreaterThan大於Equal等於
	public List<Person> findByNameAndAge(String name,int age);

	public List<Person> findByAgeGreaterThanAndName(int age,String name);
}
