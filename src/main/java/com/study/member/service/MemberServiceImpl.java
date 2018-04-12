package com.study.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.member.dao.IMemberDao;
import com.study.member.model.Member;

@Service("memberService") // 이름등록안하면 낙타식으로 알아서 해줌
public class MemberServiceImpl implements IMemberService {
	
	@Autowired
	private IMemberDao memberDao;// = new MemberDaoMyBatis();//new MemberDaoJDBC();

	// 회원 리스트
	@Override
	public List<Member> getmemberList() {
		
		List<Member> list = memberDao.getMemberList();
		return list;
		
	}//getmemberList 종료
	
	//회원 상세정보
	@Override
	public Member getMember(String mem_id) {
		Member member = memberDao.getMember(mem_id);
		return member;
		
	} //getMember end

	// 회원등록
	@Override
	public int insertMember(Member member) { //왜 int를 리턴타입으로 

		int cnt= memberDao.insertMember(member);
		return cnt;
		 
	}//등록 end
	
	// 회원수정
		//public int registMember(Member member) { // 쌤은 회원가입 registMember라고 함
		@Override
		public int updateMember(Member member) {
			
			int cnt = memberDao.updateMember(member);
			return cnt;
		
	}
		
	//회원삭제
		@Override
		public int deleteMember(Member member) {
			int cnt = memberDao.delectMember(member);
			return cnt;
		}
	

}
