package com.example.demo_project;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class CalculateServiceTest {
	public static void main(String[] args) {
//		SpringApplication.run(DemoProjectApplication.class, args);
		
		String [] textList = {"Add","Minus","Multi","Division","Mod"};
		System.out.print("請輸入計算方式: ");
		Scanner scan= new Scanner(System.in);
		String text = scan.next();
		if(text.equalsIgnoreCase(text) != false) {
			System.out.print("請重新輸入");
			System.exit(0);
		}
		System.out.print("請輸入數字: ");
		double num1 = scan.nextInt();
		
		System.out.print("請輸入第二個數字: ");
		double num2 = scan.nextInt();
		System.out.print("是否正確(y/n): ");
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
				System.out.print("請重新輸入");
				while(count<3) {
					if(count == 3) {
						System.out.println("已輸入三次");
						System.out.println("============");
						break;
					}
				}
				count++;
			}
			
			
		}
}
