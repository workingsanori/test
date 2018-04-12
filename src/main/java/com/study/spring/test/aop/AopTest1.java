package com.study.spring.test.aop;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class AopTest1 {
	
	public static void main(String[] args) {

		System.out.println(">>>>>>>>>xml에 직접등록<<<<<<<<<<<<");
		//xml파일 연결
		String cofig = "classpath:com/study/spring/test/aop/aop1.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(cofig);		
		
		//대상 빈(TargetObject)
		TargetObject target = ctx.getBean("target",TargetObject.class); //xml에서의 id와 동일하게 target
		String result = target.total(10);
		System.out.println(result);
		ctx.close();

		
		
		System.out.println(">>>>>>>>>어노테이션 사용<<<<<<<<<<<<");
		String cofig2 = "classpath:com/study/spring/test/aop/aopAnnotation.xml";
		AbstractApplicationContext ctx2 = new GenericXmlApplicationContext(cofig2);		
		
		//대상 빈(TargetObject)
		TargetObject target2 = ctx2.getBean("target",TargetObject.class); //xml에서의 id와 동일하게 target
		String result2 = target2.total(10);
		System.out.println(result2);
		ctx2.close();		
		
		
	}
	
}
