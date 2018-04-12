package com.study.member.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.study.member.model.Member;

//@Repository //@Repository 나 @Mapper(마이바티스의 에노테이션)로 등록
@Mapper
public interface IMemberDao {
	// 마이바티스는 페러미터 하나만 받을수 있기떄문에 커넥션을 줄수가 없엇다
	// 커넥션은 application-config.xml에서 설정한 트랙젝션을 쓰는건가바.....잘 몰겠당

	// 회원목록
	List<Member> getMemberList();// throws SQLException; // 빼도되고 안빼도 된다고 하는데 이번엔 빼장ㅋㅋㅋ

	// 회원상세정보
	Member getMember(String mem_id);// throws SQLException;

	// 회원등록
	int insertMember(Member member);// throws SQLException; // 회원등록 end

	// 회원수정
	int updateMember(Member member);// throws SQLException;

	// 회원삭제
	int delectMember(Member member);// throws SQLException;

}