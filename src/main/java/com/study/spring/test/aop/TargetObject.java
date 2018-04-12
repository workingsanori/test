package com.study.spring.test.aop;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//우리가만든 서비스와 같다고 생각하면 됨ㅎㅎ
@Component("target") //xml파일에서 bean id="target"
@Scope("prototype")//xml파일에서 bean id="target"
public class TargetObject {

	public String total(int maxValue) {

		System.out.println("=== total 실행 ===");
		if (maxValue < 1) {
			throw new RuntimeException(maxValue + "는 범위를 벗어났습니다.");
		}
		
		int sum = 0;
		for (int i = 1; i <= maxValue; i++) {
			sum += i;
		}
		return "결과값은 = [" + sum + "]";

	}// total
}
