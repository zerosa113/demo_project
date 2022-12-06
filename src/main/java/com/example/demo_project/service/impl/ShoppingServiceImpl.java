package com.example.demo_project.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;
import com.example.demo_project.entity.Product;
import com.example.demo_project.service.ifs.ShoppingService;
@Service
public class ShoppingServiceImpl implements ShoppingService{

//	@Override
//	public void getProduct(Product product) {
//		System.out.println("�~��: "+product.getName()+" "+
//		"�ӫ~�ƶq: "+product.getPrice()+" "+
//		"�ʶR�ƶq: "+product.getQuantity()+" "+
//		"�w�s: "+product.getStorage());
//	}
	@Override
	public void queryProduct(List<String> nameList, List<Product> productList) {
		if(nameList.isEmpty()) {
			System.out.println("�d�ߦW�٦C���o����");
			return;
		}
		Map<String,Product>queryMap = new HashMap<>();
		for(String nameitem:nameList) {
			for(Product product:productList) {	//equalsIgnoreCase�����r�����j�p�g
				if(nameitem.equalsIgnoreCase(product.getName())) {
					queryMap.put(nameitem, product);
//					System.out.println("�~��: "+product.getName()+
//							" �ӫ~�ƶq: "+product.getPrice()+
//							" �ʶR�ƶq: "+product.getQuantity()+
//							" �w�s: "+product.getStorage());
					break;
				}else {
					queryMap.put(nameitem, null);
					System.out.println("�d�ߵL���G");
				}
			}
		}
		for(Entry<String,Product>mapItem:queryMap.entrySet()) {
			if(mapItem.getValue()==null) {
				System.out.println(mapItem.getKey()+"�L�Ĭd��");
			}else {
				Product product = mapItem.getValue();
				System.out.println("�~��: "+product.getName()+
						" �ӫ~����: "+product.getPrice()+
						" �ʶR�ƶq: "+product.getQuantity()+
						" �w�s: "+product.getStorage());
			}
		}
	}
	@Override
	public void checkout(List<Product> productList) {
		int total =0;
		System.out.println("====�ʪ��~������====");
		for(Product checkout:productList) {
		System.out.println("�~��: " + checkout.getName()+
				" �ӫ~����: " + checkout.getPrice()+
				" �ʶR�ƶq: " + checkout.getQuantity());
		total += checkout.getPrice()*checkout.getQuantity();
//		checkout.setStorage(-checkout.getStorage());
		}
		System.out.println("�`���B: " + total);
		System.out.println("==============");
		System.out.println("���b");
		for(Product checkout:productList) {
			System.out.println("�~��: " + checkout.getName()+
					" �ӫ~����: " + checkout.getPrice()+
					" �ʶR�ƶq: " + checkout.getQuantity());
		}
	}
}
