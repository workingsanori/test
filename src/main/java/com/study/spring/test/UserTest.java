package com.study.spring.test;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserTest {

	public static void main(String[] args) {
		
		
		System.out.println("-------기존 객체생성 방식-------");
		User user = new User();
		user.info();
		
		System.out.println("-------DI활용-------");
		String cofig = "classpath:com/study/spring/test/userDI.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(cofig);
		UserDI userDI = ctx.getBean("userDI",UserDI.class);
		userDI.info();
		ctx.close();
	}
	
}
