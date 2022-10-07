package com.example.demo_project.entity;

import org.springframework.stereotype.Service;

import com.example.demo_project.service.ifs.Active;

@Service
public class Birdfly implements Active{

	
	@Override
	public Bird getBirdInfo(String name,int age) {
		Bird bird = new Bird();

		bird.setName(name);
		bird.setAge(age);
		return bird;

	}
	
	public void printBirdAttributes(Bird bird) {
		System.out.println(bird.getName());
		System.out.println(bird.getAge());
	}
/*	public void fly(Bird x) {
	System.out.println(x.getName() + "正在飛");
	
	}
	@Override
	public void fly(int age) {
		Bird x = new Bird();
		System.out.println("今年" + x.getAge()+"歲");
	}
	@Override
	public void fly() {
		// TODO Auto-generated method stub	
	}
	*/
//	@Override
//	public Bird printBirdAttributes(String name, int age) {
//		// TODO Auto-generated method stub
//		return null;
//	}



}
