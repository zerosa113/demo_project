package com.example.demo_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_project.entity.Menu;

@Repository
public interface OrderDao extends JpaRepository<Menu, String>{

}
