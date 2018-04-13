package com.study.member.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.member.model.Member;
import com.study.member.service.IMemberService;


@Controller
public class MemberController {
	
	@Autowired
	private IMemberService memberService;
	
	//예전박식  : (요청url) board/파일명?bo_no=10   //파일명에  확장자 없는 이유는 현제 디스패치서블릿에서 경로맵핑해놔서
	//레스트형식	: (요청url) board/파일명/10  ->최근엔 이렇게 많이 사용함	
	
	//리스
	@RequestMapping("/member/memberList")
	public String list(HttpServletRequest request) { // String로 하면 스프링이 알아서 뷰로 인식해 mvc-config.xml에 InternalResourceViewResolver에서 세팅한걸 적용시켜줌
		//위의 페러미터 값은 mvp.pdf에 14페이지 공부해보기
		System.out.println("====List====");
		
		List<Member> list = memberService.getmemberList();
		request.setAttribute("list", list);
		
		return "member/memberList"; //기존에 mvc-config.xml에 등록해놔서
		
	}

	//상세페이
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
	
	//수정
	@RequestMapping("/member/memberEdit")
	public String edit(@RequestParam("mem_id") String mem_id, Model model) { // 
		System.out.println("====Edit====");
		
/*		if(StringUtils.isEmpty(mem_id) || StringUtils.isBlank(mem_id)) { // StringUtils : pom.xml에서 String.leng 라이브러리를 넣어서 사용할수 있는 클래스
			
			아래 검증하는 부분을 활용할수 있다
		}*/
		
		if(mem_id == null || mem_id.trim().equals("")) {
			return "redirect:/member/memberList"; //스프링이 알아서 redirect:를 지우고 경로만 기억해줌
		}
		Member member = memberService.getMember(mem_id);
		model.addAttribute("member", member); //request.setAttribute("member", member); 기존엔 이렇게 썻음ㅋㅋㅋ
		return "member/memberEdit";
	}
	
	//JSON으로 처리 필요...-> 에이젝스 활용하려고 JSON을 사용하는듯
	//기존에는 유즈빈이나 유틸빈즈를 사용하여 페러미터를 모델에 저장해주었는데
	//여기는 자동으로 해줌ㅋㅋㅋ 참 세상 좋아졌네
	@RequestMapping(value="/member/memberUpdate",method=RequestMethod.POST,
					produces="application/json;charset=utf-8"	)//리스폰스 해더에 Content-Type를 html/text가 아니라json으로  
	@ResponseBody
	public String update(Member member, Model model) throws Exception { //넘어온 페러미터와 member의 setter와 일치하면 자동으로 넣어줌
		System.out.println("====Update====");
		System.out.println(member.getMem_id());
		int cnt = memberService.updateMember(member);	
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String,	Object> map = new HashMap();
		
		System.out.println("업데이트 cnt : "+cnt);
		if(cnt > 0 ) {
			map.put("result",true);
			map.put("message", "회원수정을 완료 했습니다.");
		}else {
			map.put("result",true);
			map.put("message", "회원수정을 실패하였습니다.");
		}		
		return mapper.writeValueAsString(map); // map객체를 제이슨 형식으로 만들어준다
	}
	
	@RequestMapping(value="/member/memberInsert",method=RequestMethod.POST)// 액션에서 매서드를 포스트라고 해줬어도 컨트롤러는 포스트와 겟을 차별하지는 않는다. 그래서method=RequestMethod.POST로 필터를 줘야함 그래야지 SQL인젝션을 당하지 않음 
	public String insert(Member member, Model model) { //넘어온 페러미터와 member의 setter와 일치하면 자동으로 넣어줌
		System.out.println("====Insert====");
		System.out.println(member.getMem_id());
		int cnt = memberService.insertMember(member);	
		System.out.println("인서트 cnt : "+cnt);
		if(cnt > 0 ) {
			model.addAttribute("message","회원가입 완료 했습니다.");
		}else {
			model.addAttribute("message","회원가입 수정에 실패하였습니다.");
			return "";
		}		
		return "redirect:/member/memberView?mem_id="+member.getMem_id();
	}	
	
//	form은 그냥 뷰만 리턴해주기 때문에 디스패치서블릿의 mvc-config.xml에서 뷰컨트롤을 등록해주면됨
	
	@RequestMapping("/member/memberDelete")
	public String delete(Member member, Model model) { //넘어온 페러미터와 member의 setter와 일치하면 자동으로 넣어줌
		System.out.println("====Delect====");
		int cnt = memberService.deleteMember(member);		
		System.out.println("딜리트 cnt : "+cnt);
		if(cnt > 0 ) {
			return "redirect:/member/memberList";
		}else {
		}
	
		return "redirect:/member/memberList";
	}	
	
}
