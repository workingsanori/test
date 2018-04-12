package com.study.spring.test2;

public class User {
	
	// DI를 활용하지 않은 전형적인 객체 생성 방식의 사용(new를 통해서 인스턴스 객체를 생성)
	Phone phone = new AndroidPhone();
	String name = "민경";

	//사용자 정보
	public void info() {
		System.out.println("내 이름은 "+name);
		phone.calling();
	}

}
