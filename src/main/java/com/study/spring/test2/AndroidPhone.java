package com.study.spring.test2;

import java.util.List;

import org.springframework.stereotype.Component;

@Component

public class AndroidPhone implements Phone{
	List<String> address;
	
	public void calling() {
		System.out.println("안드로이드 전화 왔어요~~~");
	}

	public List<String> getAddress() {
		return address;
	}

	public void setAddress(List<String> address) {
		this.address = address;
		
	}

}
