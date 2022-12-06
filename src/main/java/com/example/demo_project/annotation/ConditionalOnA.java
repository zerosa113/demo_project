package com.example.demo_project.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Retention(RetentionPolicy.RUNTIME) 	// 用來定義該註釋在哪個級別可用,RUNTIME:即運行時保留
@Target({ ElementType.TYPE, ElementType.METHOD }) 	// 用來定義某個註釋的範圍,TYPE:類型,METHOD:方法
@Documented		//用來說明這個註釋是由 javadoc 紀錄
@ConditionalOnProperty(
		name = "conditional.test",	// name: property 檔的 key
		havingValue = "AAA",		// havingValue: property 檔的 value
		matchIfMissing = true)		
		
public @interface ConditionalOnA {

}
