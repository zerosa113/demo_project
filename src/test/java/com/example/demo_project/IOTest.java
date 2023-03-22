package com.example.demo_project;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;

import org.junit.jupiter.api.Test;

public class IOTest {

	@Test
	public void fileOutputStreamTest() throws IOException { // 不寫指定路徑會存在預設專案裡
//		FileOutputStream fos = new FileOutputStream("C:\\Users\\ASUS\\OneDrive\\桌面\\java_project\\demo_project\\1207_test01.txt");
//		try {
//		fos.write(70);
//		System.out.println("檔案寫入成功!");
//		}catch(FileNotFoundException e){
//			e.printStackTrace();
//		}finally {
//			fos.close();
//		}

		// try-with-resource
//		try (FileOutputStream fos = new FileOutputStream("test01.txt")) {
//			String str = "65";
//			fos.write(str.getBytes());
//			System.out.println("檔案寫入成功!");
//		} catch (Exception e) {
//
//		}
		try (FileOutputStream fos = new FileOutputStream("test01.txt",true)) {
			String str = "99";
			fos.write('\n');
			fos.write(str.getBytes());
			System.out.println("檔案寫入成功!");
		} catch (Exception e) {

		}
	}

	@Test
	public void fileInputStreamTest() {
		try (FileInputStream fis = new FileInputStream("test01.txt")) {
			int b = fis.read(); // 只讀取 1 Byte
			System.out.println(b);
			System.out.println((char) b);
			System.out.println("檔案讀取成功!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//input image
	@Test
	public void fileInputStreamTest1() {	//讀取多個檔案使用;隔開
		try (FileInputStream fis = new FileInputStream("pokemon.png");
				FileOutputStream fos = new FileOutputStream("2222.jpg")) {
			
			System.out.println("檔案大小: " + fis.available());
//			byte[] b = new byte[fis.available()];
			byte[] b = fis.readAllBytes();	// 讀取所有Byte
			fos.write(b);	//來源圖檔太大會過於耗能
			System.out.println("檔案複製成功!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void bufferedFileOutputStreamTest() throws IOException { // 不寫指定路徑會存在預設專案裡
//		FileOutputStream fos = new FileOutputStream("C:\\Users\\ASUS\\OneDrive\\桌面\\java_project\\demo_project\\1207_test01.txt");
		try(FileOutputStream fos = new FileOutputStream("test02.txt");
				BufferedOutputStream bfos = new  BufferedOutputStream(fos)) {
			String str = "測試!ASDF";
			bfos.write(str.getBytes());
			bfos.flush();
			System.out.println("檔案寫入成功!");
			}catch(Exception e){
				// TODO: handle exception
			}
	}
	
	
}
