package com.example.demo_project.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component	// �w�q������A�QSpring�U��
@Aspect 	// �w�q������
public class ExecuteTimeLogAspect {

	// ���J�I�A�w�q service �� controller �U���Ҧ��{�����|�]�t
//	@Pointcut("execution (* com.example.demo_project.controller.*.*(..))")	//execution ����k�����Ĳ�o�A
//	@Pointcut("execution (public * com.example.demo_project.service.impl.*.findAll(..))")
	@Pointcut("execution (public * com.example.demo_project.service.impl.*.*(..))")
	//�Ĥ@��*�N���^���N���A(*�e�i�[�v��)�A�ĤG��.*�N����N���O�W�١A�ĤT��.*�N����N��k(�Ҧ�)/��Ӥ�k�A..�N����N�Ѽ�
	public void pointcut() {

	}
	
	@Before("pointcut()")	//�e�m�q���A�b�ؼФ�k�եΫe�եγq��	//("���w���J��k")
	public void before(JoinPoint joinPoint) {
//		System.out.println("======before advice======");
	}
	
	@After("pointcut()")	//��m�q���A�b�ؼФ�k�եΫ�եγq��
	public void after(JoinPoint joinPoint) {
//		System.out.println("======after advice======");
	}
	
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
//		System.out.println("======around advice atart======");
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		//���o��k�W��
//		System.out.println("���檺��k�W��: " + signature.getName());
		long startTime = System.currentTimeMillis();
		//�I�sproceed()�|�}�l������k
		Object result = pjp.proceed();	//ProceedingJoinPoint�i�I�s.proceed()
		long spentTime = System.currentTimeMillis() - startTime;
//		System.out.println("result: " + result);
//		System.out.println("Time spent: " + spentTime);
//		System.out.println("======around advice end======");
		return result;
	}
	
	@AfterReturning("pointcut()")	//�� pointcut = "pointcut()" �ٲ��L��
	public void afterReturning() {
//		System.out.println("======afterReturning advice======");
	}
	
	@AfterThrowing(pointcut = "pointcut()", throwing = "throwable")		//throwing = �r��W�ٻݻP�ѼƬۦP
	public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		//���o��k�W��
//		System.out.println("���檺��k�W��: " + signature.getName());
		//���o���~�T��
//		System.out.println("���~�T��: " + throwable.getMessage());
//		System.out.println("======afterThrowing advice======");
	}
}
