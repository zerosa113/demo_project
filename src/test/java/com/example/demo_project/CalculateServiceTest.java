package com.example.demo_project;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class CalculateServiceTest {
	public static void main(String[] args) {
//		SpringApplication.run(DemoProjectApplication.class, args);
		
		String [] textList = {"Add","Minus","Multi","Division","Mod"};
		System.out.print("�п�J�p��覡: ");
		Scanner scan= new Scanner(System.in);
		String text = scan.next();
		if(text.equalsIgnoreCase(text) != false) {
			System.out.print("�Э��s��J");
			System.exit(0);
		}
		System.out.print("�п�J�Ʀr: ");
		double num1 = scan.nextInt();
		
		System.out.print("�п�J�ĤG�ӼƦr: ");
		double num2 = scan.nextInt();
		System.out.print("�O�_���T(y/n): ");
	//	while() {}
		String end = scan.nextLine();
		boolean n;
		if(n=scan.equals(end)) {
			System.out.print("");
			System.exit(0);
		}
		

		double a=num1+num2;
		double b=num1-num2;
		double c=num1*num2;
		double d=num1/num2;
		double e=num1%num2;
		
		switch(text) {
		case "Add":
			System.out.println("Add: " + a);
		case "Minus":
			System.out.println("Minus: " + b);
		case "Multi":
			System.out.println("Multi: " + c);
		case "Division":
			System.out.println("Division: " + d);
		case "Mod":
			System.out.println("Mod: " + e);
		default:
			break;
		}
		
		
		int count =0;
			if((num1<2 && num1>20 )||( num2<2 && num2>20)) { 
				System.out.print("�Э��s��J");
				while(count<3) {
					if(count == 3) {
						System.out.println("�w��J�T��");
						System.out.println("============");
						break;
					}
				}
				count++;
			}
			
			
		}
}
