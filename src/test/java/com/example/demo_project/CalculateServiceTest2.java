package com.example.demo_project;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import org.hibernate.mapping.Array;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DemoProjectApplication.class)
public class CalculateServiceTest2 {
	
	@Test
	public void switchTest0223() {
		
		Scanner scan= new Scanner(System.in);	
		
		int score;
		
		System.out.println("�п�J�Ʀr: ");
		score = scan.nextInt();
		
		switch(score/10) {
		case 9:
			System.out.println("A");
			break;
		case 8:
			System.out.println("B");
			break;
		case 7:
			System.out.println("C");
			break;
		case 6:
			System.out.println("D");
			break;
		default:
			System.out.println("F");
			break;
		}
	}
	
	@Test
	public void forTest0223() {
		
		int x;
		int y;
		
		for(x = 1; x <= 9; x++) {
			System.out.println();
			for(y = 1; y <=9; y++) {
				System.out.printf("%2d*%d=%2d", x, y, x * y);
			}
		}
	}
	
	@Test
	public void homeworkTest0224() {
		System.out.printf("��J�Ʀr�G");   
		Scanner scanner = new Scanner(System.in); 
		int num = scanner.nextInt();
		
		int sum = 1;
		for(int i = num; i >= 1; i--) {
			sum = sum*i;    
			System.out.println(i);
		}
//		for(int i = 1; i <= num; i++){
//			sum = sum*i;    
//			System.out.println(sum); 
//			}    
			System.out.printf(num + "���������G " + sum);	
	}
	
	@Test
	public void primeTest0224() {
		System.out.println();
		int i;
		int prime;
		int count = 0;
		
		for (i = 11; ; i++){
			
			for (prime = 2; prime <= i; prime++) {
				if (i % prime == 0 && i!= prime) {
				break;
				
				}else if(prime == (i-1)) {
					System.out.println(i);
					count++;
				}
			}
			if(count == 20) {
				break;
				
			}
		}
	}
	
	@Test
	public void homeworkTest0301(){
		
		final int pwd =65;		//�K�X�Ʀr
		int count = 1;			//�p��q������
		int num;				//�x�s�q���Ʀr
		int max = 99;
		int min = 1;
		Scanner scanner = new Scanner(System.in);
		System.out.print("�вq1~99���Ʀr:");
		while (count < 5) {        
			num = scanner.nextInt();	//Ū��J�Ʀr
			if(num == pwd) {				
				System.out.println("���߲q��F!");
				break;
			}
			if(num < pwd) {		//�q�Ʀr�d�򴣿�
				min = num;
				System.out.printf("�Цb%2d~%2d�d�򤺲q�ӼƦr\n",min,max);				
			}else if(num > pwd) {
				max = num;
				System.out.printf("�Цb%2d~%2d�d�򤺲q�ӼƦr\n",min,max);				
			}else if(num > max || num < min) {
				System.out.printf("���~�A�п�J%2d~%2d�d�򤺪��Ʀr\n",min,max);
			}
			if(count == 5) {			
				System.out.println("�u��q5��");
				break;
				
			}else {
				System.out.println("�q���F�ЦA���@��!");
			}
			count++;	
		}
	}
	
	@Test
	public void homeworkTest0306() {
		
		Random random = new Random();
		//�H���ƨ��U����~�W���Ȥ����G(random()*�W����-�U����+1)+�U����
		int pwd = (int) (Math.random()*99+1);
		System.out.println(pwd);
		Scanner scanner = new Scanner(System.in);
		System.out.println("�п�J�Ʀr: ");
		int min = 1;
		int max = 99;
		
		while(true) {				
			int num = scanner.nextInt();
			if(num == pwd) {				
				System.out.println("�q��F");
				break;
			}else if(min <= num && num < pwd) {
				min = num;
				System.out.printf("�п�J%d~%d���Ʀr: \n", min, max);
			}else if(max >= num && num > pwd) {
				max = num;
				System.out.printf("�п�J%d~%d���Ʀr: \n", min, max);
			}else if(num > max || num < min) {
				System.out.printf("���~�A�п�J%2d~%2d�d�򤺪��Ʀr\n",min,max);
			}		
		}		
	}
	
