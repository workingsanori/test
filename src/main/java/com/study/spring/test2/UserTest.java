package com.study.spring.test2;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;


public class UserTest {

	public static void main(String[] args) {
		
		System.out.println("-------DI활용-------");
		String cofig = "classpath:com/study/spring/test2/userDI.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(cofig);
		
		UserDI userDI = ctx.getBean("userDI",UserDI.class);
		UserDI userDI1 = ctx.getBean("userDI",UserDI.class);
		
		Phone phone1 = ctx.getBean("IPhone",IPhone.class);
		Phone phone2 = ctx.getBean("IPhone",IPhone.class);
		//직접적으로 new를 쓰지 않고 beand에서 new를 하기 때문에 변수 선언을 어러개 해도 결국 하나의 빈만을 사용한다
		//.xml 콘텍스? 였나 여튼 거기에 scope이 싱글톤으로 기본설정되어 있어서
		//scope="prototype" 으로 하면 객체생성이 여러개 된다
		
		//userDI.info();
		System.out.println("1번.hashCode : "+userDI.hashCode());
		System.out.println("2번.hashCode : "+userDI1.hashCode());
		System.out.println("------");
		System.out.println("1번.hashCode : "+phone1.hashCode());
		System.out.println("2번.hashCode : "+phone2.hashCode());
		
		ctx.close();
	}
	
}
