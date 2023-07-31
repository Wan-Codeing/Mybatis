package com.kh.member.model.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.member.model.vo.Member;

public class MemberDao {
	
	
	
    public int insertMember(SqlSession sqlSession, Member m) {
    	
    	/*
    		sqlSession에서 제공하는 메소드를 통해서 sql문을 찾아서 실행하고 결과를 받아볼수 있다.
    		
    		sqlSession.sql문종류에 맞는 메소드("매퍼파일의namespace.해당sql문의 고유한 id", sql(실행시 필요한 객체);
    		=> 해당 sql문이 완성된상태라면 두번째 배개변수는 생략이 가능하다
    	*/
    	return sqlSession.insert("memberMapper.insertMember",m);
    }

    public Member loginMember(SqlSession sqlSession, Member m) {
    	
    	// selectOne : 조회결과가 없다면 null 반환
    	
    	return sqlSession.selectOne("memberMapper.loginMember",m);
    }
}
