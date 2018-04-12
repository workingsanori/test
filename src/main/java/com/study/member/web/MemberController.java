package com.study.member.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.member.model.Member;
import com.study.member.service.IMemberService;


@Controller
public class MemberController {
	
	@Autowired
	private IMemberService memberService;
	
	@RequestMapping("/member/memberList")
	public String list(HttpServletRequest request) { // String로 하면 스프링이 알아서 뷰로 인식해 mvc-config.xml에 InternalResourceViewResolver에서 세팅한걸 적용시켜줌
		//위의 페러미터 값은 mvp.pdf에 14페이지 공부해보기
		System.out.println("====List====");
		
		List<Member> list = memberService.getmemberList();
		request.setAttribute("list", list);
		
		return "member/memberList"; //기존에 mvc-config.xml에 등록해놔서
		
	}
	
	@RequestMapping("/member/memberView")
	public String view(String mem_id, Model model) {
		System.out.println("====View====");
		if(mem_id == null || mem_id.trim().equals("")) {
			return "redirect:/member/memberList"; //스프링이 알아서 redirect:를 지우고 경로만 기억해줌
		}
		Member member = memberService.getMember(mem_id);
		model.addAttribute("member", member); //request.setAttribute("member", member); 기존엔 이렇게 썻음ㅋㅋㅋ
		return "member/memberView";
	}
	

}
