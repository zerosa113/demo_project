package com.example.demo_project;
import java.util.Scanner;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MathText {
//	public static void main(String[] args) {	}
//	SpringApplication.run(DemoProjectApplication.class, args);
	@Test
	public void calculaTetest() {
		inputString();
		inputNumber();
		inputStringName();
		inputNumberName();
	}
//	public void inputFunction(String fun) {
//		switch (fun) {
//	    case "add": 
//	    	addFunction();
//	        break;
//	    case "minus":
//	    	minusFunction();
//	    	break;
//		}
//	}
	public String inputString() {
		System.out.print("�п�J�p��覡: ");
		Scanner scan= new Scanner(System.in);
		String text = scan.next();
		return text;
	}
	public String inputNumber() {
		System.out.print("�п�J�Ʀr: ");
		Scanner scan= new Scanner(System.in);
		String num = scan.next();
		return num;
	}
	public String inputStringName() {
		String [] textList = {"Add","Minus","Multi","Division","Mod"};
		String checkStr = null;
		int error = 0;
		while(error < 3) {
			checkStr = inputString();
			Boolean checkString = false;
			for(String item:textList) {
				if(checkStr.equalsIgnoreCase(item)) {
					checkString = true;
				}
			}
			if(checkString) {
				break;
			}else if(error == 3) {
				System.out.println("�w��J�T��");
				System.out.println("============");
				checkStr = null;
				break;
			}else {
				System.out.println("��J���~: "+(error+1));
				System.out.println();
				error += 1;
			}
		}
		if(checkStr.equals(null)) {
			System.exit(0);
		}
		return checkStr;
	}
	public void inputNumberName() {
		String num1 = inputNumber();
		Double dnum1 = Double.parseDouble(num1);
		int inum1 = Integer.parseInt(num1);
		if(dnum1==inum1) {
			if((inum1<2 && inum1>20 )||( dnum1<2 && dnum1>20)) { 
				System.out.println("�Э��s��J");
			}else {
				System.out.println("�Ʀr: "+num1);
			}
		}
	}
	private void addFunction() {
		// TODO Auto-generated method stub
		
	}

	private void minusFunction() {
		// TODO Auto-generated method stub
		
	}
	
//		Scanner scan= new Scanner(System.in);
//		String [] cal = {"8","+","6"};
//		
//		int ans = 0;
//	 
//		switch (cal[1].charAt(0)) {
//	    case '+': ans = Integer.parseInt(cal[0]) + Integer.parseInt(cal[2]);
//	               break;
//	    case '-': ans = Integer.parseInt(cal[0]) - Integer.parseInt(cal[2]);
//	               break;
//	    case '.': ans = Integer.parseInt(cal[0]) * Integer.parseInt(cal[2]);
//	               break;
//	    case '/': ans = Integer.parseInt(cal[0]) / Integer.parseInt(cal[2]);
//	   }
//	 System.out.printf("%s %s %s = %d%n", args[0], args[1], args[2], ans);
//	 }
}
