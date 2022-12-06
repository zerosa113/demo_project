package com.example.demo_project.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo_project.entity.ToDo;

@Repository
public interface ToDoDao extends JpaRepository<ToDo, UUID> {

	public List<ToDo> findByNameContaining(String name);

}
