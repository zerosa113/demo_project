package com.example.demo_project;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;

import org.junit.jupiter.api.Test;

public class IOTest {

	@Test
	public void fileOutputStreamTest() throws IOException { // ���g���w���|�|�s�b�w�]�M�׸�
//		FileOutputStream fos = new FileOutputStream("C:\\Users\\ASUS\\OneDrive\\�ୱ\\java_project\\demo_project\\1207_test01.txt");
//		try {
//		fos.write(70);
//		System.out.println("�ɮ׼g�J���\!");
//		}catch(FileNotFoundException e){
//			e.printStackTrace();
//		}finally {
//			fos.close();
//		}

		// try-with-resource
//		try (FileOutputStream fos = new FileOutputStream("test01.txt")) {
//			String str = "65";
//			fos.write(str.getBytes());
//			System.out.println("�ɮ׼g�J���\!");
//		} catch (Exception e) {
//
//		}
		try (FileOutputStream fos = new FileOutputStream("test01.txt",true)) {
			String str = "99";
			fos.write('\n');
			fos.write(str.getBytes());
			System.out.println("�ɮ׼g�J���\!");
		} catch (Exception e) {

		}
	}

	@Test
	public void fileInputStreamTest() {
		try (FileInputStream fis = new FileInputStream("test01.txt")) {
			int b = fis.read(); // �uŪ�� 1 Byte
			System.out.println(b);
			System.out.println((char) b);
			System.out.println("�ɮ�Ū�����\!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	//input image
	@Test
	public void fileInputStreamTest1() {	//Ū���h���ɮרϥ�;�j�}
		try (FileInputStream fis = new FileInputStream("pokemon.png");
				FileOutputStream fos = new FileOutputStream("2222.jpg")) {
			
			System.out.println("�ɮפj�p: " + fis.available());
//			byte[] b = new byte[fis.available()];
			byte[] b = fis.readAllBytes();	// Ū���Ҧ�Byte
			fos.write(b);	//�ӷ����ɤӤj�|�L��ӯ�
			System.out.println("�ɮ׽ƻs���\!");
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@Test
	public void bufferedFileOutputStreamTest() throws IOException { // ���g���w���|�|�s�b�w�]�M�׸�
//		FileOutputStream fos = new FileOutputStream("C:\\Users\\ASUS\\OneDrive\\�ୱ\\java_project\\demo_project\\1207_test01.txt");
		try(FileOutputStream fos = new FileOutputStream("test02.txt");
				BufferedOutputStream bfos = new  BufferedOutputStream(fos)) {
			String str = "����!ASDF";
			bfos.write(str.getBytes());
			bfos.flush();
			System.out.println("�ɮ׼g�J���\!");
			}catch(Exception e){
				// TODO: handle exception
			}
	}
	
	
}
