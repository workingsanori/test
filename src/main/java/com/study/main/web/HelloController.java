package com.study.main.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping("/hello") // 어떤 컨트롤러, 어떤 메소드가 처리할지 맵핑해줌 기본이 value(String)이다 // xml에서 /로 경로맵핑 되어 있어서 /hello로 해준다...?? 저 hello는 폴더야 파일이야....??? 파일명이네ㅋㅋㅋ.do를 쓰징낳아도 되는건 /로 설정해놔서 
	public String hello(Model model) {// 어트리뷰트에 담을 객체...??????????
		model.addAttribute("message","아라야 졸려~~~졸려~어깨 마사지 해주께ㅋㅋㅋㅋㅋㅋㅋ");
		return "hello"; //mvc-config.xml에서 bean의 기본 경로가 /WEB-INF/view/라서 이부분을 생략하고 쓸수있고 .jsp라고 확장자를 붙여줌 
	}
	
	
}
