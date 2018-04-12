package com.study.spring.test2;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")//기본이 싱글톤이라 여러개의 객체를 생성하고 싶을때 스콥이라고 쓰면 된다
public class IPhone implements Phone {
	List<String> address;
	public void calling() {
		System.out.println("아이폰 : 전화 왔어요~~");
		
	}

	public List<String> getAddress() {
		return address;
	}

	public void setAddress(List<String> address) {
		this.address = address;
		
	}

	

}
