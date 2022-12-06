package com.example.demo_project.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo_project.constants.RtnInfo;
import com.example.demo_project.entity.ToDo;
import com.example.demo_project.service.ifs.ToDoService;
import com.example.demo_project.vo.ToDoReq;
import com.example.demo_project.vo.ToDoRes;

@CrossOrigin
@RestController
public class ToDoController {
	@Autowired
	private ToDoService toDoService;
	
	@PostMapping("/getToDoList")
	public ToDoRes getToDoList(@RequestBody ToDoReq req) {
		List<ToDo>toDoListRes = new ArrayList<>();
		try {
			toDoListRes = StringUtils.hasText(req.getName())
					? toDoService.getToDoList(req)
					: toDoService.getToDoList();
			
		}catch(Exception e) {
			return new ToDoRes(
					RtnInfo.FAILED.getCode(),
					RtnInfo.FAILED.getMessage(),
					null
					);
		}
		return new ToDoRes(
				RtnInfo.SUCCESSFUL.getCode(),
				RtnInfo.SUCCESSFUL.getMessage(),
				toDoListRes
				);		
	}
	
	@PostMapping("/createToDo")
	public ToDoRes createToDo(@RequestBody ToDoReq req) {
		List<ToDo> toDoListRes = new ArrayList<>();
		try {
			if(!StringUtils.hasText(req.getName())) {
				return new ToDoRes(
						RtnInfo.DATA_REQUIRED.getCode(),
						RtnInfo.DATA_REQUIRED.getMessage(),
						null
						);
			}
			
			toDoService.createToDo(req);
			toDoListRes = toDoService.getToDoList();
		}catch(Exception e) {
			return new ToDoRes(
					RtnInfo.FAILED.getCode(),
					RtnInfo.FAILED.getMessage(),
					null
					);
		}
		return new ToDoRes(
				RtnInfo.SUCCESSFUL.getCode(),
				RtnInfo.SUCCESSFUL.getMessage(),
				toDoListRes
				);
	}
	@PostMapping("/deleteToDo")
	public ToDoRes deleteToDo(@RequestBody ToDoReq req) {
		List<ToDo> toDoListRes = new ArrayList<>();
		try {
			if(!StringUtils.hasText(req.getId())) {
				return new ToDoRes(
						RtnInfo.DATA_REQUIRED.getCode(),
						RtnInfo.DATA_REQUIRED.getMessage(),
						null
						);
			}
			
			toDoService.deleteToDo(req);
			toDoListRes = toDoService.getToDoList();
		}catch(Exception e) {
			return new ToDoRes(
					RtnInfo.FAILED.getCode(),
					RtnInfo.FAILED.getMessage(),
					null
					);
		}
		return new ToDoRes(
				RtnInfo.SUCCESSFUL.getCode(),
				RtnInfo.SUCCESSFUL.getMessage(),
				toDoListRes
				);	
	}
	
	@PostMapping("/updateToDo")
	public ToDoRes updateToDo(@RequestBody ToDoReq req) {
		List<ToDo> toDoListRes = new ArrayList<>();
		try {
			if(!StringUtils.hasText(req.getId()) || !StringUtils.hasText(req.getName())) {
				return new ToDoRes(
						RtnInfo.DATA_REQUIRED.getCode(),
						RtnInfo.DATA_REQUIRED.getMessage(),
						null
						);
			}
			
			toDoService.updateToDo(req);
			toDoListRes = toDoService.getToDoList();
		}catch(Exception e) {
			return new ToDoRes(
					RtnInfo.FAILED.getCode(),
					RtnInfo.FAILED.getMessage(),
					null
					);
		}
		return new ToDoRes(
				RtnInfo.SUCCESSFUL.getCode(),
				RtnInfo.SUCCESSFUL.getMessage(),
				toDoListRes
				);	
	}
	
	@PostMapping("/setIsCheckedOfToDo")
	public ToDoRes setIsCheckedOfToDo(@RequestBody ToDoReq req) {
		List<ToDo> toDoListRes = new ArrayList<>();
		try {
			if(!StringUtils.hasText(req.getId())) {
				return new ToDoRes(
						RtnInfo.DATA_REQUIRED.getCode(),
						RtnInfo.DATA_REQUIRED.getMessage(),
						null
						);
			}
			
			toDoService.setIsCheckedOfToDo(req);
			toDoListRes = toDoService.getToDoList();
		}catch(Exception e) {
			return new ToDoRes(
					RtnInfo.FAILED.getCode(),
					RtnInfo.FAILED.getMessage(),
					null
					);
		}
		return new ToDoRes(
				RtnInfo.SUCCESSFUL.getCode(),
				RtnInfo.SUCCESSFUL.getMessage(),
				toDoListRes
				);	
	}
}
