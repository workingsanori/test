package com.study.member.service;

import java.util.List;

import com.study.member.model.Member;

public interface IMemberService {

	// 회원 리스트
	List<Member> getmemberList();//getmemberList 종료

	//회원 상세정보
	Member getMember(String mem_id); //getMember end

	// 회원등록
	int insertMember(Member member);//등록 end

	// 회원수정
	//public int registMember(Member member) { // 쌤은 회원가입 registMember라고 함
	int updateMember(Member member);

	//회원삭제
	int deleteMember(Member member);

}