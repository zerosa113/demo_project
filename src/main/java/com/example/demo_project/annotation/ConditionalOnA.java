package com.example.demo_project.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

@Retention(RetentionPolicy.RUNTIME) 	// �Ψөw�q�ӵ����b���ӯŧO�i��,RUNTIME:�Y�B��ɫO�d
@Target({ ElementType.TYPE, ElementType.METHOD }) 	// �Ψөw�q�Y�ӵ������d��,TYPE:����,METHOD:��k
@Documented		//�Ψӻ����o�ӵ����O�� javadoc ����
@ConditionalOnProperty(
		name = "conditional.test",	// name: property �ɪ� key
		havingValue = "AAA",		// havingValue: property �ɪ� value
		matchIfMissing = true)		
		
public @interface ConditionalOnA {

}
