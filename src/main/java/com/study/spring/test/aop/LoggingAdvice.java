package com.study.spring.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggingAdvice {

	// 공통기능을 처리하는 클래스 작성

	public void logging(JoinPoint joinPoint) { // JoinPoint는 반드시 첫번재 패러미터로 받아야한다.
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		Object[] args = joinPoint.getArgs();
		System.out.println("beforMethod 실행 " + className + "." + methodName);
		if(args != null && args.length > 0) {
			System.out.println("첫번째 인자 = "+args[0]);
		}
	}
	
	
	// 실행전 처리, 타겟 메서드 직접호출, 예외처리 가능
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable { //around는 ProceedingJoinPoint로!!!!
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		System.out.println("aroundMethod 실행 - 1");
		long startTime = System.currentTimeMillis();
		
		//대상 객체 콜
		Object retVal = joinPoint.proceed(); // ex)target.total()를 콜할때..
		
		System.out.println("aroundMethod 실행 - 2 " + className + "." + methodName + ", lead time ="
				+ (System.currentTimeMillis() - startTime));
		return retVal;
	}

}
