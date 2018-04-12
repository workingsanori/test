package com.study.spring.test;

import java.util.List;

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
