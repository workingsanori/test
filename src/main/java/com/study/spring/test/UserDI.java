package com.study.spring.test;

//User.java와 다른 방법
public class UserDI {

	// 스프링의 DI를 통해서 객체 받기(setter)
	Phone phone; //인터페이스만 선언해주고 객체생성 new AndroidPhone();를 셋터를 통해 xml에서 받는다
	String name;
	
	//초기화...init...이니셜라이즈 등등 알아먹을수 있는 네이밍....
	//빈으로 등록할때 초기화 관련 호출
	public void init() {
		System.out.println("-----init메서드 호출 : 초기화 관련 메서드");
	}

	// 빈에서 해제될대 호출
	public void close() { // 사용했던 자원 해제...자원은 빈인가...???
		System.out.println("-----close메서드 호출 : 자원해제");
	}
	//사용자 정보
	public void info() {
		System.out.println("내 이름은 "+name);
		phone.calling(); //new를 생성하지 않고 AndroidPhone의 calling()메서드를 호출 할수 있다
		System.out.println(">>>>주소록<<<<");
		for(String address : phone.getAddress()) {
			System.out.println(address);
		}
		System.out.println(">>>>END<<<<");
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}

	public void setName(String name) {
		this.name = name;
	}
}
