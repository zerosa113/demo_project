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
//		System.out.println("品項: "+product.getName()+" "+
//		"商品數量: "+product.getPrice()+" "+
//		"購買數量: "+product.getQuantity()+" "+
//		"庫存: "+product.getStorage());
//	}
	@Override
	public void queryProduct(List<String> nameList, List<Product> productList) {
		if(nameList.isEmpty()) {
			System.out.println("查詢名稱列表不得為空");
			return;
		}
		Map<String,Product>queryMap = new HashMap<>();
		for(String nameitem:nameList) {
			for(Product product:productList) {	//equalsIgnoreCase忽略字母的大小寫
				if(nameitem.equalsIgnoreCase(product.getName())) {
					queryMap.put(nameitem, product);
//					System.out.println("品項: "+product.getName()+
//							" 商品數量: "+product.getPrice()+
//							" 購買數量: "+product.getQuantity()+
//							" 庫存: "+product.getStorage());
					break;
				}else {
					queryMap.put(nameitem, null);
					System.out.println("查詢無結果");
				}
			}
		}
		for(Entry<String,Product>mapItem:queryMap.entrySet()) {
			if(mapItem.getValue()==null) {
				System.out.println(mapItem.getKey()+"無效查詢");
			}else {
				Product product = mapItem.getValue();
				System.out.println("品項: "+product.getName()+
						" 商品價格: "+product.getPrice()+
						" 購買數量: "+product.getQuantity()+
						" 庫存: "+product.getStorage());
			}
		}
	}
	@Override
	public void checkout(List<Product> productList) {
		int total =0;
		System.out.println("====購物品項明細====");
		for(Product checkout:productList) {
		System.out.println("品項: " + checkout.getName()+
				" 商品價格: " + checkout.getPrice()+
				" 購買數量: " + checkout.getQuantity());
		total += checkout.getPrice()*checkout.getQuantity();
//		checkout.setStorage(-checkout.getStorage());
		}
		System.out.println("總金額: " + total);
		System.out.println("==============");
		System.out.println("結帳");
		for(Product checkout:productList) {
			System.out.println("品項: " + checkout.getName()+
					" 商品價格: " + checkout.getPrice()+
					" 購買數量: " + checkout.getQuantity());
		}
	}
}
