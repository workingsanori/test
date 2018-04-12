package com.study.spring.test2;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component//("user")직접id를 입력할수 도 있다 // 빈등록될때 낙타식(카멜표기법)으로 된다 userDI
@Scope("prototype")//기본이 싱글톤이라 여러개의 객체를 생성하고 싶을때 스콥이라고 쓰면 된다
public class UserDI {

	// 스프링의 DI를 통해서 객체 받기(setter)
	//@Autowired(required=false) 객체생성안하고 null을 해야 할경우...ㅋㅋ보통 기본이  true 컴포넌트가 꼭필요함
	//@Autowired //@Autowired를 쓰면 세터를 만들지 않아도 된다
	//@Qualifier("IPhone") // 아이폰인지 안드로이드인지 알수가 없어 충돌이 날경우 써줘야 한다
	
	@Resource(name="IPhone") ///@Autowired/@Qualifier("")를 한줄로 써줄수 있다
	Phone phone; //인터페이스만 선언해주고 객체생성 new AndroidPhone();를 셋터를 통해 xml에서 받는다
	@Value("어노테이션") // 보통 이렇게 직접 쓰지 않는다
	String name;

	//초기화...init...이니셜라이즈 등등 알아먹을수 있는 네이밍....
	//빈으로 등록할때 초기화 관련 호출
	@PostConstruct // userDI.xml에 init-method="init"를 등록하지 않아도 된다
	public void init() {
		System.out.println("-----init메서드 호출 : 초기화 관련 메서드");
	}

	// 빈에서 해제될대 호출
	@PreDestroy // userDI.xml에 destroy-method="close"를 등록하지 않아도 자동으로 세팅
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

	//위에서 phone에 @Autowired를 해주었기 때문에 생성하지 않아도 된다
//	public void setPhone(Phone phone) {
//		this.phone = phone;
//	}

	public void setName(String name) {
		this.name = name;
	}
}