	@Test
	public void  calculateTest0307(){
		String qus = "����L�Q�O���L�P�p�s�k���G�ơA�ڤ����w�p�s�k���ҥP�A���M�p�s�k�b���L�����O�M�s��U";
		String str = "�p�s�k"; 
		int count = 1;
		int num = 0;
		
		System.out.println(qus.length());
		System.out.println(qus.indexOf(str));
		
		for(int i = 0; i < qus.length(); i++) {	
			if(qus.indexOf(str,num) != -1) {
				System.out.printf("��%d�p�s�k�b��%d�Ӧr\n" , count, qus.indexOf(str,num) + 1);
				num = qus.indexOf(str, num)+1;
				count++;
			}else{
				break;
			}	
		}
		System.out.printf("�p�s�k�X�{%d��" , count - 1);	
	}
	
	@Test
	public void  calculateTest0313(){
		int list[] = {1,9};
		StringBuffer strBuf = new StringBuffer();
		for(int i:list) {
			strBuf.append(i);
		}
		Integer num = Integer.parseInt(strBuf.toString())+1;
		String strNum = num.toString();
		
		List<Character> numList= new ArrayList<>();
	
		for(int i = 0; i <= strNum.length()-1; i++) {
			numList.add(strNum.charAt(i));
		}
		System.out.println(numList.toString());
	}
	
	@Test
	public int[]  calculateTest0313_1(){
		int[] nums = null;
		int target = 0;
		Map<Integer,Integer>numMap = new HashMap<>();
	    int[]result = new int[2];
		for(int i = 0; i < nums.length; i++){
			int x = target-nums[i];
			if(numMap.containsKey(x)) {
				result[1] = i;
				result[0]=numMap.get(x);
				return result;
			}
			numMap.put(nums[i],i);	
	    }

//		nums[0] + nums[1] == target
//		numMap.containsKey(nums);
		return result;  
	}
	
	
	
	@Test
	public void  calculateTest0314(){
//		String str1 = "java";
//		String str2 = "0912-345-678";
//		String str3 = "1111-111222";
//		String pattern = "\\d{4}(-\\d{3}){2}";
//		
//		System.out.println("str1����榡"+str1.matches(pattern));
//		System.out.println("str2����榡"+str2.matches(pattern));
//		System.out.println("str3����榡"+str3.matches(pattern));
//		
//		String str4 = "\\";	//�e������Ÿ�
//		String pattern2 = "\\\\";
//		
//		System.out.println("str4�榡"+str4.matches(pattern2));
//		
//		String pattern3 = "\\d{2}-\\d{8}|\\(\\d{2}\\)-\\d{8}";	//|��
//		
//		String str5 = "Johnson";
//		String pattern4 = "John(na)*son";
//		System.out.println("str5�榡"+str5.matches(pattern4));
		
		String phone = "02-1234567";
		String pattern6 = "0[1-9]{1,3}-\\d{7,8}";
		System.out.println("���ܮ榡"+phone.matches(pattern6));
		
		String pattern7 = "[A-Za-z]\\d{1,2}\\d{8}";
		String pattern8 = "[A-Za-z&&[^ABDEFHabdefh]]\\d{1,2}\\d{8}";
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("�п�J������: ");
//		String id = scanner.next();
		String id = "A123456789";
		System.out.println("�����Ү榡"+id.matches(pattern8));
		
	}
	
	@Test
	public void  calculateTest0321(){
		List<String>strList = new ArrayList<>();
		strList.add("�_��");
		strList.add("����");
		strList.add("�W��");
		System.out.println(strList);
	}
}
		
		

	
