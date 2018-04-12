package com.study.spring.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect //xml파일의 aop:aspect  와 같음 
public class LoggingAdviceAnnotation {

	//pointcut컷을 만들기 위한 메서드 //@Before(value="pc()")이렇게 활용하는경우
	@Pointcut("execution(public String com.study.spring..*.to*(..))")
	public void pc() {
		
	}
	
	// 공통기능을 처리하는 클래스 작성

	//@Before(value="execution(* total(..))") //xml파일의 aop:before
	@Before(value="pc()") //xml파일의 aop:before
	public void logging(JoinPoint joinPoint) { // JoinPoint는 반드시 첫번재 패러미터로 받아야한다.
		System.out.println("++++++Annotation+++++");
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		Object[] args = joinPoint.getArgs();
		System.out.println("beforMethod 실행 " + className + "." + methodName);
		if(args != null && args.length > 0) {
			System.out.println("첫번째 인자 = "+args[0]);
		}
	}
	
	
	//Around로 한번에 비포 에프터 리턴 익셉션등 다 처리가능해서 자주사용한다
	// 실행전 처리, 타겟 메서드 직접호출, 예외처리 가능
	@Around(value="pc()")//value단독으로 사용할 경우는 value생략 가능 그냥 pc()로 쓰면된다. 
	public Object aroundMethod(ProceedingJoinPoint joinPoint) throws Throwable { //around는 ProceedingJoinPoint로!!!!
		System.out.println("++++++Annotation+++++");
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
