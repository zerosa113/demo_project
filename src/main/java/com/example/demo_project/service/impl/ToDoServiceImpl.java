package com.example.demo_project.service.impl;


import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo_project.entity.ToDo;
import com.example.demo_project.repository.ToDoDao;
import com.example.demo_project.service.ifs.ToDoService;
import com.example.demo_project.vo.ToDoReq;


@Service
public class ToDoServiceImpl implements ToDoService{

	@Autowired
	private ToDoDao toDoDao;
	
	@Override
	public List<ToDo> getToDoList() throws Exception {
		List<ToDo> toDoList = toDoDao.findAll();
		if(toDoList == null || toDoList.isEmpty()) return null;
		toDoList.sort(Comparator.comparing(ToDo::getUpdateDate).reversed());
		return toDoList;
	}

	@Override
	public List<ToDo> getToDoList(ToDoReq req) throws Exception {
		List<ToDo> toDoList = toDoDao.findByNameContaining(req.getName());
		if(toDoList == null || toDoList.isEmpty()) return null;
		toDoList.sort(Comparator.comparing(ToDo::getUpdateDate).reversed());
		return toDoList;
	}

	@Override
	public void createToDo(ToDoReq req) throws Exception {
		ToDo toCreateToDo = new ToDo();
		
		toCreateToDo.setId(UUID.randomUUID());
		toCreateToDo.setName(req.getName());
		toCreateToDo.setIsChecked(false);
		toCreateToDo.setCreateDate(LocalDateTime.now());
		toCreateToDo.setUpdateDate(LocalDateTime.now());
		
		toDoDao.save(toCreateToDo);
	}

	@Override
	public void setIsCheckedOfToDo(ToDoReq req) throws Exception {
		UUID id = UUID.fromString(req.getId());
		Optional<ToDo> toDoOp = toDoDao.findById(id);
		if(!toDoOp.isPresent()) throw new Exception("To set isChecked of to do is null");
		
		ToDo toUpdateToDo = toDoOp.get();
		toUpdateToDo.setIsChecked(req.isChecked());
		toDoDao.save(toUpdateToDo);
	}

	@Override
	public void updateToDo(ToDoReq req) throws Exception {
		UUID id = UUID.fromString(req.getId());
		Optional<ToDo> toDoOp = toDoDao.findById(id);
		if(!toDoOp.isPresent()) throw new Exception("To update to do is null");
		
		ToDo toUpdateToDo = toDoOp.get();
		toUpdateToDo.setName(req.getName());
		toUpdateToDo.setUpdateDate(LocalDateTime.now());
		toDoDao.save(toUpdateToDo);
	}

	@Override
	public void deleteToDo(ToDoReq req) throws Exception {
		UUID id = UUID.fromString(req.getId());
		Optional<ToDo> toDoOp = toDoDao.findById(id);
		if(!toDoOp.isPresent()) throw new Exception("To delete to do is null");
		ToDo toDeleteToDo = toDoOp.get();
		toDoDao.delete(toDeleteToDo);
	}
	
}
