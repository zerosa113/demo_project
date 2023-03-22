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
		
		System.out.println("請輸入數字: ");
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
		System.out.printf("輸入數字：");   
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
			System.out.printf(num + "的階乘為： " + sum);	
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
		
		final int pwd =65;		//密碼數字
		int count = 1;			//計算猜的次數
		int num;				//儲存猜的數字
		int max = 99;
		int min = 1;
		Scanner scanner = new Scanner(System.in);
		System.out.print("請猜1~99的數字:");
		while (count < 5) {        
			num = scanner.nextInt();	//讀輸入數字
			if(num == pwd) {				
				System.out.println("恭喜猜對了!");
				break;
			}
			if(num < pwd) {		//猜數字範圍提醒
				min = num;
				System.out.printf("請在%2d~%2d範圍內猜個數字\n",min,max);				
			}else if(num > pwd) {
				max = num;
				System.out.printf("請在%2d~%2d範圍內猜個數字\n",min,max);				
			}else if(num > max || num < min) {
				System.out.printf("錯誤，請輸入%2d~%2d範圍內的數字\n",min,max);
			}
			if(count == 5) {			
				System.out.println("只能猜5次");
				break;
				
			}else {
				System.out.println("猜錯了請再答一次!");
			}
			count++;	
		}
	}
	
	@Test
	public void homeworkTest0306() {
		
		Random random = new Random();
		//隨機數取下限值~上限值公式：(random()*上限值-下限值+1)+下限值
		int pwd = (int) (Math.random()*99+1);
		System.out.println(pwd);
		Scanner scanner = new Scanner(System.in);
		System.out.println("請輸入數字: ");
		int min = 1;
		int max = 99;
		
		while(true) {				
			int num = scanner.nextInt();
			if(num == pwd) {				
				System.out.println("猜對了");
				break;
			}else if(min <= num && num < pwd) {
				min = num;
				System.out.printf("請輸入%d~%d的數字: \n", min, max);
			}else if(max >= num && num > pwd) {
				max = num;
				System.out.printf("請輸入%d~%d的數字: \n", min, max);
			}else if(num > max || num < min) {
				System.out.printf("錯誤，請輸入%2d~%2d範圍內的數字\n",min,max);
			}		
		}		
	}
	
	@Test
	public void  calculateTest0307(){
		String qus = "神鵰俠侶是楊過與小龍女的故事，我不喜歡小龍女的甲仙，雖然小龍女在楊過眼中是清新脫俗";
		String str = "小龍女"; 
		int count = 1;
		int num = 0;
		
		System.out.println(qus.length());
		System.out.println(qus.indexOf(str));
		
		for(int i = 0; i < qus.length(); i++) {	
			if(qus.indexOf(str,num) != -1) {
				System.out.printf("第%d小龍女在第%d個字\n" , count, qus.indexOf(str,num) + 1);
				num = qus.indexOf(str, num)+1;
				count++;
			}else{
				break;
			}	
		}
		System.out.printf("小龍女出現%d次" , count - 1);	
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
//		System.out.println("str1手機格式"+str1.matches(pattern));
//		System.out.println("str2手機格式"+str2.matches(pattern));
//		System.out.println("str3手機格式"+str3.matches(pattern));
//		
//		String str4 = "\\";	//前為跳脫符號
//		String pattern2 = "\\\\";
//		
//		System.out.println("str4格式"+str4.matches(pattern2));
//		
//		String pattern3 = "\\d{2}-\\d{8}|\\(\\d{2}\\)-\\d{8}";	//|或
//		
//		String str5 = "Johnson";
//		String pattern4 = "John(na)*son";
//		System.out.println("str5格式"+str5.matches(pattern4));
		
		String phone = "02-1234567";
		String pattern6 = "0[1-9]{1,3}-\\d{7,8}";
		System.out.println("市話格式"+phone.matches(pattern6));
		
		String pattern7 = "[A-Za-z]\\d{1,2}\\d{8}";
		String pattern8 = "[A-Za-z&&[^ABDEFHabdefh]]\\d{1,2}\\d{8}";
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("請輸入身分證: ");
//		String id = scanner.next();
		String id = "A123456789";
		System.out.println("身分證格式"+id.matches(pattern8));
		
	}
	
	@Test
	public void  calculateTest0321(){
		List<String>strList = new ArrayList<>();
		strList.add("北京");
		strList.add("香港");
		strList.add("上海");
		System.out.println(strList);
	}
}
		
		

	
