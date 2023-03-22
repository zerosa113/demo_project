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

@Component	// 定義成元件，被Spring託管
@Aspect 	// 定義成切面
public class ExecuteTimeLogAspect {

	// 切入點，定義 service 或 controller 下面所有程式都會包含
//	@Pointcut("execution (* com.example.demo_project.controller.*.*(..))")	//execution 指方法執行時觸發，
//	@Pointcut("execution (public * com.example.demo_project.service.impl.*.findAll(..))")
	@Pointcut("execution (public * com.example.demo_project.service.impl.*.*(..))")
	//第一個*代表返回任意型態(*前可加權限)，第二個.*代表任意類別名稱，第三個.*代表任意方法(所有)/單個方法，..代表任意參數
	public void pointcut() {

	}
	
	@Before("pointcut()")	//前置通知，在目標方法調用前調用通知	//("指定切入方法")
	public void before(JoinPoint joinPoint) {
//		System.out.println("======before advice======");
	}
	
	@After("pointcut()")	//後置通知，在目標方法調用後調用通知
	public void after(JoinPoint joinPoint) {
//		System.out.println("======after advice======");
	}
	
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
//		System.out.println("======around advice atart======");
		MethodSignature signature = (MethodSignature) pjp.getSignature();
		//取得方法名稱
//		System.out.println("執行的方法名稱: " + signature.getName());
		long startTime = System.currentTimeMillis();
		//呼叫proceed()會開始執行原方法
		Object result = pjp.proceed();	//ProceedingJoinPoint可呼叫.proceed()
		long spentTime = System.currentTimeMillis() - startTime;
//		System.out.println("result: " + result);
//		System.out.println("Time spent: " + spentTime);
//		System.out.println("======around advice end======");
		return result;
	}
	
	@AfterReturning("pointcut()")	//為 pointcut = "pointcut()" 省略過後
	public void afterReturning() {
//		System.out.println("======afterReturning advice======");
	}
	
	@AfterThrowing(pointcut = "pointcut()", throwing = "throwable")		//throwing = 字串名稱需與參數相同
	public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		//取得方法名稱
//		System.out.println("執行的方法名稱: " + signature.getName());
		//取得錯誤訊息
//		System.out.println("錯誤訊息: " + throwable.getMessage());
//		System.out.println("======afterThrowing advice======");
	}
}
