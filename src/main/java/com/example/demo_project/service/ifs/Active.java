package com.example.demo_project.service.ifs;

import com.example.demo_project.entity.Bird;

public interface Active {
//	public void fly();

//	public void fly(String name,int age);
//	public void fly(int age);

	public Bird getBirdInfo(String name, int age);
	public void printBirdAttributes(Bird bird);

}
